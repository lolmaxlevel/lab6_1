package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;

/**
 * Class of exit cmd
 */

public class Exit extends AbstractCommand {
    public static String alias = "exit";
    static String description = "Завершает работу клиента";
    private static final String[] acceptedArgs = {};
    public Exit() {}

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов");
        }
        else {
            return new Reply(true, "exit");
        }
    }
    @Override
    public String toString() {
        return alias + ":\n\t" + description;
    }
    public boolean needProduct(){
        return false;
    };
}
