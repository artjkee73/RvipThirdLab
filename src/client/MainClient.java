package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {
    private static final ArrayList<String> imgUrls = new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService messageSourceService = Executors.newFixedThreadPool(100);

        //цикл ниже вызывает подозрение, разберусь позже
        for (int i = 0; i < 1; i++) {
            messageSourceService.execute(new MessageSource(i,imgUrls));
        }

    }
    static void writeResultList(ArrayList<String> imgUrls , int clientNumber){
        System.out.println("Клиент № "+clientNumber +" Итоговый список URL-каритинок");
        for ( String imgUrl:imgUrls ) {
            System.out.println(imgUrl);
        }
    }
}
