package server;

import site_structure.Page;

import java.io.*;
import java.net.Socket;

/**
 * Created by artemqa on 10.12.2017.
 */
public class MonoThreadClientHandler implements Runnable {
    private static Socket clientDialog;
    private static Page page ;

    MonoThreadClientHandler(Socket client, Page pageToClient) {
        MonoThreadClientHandler.clientDialog = client;
        page = pageToClient;
    }

    @Override
    public void run() {

        try {
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            ObjectOutputStream objOut = new ObjectOutputStream(out);

            while (!clientDialog.isClosed()) {
                String entry = in.readUTF();
                System.out.println("Сообщение из диалога с клиентом  - " + entry);
                if (entry.equalsIgnoreCase("quit")) {
                    System.out.println("Клиент уничтожил соединение с сервером ...");
                    Thread.sleep(50);
                    break;
                }
                out.writeUTF("Ответ сервера - " + entry + " - OK");
                out.flush();
                objOut.writeObject(page);
                objOut.flush();

            }

            // если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();
            objOut.close();

            // потом закрываем сокет общения с клиентом в нити моносервера
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }
    }
}
