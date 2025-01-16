package org.example.analysis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author batal
 * @Date 04.01.2025
 */
@Getter
@Setter
public class StringAnalyst implements Analyst {

    private final boolean haveStatistic;
    private final boolean fullStatistic;

    private double min;
    private double max;
    private long count = 0;

    public StringAnalyst(boolean haveStatistic, boolean fullStatistic) {
        this.haveStatistic = haveStatistic;
        this.fullStatistic = fullStatistic;
    }

    @Override
    public void printStatistic(File file) {
        if (file.exists()){
            setMin(Double.MAX_VALUE);
            setMax(Double.MIN_VALUE);
            Scanner sc;

            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (sc.hasNextLine()){
                setCount(getCount() + 1);
                String value = String.valueOf(sc.nextLine());
                if (value.length() > getMax()){
                    setMax(value.length());
                }
                if (value.length() < getMin()){
                    setMin(value.length());
                }
            }
            if(haveStatistic){
                System.out.println("Статистика для: " + file.getName());
                System.out.println("Количество элементов: " + getCount());
                if (fullStatistic){
                    printFullStringResult();
                }
            }
            setCount(0);

        } else {
            System.out.println("Файл " + file.getName() + " не найден");
        }
    }

    private void printFullStringResult() {
        System.out.println("Дополнительные детали:");
        System.out.println("Минимум: " + getMin());
        System.out.println("Максимум: " + getMax());
        System.out.println();
    }
}
