package org.example.task1_ex19.library;

public class FileNotDirectoryException extends Exception {
    public FileNotDirectoryException(String message) {
        super(message);
    }
}