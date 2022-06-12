package modules;

import modules.manages.ClientManager;
import modules.manages.CommandManager;
import modules.manages.FileManager;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Server class */
public class Server {
    private final int port;
    private ServerSocketChannel server;
    private final Logger log;

    public Server(int port) {
        this.port = port;
        log = Logger.getLogger(Server.class.getName());
        log.setLevel(Level.ALL);
    }

    /** Connects client */
    public void connect()  {
        while (server != null) {
            try {
                log.info("ожидание нового пользователя");
                SocketChannel client = server.accept();
                log.info("новый пользователь подключился");
                ClientManager clientmngr = new ClientManager(client, new CommandManager());
                clientmngr.run();
            } catch (IOException e) {
                log.log(Level.SEVERE,"Ошибка подключения", e);
            }
        }
    }

    /**
     * Starts server
     *
     * @return Boolean
     */
    public boolean start() {
        try {
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            log.info("Сервер успешно запущен");
            return true;
        } catch (IOException e) {
            log.severe("Ошибка запуска сервера");
        }
        return false;
    }

    /** Closes server */
    public void close() {
        try {
            try {
                FileManager.saveToFile();
            } catch (JAXBException e) {
                System.out.println("Не удалось сохранить коллекцию при выходе");
            }
            server.close();
            log.info("Завершение работы сервера успешно");
        } catch (IOException e) {
            log.severe("Ошибка завершения работы сервера");
        }
    }

    public void load() {
        try {
            FileManager.loadFromFile();
        } catch (JAXBException e) {
            log.warning("Не удалось загрузить коллекцию из файла");
        }
    }
}