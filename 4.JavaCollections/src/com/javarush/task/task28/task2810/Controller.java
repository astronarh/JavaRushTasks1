package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ShkerdinVA on 06.06.2017.
 */
public class Controller {
    private Provider[] providers;

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public Controller(Provider... providers) {
        if (providers.length == 0) throw new IllegalArgumentException();
        else this.providers = providers;
    }

    public void scan() {
        try {
            List<Vacancy> list = new ArrayList<>();
            for (Provider provider : providers) {
                List<Vacancy> listVacPr = provider.getJavaVacancies("???");
                for (Vacancy vac : listVacPr) {
                    list.add(vac);
                }
            }
            System.out.println(list.size());
        } catch (NullPointerException e) {
            System.out.println(0);
        }
    }
}
