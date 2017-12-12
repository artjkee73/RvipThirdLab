package client;

import site_structure.Image;
import site_structure.Page;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemqa on 09.12.2017.
 */
public class MessageSource implements Runnable {

    private Integer num;
    private static Socket socket;
    private final ArrayList<String> imgUrls;
    private final static String SERVER_HOST = "localhost";
    private final static int SERVER_PORT = 3345;

    MessageSource(Integer num, ArrayList<String> imgUrls) {
        this.num = num;
        this.imgUrls = imgUrls;
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try (
                DataInputStream ois = new DataInputStream(socket.getInputStream());
                ObjectInputStream objIs = new ObjectInputStream(ois);
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream())) {
            {
                if (!socket.isClosed()) {
                    oos.writeUTF("Клиент № " + num + " подключен и ждет данных");
                    System.out.println("Клиент № " + num + " подключен и ждет данных");
                    oos.flush();
                    Thread.sleep(50);
                    String entry = ois.readUTF();
                    System.out.println(entry);
                    System.out.println("Клиент № " + num + " считывание данных с сервера...");
                    Thread.sleep(50);
                    Page serPageFromServer = (Page) objIs.readObject();
                    parsingPage(serPageFromServer);
                } else {
                    return;
                }
            }
            oos.writeUTF("quit");
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parsingPage(Page page) {
        try {
            Thread.sleep(10);
            System.out.println("Клиент № " + num + " начал парсинг страницы");
            List<Image> imageList = page.getListImages();
            for (Image image : imageList) {
                imgUrls.add(image.getUrl());
            }
            MainClient.writeResultList(imgUrls, num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}