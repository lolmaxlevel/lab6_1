package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;


/**
 * Class of Info cmd
 */

public class Info extends AbstractCommand {
    public static String alias = "info";
    static String description = "Вывод информации о коллекции";
    private static final String[] acceptedArgs = {};
    public Info() {

    }

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов"+ Arrays.toString(acceptedArgs));
        }
        else {
            return new Reply(true,"Тип: " + collectionManager.getCollection().getClass().getName()
                    +"\nДата инициализации: " +
                    collectionManager.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+
            "\nКоличество элементов: " + collectionManager.getSize());
        }
    }
    @Override
    public String toString() {
        return alias + ":\n\t" + description+"\n";
    }
    public boolean needProduct(){
        return false;
    };
}
