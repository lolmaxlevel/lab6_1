package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;


/**
 * Class of cmd Clear
 */
public class Clear extends AbstractCommand {
     public static final String alias = "clear";
     public static final String description = "Очистить коллекцию";
    private static final String[] acceptedArgs = {};

    public static String getAlias() {
        return alias;
    }

    public static String getDescription() {
        return description;
    }

    public Clear() {}

    /**
     * Clears collection if it's not empty
     *
     * @param collectionManager collection manager
     * @return Reply to client
     * @throws WrongAmountOfArgsException risen if any args given
     */
    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов");
        }
        else {
            if (collectionManager.isEmpty()){
                return new Reply(true,"Коллекция уже пуста");
            }else {
                collectionManager.clear();
                return new Reply(true, "Коллекция очищена");
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
