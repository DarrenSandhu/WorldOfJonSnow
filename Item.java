import java.util.Random;
import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Jon Snow" application. 
 * "World of Jon Snow" is a very simple, text based puzzle/adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room. The room also stores items
 * and characters which can be interacted with by the player
 * 
 * @author Darren Sandhu - k20052831
 * @date 03/12/2021
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private String itemName;
    private int itemWeight;
    /**
     * Constructor for objects of class Item
     */
    public Item(String itemName, String itemDescription)
    {
        this.itemDescription = itemDescription;
        this.itemName = itemName;
        randomItemWeight();
    }
    
    /**
     * Returns the name of item
     * @return the name of the item
     */
    public String getItemName()
    {
        return itemName;
    }
    
    /**
     * Returns the description of the item
     * @return the description of the item
     */
    public String showItemDescription()
    {
        return itemDescription;
    }
    
    /**
     * Returns the item as a string
     * @return the item as string
     */
    public String makeItemToString(Item item)
    {
        String itemString = item.toString();
        return itemString;
    }
    
    /**
     * Make the item weight a random integer between 1 and 3.
     * If item is golden key then random weight between 8 and 10.
     */
    
    public void randomItemWeight()
    {
        Random random = new Random();
        if (itemName.equals("goldenkey")){
            int weight = random.nextInt((10-8)+10)+8;
            itemWeight = weight;    
        }
        else{
            int weight = random.nextInt(3)+1;  
            itemWeight = weight;
        }
    }
    
    /**
     * Allows item weight to be changed to integer in parameter
     */
    public void setItemWeight(int index)
    {
        itemWeight = index;
    }
    
    /**
     * Returns the weight of the item
     * @return item weight
     */
    public int getItemWeight()
    {
        return itemWeight;
    }
}
