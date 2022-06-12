import modules.Client;

import java.util.Scanner;
public class Main {
    static String host; static int port;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (args.length == 2){
            host = args[0];
            try {port = Integer.parseInt(args[1]);}
            catch (NumberFormatException e) {
                System.out.println("Введен неверный порт, будет использован порт по умолчанию");
                port=1703;
            }
        }
        else
        if (args.length==0){
            System.out.println("Хост и порт по умолчанию(localhost и 1703)");
            host = "localhost";
            port = 1703;
        }
        System.out.println(host);
        Client client = new Client(host, port, scanner);
        while (client.connect()) {
            client.setup();
            client.getCommands();
            client.run();
            client.close();
        }

    }
}