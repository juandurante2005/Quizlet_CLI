package Helpers;

import java.util.Scanner;
import java.util.TreeMap;
import java.io.*;
import static java.lang.System.*;
public class Creator {
    private String name;
    TreeMap<String,String> studySet;
    public Creator(String nm) throws IOException{
        name = nm;
        studySet  = new TreeMap<String,String>();
        promptTermDefs();
        promptCompletion();
    }
    void promptTermDefs(){
        Scanner scan = new Scanner(in);
        //future update- read term/defs until a certain macro?
        int counter = 1;
        String input = "";
        while(!input.equals("q!")){
            String t = askUser("Enter term " + counter + " (input \"q!\" to quit): ");
            clearScreen();
            if(t.equals("q!")) break;
            String d = askUser("Enter definition for term " + counter + ": ");
            input = askUser("Confirm? (y/n): ");
            if(input.equals("y")){
                studySet.put(t,d);
                counter++;
                clearScreen();
            }
            else if(input.equals("n")){
                out.println("Resetting...");
            }
            else{
                out.println("Invalid input, resetting...");
            }
            
        }
       // scan.close();

    }
    void promptCompletion() throws IOException{
        Scanner scan = new Scanner(System.in);
        out.println("Completed Set:\n");
        HelpfulMethods.printSet(studySet);
        String inside = askUser("Save? (y/n): ");
        clearScreen();
        if(inside.equals("y")){
           // Saver s = new Saver(studySet, name);
           HelpfulMethods.writeToFile(studySet, name);
           
            out.println("Saved! Returning to main menu.\n");
        }
        else
        {
            out.println("Not saving!");
        }
        

    }

    public String askUser(String question){
        Scanner scan = new Scanner(in);
        out.print(question);
        String response = scan.nextLine();
        out.println();
        clearScreen();
        return response;
    }
    private void clearScreen(){System.out.print("\033[H\033[2J");}
    
}
