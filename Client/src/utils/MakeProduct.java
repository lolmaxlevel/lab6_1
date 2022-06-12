package utils;

import mycollection.*;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * класс, который используется для создания элемента Product
 */
public class MakeProduct {
    private final Scanner scanner;

    public MakeProduct(Scanner scanner){
        this.scanner = scanner;
    }
    public Product askAll() throws WrongArgException {
        return new Product(askName(2), askCoordinates(), askPrice(), askManufactureCost(), askUOM(), askOwner());
    }
    public String askName(int fl){
        while (true){
            if (fl != 1)
                System.out.print("Введите имя продукта(строка, непустая, не null):");
            else
                System.out.print("Введите имя владельца(строка, непустая, не null):");
            try {
                String name = scanner.nextLine().trim();
                if (name.equals("null") || name.isEmpty()) throw new WrongInputException();
                else return name;
            } catch (NoSuchElementException e) {
                System.exit(1);
            } catch (WrongInputException e) {
                System.out.println("Что-то пошло не так, повторите ввод");
            }
        }
    }
    public Coordinates askCoordinates() {
        return new Coordinates(askX(), askY());
    }
    public Integer askX(){
        while (true){
            System.out.print("Введите координату X товара(Integer, не null, не больше 6):");
            try {
                String x = scanner.nextLine().trim();
                if (x.equals("null") || x.isEmpty() || Integer.parseInt(x)>6) throw new WrongInputException("что то пошло не так");
                else return Integer.parseInt(x);
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            catch (NumberFormatException | WrongInputException e){
                System.out.println("что то пошло не так");
            }
        }
    }
    public float askY(){
        while (true){
            System.out.print("Введите координату Y товара(float):");
            try {
                String y = scanner.nextLine().trim();
                y = y.isEmpty() ? null : y;
                if (y==null) return 0;
                return Float.parseFloat(y);
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            catch (NumberFormatException e){
                System.out.println("Это не формат Long");
            }
        }
    }
    public double askPrice(){
        while (true){
            System.out.print("Введите цену товара(double, больше 0):");
            try {
                String price = scanner.nextLine().trim();
                price = price.isEmpty() ? null : price;
                if (price==null) throw new WrongInputException();
                return Double.parseDouble(price);
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            catch (NumberFormatException e){
                System.out.println("Это не формат double");
            } catch (WrongInputException e) {
                System.out.println("цена не может быть нулевой");
            }
        }
    }
    public Long askManufactureCost(){
        while (true){
            System.out.print("Введите цену производства товара(Long):");
            try {
                String mprice = scanner.nextLine().trim();
                mprice = mprice.isEmpty() ? null : mprice;
                if (mprice==null) return null;
                return Long.parseLong(mprice);
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            catch (NumberFormatException e){
                System.out.println("Это не формат Long");
            }
        }
    }
    public UnitOfMeasure askUOM(){
        System.out.print("Введите единицы измерения"+ Arrays.toString(UnitOfMeasure.values()) +":");
        while (true){
            try {
                String UOM = scanner.nextLine().trim();
                UOM = UOM.isEmpty() ? null : UOM;
                if (UOM==null) return null;
                return UnitOfMeasure.valueOf(UOM);
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            catch (IllegalArgumentException e){
                System.out.print("Возможные значения "+Arrays.toString(UnitOfMeasure.values())+":");
            }
        }
    }
    public Person askOwner() throws WrongArgException {
        return new Person(askName(1),askWeight(),askPassportID());
    }
    public long askWeight(){
        while (true){
            System.out.print("Введите вес владельца(long, больше 0):");
            try {
                String weight = scanner.nextLine().trim();
                weight = weight.isEmpty() ? null : weight;
                if (weight==null) throw new WrongInputException();
                return Long.parseLong(weight);
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            catch (NumberFormatException e){
                System.out.println("Это не формат Long");
            } catch (WrongInputException e) {
                System.out.println("что то пошло не так");
            }
        }
    }
    public String askPassportID(){
        while (true){
            System.out.print("Введите паспортные данные(String, больше 4, меньше 38, не null):");
            try {
                String passportID = scanner.nextLine().trim();
                passportID = passportID.isEmpty() ? null : passportID;
                if (passportID==null || passportID.length()>38 || passportID.length()<4) throw new WrongInputException();
                if (Person.checkPassportID(passportID)) return passportID;
                else System.out.println("Такой паспорт уже существует, введите уникальный");
            } catch (NoSuchElementException e) {
                System.exit(1);
            }
            catch (WrongInputException e) {
                System.out.println("что то пошло не так");
            }
        }
    }
}
