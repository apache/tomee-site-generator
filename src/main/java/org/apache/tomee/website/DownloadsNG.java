package org.apache.tomee.website;

import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

import static lombok.AccessLevel.PUBLIC;

@RequiredArgsConstructor(access = PUBLIC)
public class DownloadsNG extends AbstractDownloadsNG {

    public static void main(final String[] args) throws IOException, TikaException, SAXException {

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "512");

        boolean archive = false;
        if(args.length > 0) {
            archive = Boolean.parseBoolean(args[0]);
        }

        if(archive) {
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("Please note, that versions prior to 1.5.1 are not available on archive.apache.org! You need to reference them via repo.maven.apache.org.");
            System.out.println("Please remove current versions from the output...");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            //TODO Possible Improvement: Remove current release versions before out putting them for the archives ;)
            generate(ARCHIVE_BASE_URL);
        }else {
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("Generating current download links targeting the mirroring system.");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            System.out.println("###################");
            generate(BASE_URL);
        }

    }

}
