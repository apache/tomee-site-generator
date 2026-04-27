package org.apache.tomee.website;

import com.orientechnologies.orient.core.Orient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.tomee.embedded.Configuration;
import org.apache.tomee.embedded.Container;
import org.jbake.app.ConfigUtil;
import org.jbake.app.Oven;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public class JBake {
    public static void main(final String[] args) throws Exception {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "64"); // try to have parallelStream better than default

        final File source = args == null || args.length < 1 ? new File("src/main/jbake") : new File(args[0]);
        final File destination = args == null || args.length < 2 ? new File("target/site-tmp") : new File(args[1]);
        final boolean startHttp = args == null || args.length < 2 || Boolean.parseBoolean(args[2]); // by default we dev


        final Source[] website = org.apache.tomee.website.Configuration.getSources();

        final Sources sources = new Sources(new File("target/jbake"), new File("repos"), new File("src/main/jbake"), destination, website);


        sources.prepare();


        //ToDo: Refactor pending
        //Move the content of src/main/jbake/content/certifications into
        // target/jbake/content  AND target/site-1.0-SNAPSHOT
        final IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".adoc");
        final IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);
        final FileFilter JointFilter = FileFilterUtils.orFileFilter(DirectoryFileFilter.DIRECTORY, txtFiles);

        final File srcDir = new File(source.getAbsolutePath()+"/content/certifications");
        final File destJbakeDir = new File("target/jbake/content");

        FileUtils.copyDirectory(srcDir, destJbakeDir, JointFilter, true);
        // for future: automate the creation of target/jbake/content/certifications.adoc
        FileUtils.deleteDirectory(new File (destJbakeDir+"/certifications"));



        final Runnable build = () -> {
            System.out.println("Building TomEE website in " + destination);
            final Orient orient = Orient.instance();
            try {
                orient.startup();

                final Oven oven = new Oven(sources.getJbake(), destination, new CompositeConfiguration() {{
                    addConfiguration(ConfigUtil.load(sources.getJbake()));
                }}, true);
                oven.setupPaths();

                System.out.println("  > baking");
                oven.bake();

//                if (!skipPdf) {
//                    System.out.println("  > pdfifying");
//                    PDFify.generatePdf(pdfSource, destination);
//                }

                copyFileLayoutToDirStructure(destination);
                System.out.println("  > done :)");
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                orient.shutdown();
            }
        };

        build.run();

        new Checks().check(destJbakeDir, destination);

        if (startHttp) {
            final Path watched = source.toPath();
            final WatchService watchService = watched.getFileSystem().newWatchService();
            watched.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            final AtomicBoolean run = new AtomicBoolean(true);
            final AtomicLong render = new AtomicLong(-1);
            final Thread renderingThread = new Thread() {
                {
                    setName("jbake-renderer");
                }

                @Override
                public void run() {
                    long last = System.currentTimeMillis();
                    while (run.get()) {
                        if (render.get() > last) {
                            last = System.currentTimeMillis();
                            try {
                                build.run();
                            } catch (final Throwable oops) {
                                oops.printStackTrace();
                            }
                        }
                        try {
                            sleep(TimeUnit.SECONDS.toMillis(1));
                        } catch (final InterruptedException e) {
                            Thread.interrupted();
                            break;
                        }
                    }
                }
            };
            final Thread watcherThread = new Thread() {
                {
                    setName("jbake-file-watcher");
                }

                @Override
                public void run() {
                    while (run.get()) {
                        try {
                            final WatchKey key = watchService.poll(1, TimeUnit.SECONDS);
                            if (key == null) {
                                continue;
                            }

                            for (final WatchEvent<?> event : key.pollEvents()) {
                                final WatchEvent.Kind<?> kind = event.kind();
                                if (kind != ENTRY_CREATE && kind != ENTRY_DELETE && kind != ENTRY_MODIFY) {
                                    continue; // unlikely but better to protect ourself
                                }

                                final Path updatedPath = Path.class.cast(event.context());
                                if (kind == ENTRY_DELETE || updatedPath.toFile().isFile()) {
                                    final String path = updatedPath.toString();
                                    if (!path.contains("___jb") && !path.endsWith("~")) {
                                        render.set(System.currentTimeMillis());
                                    }
                                }
                            }
                            key.reset();
                        } catch (final InterruptedException e) {
                            Thread.interrupted();
                            run.compareAndSet(true, false);
                        } catch (final ClosedWatchServiceException cwse) {
                            if (!run.get()) {
                                throw new IllegalStateException(cwse);
                            }
                        }
                    }
                }
            };

            renderingThread.start();
            watcherThread.start();

            final Runnable onQuit = () -> {
                run.compareAndSet(true, false);
                Stream.of(watcherThread, renderingThread).forEach(thread -> {
                    try {
                        thread.join();
                    } catch (final InterruptedException e) {
                        Thread.interrupted();
                    }
                });
                try {
                    watchService.close();
                } catch (final IOException ioe) {
                    // not important
                }
            };

            try (final Container container = new Container(new Configuration() {{
                setWebResourceCached(false);
                property("openejb.additional.exclude", "logback,jbake");
            }}).deployClasspathAsWebApp(null, destination)) {
                System.out.println("Started on http://localhost:" + container.getConfiguration().getHttpPort());

                final Scanner console = new Scanner(System.in);
                String cmd;
                while (((cmd = console.nextLine())) != null) {
                    if ("quit".equals(cmd)) {
                        return;
                    } else if ("r".equals(cmd) || "rebuild".equals(cmd) || "build".equals(cmd) || "b".equals(cmd)) {
                        render.set(System.currentTimeMillis());
                    } else {
                        System.err.println("Ignoring " + cmd + ", please use 'build' or 'quit'");
                    }
                }
            }
            onQuit.run();
        }
    }

    private static void copyFileLayoutToDirStructure(File destination) throws IOException {
        final File adminFolder = new File(destination, "admin");
        final File fileLayoutHtml = new File(adminFolder, "file-layout.html");
        final File dirStructureHtml = new File(adminFolder, "directory-structure.html");
        final File fileLayoutPdf = new File(adminFolder, "file-layout.pdf");
        final File dirStructurePdf = new File(adminFolder, "directory-structure.pdf");

        if (fileLayoutPdf.exists()) {
            FileUtils.copyFile(fileLayoutPdf, dirStructurePdf);
        }

        if (fileLayoutHtml.exists()) {
            FileUtils.copyFile(fileLayoutHtml, dirStructureHtml);
        }
    }

}
