package modules.commands;


import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;

/**
 * Class of remove_first cmd
 */
public class RemoveFirst extends AbstractCommand {
    public static String alias = "remove_first";
    static String description = "Удаляет первый элемент коллекции";
    private static final String[] acceptedArgs = {};
    public RemoveFirst() {

    }


    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            throw new WrongAmountOfArgsException(String.valueOf(acceptedArgs.length),
                    String.join(" ", acceptedArgs));
        }
        else {
            if (collectionManager.isEmpty()){
                return new Reply(false,"Коллекция пуста");
            } else collectionManager.removeFirst(); return new Reply(true);
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
