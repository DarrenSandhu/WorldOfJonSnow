import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

/**
 * Class ItemList - a list of class Item in an adventure game.
 *
 * This class is part of the "World of Jon Snow" application. 
 * "World of Jon Snow" is a very simple, text based puzzle/adventure game.  
 *
 * A "ItemList" represents a list of class Item in the game.  It is 
 * in certain rooms and represents the players inventory
 * 
 * @author Darren Sandhu - k20052831
 * @date 03/12/2021
 */
public class ItemList
{
    // stores a list of objects from class Item
    private ArrayList<Item> items;
    /**
     * Create an item list containing objects from class Item.
     */
    public ItemList()
    {
        items = new ArrayList<Item>();
    }
    
    /**
     * Iterates through item list.
     */
    public Iterator iterateItemList()
    {
        return items.iterator();
    }
    
    /**
     * Adds item to item list if item name matches string in parameter
     * @param name of item you want to add.
     */
    public void addItemName(String itemName)
    {
        for(int i = 0; i < itemsSize(); i++) {
            if(get(i).getItemName().equals(itemName)){
                Item item = get(i);
                addItem(item);
            }
        }
    }
    
    /**
     * Removes item from item list if item name matches string in parameter
     * @param name of item you want to remove
     * @return removes item from item list if item name matches string in parameter
     */
    public Item removeItemName(String itemName)
    {
        for(int i = 0; i < itemsSize(); i++) {
            if(get(i).getItemName().equals(itemName)){
                Item item = get(i);
                return removeItem(item);
            }
        }
        return null;
    }
    
    /**
     * Add item to item list
     * @param item object name.
     */
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    /**
     * Remove item from item list
     * @param item object name.
     * @return removes item from item list
     */
    public Item removeItem(Item item)
    {
        items.remove(item);
        return item;
    }
    
    /**
     * Returns size of item list
     * @return returns size of item list
     */
    public int itemsSize()
    {
        return items.size();
    }
    
    /**
     * Removes item from item list at given index
     * @param index of item that you want to remove
     * @return removes item from item list at given index
     */
    public Item removeItem(int index)
    {
        return items.remove(index);
    }
    
    /**
     * Returns item from item list at given index
     * @param index of item you want to get
     * @return the item from item list at given index.
     */
    public Item get(int index)
    {
        return items.get(index);
    }
    
    /**
     * Returns item name of the item you want to get in the item list.
     * @param  item you want to get
     * @return returns item name of the item you want to get in the item list.
     */
    public String getItemFromList(Item item)
    {
        return item.getItemName();
    }
    
    
    /**
     * Returns item name of the item you want to get in the item list.
     * @param  item you want to get
     * @return returns item name of the item you want to get in the item list.
     */
    public String getItemName(Item item)
    {
        return item.getItemName();
    }
    
    /**
     * Returns the item list to a string
     * @return returns the item list to a string
     */
    public String stringItemList()
    {
        return items.toString();
    }
    
    /**
     * Iterates through item list and gets each items description
     * @return returns each items description
     */
    public String getDescription()
    {
        String returnString = "";
        Iterator<Item> itemList = items.iterator();
        while (itemList.hasNext()){
            returnString += " " + itemList.next().showItemDescription() + ", ";
        }
        return returnString;
    }
    
    /**
     * Iterartes through item list and gets each items weight,
     * and adds it to total weight
     * @return returns total weight of all items in item list
     */
    public int getTotalWeight()
    {
        int totalWeight = 0;
        Iterator<Item> iter = items.iterator();
        while (iter.hasNext()){
            totalWeight += iter.next().getItemWeight();
        }
        return totalWeight;
    }
}
    
    
