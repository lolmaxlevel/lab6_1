package modules.manages;

import modules.commands.exceptions.NotFoundIdException;
import mycollection.Product;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 *Class that works with collection
 */
public class CollectionManager {
    private final static ArrayList<Product> collection = new ArrayList<>();
    ZonedDateTime creationDate;

    /**
     * Class constructor
     */
    public CollectionManager() {
        this.creationDate= ZonedDateTime.now();
    }

    /**
     * adds element to collection
     * @param abob element(Product)
     */
    public void addItem(Product abob) {
        collection.add(abob);
        Collections.sort(collection);
    }

    /**
     * checks if collection is empty
     * @return True if collection is empty, false if else
     */
    public boolean isEmpty(){
        return collection.isEmpty();
    }

    /**
     * clears collection
     */
    public void clear(){
        collection.clear();
    }
    /**
     * deletes first element of collection
     */
    public void removeFirst(){
        Collections.sort(collection);
        collection.remove(0);
    }

    /**
     * gets collection
     * @return collection
     */
    public ArrayList<Product> getCollection(){
        Collections.sort(collection);
        return collection;
    }

    /**
     *
     * @return collection size
     */
    public int getSize(){
        return collection.size();
    }

    /**
     * deletes element by id
     * @param id id of element that should be removed
     */
    public void removeByID(int id){
        collection.removeIf(id1 -> id1.getId()==(id));
    }
    /**
     * deletes all elements with price
     * @param price price with which elements should be deleted
     */
    public void removeByManufactureCost(Long price){
        collection.removeIf(id1 -> Objects.equals(id1.getManufactureCost(), price));
    }

    /**
     * deletes all elements that is higher in collection
     * @param pr element greater which elements should be deleted
     */
    public void removeGreater(Product pr){
        collection.add(pr);
        Collections.sort(collection);
        collection.indexOf(pr);
        collection.removeAll(collection.subList(0, collection.indexOf(pr) + 1));
        collection.remove(pr);
    }
    /**
     * deletes all elements that is lower in collection
     * @param pr element lower which elements should be deleted
     */
    public void removeLower(Product pr){
        collection.add(pr);
        Collections.sort(collection);
        collection.indexOf(pr);
        collection.removeAll(collection.subList(collection.indexOf(pr) + 1, collection.size()));
        collection.remove(pr);
    }

    /**
     *
     * @param products all elements that should be added
     */
    public void addAll(ArrayList<Product> products) {
        this.collection.addAll(products);
    }
    public ZonedDateTime getCreationDate(){
        return this.creationDate;
    }
    public Product getElementById(int id) throws NotFoundIdException {
        for(Product product: getCollection()) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new NotFoundIdException();
    }
}
