package tcp;

public class Main {
    public static void main(String[] args) {

            try {
                // Создание потоков для сервера и клиента
                Thread serverThread = new Thread(() -> SellerServer.runServer("127.0.0.1", 1024));
                Thread clientThread = new Thread(() -> BuyerClient.runClient("127.0.0.1", 1024));

                // Запуск потоков
                serverThread.start();
                clientThread.start();

                // Ожидание завершения потоков
                serverThread.join();
                clientThread.join();
            } catch (Exception ex) {
                System.out.println("Главный поток: что-то пошло не так - " + ex.getMessage());
            }
        }
}
