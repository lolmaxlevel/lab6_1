package mycollection;

import java.io.Serializable;

/**
 * Класс координат для класса Product
 */
public class Coordinates implements Serializable {
    private Integer x; //Максимальное значение поля: 6, Поле не может быть null
    private float y;
    public Coordinates(Integer x, float y)  {
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}

    public Integer getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public void setX(Integer x) throws WrongArgException {
        if (x!=null && x<=6){this.x = x;}
        else {throw new WrongArgException();}
    }
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "X=" + x + ",Y="+y;
    }
}
