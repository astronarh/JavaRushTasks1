package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by ShkerdinVA on 06.06.2017.
 */
public class Provider {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        try {
            return strategy.getVacancies(searchString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
