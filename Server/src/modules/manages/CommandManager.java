package modules.manages;

import modules.commands.AbstractCommand;
import modules.commands.*;
import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.commands.exceptions.WrongArgException;
import communicate.Reply;
import communicate.Request;

import java.util.HashMap;


/**
 * Class for executing command
 */
public class CommandManager {
    public static final HashMap<String, AbstractCommand> commands = new HashMap<>();
    private static final CollectionManager collectionManager= new CollectionManager();

    /**
     * class constructor
     */
    public CommandManager(){
        commands.put(AvailableCommands.alias, new AvailableCommands());
        commands.put(Add.alias, new Add());
        commands.put(Clear.alias, new Clear());
        commands.put(Exit.alias, new Exit());
        commands.put(FilterGreaterThanUOM.alias, new FilterGreaterThanUOM());
        commands.put(Help.alias, new Help());
        commands.put(Info.alias, new Info());
        commands.put(PrintFieldDescendingPrice.alias, new PrintFieldDescendingPrice());
        commands.put(RemoveById.alias, new RemoveById());
        commands.put(RemoveByManufactureCost.alias, new RemoveByManufactureCost());
        commands.put(RemoveFirst.alias, new RemoveFirst());
        commands.put(RemoveGreater.alias, new RemoveGreater());
        commands.put(RemoveLower.alias, new RemoveLower());
        commands.put(Show.alias, new Show());
        commands.put(UpdateById.alias, new UpdateById());
    }

    /**
     * executes a command if exists
     *
     * @return
     */
    public static Reply executeCommand(Request request) throws WrongArgException, WrongAmountOfArgsException {
        String commandName = request.getCommand();
        if (commands.containsKey(commandName)) {
            return commands.get(commandName).execute(request, collectionManager);
        } else {
           return new Reply(false, "Такой команды нет. Введите help, чтобы получить список и описание команд.");
        }
    }

    public static HashMap<String, AbstractCommand> getCommands(){
        return commands;
    }

}
