package com.hangmanHardGame.com;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Random;
import java.util.Scanner;

public class Play {
//
//    public Play(){
//        guessThePhrase();
//    }


    public static void guessThePhrase(){

        int phraseLength = getNumberOfCharacters();
        final int MAXIMUM_GUESSES = phraseLength / 2;
        String phrase = generateSecretPhrase(phraseLength);
        boolean guessed = yourGuess(phrase, MAXIMUM_GUESSES);
        if(guessed)
        {
            System.out.println("\nYou've guessed the secret phrase");
        }
        else
        {
            System.out.println("\nYou've not guessed the secret phrase");
        }
    }

    public static int getNumberOfCharacters()
    {
        Scanner input = new Scanner(System.in);
        int numberOfCharacters = -1;

        do
        {
            System.out.print("Enter the number of characters in the secret phrase: \n" +
                                " PS: The number of attempts will be half of the length of the secret phrase.\n" +
                                    "  PS2: Type your answer in Upper Case only.\n" +
                                        "\n\t >> ");
            if(input.hasNextInt())
            {
                numberOfCharacters = input.nextInt();
            }
            else
            {
                input.nextLine();
                System.out.println("Invalid number");
            }
        } while(numberOfCharacters == -1);

        return numberOfCharacters;
    }

    public static String generateSecretPhrase(int lengthOfPhrase)
    {
        String phrase = "";
        char character = ' ';
        Random rand = new Random();

        for(int i = 0; i < lengthOfPhrase; i++)
        {
            character  = (char) (rand.nextInt(('Z' - 'A') + 1) + 'A');
            phrase += character;
        }

//		System.out.println(phrase);

        return phrase;
    }

    public static boolean yourGuess(String phrase, int maximumGuesses)
    {

        int maxG = maximumGuesses;
        Scanner input = new Scanner(System.in);
        final int LENGTH = phrase.length();
        int guessCounter = 1;
        String displayPhrase = "";
        char letter, guess;
        int position;

        for(int i = 0; i < LENGTH; i++)
        {
            displayPhrase += "_";
        }

        while(displayPhrase.indexOf('_') != -1 && guessCounter <= maximumGuesses)  // a maximum of phrase length/2 guesses
        {


            System.out.println("Lives remaining: " + maxG--);
            System.out.println(displayPhrase);
            System.out.print("\nGuess a letter >> ");
            guess = input.nextLine().charAt(0);
            ++guessCounter;
            for(position = 0; position < LENGTH; ++position)
            {
                letter = phrase.charAt(position);
                if(letter == guess)
                {
                    --guessCounter;		  // only incorrect guesses count against the maximum number of attempts
                    displayPhrase = displayPhrase.substring(0, position) +
                            guess + displayPhrase.substring(position + 1, LENGTH);

                }

            }

        }

        System.out.println();
        System.out.println("Your guess    >> " + displayPhrase);
        System.out.println("Secret phrase >> " + phrase);

        return phrase.equals(displayPhrase);
    }
}
