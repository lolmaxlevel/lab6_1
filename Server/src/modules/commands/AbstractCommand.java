package modules.commands;
import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.commands.exceptions.WrongArgException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;



/**
 * Class for abstract command
 */
public abstract class AbstractCommand {
    static String alias;
    static String description;
    private static final String[] acceptedArgs = {"Product"};

    public static String getAlias() {
        return alias;
    }

    public static String getDescription() {
        return description;
    }

    /**
     *
     * @param args args for command
     * @param collectionManager manager of collection
     * @throws WrongAmountOfArgsException risen when wrong amount of args given to command
     * @throws WrongArgException risen when wrong arg given to cmd
     */

    public abstract Reply execute(Request args, CollectionManager collectionManager) throws WrongAmountOfArgsException, WrongArgException;
    public String toString(){
        return null;
    };

    public boolean needProduct() {
        return false;
    }
}

