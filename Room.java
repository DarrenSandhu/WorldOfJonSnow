import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Iterator;
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

public class Room 
{
    // stores description of this room
    private String description;
    // stores exits of this room.
    private HashMap<String, Room> exits;
    // stores items in the room
    private ItemList items; 
    // stores characters in the room
    private Characters characters;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ItemList();
        characters = new Characters();
    }
    
    /**
     * Returns true if room is in the exits values.
     * @return returns true if room is in the exits values.
     */
    public boolean getRoomName(Room room)
    {
        return exits.values().contains(room);
    }
    
    /**
     * Returns item list of room.
     * @return item list of room
     */
    public ItemList getItemList()
    {
        return items;
    }
    
    /**
     * Returns item if string matches respective item name,
     * else returns null.
     * @param item name
     * @return returns item if string matches respective item name,
     * else returns null.
     */
    public Item getItem(String itemName)
    {
        for(int i = 0; i < items.itemsSize(); i++) {
            if(items.get(i).getItemName().equals(itemName)){
                return items.get(i);
            }
        }
        return null;
    }
    
    /**
     * Returns character if string matches respective character name,
     * else returns null.
     * @param character name
     * @return returns character if string matches respective character name,
     * else returns null.
     */
    public Character getCharacter(String characterName)
    {
        for(int i = 0; i < characters.charactersSize(); i++) {
            if(characters.get(i).getCharacterName().equals(characterName)){
                return characters.get(i);
            }
        }
        return null;
    }
    /**
     * Return the item name of the given item in paranthesis.
     * @param item object
     */
    public String getItemName(Item itemName)
    {
        return items.getItemName(itemName);
    }
        
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Add character to room.
     * @param character object
     */
    public void addCharacter(Character character)
    {
        characters.addCharacter(character);
    }
    
    /**
     * Checks if character name equals parameter
     * @param character name
     */
    public void getCharacterName(String characterName)
    {
        characters.equals(characterName);
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Return integer of item list size in room.
     */
    public int getItemListSize()
    {
        return items.itemsSize();
    }
    
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     Items:
     *     Characters:
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + "\n" + getExitString() + "\n" + "\n" + getName() + "\n" +  "\n" + characters.getCharacterNames();
    }
    
    /**
     * Return a string describing the room's items, for example
     * "Items: message".
     * @return Details of the rooms items.
     */
    public String getName()
    {
        String returnString = "Items: ";
        Iterator<Item> itemList = items.iterateItemList();
        while (itemList.hasNext()){
            returnString += " " + itemList.next().getItemName();
        }
        return returnString;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Add item to room
     * @param item object name
     */
    public void addItem(Item item)
    {
        items.addItem(item);
    }
    
    /**
     * Remove item from room.
     * @param item object name
     */
    public void removeItem(Item item)
    {
        items.removeItem(item);
    }
    
    /**
     * Remove item from room if string matches item name.
     * @param item name 
     * @return remove item from room if string matches item name.
     */
    public Item removeItem(String itemName)
    {
        for(int i = 0; i < items.itemsSize(); i++) {
            if(items.get(i).getItemName().equals(itemName)){
                return items.removeItem(i);
            }
        }
        return null;
    }
}

