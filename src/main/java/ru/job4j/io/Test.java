package ru.job4j.io;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("C:/projects/pam.txt");
        ParseFile parseFile = new ParseFile(file);
        System.out.println(parseFile.getContent(data -> data <0x080));
        SaveFile saveFile = new SaveFile(file);
        saveFile.saveContent("refactor");
    }
}
