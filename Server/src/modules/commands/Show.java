package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;

/**
 * Class of show cmd
 */

public class Show extends AbstractCommand {
    public static String alias = "show";
    static String description = "Вывод все элементы коллекции";
    private static final String[] acceptedArgs = {};

    public Show() {

    }

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов: "
                    + Arrays.toString(acceptedArgs));
        }
        else
        {
            if (collectionManager.isEmpty()) return new Reply(false,"Коллекция пуста");
            else {
                StringBuilder msg = new StringBuilder();
                collectionManager.getCollection().forEach(x -> {
                    msg.append(x.toString()).append("\n");});
                return new Reply(true, msg);
            }
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