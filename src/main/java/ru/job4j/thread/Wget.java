package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final long speed;
    private final byte[] dataBuffer = new byte[1024];
    private static final String OUTPUT_FILE_NAME = "./pom_temp.xml";

    public Wget(String url, long speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_NAME)) {
            int bytesRead;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            long finishTime = System.currentTimeMillis();
            long duration = finishTime - startTime;
            if (duration < speed) {
                long extraTime = speed - duration;
                System.out.println("Waiting for extra time " + extraTime);
                Thread.sleep(extraTime);
            }
            System.out.println("Done");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Without waiting
       /* String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        System.out.println(url);
        long speed = Long.parseLong("1");
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
        */
        String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        System.out.println(url);
        long speed = Long.parseLong("5000");
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
