package org.apache.tomee.website;

import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

import static lombok.AccessLevel.PUBLIC;

@RequiredArgsConstructor(access = PUBLIC)
public class DownloadsNG extends AbstractDownloadsNG {

    public static void main(final String[] args) throws Exception {

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "512");

        boolean archive = false;
        if (args.length > 0) {
            archive = Boolean.parseBoolean(args[0]);
        }

        if (archive) {
            printSeparator(4);
            System.out.println("Please note, that versions prior to 1.5.1 are not available on archive.apache.org! You need to reference them via repo.maven.apache.org.");
            //TODO Improvement: Remove current release versions before printing the archives
            System.out.println("Please remove current versions from the output...");
            printSeparator(4);
            generate(ARCHIVE_BASE_URL);
        } else {
            printSeparator(4);
            System.out.println("Generating current download links targeting the mirroring system.");
            printSeparator(4);
            generate(BASE_URL);
        }
    }

    private static void printSeparator(int times) {
        for(int i = 0; i < times; i ++) {
            System.out.println("###################");
        }
    }

}
