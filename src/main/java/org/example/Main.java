package org.example;

import org.example.FileManager.AdditionalOptions;
import org.example.FileManager.FileManager;
import org.example.analysis.Analyst;
import org.example.analysis.NumericAnalyst;
import org.example.analysis.StringAnalyst;

import java.io.File;
import java.io.IOException;

/**
 * @author batal
 * @Date 04.01.2025
 */
public class Main {
        public static void main(String[] args){
                AdditionalOptions options = new AdditionalOptions();
                options.parseProgramArguments(args);

                FileManager fileManager = new FileManager(options.isAppendMode(), options.getOutputPath(), options.getFilePrefix());
                Analyst integerAnalyst = new NumericAnalyst(options.isHaveStatistic(), options.isFullStatistic());
                Analyst floatAnalyst = new NumericAnalyst(options.isHaveStatistic(), options.isFullStatistic());
                Analyst stringAnalyst = new StringAnalyst(options.isHaveStatistic(), options.isFullStatistic());
                for (String file : options.getFiles()){
                        fileManager.filterFile(file);
                }
                fileManager.deleteFileIfEmpty();

                integerAnalyst.printStatistic(fileManager.getIntegersFile());
                floatAnalyst.printStatistic(fileManager.getFloatsFile());
                stringAnalyst.printStatistic(fileManager.getStringsFile());
        }
}