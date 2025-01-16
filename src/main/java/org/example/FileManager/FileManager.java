package org.example.FileManager;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author batal
 * @Date 04.01.2025
 */
@Getter
@Setter
public class FileManager implements FileHandler {
    private String outputPath;
    private String filePrefix;

    private PrintWriter intWriter;
    private PrintWriter stringWriter;
    private PrintWriter floatWriter;

    int intCount = 0;
    int floatCount = 0;
    int stringCount = 0;

    public FileManager(boolean appendLine, String path, String prefix) {
        this.outputPath = path;
        this.filePrefix = prefix;
        String separator = String.valueOf(File.separatorChar);

        if (!path.isEmpty()){
            path += separator;
        }
        try {
            String intFileName = path  + prefix + "integers.txt";
            String strFileName = path  + prefix + "strings.txt";
            String floatFileName = path + prefix + "floats.txt";

            // Файлы для каждого типа строки из файла.
            integersFile = new File(intFileName);
            stringsFile = new File(strFileName);
            floatsFile = new File(floatFileName);

            // Инициализация PrintWriter с опцией записи / перезаписи файлов.
            intWriter = new PrintWriter(new FileWriter(integersFile, appendLine));
            stringWriter = new PrintWriter(new FileWriter(stringsFile, appendLine));
            floatWriter = new PrintWriter(new FileWriter(floatsFile, appendLine));
        } catch (IOException e) {
            System.out.println();
            System.out.println(" >>> Ошибка!!! Невозможно найти указанный путь: " + e.getMessage());
            System.out.println("┌────────────────────────────   Message:   ────────────────────────┐");
            System.out.println("| Убедитесь, что вы ввели правильный путь, и повторите попытку. |");
            System.out.println("└──────────────────────────────────────────────────────────────────┘");
            System.exit(1);
        }
    }
    String intFileName = outputPath + filePrefix + "integers.txt";
    String strFileName = outputPath + filePrefix + "strings.txt";
    String floatFileName = outputPath + filePrefix + "floats.txt";

    private File integersFile = new File(intFileName);
    private File stringsFile = new File(strFileName);
    private File floatsFile = new File(floatFileName);

    // целые числа
    public String getIntRegex() {
        return "^[+-]?\\d+$";
    }

    // строки
    public String getStringRegex() {
        return "^(?![+-]?\\d)(?!\\n)[^\\d ].*$";
    }

    // числа с плавающей точкой
    public String getFloatRegex() {
        return "[+-]?\\d+\\.\\d+([eE][+-]?\\d+)?";
    }

    @Override
    public void filterFile(String fileName) {
        File file = new File(fileName);
        try(Scanner sc = new Scanner(file)){
            while (sc.hasNextLine()) {
                filterLine(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println(" >>> Ошибка!!! Файл не найден:" + e.getMessage());
            System.out.println("┌────────────────────────────   Message:   ───────────────────────────┐");
            System.out.println("| Убедитесь, что вы ввели правильное имя файла, и повторите попытку.    |");
            System.out.println("└─────────────────────────────────────────────────────────────────────┘");
            System.exit(1);
        }
    }

    @Override
    public void filterLine(String line) {
        if (line.matches(getIntRegex())) {
            intCount++;
            getIntWriter().append(line).append('\n').flush();
        }
        if (line.matches(getStringRegex())) {
            stringCount++;
            getStringWriter().append(line).append('\n').flush();
        }
        if (line.matches(getFloatRegex())) {
            floatCount++;
            getFloatWriter().append(line).append('\n').flush();
        }
    }

    @Override
    public void checkAndDelete(int counter, PrintWriter writer, File file) {
        if (counter == 0) {
            writer.close();
            if (file.exists()) {
                boolean delete = file.delete();
            }
        }
    }

    @Override
    public void deleteFileIfEmpty() {
        checkAndDelete(intCount, getIntWriter(), integersFile);
        checkAndDelete(stringCount, getStringWriter(), stringsFile);
        checkAndDelete(floatCount, getFloatWriter(), floatsFile);
    }   
}
