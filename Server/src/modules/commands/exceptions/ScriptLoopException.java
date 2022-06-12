package modules.commands.exceptions;

import java.io.PrintStream;

/**
 * Класс-исключение - вызывается, когда скрипт вызывает сам себя
 */
public class ScriptLoopException extends Exception{
    /**
     * Устанавливает поток вывода на стандартную консоль
     * @param c поток вывода
     */
    public ScriptLoopException(PrintStream c){
        System.setOut(c);
    }
}
