package modules.commands;

import modules.manages.CollectionManager;
import modules.manages.CommandManager;
import communicate.Reply;
import communicate.Request;

import java.util.ArrayList;

public class AvailableCommands extends AbstractCommand{
    public static String alias = "AvailableCommands";
    public AvailableCommands(){}

    @Override
    public Reply execute(Request args, CollectionManager collectionManager){
        ArrayList<Object> commands = new ArrayList<>();
        for (String cmd : CommandManager.getCommands().keySet()){
            if (CommandManager.getCommands().get(cmd).needProduct()){commands.add(cmd);}
        }
        return new Reply(true, commands);
    }

    @Override
    public String toString() {
        return "";
    }
}
