package site_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static List<String> imgUrls = new ArrayList<>();


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        downloadingSite();
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("Скачивание и парсинг сайта закончен, потрачено  : " + timeSpent +  " миллисекунд " + " /n " + "Всего было обнаружено URL картинок на сайте : " + imgUrls.size());
        for (int i = 0;i<imgUrls.size();i++){
            System.out.println(imgUrls.get(i));
        }
    }

    private static void downloadingSite() {
        Site site = new Site();
        List<Page> listPages = site.getListPages();

        for (int i = 0; i < listPages.size(); i++) {
            try {
                Thread.sleep(300);
                parsingPages(listPages.get(i), i);
                System.out.println("Скачана страница " + i + " из " + listPages.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parsingPages(Page page, int numberPage) {
        ParsingThread parsingThread = new ParsingThread(numberPage, page, imgUrls);
        parsingThread.start();

    }
}