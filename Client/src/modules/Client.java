package modules;

import mycollection.WrongArgException;
import tranceiving.Receiver;
import tranceiving.ReceiverException;
import tranceiving.Sender;
import util.ProductWrapper;
import communicate.Reply;
import communicate.Request;
import utils.InputHandler;
import utils.MakeProduct;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

/** Client class */
public class Client {
    private final int port;
    private final String host;
    private Socket socket;
    private  Sender sender;
    private  Receiver receiver;
    private final Scanner scanner;
    private MakeProduct makeProduct;
    private ArrayList<Object> availableCommands = new ArrayList<>();
    private InputHandler in;

    public Client(String host, int port, Scanner scanner) {
        this.port = port;
        this.host = host;
        this.scanner = scanner;
        this.in = new InputHandler(scanner);
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * Connects to the server
     *
     * @return True if connected
     */
    public boolean connect() {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(InetAddress.getByName(host), port));
        } catch (IOException e) {
            System.out.println("Ошибка подключения к серверу");
            System.out.println(e);
            return false;
        }
        System.out.println("Соединение установлено");
        return true;
    }
    public void getCommands(){
            sender.send(new Request("AvailableCommands"));
            try {
                Reply reply = (Reply)receiver.readObject();
                if (!reply.isSuccess()){
                    System.out.println("Ошибка при получении списка команд");
                }
                availableCommands = (ArrayList<Object>) reply.getReply();
            } catch (ReceiverException e) {
                System.out.println("Ошибка при получении списка команд");
            }
    }

    /**
     * Setups connection
     *
     * @throws IOException if not setup
     */
    public void setup() {
        try {
            sender = new Sender(new ObjectOutputStream(socket.getOutputStream()));
            sender.flush();
        } catch (IOException e) {
            System.out.println("Ошибка создания объекта для отправки данных");
        }
        try {
            receiver = new Receiver(new ObjectInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Ошибка создания объекта для получения данных");
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия сокета");
        }
    }

    public void waitConnection() {
        try {
            receiver.close();
            sender.close();
        } catch (IOException e) {
            System.out.println("не удалось закрыть потоки");
        }
        int sec = 0;
        while (socket.isClosed() || !socket.isConnected()) {
            socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(InetAddress.getByName(host), port));
                setup();
                System.out.println("Повторное подключение произведено успешно. Продолжение выполнения");
                return;
            } catch (IOException e) {
                System.out.println("Ошибка повторного подключения к серверу");
            }
            sec++;
            if (sec > 60) {
                System.out.println("Клиент не дождался подключения. Завершение работы программы");
                System.exit(0);
            }
            System.out.println("Ошибка подключения. Ожидание повторного подключения: " + sec + "/60 секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Ошибка приостановки выполнения потока");
            }
        }
    }

    public void run() {
        while (!getSocket().isOutputShutdown()) {
            System.out.print("Введите команду: ");
            if (in.isReady()) {
                String[] args = in.getCommand();
                try {
                    get(args[0],Arrays.copyOfRange(args, 1, args.length) );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                in.setScanner(new Scanner(System.in));
                in.setScript(false);
                }
            }
        }

    /** One request */
    public void get(String command, String[] args) throws IOException {
        if (availableCommands.contains(command)){
            try {
                makeProduct = new MakeProduct(in.getScanner());
                ProductWrapper pw = new ProductWrapper(makeProduct.askAll());
                sender.send(new Request(command, args, pw));
            } catch (WrongArgException e) {
                System.out.println("Не получилось собрать объект для отправки");
            }

        }else
            sender.send(new Request(command,args));
        try {
            Reply reply = (Reply)receiver.readObject();
            if (reply.getReply().equals("exit")) System.exit(0);
            if (reply.isSuccess()){
                System.out.println("Команда выполнена успешно");
                System.out.print(Objects.equals(reply.getReply().toString(), "") ?"":reply.getReply().toString()+"\n");
            }
            else {
                System.out.println("Команда не выполнена");
                System.out.print(Objects.equals(reply.getReply().toString(), "") ?"":reply.getReply().toString()+"\n");
            }
        } catch (ReceiverException e) {
            System.out.println("Судя по всему сервер в данный момент недоступен, ждем");
            waitConnection();
        }
    }
}