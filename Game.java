import java.util.ArrayList;
import java.util.Random;

/**
 *  This class is the main class of the "World of Jon Snow" application. 
 *  "World of Jon Snow" is a very simple, text based puzzle/adventure game.  Users 
 *  can walk around some scenery, pick up items and talk to characters.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, items, characters, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 *  @author Darren Sandhu - k20052831
 * @date 03/12/2021
 */

public class Game 
{
    private Parser parser;
    // stores the current room to create start room for player.
    private Room currentRoom;  
    // stores the array list of rooms
    private ArrayList<Room> rooms;
    // stores the player
    private Player player;           
    /**
     * Create the game and initialise its internal map.
     * @param the player name
     */
    public Game(String playerName)
    {
        createRoomsAndItems();      // invokes the createRoomsAndItemsAndCharacters method to create the basis of game
        parser = new Parser();
        player = new Player(playerName);      // sets player name to the string in constructor parenthesis.
        player.goToRoom(currentRoom);       // sets player currentRoom
        rooms = new ArrayList<Room>();      // creates array list of rooms for magic transporter room
    }

    /**
     * Creates:
     * .All the rooms and link their exits together.
     * .All the items and places them in rooms and characters.
     * .All the characters and places them in rooms.
     */
    private void createRoomsAndItems()
    {
        Room dungeon, dungeon_hallway, creepy_red_door, red_door_area, blue_door, blue_door_area, dark_tunnel, light_tunnel, small_room, giants_room, wormhole, exit; // changed pub to resturant and added gym  
        rooms = new ArrayList<Room>();
        // create the rooms
        dungeon = new Room("in dark deep dungeon deep below winterfell castle...");
        dungeon_hallway = new Room("in  mysterious hallway full of suprises...");
        creepy_red_door = new Room("at an out of place red door....I wonder whats behind here?");
        red_door_area = new Room("in the mysterious area behind the red door,housing a suspicous looking guest");
        blue_door = new Room("at a blue door on the opposite end of hallway...");
        blue_door_area = new Room("in a room that looks like it could be the key out of here");
        dark_tunnel = new Room("travelling through a dark tunnel which seems to be never ending...");
        light_tunnel = new Room("travelling through a light tunnel which seems to be promising.");
        small_room = new Room("opening a small door, almost hidden in plain sight, and see a dwarf");
        giants_room = new Room("opening a great, towering door, and see the most terrifying of beasts");
        wormhole = new Room("travelling through a hypnotsing spiral that feels like it could take me anywhere");
        exit = new Room("outisde the castle!");
        
        // initialise room exits
        dungeon.setExit("north", dungeon_hallway);                                          
        
        
        dungeon_hallway.setExit("east", red_door_area);
        dungeon_hallway.setExit("west", blue_door_area);
        dungeon_hallway.setExit("south", dungeon);
        dungeon_hallway.setExit("north", exit);
        
        
        blue_door_area.setExit("south", dungeon_hallway);
        blue_door_area.setExit("west", light_tunnel);
        blue_door_area.setExit("east", dark_tunnel);
        
        
        red_door_area.setExit("south", dungeon_hallway);
        
        
        dark_tunnel.setExit("south", blue_door_area);
        dark_tunnel.setExit("north", wormhole);
        
        
        light_tunnel.setExit("south", blue_door_area);
        light_tunnel.setExit("west", small_room);
        light_tunnel.setExit("east", giants_room);
        
        
        giants_room.setExit("south", light_tunnel);
        
        
        small_room.setExit("south", light_tunnel);
        
        
        
        currentRoom = dungeon;  // start game in dungeon
        
        //Create the items
        Item message, blue_door_key, red_door_key, magic_spell, hat, jumper, trousers, wand, golden_key, backpack;
        
        
        message = new Item("message","If anyone finds this letter: " + "\n" + "Please follow the light....." + "\n" + "The dwarf is the key....");
        blue_door_key = new Item("bluedoorkey","A key that could be used to open a certain door..");
        red_door_key = new Item("reddoorkey","A key that could be used to open a certain door..");
        magic_spell = new Item("magicspell","The spell given to you by the dwarf.");
        hat = new Item("dirtyhat","A weird looking hat found on the floor.");
        jumper = new Item("christmasjumper","Looks cosy.");
        trousers = new Item("tweedtrousers","What could I do with these?");
        wand = new Item("magicalwand","Old, tarnished wand, needed to perform magic spell");
        golden_key = new Item("goldenkey","Your just in luck!");
        backpack = new Item("backpack","Increases maximum carrying capacity");
    
        //Create the characters
        Character dwarf, giant, goblin;
        
        dwarf = new Character("dwarf", null);
        giant = new Character("giant", null);
        goblin = new Character("goblin", null);
        
        
        //set items in rooms
        dungeon.addItem(message);
        dungeon_hallway.addItem(blue_door_key);
        dungeon_hallway.addItem(red_door_key);
        dungeon_hallway.addItem(golden_key);
        dungeon_hallway.addItem(backpack);
        blue_door_area.addItem(hat);
        blue_door_area.addItem(trousers);
        red_door_area.addItem(jumper);
        small_room.addItem(wand);
        small_room.addItem(magic_spell);
        
        //set characters in rooms
        small_room.addCharacter(dwarf);
        giants_room.addCharacter(giant);
        red_door_area.addCharacter(goblin);
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     *  Allows player to win the game and lose.
    */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.getCurrentRoom().getShortDescription().equals("outisde the castle!")) {  // allows player to beat the game.
                System.out.println();
                victoryMessage();
                finished = true;
            }
            
            if (player.playerDead()) { //allows player to lose the game
                playerDeathMessage();
                finished = true;
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("Hello Player: " + player.getPlayerName()); // welcomes the player by chosen name
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getLongDescription()); // prints where the player is and whats available to player in current room.
    }
    
    /**
     * Prints victory message when player wins.
     */
    private void victoryMessage()
    {
        System.out.println("Congratulations, you have beat the game!");
    }
    
    /**
     * Prints death message when player reaches max moves.
     */
    private void playerDeathMessage()
    {
        System.out.println("You are dead.");
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        else if (commandWord.equals("pick")) {
            pickUpItem(command);
        }
        else if (commandWord.equals("inspect")) {
            inspectItem(command);
        }
        else if (commandWord.equals("use")) {
            useItem(command);
        }
        else if (commandWord.equals("back")) {
            goPreviousRoom(command);
        }
        else if (commandWord.equals("talk")) {
            talkToCharacter(command);
        }
        else if (commandWord.equals("weight")) {
            System.out.println(player.showWeightInformation());
        }
        // else command not recognised.
        return wantToQuit;
    }
    
    
    // implementations of user commands:

    /**
     * Print out some help information.
     * Information that contains the players current weight and inventory.
     * As well as some helpful information to guide the player on what to do.
     */
    private void printHelp() 
    {
        System.out.println("Welcome, " + player.getPlayerName()); // prints players chosen name
        System.out.println();
        System.out.println("You find yourself locked in the dungeon of Winterfell Castle. You can hear the distant screams of dragons and giants.");
        System.out.println("Theres a message placed in between the deep crevices on the floor. It tells you");
        System.out.println("that to 'follow the light....the dwarf is the key....'");
        System.out.println();
        System.out.println(player.showInventory()); // shows players current inventory
        System.out.println();
        System.out.println(player.playerTotalWeight()); // shows players current weight
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in to one direction. If there is an exit and player has correct item, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is nothing here....");
        }
        else if ((nextRoom.getShortDescription().equals("in the mysterious area behind the red door,housing a suspicous looking guest"))) { // checks if next room is red door area.
            if ((player.itemInInventory("reddoorkey"))||(player.itemInInventory("goldenkey"))) { // checks if players inventory contains correct items to enter room
               player.goToRoom(nextRoom);
               System.out.println(player.getLongDescription()); 
            }
            else {
                System.out.println("You are missing the key for the red door.");
            }
        }
        else if ((nextRoom.getShortDescription().equals("in a room that looks like it could be the key out of here"))) { // checks if next room is blue door area.
            if ((player.itemInInventory("bluedoorkey"))||(player.itemInInventory("goldenkey"))) { // checks if players inventory contains correct items to enter room
                player.goToRoom(nextRoom);
                System.out.println(player.getLongDescription()); 
            }
            else {
                System.out.println("You are missing the key for the blue door.");
            }
        }
        else if ((nextRoom.getShortDescription().equals("travelling through a hypnotsing spiral that feels like it could take me anywhere")))  { // checks if player entered magic transporter room
            Random random = new Random();
            player.goToRoom(rooms.get(random.nextInt(rooms.size())));
            System.out.println(player.getLongDescription());
        }
        else if ((nextRoom.getShortDescription().equals("outisde the castle!"))) { // checks if next room is exit.
            if ((player.itemInInventory("magicalwand")) && (player.itemInInventory("magicalspell"))) { // checks if players inventory contains correct items to enter room
                player.goToRoom(nextRoom);
                System.out.println(player.getLongDescription()); 
            }
            else if ((player.itemInInventory("magicalwand"))) {
                System.out.println("You need the magical spell as well.");
            }
            else if ((player.itemInInventory("magicalspell"))) {
                System.out.println("You need the magical wand to perform spell.");
            }
            else {
                System.out.println("You need something magical.");
            }
        }
        else {
            player.goToRoom(nextRoom);
            System.out.println(player.getLongDescription());
        }
    }
    
    /**
     * Allows player to try and use item that is currently in their inventory,
     * and performs changes on player if item is used, otherwise states that item
     * cannot be used.
     */
    
    private void useItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            System.out.println("Use what?");
            return;
        }
        String itemName = command.getSecondWord();
        
        // Try to use item.
        Item item = player.getItemFromInventory(itemName);
        if ((item == null)) {
            System.out.println("This item is not in your inventory.");    
        }
        else if(itemName.equals("backpack")) { // checks if item is backpack which allows changes to happen on player if used.
            System.out.println("You have used " + itemName + ".");
            player.addMaxWeight(20);
            System.out.println("Maximum carrying capacity increased by 20!");
        }
        else if(itemName.equals("message")) {
            System.out.println("How can I use this....");
        }
        else {
            System.out.println("Cant use this item.");
        }
    }
    
    /**
     * Allows player to try and talk to character that is in current room,
     * and performs changes on player if player talks to certain characters, 
     * otherwise states that character does not exist in current room or
     * character doesnt want to talk.
     */
    
    private void talkToCharacter(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Talk to who?");
            return;
        }
        String characterName = command.getSecondWord();
        //Try to talk to character
        Character character = player.getCurrentRoom().getCharacter(characterName);
        if ((character == null)) {
            System.out.println("This character does not exist here....");    
        }
        else if (character.getCharacterName().equals("dwarf")) { // checks if character name is dwarf
            System.out.println("The dwarf has spoken about the magical exit to get outside the castle.");
            System.out.println("The dwarf has given you his magical wand to use this magical exit.");
            Item characterItem = player.getCurrentRoom().getItem("magicalwand");    //adds magical wand to player inventory if player talks to dwarf
            player.addToInventory(characterItem);
        }
        else {
            System.out.print("This character does not want to talk to you.");
        }
    }
    
    /**
     * Allows player to try and inspect item that is currently in their inventory,
     * and prints information about item, otherwise states that item
     * is not in inventory.
     */
    
    private void inspectItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Inspect what?");
            return;
        }
        String itemName = command.getSecondWord();
        //Try to inspect item
        Item item = player.getItemFromInventory(itemName);
        if ((item == null)) {
            System.out.println("This item is not in your inventory.");    
        }
        else {
            System.out.println("Item description: ");
            System.out.println();
            System.out.println(player.getItemFromInventory(itemName).showItemDescription());
        }
    }
    
    /**
     * Allows player to try and pick item that is currently in the room player is in,
     * and performs changes on player if item is picked up, otherwise states that item
     * cannot be picked up or is too heavy for player.
     */
    private void pickUpItem(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Pick up what?");
            return;
        }
        String itemName = command.getSecondWord();
        //Try to pick up item
        Item item = player.getCurrentRoom().getItem(itemName);
        if ((item == null)) {
            System.out.println("Can't pick up the item: " + (itemName));    
        }
        else if((player.getInventoryWeight() + item.getItemWeight()) > player.getMaxWeight()) {
            System.out.println("Picking up this item would exceed your maximum carrying capacity.");
        }
        else if(item.getItemName().equals("magicalwand")) {
            System.out.println("This item is in possesion of someone else.");
        }
        else {
            System.out.println("You have picked up: " + item.getItemName());
            player.addToInventory(item);
            player.getCurrentRoom().removeItem(item);
        }
    }
    
    
    /**
     * Allows player go back to previous room they was in,
     * and checks wether there was a previous room.
     */
    private void goPreviousRoom(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Just say back....");
            return;
        }
        if(player.getPreviousRoom() == null) { // checks if there is a previous room
            System.out.println("Come on " + player.getPlayerName() + ", theres nowehere you can go back from here.");
        }
        else {
            player.goToRoom(player.getPreviousRoom());
            System.out.println(player.getLongDescription());
        }
    }
    
    /**
     * Allows player to try and drop item that is currently in their inventory,
     * and performs changes on player if item is dropped, otherwise states that item
     * cannot be dropped.
     */
    private void dropItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        //Try to drop item
        boolean ItemInInventory = player.itemInInventory(itemName);
        Item item = player.getInventory().removeItemName(itemName);
        if ((item == null) || ((ItemInInventory == false))) {
            System.out.println("Can't drop " + (itemName) + ". " + "You do not carry this item!");    
        }
        else {
            System.out.println("You have dropped: " + item.getItemName());
            player.removeFromInventory(item);
            player.getCurrentRoom().addItem(item);
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Main method to run the game.
     */
    public static void main(String[] args)
    {
        Game game = new Game("Jon Snow");
        game.play();
    }
}
