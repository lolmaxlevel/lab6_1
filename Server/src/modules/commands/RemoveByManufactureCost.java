package modules.commands;


import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class of remove_by_manufacture_cost cmd
 */
public class RemoveByManufactureCost extends AbstractCommand {
    public static String alias = "remove_by_manufacture_cost";
    static String description = "Удаляет все элементы коллекции которые дешевле данного";
    private static final String[] acceptedArgs = {"price"};
    public RemoveByManufactureCost() {

    }

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов: "
                    + Arrays.toString(acceptedArgs));
        }
        else {
            if (Objects.equals(request.getArgs()[0], "null")) {
                collectionManager.removeByManufactureCost(null);
            } else {
                try {
                    collectionManager.removeByManufactureCost(Long.parseLong(request.getArgs()[0]));
                    return new Reply(true,"Элементы коллекции удалены");
            } catch (NumberFormatException e){return new Reply(false,"Команда принимает аргументы:"
                        +String.join(",", acceptedArgs));}
            }
        }
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
