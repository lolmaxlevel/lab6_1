package tranceiving;

import java.io.*;

public class Receiver {
    private final ObjectInputStream receiver;

    public Receiver(ObjectInputStream receiver) {
        this.receiver = receiver;
    }

    public Object readObject() throws ReceiverException {
        try {
            return receiver.readObject();
        } catch (ClassNotFoundException e) {
            throw new ReceiverException("Класс не найден", e);
        } catch (InvalidClassException e) {
            throw new ReceiverException("Неисправность в классе, использованном сериализацией", e);
        } catch (StreamCorruptedException e) {
            throw new ReceiverException("Информация в потоке повреждена", e);
        } catch (IOException e) {
            throw new ReceiverException("Ошибка получения объекта", e);
        }
    }
    public void close() throws IOException {
        receiver.close();
    }
}