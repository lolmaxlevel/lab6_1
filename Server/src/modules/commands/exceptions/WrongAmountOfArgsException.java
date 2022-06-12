package modules.commands.exceptions;
/**
 * Класс-исключение - вызывается, когда команде передано неверное количество аргументов
 */
public class WrongAmountOfArgsException extends Exception{
    public WrongAmountOfArgsException() {
    }

    public WrongAmountOfArgsException(String message, String args) {
        super("Команда принимает "+message+" аргументов " + args);
    }

    public WrongAmountOfArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongAmountOfArgsException(Throwable cause) {
        super(cause);
    }
}
