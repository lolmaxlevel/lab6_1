package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class InputHandler {
    Scanner scanner;
    boolean isScript;
    public InputHandler(Scanner scanner){
        this.scanner = scanner;
    }
    public boolean isReady(){
        return (scanner.hasNextLine());
    }
    public String[] getCommand(){
        String in = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        String[] cmd = in.split("\\s+");
        if (cmd[0].equals("execute_script") && !isScript){
            isScript=true;
            try {
                setScanner(new Scanner(new File("script.txt")));
            } catch (FileNotFoundException e) {
                System.out.println("Файл со скриптом не найден");
                isScript=false;
                setScanner(new Scanner(System.in));
            }

        }
        return cmd;
    }
    public Scanner getScanner(){
        return scanner;
    }
    public boolean isScript(){
        return isScript;
    }
    public void setScanner(Scanner scanner){
        this.scanner=scanner;
    }
    public void setScript(boolean s){
        this.isScript = s;
    }
}
