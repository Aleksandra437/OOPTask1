package org.example.task1_ex19.project;

import org.example.task1_ex19.library.FileNotDirectoryException;
import org.example.task1_ex19.library.FilesManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Main {
    //    public static final String PATH = "C:\\Users\\Denis\\Downloads";
    public static final String PATH = "C:\\Users\\Denis\\Desktop\\delete";

    private static final FilesManager filesManager = new FilesManager();

    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.setErr(new PrintStream(System.out, true, "UTF-8"));

        try {
            List<File> files = filesManager.filesInDirectory(PATH);
            System.out.println("Всего файлов в директории " + files.size());

            String fileNameExtension = ".txt";
            files = filesManager.filterByExtension(files, fileNameExtension);
            System.out.println("После фильтрации по типу файла '" + fileNameExtension + "' осталось файлов в директории " + files.size());
            System.out.println("   " + filesListToString(files));

            String fileNameContains = "2";
            files = filesManager.filterByFileNameContains(files, fileNameContains);
            System.out.println("После фильтрации по имени файла '" + fileNameContains + "' осталось файлов в директории " + files.size());
            System.out.println("   " + filesListToString(files));

            String fileContentContains = "абв";
            files = filesManager.filterByContentContains(files, fileContentContains);
            System.out.println("После фильтрации по содержимому '" + fileContentContains + "' осталось файлов в директории " + files.size());
            System.out.println("   " + filesListToString(files));

            for (File file : files) {
                System.out.println(file.getName());
            }
        } catch (FileNotDirectoryException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String filesListToString(List<File> files) {
        return String.join(", ", files.stream().map(f -> f.getName()).toList());
    }
}
