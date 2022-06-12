package tranceiving;

import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * Class that sends objects
 */
public class Sender {
    private final ObjectOutputStream sender;
    public Sender(ObjectOutputStream sender) {
        this.sender = sender;
    }


    /**
     * send obj
     *
     * @param request request to server
     */
    public void send(Object request) {
        try {
           sender.writeObject(request);
           sender.flush();
        } catch (IOException e) {
            System.out.println("Ошибка отправки объекта на сервер");
        }
    }
    public void flush(){
        try {
            sender.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void close() throws IOException {
        sender.close();
    }
}
