import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class Characters - a list of characters for a room in an adventure game.
 *
 * This class is part of the "World of Jon Snow" application. 
 * "World of Jon Snow" is a very simple, text based puzzle/adventure game.  
 *
 * A "characters" list  represents different a list of characters from class "Character"
 * which are in a room. It allows multiple characters to be in one room.
 * 
 * @author Darren Sandhu - k20052831
 * @date 03/12/2021
 */
public class Characters
{
    // instance variables - replace the example below with your own
    private ArrayList<Character> characters;

    /**
     * Create a characters list containing objects from class Character
     */
    public Characters()
    {
        // initialise instance variables
        characters = new ArrayList<Character>();
    }
    
    /**
     * Iterates through character list.
     */
    public Iterator iterateItemList()
    {
        return characters.iterator();
    }
    
    /**
     * Adds character to characters list if character name matches string in parameter
     * @param name of character you want to add.
     */
    public void addItemName(String charactersName)
    {
        for(int i = 0; i < characters.size(); i++) {
            if(get(i).getCharacterName().equals(charactersName)){
                Character character = get(i);
                addCharacter(character);
            }
        }
    }
    
    /**
     * Removes character from characters list if character name matches string in parameter
     * @param name of character you want to remove
     * @return removes character from character list if character name matches string in parameter
     */
    public Character removeCharacterName(String characterName)
    {
        for(int i = 0; i < characters.size(); i++) {
            if(get(i).getCharacterName().equals(characterName)){
                Character character = get(i);
                return removeCharacter(character);
            }
        }
        return null;
    }
    
    /**
     * Add character to characters list
     * @param character object name.
     */
    public void addCharacter(Character character)
    {
        characters.add(character);
    }
    
    /**
     * Removes charcater from character list
     * @param character object name.
     * @return removes character from item list
     */
    public Character removeCharacter(Character character)
    {
        characters.remove(character);
        return character;
    }
    
    /**
     * Returns size of character list
     * @return returns size of character list
     */
    public int charactersSize()
    {
        return characters.size();
    }
    
    /**
     * Removes character from characters list at given index
     * @param index of character that you want to remove
     * @return removes character from characters list at given index
     */
    public Character removeCharacter(int index)
    {
        return characters.remove(index);
    }
    
    /**
     * Returns character from characters list at given index
     * @param index of character you want to get
     * @return the character from characters list at given index.
     */
    public Character get(int index)
    {
        return characters.get(index);
    }
    
    /**
     * Returns the item of the character
     * @param character you want item from
     * @return the item of character
     */
    public Item getCharacterItem(Character character)
    {
        return character.getCharacterItem();
    }
    
    /**
     * Returns the item name the character is holding
     * @param item in character possesion
     * @return item name the character is holding
     */
    public String getCharacterName(Item item)
    {
        return item.getItemName();
    }
    
    /**
     * Iterates through the characters list and returns
     * the items the characters are holding
     * @return the items the characters are holding 
     */
    public String getCharacterItems()
    {
        String returnString = "Character Items: ";
        Iterator<Character> characterList = characters.iterator();
        while (characterList.hasNext()){
            returnString += " " + characterList.next().getCharacterItemName() + ", ";
        }
        return returnString;
    }
    
    /**
     * Iterates through the characters list and returns
     * the characters names
     * @return the characters names
     */
    public String getCharacterNames()
    {
        String returnString = "Characters: ";
        Iterator<Character> characterList = characters.iterator();
        while (characterList.hasNext()){
            returnString += " " + characterList.next().getCharacterName()+ ", ";
        }
        return returnString;
    }
}
    
    

