package modules.commands;

import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import mycollection.Product;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;


public class Add extends AbstractCommand {
    public static final String alias = "add";
    public static final String description = "Добавление нового элемента в коллекцию";
    public static final String[] acceptedArgs = {"Product"};

    public static String getDescription() {
        return description;
    }

    public Add() {}

    public static String getAlias() {
        return alias;
    }
    public static String[] getAcceptedArgs(){return acceptedArgs;}
    /**
     * Adds element(Product) to collection
     *
     * @param collectionManager collection manager
     * @return
     * @throws WrongAmountOfArgsException risen if given wrong amount of args
     */
    @Override
    public Reply execute(Request request, CollectionManager collectionManager) {
        System.out.println(request.getProduct().getName());
            collectionManager.addItem(new Product(request.getProduct()));
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
