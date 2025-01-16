package org.example.FileManager;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author batal
 * @Date 07.01.2025
 */
@Getter
@Setter
public class AdditionalOptions {
    private final StringBuilder outputPath = new StringBuilder("");
    private final StringBuilder filePrefix = new StringBuilder("");

    private boolean appendMode = false;
    private boolean fullStatistic = false;
    private boolean haveStatistic = false;

    private final List<String> files = new ArrayList<>();

    public String getOutputPath() {
        return outputPath.toString();
    }
    public String getFilePrefix() {
        return filePrefix.toString();
    }

    public void parseProgramArguments(String[] options) {
        for (int i = 0; i < options.length; i++) {
            if ("-o".equals(options[i])) {
                outputPath.append(options[i + 1]);
            } else if ("-p".equals(options[i])) {
                filePrefix.append(options[i + 1]);
            } else if ("-a".equals(options[i])) {
                appendMode = true;
            } else if ("-s".equals(options[i])) {
                haveStatistic = true;
                fullStatistic = false;
            } else if ("-f".equals(options[i])) {
                haveStatistic = true;
                fullStatistic = true;
            } else if (options[i].endsWith(".txt")) {
                files.add(options[i]);
            } else if (options[i - 1].equals("-p") || options[i - 1].equals("-o")) {
                continue;
            } else {
                System.out.println();
                System.out.println(" >>> Ошибка!!! Неизвестная опция: " + options[i]);
                System.out.println("┌────────────────────────────   Сообщение:   ───────────────────────────┐");
                System.out.println("| Убедитесь, что вы ввели правильные параметры, и повторите попытку. |");
                System.out.println("└─────────────────────────────────────────────────────────────────────┘");

                System.exit(1);
            }
        }
    }
}
