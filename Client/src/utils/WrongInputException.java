package utils;
/**
 * Класс-исключение - вызывается, когда при создании продукта происходит неправильный ввод
 */
public class WrongInputException extends Exception{
    public WrongInputException(String message) {
        super(message);
    }

    public WrongInputException() {
    }
}
