import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        ArrayList<String> wordList = new ArrayList<>();
        String filepath = "words.txt";

        //adding values in wordlist from words textfile
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line=reader.readLine()) != null){
                //split the words in each line separated by whitespaces (\\s+)
                String[] wordsInLine=line.split("\\s+");

                for (String word:wordsInLine){
                    //remove anything other than alphanumerics from each word
                    word=word.replaceAll("[^a-zA-Z0-9]","");

                    //if word still exists after punctuation removal, add it in wordList
                    if (!word.isEmpty()) {
                        wordList.add(word.toLowerCase());
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Invalid File Path. Please check if the File exists at the given path."+ e.getMessage());
        }
        catch(IOException f){
            System.out.println("Couldn't read file"+ f.getMessage());
        }
        
        String playAgain = "yes";

        while(playAgain.equals("yes")){
            //choose random word from list
            int index = random.nextInt(0,wordList.size()); 
            String mysteryword = wordList.get(index);
            
            //create n underscores array for word
            ArrayList<Character> guess = new ArrayList<>();
            for (int i=0; i<mysteryword.length(); i++){
                guess.add('_');
            }
            System.out.println(guess);

            int wrongGuessCounter = 0;
            while(wrongGuessCounter!=6){
                //ask user to guess a letter
                System.out.print("Guess a letter in the word: ");
                char userguess = scanner.nextLine().charAt(0);

                //fill the letter in underscored array if there
                boolean correctGuess = false;
                for (int i=0; i<guess.size(); i++){
                    if (mysteryword.charAt(i) == userguess){
                        guess.set(i,userguess);
                        correctGuess= true;
                    }   
                }
                System.out.println(guess);
               
                //increase wrong attempt counter if not there and display hangman
                if (!correctGuess){
                    System.out.println("Wrong Guess.");
                    wrongGuessCounter++;
                    switch(wrongGuessCounter){
                        case 1 ->{System.out.println(" ====|    ");  
                                  System.out.println(" |   O    ");
                                  System.out.println(" |        ");
                                  System.out.println(" |        ");
                                  System.out.println(" |        ");
                                  System.out.println(" L        ");}

                        case 2 ->{System.out.println(" ====|    ");  
                                  System.out.println(" |   O    ");
                                  System.out.println(" |   |    ");
                                  System.out.println(" |   |    ");
                                  System.out.println(" |        ");
                                  System.out.println(" L        ");}
                                
                        case 3 ->{System.out.println(" ====|    ");  
                                  System.out.println(" |   O    ");
                                  System.out.println(" |  /|    ");
                                  System.out.println(" |   |    ");
                                  System.out.println(" |        ");
                                  System.out.println(" L        ");}

                        case 4 ->{System.out.println(" ====|    ");  
                                  System.out.println(" |   O    ");
                                  System.out.println(" |  /|\\  ");
                                  System.out.println(" |   |    ");
                                  System.out.println(" |        ");
                                  System.out.println(" L        ");}

                        case 5 ->{System.out.println(" ====|    ");  
                                  System.out.println(" |   O    ");
                                  System.out.println(" |  /|\\  ");
                                  System.out.println(" |   |    ");
                                  System.out.println(" |  /     ");
                                  System.out.println(" L        ");}

                        case 6 ->{System.out.println(" ====|    ");  
                                  System.out.println(" |   O    ");
                                  System.out.println(" |  /|\\  ");
                                  System.out.println(" |   |    ");
                                  System.out.println(" |  / \\  ");
                                  System.out.println(" L        ");
                                  System.out.printf("You lost.\nThe word was %s.\n",mysteryword);
                                  break;}
                    }
                }
                 
                //if user guessed the whole word
                if (!guess.contains('_')){
                    System.out.printf("YOU WON!!!\nThe word was %s.\n",mysteryword);
                    break;
                }
            }
            
            //does the user want to play again
            do{System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().toLowerCase().trim();
            if (!playAgain.equals("yes") && !playAgain.equals("no")){
                System.out.println("Invalid input! Please enter yes/no: ");
            }} 
            while(!playAgain.equals("yes") && !playAgain.equals("no"));
        }
        System.out.println("Thanks for Playing!!!");
        scanner.close();

    }
}
    