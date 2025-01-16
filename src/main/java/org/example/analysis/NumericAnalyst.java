package org.example.analysis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author batal
 * @Date 04.01.2025
 */
@Getter
@Setter
public class NumericAnalyst implements Analyst {
    private final boolean fullStatistic;
    private final boolean haveStatistic;

    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private long count = 0;
    private double amount = 0;

    public NumericAnalyst(boolean fullStatistic, boolean haveStatistic) {
        this.fullStatistic = fullStatistic;
        this.haveStatistic = haveStatistic;
    }

    @Override
    public void printStatistic(File file) {
        if(file.exists()){
            Scanner sc;
            try{
                sc = new Scanner(file);
            }catch (FileNotFoundException e ){
                throw new RuntimeException(e);
            }
            while(sc.hasNext()){
                float value = Float.parseFloat(sc.next());
                setAmount(getAmount() +  value);
                setCount(getCount() + 1);
                if(value > getMax()){
                    setMax(value);
                }
                if(value < getMin()){
                    setMin(value);
                }
            }
            if(haveStatistic){
                System.out.println("Статистика для: " + file.getName());
                System.out.println("Количество элементов: " + getCount());
                if(fullStatistic){
                    printFullStatistic(file);
                }
            }
            setCount(0);
        }
        else {
            System.out.println("Файл " + file.getName() + " не найден");
        }
    }

    private void printFullStatistic(File file) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Дополнительные детали:");
        System.out.println("Минимум: " + df.format(getMin()));
        System.out.println("Максимум: " + df.format(getMax()));
        System.out.println("Сумма: " + df.format(getAmount()));
        System.out.println("Среднее: " + df.format(amount/getCount()));
    }
}
