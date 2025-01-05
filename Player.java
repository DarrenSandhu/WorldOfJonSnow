import java.util.ArrayList;
import java.util.Random;

/**
 * Class Player - the player in an adventure game.
 *
 * This class is part of the "World of Jon Snow" application. 
 * "World of Jon Snow" is a very simple, text based puzzle/adventure game.  
 *
 * A "Player" represents one player in the game.  The Player is 
 * placed in different rooms through commands and is able interact with
 * the world. For example the player can pick up items and talk to
 * different characters.
 * 
 * @author Darren Sandhu - k20052831
 * @date 03/12/2021
 */
public class Player
{
    // stores player name
    private String playerName;
    // stores player current room
    private Room currentRoom;  
    // stores player previous room
    private Room previousRoom; 
    // stores the players inventory of items
    private ItemList inventory = new ItemList();
    // stores maximum weight of player
    private int maxWeight = 10;
    // stores maximum moves player can have
    private int maxPlayerMoves = 30;
    //stores current player moves
    private int playerMoves = 0;
    /**
     * Creates a player with the player name in the parameter
     * @param the name of the player
     */
    public Player(String playerName)
    {
        this.playerName = playerName;
    }
    
    /**
     * Allows players name to change to string in parameter
     * @param the name of the player
     */
    public void setPlayerName(String name)
    {
        playerName = name;
    }
    
    /**
     * Allows players max weight to be increased.
     * @param the ammount you want to increase max weight
     */
    public void addMaxWeight(int index)
    {
        maxWeight += index; 
    }
    
    /**
     * Returns the previous room the player was in
     * @return returns the previous room the player was in
     */
    public Room getPreviousRoom()
    {
        return previousRoom;
    }
    
    /**
     * Makes player go to new room that is in parameter and stores
     * previous room to the room before. Also increments player moves
     * by 1.
     * @param the room the player wants to go to.
     */
    public void goToRoom(Room nextRoom)
    {
        playerMoves++;
        previousRoom = currentRoom;
        currentRoom = nextRoom;
    }
    
    /**
     * Returns true if conditon is met which allows game
     * to end in Game class.
     * @return returns true if player playerMoves > maxPlayerMove. 
     */
    public boolean playerDead()
    {
        return playerMoves > maxPlayerMoves;
    }
    
    /**
     * Returns current room player is in
     * @return returns current room player is in
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Returns the players name
     * @return the players name
     */
    public String getPlayerName()
    {
        return playerName;
    }
    
    /**
     * Returns the players inventory
     * @return the players inventory.
     */
    public ItemList getInventory()
    {
        return inventory;
    }
    
    /**
     * Returns the players weight
     * @return the players weight
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }
    
    /**
     * Returns the total inevntory weight of the player
     * @return the toal inventory weight of the player
     */
    public int getInventoryWeight()
    {
        return inventory.getTotalWeight();
    }    
    
    /**
     * Adds item to inventory of player if parameter matches item name
     * @param the item name
     */
    public void addToInventory(String itemName)
    {
        inventory.addItemName(itemName);
    }
    
    /**
     * Adds item to inventory if parameter matches item object name
     * @param item object name 
     */
    public void addToInventory(Item item)
    {
        inventory.addItem(item);
    }
    
    /**
     * Iterates through player inventory and checks if item name
     * matches string in paramter, and then returns the item if it does,
     * else returns null
     * @param item name you want to get
     * @return the item if it matches string in paramter, else null
     */
    public Item getItemFromInventory(String itemName)
    {
        for(int i = 0; i < inventory.itemsSize(); i++) {
            if (inventory.get(i).getItemName().equals(itemName)) {
                Item item = inventory.get(i);
                return item;
            }
        }
        return null;
    }
    
    /**
     * Itertates through item list and returns true if string in
     * parameter matches item name.
     * @param the item name you want to get
     * @return true if string matches item name, otherwise false
     */
    public boolean itemInInventory(String itemName)
    {
        for(int i = 0; i < inventory.itemsSize(); i++) {
            if (inventory.get(i).getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Removes item from inventory if item object name 
     * matches parameter
     * @param item object name
     */
    public void removeFromInventory(Item item)
    {
        inventory.removeItem(item);
    }
    
    /**
     * Returns a string of all the items in the players inventory
     * @return a string of all the items in the players inventory
     */
    public String showInventory()
    {
        String returnString = "You are carrying: ";
        for(int i = 0; i < inventory.itemsSize(); i++) {
            returnString += inventory.get(i).getItemName() + " ";
        }
        if (inventory.itemsSize() == 0) {
            returnString += "nothing";
        }
        return returnString;
    }

    /**
     * Returns players current weight and max weight
     * @return a string of players current weight and max weight
     */
    public String showWeightInformation()
    {
        String returnString = "Your weight is: " + getInventoryWeight() + " / " + maxWeight;
        return returnString;
    }

    
    /**
     * Returns the description of the current room player is in, as well
     * as the items and characters in the current room. Also returns what
     * items player is carrying.
     * @return the description of the current room player is in, as well
     * as the items and characters in the current room. Also returns what
     * items player is carrying.
     */
    public String getLongDescription()
    {
        return currentRoom.getLongDescription() + "\n" + "\n" + showInventory();
    }
    
    /**
     * Returns a string of the players total weight
     * @return a string of the players total weight
     */
    public String playerTotalWeight()
    {
        String returnString = "Your characters weight is: " + getInventoryWeight();
        return returnString;
    }
    
}
