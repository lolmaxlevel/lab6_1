package mycollection;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс владельца для класса Product
 */
public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Значение этого поля должно быть уникальным, Длина строки не должна быть больше 38, Длина строки должна быть не меньше 4, Поле не может быть null
    private static final List<String> passportIDs = new ArrayList<>();
    @Override
    public String toString() {
        return
                "Имя " + name +
                ", вес " + weight +
                ", номер паспорта " + passportID;
    }

    private static String[] existingIDs;
    public Person(String name, long weight, String passportID) throws WrongArgException {
        setName(name);
        setWeight(weight);
        setPassportID(passportID);
        passportIDs.add(passportID);
    }
    public Person(){}

    public void setName(String name) throws WrongArgException {
        if(name != null && !name.isEmpty()){
            this.name = name;
        }
        else {
            this.name = "defaultName";
            throw new WrongArgException("Имя");
        }
    }

    public void setWeight(long weight) throws WrongArgException {
        if(weight >= 0){
            this.weight = weight;
        }
        else {
            this.weight = Long.parseLong("1");
            throw new WrongArgException("Вес");
        }
    }

    /**
     * функция задания номера паспорта
     * @param passportID номер паспорта
     * @throws WrongArgException исключение, которое вызывается если номер паспорта неверен
     */
    public void setPassportID(String passportID) throws WrongArgException {
        if (passportIDs.contains(passportID))
            throw new WrongArgException("Номер паспорта");
        else {
            this.passportID = passportID;
            passportIDs.add(passportID);
        }
    }

    public String getName() {
        return name;
    }

    public long getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }
    public static boolean checkPassportID(String potentialID){
        return !passportIDs.contains(potentialID);
    }
}
