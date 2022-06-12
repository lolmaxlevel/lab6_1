package modules.manages;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.commands.exceptions.WrongArgException;
import tranceiving.Receiver;
import tranceiving.ReceiverException;
import tranceiving.Sender;
import communicate.Reply;
import communicate.Request;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/** ClientHandler class */
public class ClientManager{
    private final SocketChannel client;
    private final CommandManager commandManager;
    private Sender sender;
    private Receiver receiver;
    final Logger log = Logger.getLogger(ClientManager.class.getName());

    public ClientManager(SocketChannel client, CommandManager commandManager) {
        this.client = client;
        this.commandManager = commandManager;
        log.setLevel(Level.ALL);
    }

    /** Setups connection */
    public void setup() {
        try {
            sender = new Sender(new ObjectOutputStream(client.socket().getOutputStream()));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Не удалось настроить отправитель",e);
        }
        try {
            receiver = new Receiver(new ObjectInputStream(client.socket().getInputStream()));
        } catch (IOException e) {
            log.log(Level.SEVERE,"Не удалось настроить получатель",e);
        }
        log.info("Соединение с пользователем установлено");
    }

    /** Closes connection */
    public void close() {
        try {
            FileManager.saveToFile();
            receiver.close();
            sender.close();
            client.close();
        } catch (IOException e) {
            log.log(Level.SEVERE,"Ошибка закрытия соединения", e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    /** One request */
    public void get() throws WrongArgException, WrongAmountOfArgsException {
        Request request;
        try {
            request = (Request) receiver.readObject();
        } catch (ReceiverException e) {
            try {
                FileManager.saveToFile();
                log.info("сохраняем данные коллекции");
            } catch (JAXBException | FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            log.log(Level.SEVERE, "Ошибка при получении данных клиента. Закрытие соединения");
            close();
            return;
        }
        log.info("Получен новый запрос:\nкоманда:"+request.getCommand()+
                ((request.getArgs().length==0)?"\nБез аргументов":"\nАргументы:"+ Arrays.toString(request.getArgs())));
        Reply reply = CommandManager.executeCommand(request);
        sender.send(reply);
        }

    public void run() {
        setup();
        while (!client.socket().isClosed()) {
            try {
                get();
            } catch (WrongArgException | WrongAmountOfArgsException e) {
                log.warning("команде были переданы неверные аргументы");
            }
        }
        close();
    }
}