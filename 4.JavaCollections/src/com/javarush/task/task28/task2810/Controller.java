package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Provider;

import java.util.Arrays;

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
}
