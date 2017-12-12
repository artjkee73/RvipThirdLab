package server;

import site_structure.Page;
import site_structure.Site;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainServer {
    private static ExecutorService executeIt = Executors.newFixedThreadPool(100);
    private final static int i = 0;


    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(3345)) {
            System.out.println("Создан сокет сервера, вывод команд передаваемых серверу осуществляется через консоль");

            while (!server.isClosed()) {
                Socket client = server.accept();
                executeIt.execute(new MonoThreadClientHandler(client, getPageToClient(i)));
                System.out.println("Соединение установлено.");
            }

            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Page getPageToClient(int i) {
        List<Page> listPages = downloadingSite();
        return listPages.get(i);
    }

    private static List<Page> downloadingSite() {
        Site site = new Site();
        List<Page> listPages = site.getListPages();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listPages;
    }
}
