package site_structure;

import java.util.List;

public class ParsingThread extends Thread {

    private int numberTread;
    private Page page;
    private final List<String> imgUrls;

    public ParsingThread(int numberTread, Page page, List<String> imgUrls){
        super("Thread number : " + numberTread);
        System.out.println("Операция парсинга вынесена в отдельный поток " + Thread.currentThread().getName());
        this.numberTread = numberTread;
        this.page = page;
        this.imgUrls = imgUrls;
    }

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(50 + 300);
            System.out.println("Открытие и парсинг страницы номер " + numberTread );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (imgUrls){
            try {
                Thread.sleep(10);
                System.out.println("Сохранение URL картинок в список" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Image> imageList = page.getListImages();
            for (Image image : imageList) {
                imgUrls.add(image.getUrl());
            }
        }

    }
}