package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import modules.manages.CommandManager;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;


/**
 * Class of Help cmd
 */

public class Help extends AbstractCommand {
    public static String alias = "help";
    static String description = "Вывод данного сообщения";
    private static final String[] acceptedArgs = {};
    public Help() {}

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов"+ Arrays.toString(acceptedArgs));
        }
        else {
            StringBuilder msg = new StringBuilder();
            CommandManager.getCommands().values().forEach(x -> {
                msg.append(x.toString());});
            return new Reply(true, msg);
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
