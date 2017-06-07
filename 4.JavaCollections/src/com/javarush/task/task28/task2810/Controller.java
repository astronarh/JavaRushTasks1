package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Model;

/**
 * Created by ShkerdinVA on 06.06.2017.
 */
public class Controller {
    //private Provider[] providers;
    private Model model;

    /*@Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }*/

    public Controller(Model model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }

    /*public void scan() {
        try {
            List<Vacancy> list = new ArrayList<>();
            for (Provider provider : providers) {
                List<Vacancy> listVacPr = provider.getJavaVacancies("Киев");
                for (Vacancy vac : listVacPr) {
                    list.add(vac);
                }
            }
            System.out.println(list.size());
        } catch (NullPointerException e) {
            System.out.println(0);
        }
    }*/
}
