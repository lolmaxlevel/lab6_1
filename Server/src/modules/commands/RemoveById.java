package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;

/**
 * Class of remove_by_id cmd
 */

public class RemoveById extends AbstractCommand {
    public static String alias = "remove_by_id";
    static String description = "Удаление элемента из коллекции по id";
    private static final String[] acceptedArgs = {"id"};
    public RemoveById() {

    }

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов: "+ Arrays.toString(acceptedArgs));
        }
        else {
            try {
                collectionManager.removeByID(Integer.parseInt(request.getArgs()[0]));
                return new Reply(true,"Элемент коллекции удален");
            } catch (NumberFormatException e){return new Reply(false,"Команда принимает аргументы:"+String.join(",", acceptedArgs));}
        }
    }
    @Override
    public String toString() {
        return alias + ":\n\t" + description+"\n";
    }
    public boolean needProduct(){
        return Arrays.asList(acceptedArgs).contains("Product");
    };
}
