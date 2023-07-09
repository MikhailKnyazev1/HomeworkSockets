package tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SellerServer {
    public static void runServer(String ipAddress, int port) {
        ServerSocket server = null;
        Socket client = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            server = new ServerSocket(port, 1, InetAddress.getByName(ipAddress));
            System.out.println("Сервер: сервер создан");

            while (true) {
                System.out.println("Сервер: ожидание входящего подключения");
                client = server.accept();
                System.out.println("Сервер: подключен клиент " + client.getInetAddress() + ":" + client.getPort());

                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                // Получение сообщения от клиента
                String message = in.readLine();
                System.out.println("Покупатель: " + message);

                // Отправка ответа клиенту
                String serverMessage = "Здравствуйте! У нас есть отличный товар для вас.";
                out.println(serverMessage);
                System.out.println("Сервер: " + serverMessage);

                // Завершение работы сервера
                System.out.println("Сервер: завершение работы");

                // Закрытие соединения с текущим клиентом
                client.close();
            }
        } catch (Exception ex) {
            System.out.println("Сервер: что-то пошло не так - " + ex.getMessage());
        } finally {
            try {
                if (server != null && !server.isClosed()) {
                    server.close();
                }
                if (client != null && !client.isClosed()) {
                    client.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                System.out.println("Сервер: что-то пошло не так в finally - " + ex.getMessage());
            }
        }
    }
}