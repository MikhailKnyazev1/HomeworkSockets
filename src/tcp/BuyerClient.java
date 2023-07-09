package tcp;

import java.io.*;
import java.net.Socket;

public class BuyerClient {
    public static void runClient(String serverIpAddress, int serverPort) {
        Socket client = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            client = new Socket(serverIpAddress, serverPort);
            System.out.println("Клиент: клиент создан и подключен к серверу");

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

            // Приветствие от покупателя
            String buyerMessage = "Привет! Я хочу купить ваш товар.";
            out.println(buyerMessage);
            System.out.println("Покупатель: " + buyerMessage);

            // Получение ответа от сервера
            String message = in.readLine();
            System.out.println("Сервер: " + message);

            // Завершение работы клиента
            System.out.println("Клиент: завершение работы");
        } catch (Exception ex) {
            System.out.println("Клиент: что-то пошло не так - " + ex.getMessage());
        } finally {
            try {
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
                System.out.println("Клиент: что-то пошло не так в finally - " + ex.getMessage());
            }
        }
    }
}
