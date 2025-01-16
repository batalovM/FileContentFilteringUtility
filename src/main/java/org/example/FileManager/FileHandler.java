package org.example.FileManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author batal
 * @Date 04.01.2025
 */
public interface FileHandler {
    void filterFile(String fileName);
    void filterLine(String line);
    void checkAndDelete(int counter, PrintWriter writer, File file);
    void deleteFileIfEmpty();
}
