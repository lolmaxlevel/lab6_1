package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import mycollection.Product;
import mycollection.UnitOfMeasure;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Class of Filter Greater Than unite_of_measure cmd
 */
public class FilterGreaterThanUOM extends AbstractCommand{
    public static String alias = "filter_greater_than_uom";
    static String description = "Выводит элементы у которых поле unitOfMeasure больше данного";
    private static final String[] acceptedArgs = {"UnitOfMeasure"};
    public FilterGreaterThanUOM() {

    }

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов"+ Arrays.toString(acceptedArgs));
        }
        else {
            try {
                UnitOfMeasure UOM = UnitOfMeasure.valueOf(request.getArgs()[0].toUpperCase(Locale.ROOT));
                List<Product> abob = collectionManager.getCollection().
                        stream().filter(o -> o.getUnitOfMeasure().compareTo(UOM)<0).collect(Collectors.toList());
                if (abob.toArray().length == 0){
                    return new Reply(false, "В коллекции нет таких элементов");
                }
                else return new Reply(true, abob.toString());
            }catch (IllegalArgumentException e)
            {
                return new Reply(false, "неверный аргумент");
            }
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
