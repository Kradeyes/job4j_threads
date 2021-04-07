package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Integer> pred) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = reader.read()) != -1) {
                if (pred.test(data)) {
                   content.append((char) data);
                }
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        return content.toString();
    }


    public synchronized void saveContent(String content) {
        try (PrintStream printer = new PrintStream(file)) {
            printer.print(content);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}

