package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (PrintStream printer = new PrintStream(file)) {
            printer.print(content);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}