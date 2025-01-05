
/**
 * Class Character - a character in an adventure game.
 *
 * This class is part of the "World of Jon Snow" application. 
 * "World of Jon Snow" is a very simple, text based puzzle/adventure game.  
 *
 * A "Character" represents one character in the game. A character can 
 * be in different rooms in the game. A character can hold different items,
 * which can be given to the player.
 * 
 * @author Darren Sandhu - k20052831
 * @date 03/12/2021
 */
public class Character
{
    // stores the characters name
    private String characterName;
    // stores the item the characters holding
    private Item characterItem;
    /**
     * Create a character with a name and item it is holding.
     */
    public Character(String characterName, Item characterItem)
    {
        this.characterName = characterName;
        characterItem = characterItem;
    }
    
    /**
     * Returns the characters name
     * @return the characters name
     */
    public String getCharacterName()
    {
        return characterName;
    }
    
    /**
     * Change item character is holding to item in paramter
     */
    public void setItem(Item item)
    {
        characterItem = item;
    }
    
    /**
     * Returns the item the character is holding
     * @return the item the character is holding
     */
    public Item getCharacterItem()
    {
        return characterItem;
    }
    
    /**
     * Returns the item name of the item the character is holding
     * @return the item name of the item the character is holding
     */
    public String getCharacterItemName()
    {
        return characterItem.getItemName();
    }
}
