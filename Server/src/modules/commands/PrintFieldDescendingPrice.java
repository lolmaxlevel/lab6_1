package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;

import communicate.Reply;
import communicate.Request;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;


/**
 * Class of Print_field_descending_price cmd
 */
public class PrintFieldDescendingPrice extends AbstractCommand{
    public static String alias = "print_field_descending_price";
    static String description = "Выводит все цены в порядке убывания";
    private static final String[] acceptedArgs = {};
    public PrintFieldDescendingPrice() {

    }

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов"+ Arrays.toString(acceptedArgs));
        }
        else {
            if (collectionManager.getSize()!=0){
                ArrayList<Double> msg = new ArrayList<>();
                collectionManager.getCollection().forEach(x -> {
                    msg.add(x.getPrice());});
                Collections.sort(msg);
                String listString = msg.stream().map(Object::toString)
                        .collect(Collectors.joining("\n"));
                return new Reply(true,listString);
            } else return new Reply(false, "Коллекция пуста");
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
