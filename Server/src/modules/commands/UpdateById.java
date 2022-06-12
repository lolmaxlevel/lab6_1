package modules.commands;

import modules.commands.exceptions.NotFoundIdException;
import modules.commands.exceptions.WrongAmountOfArgsException;
import modules.manages.CollectionManager;
import mycollection.Product;
import mycollection.WrongArgException;
import communicate.Reply;
import communicate.Request;

import java.util.Arrays;

/**
 * Class of update_by_id cmd
 */

public class UpdateById extends AbstractCommand {
    public static String alias = "update_by_id";
    static String description = "Обновить элемент коллекции по id";
    private static final String[] acceptedArgs = {"id", "Product"};

    public UpdateById() {

    }

    @Override
    public Reply execute(Request request, CollectionManager collectionManager) throws WrongAmountOfArgsException {
        if (request.getArgs().length+1 != acceptedArgs.length){
            return new Reply(false, "Команда принимает "+acceptedArgs.length+" аргументов: "
                    + Arrays.toString(acceptedArgs));
        }
        else {
            try {
                Product prod = collectionManager.getElementById(Integer.parseInt(request.getArgs()[0]));
                prod.setName(request.getProduct().getName());
                prod.setCoordinates(request.getProduct().getCoordinates());
                prod.setPrice(request.getProduct().getPrice());
                prod.setManufactureCost(request.getProduct().getManufactureCost());
                prod.setUnitOfMeasure(request.getProduct().getUnitOfMeasure());
                prod.setOwner(request.getProduct().getOwner());
            } catch (WrongArgException e) {
                System.out.println("Вы ввели неправильное значение");
            }catch (NumberFormatException e){
                System.out.println("Неверный id(id-целое число, введите show для простоты)");
            } catch (NotFoundIdException e) {
                System.out.println("id не найден в коллекции");
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
