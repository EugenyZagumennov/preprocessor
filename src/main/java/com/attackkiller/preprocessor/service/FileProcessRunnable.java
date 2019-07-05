package com.attackkiller.preprocessor.service;

import lombok.AllArgsConstructor;

/**
 * Job processing file with c-sharp code
 *
 * @author evgenii.zagumennov
 */
@AllArgsConstructor
public class FileProcessRunnable implements Runnable {

    private String filename;
    private String outputDir;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(filename);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
