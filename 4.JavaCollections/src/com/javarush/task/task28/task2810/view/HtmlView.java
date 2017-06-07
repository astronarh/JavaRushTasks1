package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ShkerdinVA on 07.06.2017.
 */
public class HtmlView implements View{
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        //System.out.println(vacancies.size());
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> list) {
        return "";
    }

    private void updateFile(String string) {
        try (BufferedWriter fWriter = new BufferedWriter(new FileWriter(filePath));) {
            fWriter.write(string);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
