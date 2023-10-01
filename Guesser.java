/**
 * Guesser
 */

import java.util.Random;
import java.util.Scanner;

public class Guesser {

    public static Scanner scan = new Scanner(System.in);

    public static boolean testNumericInput(String input){
        // returns true if input is a number or false if it is not//

        try{
                
                Integer.parseInt(input);
                return true;
        }
        catch(NumberFormatException NFE){
                System.out.println("please input a valid integer\n");
                return false;
        }

    }

    public static int userInput(int start, int range, String color){
        // Recieves input from User//

        String input;
        int guess = Integer.MIN_VALUE;

        while(guess < start || guess > range){

            System.out.println("What is your "  + color + "guess? ("  + start + " - "  + range + ")\n");
            input = scan.nextLine();

            if(testNumericInput(input)){

                guess = Integer.parseInt(input);

                if(guess < start || guess > range){
                    System.out.println("please input an integer between "  + start + " and " + range + ")\n");
                }
            }
        }
        
        return guess;
    }

    public static int userHexInput(){
        // Recieves input from User//

        String input;
        int guess = Integer.MIN_VALUE;

        while(guess < 0 || guess > 255){

            System.out.println("What is your hex guess? (0 - ff)");
            input = scan.nextLine();
            System.out.println("\n");
            
            if(input.length() <= 2){
                guess = convertHex(input);
            }
            if(guess < 0){
                System.out.println("please input an integer between 0 and ff)\n");
            }
            
        }
        
        return guess;
    }

    public static int convertCharHex(char hex){
        //returns the hex value of a single char in decimal//

        String hexChars = "0123456789abcdef";
        if(hex >= 65 && hex <= 90){
            hex += 32;
        }

        return (hexChars.indexOf(hex));
    }

    public static int convertHex(String hex){
        int decimal = 0;
        int counter = 0;

        for(int i = hex.length() - 1; i >= 0; i--){

            int hexChar = convertCharHex(hex.charAt(i));
            if(hexChar == -1){
                //if they input an invalid character in the hex string return -1
                return -1;
            }

            hexChar *= Math.pow(16, counter);

            decimal += hexChar;
            
            counter++;
        }

        return decimal;
    }

    public static void standardGame(){
    // Guesser randomly generates a number and the user has to guess that number//
    // while the game states whether the number is higher or lower//

        Random rand = new Random();
        int range = 100;
        int secret = rand.nextInt(range) + 1; // sets a random int between 0 and range - 1
        int guess = Integer.MIN_VALUE; 
        int numGuesses = 0;

        while(guess != secret){

            numGuesses += 1;
            guess = userInput(1, range, "");
            
            if(guess < secret){
                System.out.println("Guess Higher!\n");
            }
            else if(guess > secret){
                System.out.println("Guess Lower!\n");
            }
        }
        
        System.out.println("It took you " + numGuesses + " guesses!\n");

    }

     public static void hexGame(){
    // Guesser randomly generates a number and the user has to guess that number//
    // while the game states whether the number is higher or lower//

        Random rand = new Random();
        int range = 256;
        int secret = rand.nextInt(range); // sets a random int between 0 and range - 1
        int guess = Integer.MIN_VALUE; 
        int numGuesses = 0;

        while(guess != secret){

            numGuesses += 1;
            guess = userHexInput();
            
            if(guess < secret){
                System.out.println("Guess Higher!\n");
            }
            else if(guess > secret){
                System.out.println("Guess Lower!\n");
            }
        }
        
        System.out.println("It took you " + numGuesses + " guesses!\n");

    }

    public static void colorGame(){
        Random rand = new Random();
        int range = 256;
        int[] secrets = {rand.nextInt(range), rand.nextInt(range), rand.nextInt(range)}; // sets 3 random ints between 0 and 255
        int[] guesses = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}; 
        String[] colors = {"Red", "Green" , "Blue"};
        String response = ""; 
        int numGuesses = 0;
        range -= 1; //range decreasing so that displayed range is in proper bounds

        System.out.println("Red " + secrets[0] + " Green " + secrets[1] + " Blue " + secrets[2]);

        while(guesses[0] != secrets[0] || guesses[1] != secrets[1] || guesses[2] != secrets[2]){

            numGuesses += 1;

            for(int i = 0; i < 3; i++){

                if(!colors[i].equals("")){
                    guesses[i] = userInput(0, range, colors[i] + " ");

                    if(guesses[i] < secrets[i]){
                        response +=  "we need more " + colors[i] + "\n";
                    }
                    else if(guesses[i] > secrets[i]){
                        response += "we need less " + colors[i] + "\n";
                    }
                    else{
                        response += "we have enough " + colors[i] + "\n";
                        colors[i] = "";
                    }
                }
                
            }
            
            System.out.println(response);
            response = "";
            
        }

        System.out.println("It took you " + numGuesses + " guesses!\n");
        System.out.println("The color was [" + guesses[0] + ", "+ guesses[1] + ", "+ guesses[2] +"]\n");
    }

    public static void main(String[] args){
        
        String gameType = "";
        boolean isGameOver = false;

        while(!isGameOver){
            System.out.println("What game version would you like to play?");
            System.out.println("Standard, Color, or Hex Guesser? (s, c, or h)");
            gameType = scan.nextLine().toLowerCase();


            if(gameType.equals("s")){
                standardGame();
            }
            else if(gameType.equals("c")){
                colorGame();
            }
            else if(gameType.equals("h")){
                hexGame();
            }

            String keepPlaying = "";
            while(keepPlaying.equals("")){
                System.out.println("Would you like to continue playing? (y/n) ");
                keepPlaying = scan.nextLine().toLowerCase();
                if(keepPlaying.equals("y")){
                    isGameOver = false;
                }
                else if (keepPlaying.equals("n")){
                    isGameOver = true;
                }
                else{
                    keepPlaying = "";
                    System.out.println("Please use the proper lettering: (y/n) \n");
                }
            }
            
        }
        


    }
}