package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
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
        Document doc = null;
        try {
            doc = getDocument();
            Element element = doc.getElementsByClass("template").first();
            Element cloneElement = element.clone();
            cloneElement.removeClass("template").removeAttr("style");
            doc.getElementsByAttributeValue("class","vacancy").remove();
            for (Vacancy vacancy : list)
            {
                Element vac = cloneElement.clone();
                vac.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                vac.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                vac.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                Element link = vac.getElementsByTag("a").get(0);
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                element.before(vac.outerHtml());
            }
            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String string) {
        try (BufferedWriter fWriter = new BufferedWriter(new FileWriter(filePath));) {
            fWriter.write(string);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
