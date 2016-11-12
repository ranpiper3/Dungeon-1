import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The state of this specified dungeon game.
 * 
 * @author Maas Lalani
 * @version 1.0 2016-11-11
 */
public class State
{
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Saves the state of this player to a text file.
     * 
     * @param player the player whose state should be saved
     */
    public static void saveState(Player player)
    {
        /* Check if the user has a name, if not get the name */
        if (player.getName().equals(""))
        {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            player.setName(name);
        }

        try
        {
            File userData = new File("users/" + player.getName() + ".txt");
            PrintWriter writer = new PrintWriter(userData);

            /* Save player data to file. */
            writer.println(player.getData());

            writer.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File could not be written. Data was not saved.");
        } // end of catch (FileNotFoundException exception)
    }

    /**
     * Loads the state of a player from a text file and updates the player using those data.
     */
    public static void loadState(Player player) throws FileNotFoundException, IOException
    {
        /* Order of the data is [name, hasSword, hasArmour, enemiesKilled, health, numberOfPotions] */

        BufferedReader reader = new BufferedReader(new FileReader("users/" + player.getName() + ".txt"));

        /* Load saved player data */
        String[] data = reader.readLine().split(" ");

        /* Extract the data into local variables from the string array */
        String name = data[0];
        boolean hasSword = Boolean.parseBoolean(data[1]);
        boolean hasArmour = Boolean.parseBoolean(data[2]);
        int enemiesKilled = Integer.parseInt(data[3]);
        int health = Integer.parseInt(data[4]);
        int numberOfPotions = Integer.parseInt(data[5]);

        /* Set the name of this player. */
        player.setName(name);

        /* Add a wood sword if the player had a sword. */
        if (hasSword) player.addSword("wood");

        /* Add leather armour if the player had armour. */
        if (hasArmour) player.addArmour("leather");

        /* Set the score of this player in terms of enemies killed. */
        player.setEnemiesKilled(enemiesKilled);

        /* Set the health points of this player based on the data. */
        player.setHealth(health);

        /* Set the number of potions of this player. */
        player.setNumberOfPotions(numberOfPotions);
    } // end of method saveState(State state)
} // end of class State