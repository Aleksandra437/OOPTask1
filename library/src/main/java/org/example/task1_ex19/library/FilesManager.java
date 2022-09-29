package org.example.task1_ex19.library;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesManager {

    public List<File> filesInDirectory(String path) throws FileNotDirectoryException {
        File parent = new File(path);
        if (parent.isDirectory()) {
            return Arrays.stream(parent.listFiles()).toList();
        } else {
            throw new FileNotDirectoryException(" Это не файл");
        }
    }

    public List<File> filterByFileNameContains(List<File> files, String name) {
        return files.stream()
                .filter(file -> file.getName().contains(name))
                .toList();
    }

    public List<File> filterByExtension(List<File> files, String extension) {
        String ext ;
        if (extension.startsWith("."))
            ext = extension;
        else
            ext = "." + extension;
        return files.stream()
                .filter(file -> file.getName().endsWith(ext))
                .toList();
    }

    public List<File> filterByContentContains(List<File> files, String partOfContent) throws IOException {
        List<File> result = new ArrayList<>();

        for (File file : files) {
            if (!file.isDirectory() && file.canRead()) {
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(file);

                    byte[] allBytes = inputStream.readAllBytes();
                    String fileContent = new String(allBytes, StandardCharsets.UTF_8);
                    if (fileContent.contains(partOfContent))
                        result.add(file);
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return result;
    }

}
