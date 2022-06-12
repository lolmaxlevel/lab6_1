package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.commands.exceptions.WrongArgException;
import modules.manages.CollectionManager;
import mycollection.Product;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;


/**
 * Класс команды remove lower
 */
public class RemoveLower extends AbstractCommand {
    public static String alias = "remove_lower";
    static String description = "Удаляет элементы дешевле заданного";
    private static final String[] acceptedArgs = {"Product"};
    public RemoveLower() {}

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException, WrongArgException {
            collectionManager.removeLower(new Product(request.getProduct()));
        return new Reply(true);
    }
    @Override
    public String toString() {
        return alias + ":\n\t" + description+"\n";
    }
    public boolean needProduct(){
        return Arrays.asList(acceptedArgs).contains("Product");
    };
}
