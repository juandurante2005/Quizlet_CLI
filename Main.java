import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Assets.*;

import static java.lang.System.*;
public class Main
{
    
    public static void main(String[] args) throws IOException{


    Scanner input = new Scanner(in);
    String response = "";
    while(!response.equals("q")){
    
        response = askUser("Would you like to open a text file, read, delete, edit, or create a set, list all sets, or quit? (o,r,d,e,c,l,q): ");
        if(response.equals("r")){
            printAvailables();
            new SetReader(askUser("Enter name of study set to read: "));
        }

        else if(response.equals("d")){
            printAvailables();
            HelpfulMethods.deleteSet(askUser("Enter name of study set to delete: "));
        }
        
        else if(response.equals("e")){
            printAvailables();
            new Editor(askUser("Enter name of study set to edit: "));
        }
        
        else if(response.equals("c")){
            new Creator(askUser("Enter name of new study set: "));
        }
        
        else if(response.equals("l")){
            ArrayList<String> list = HelpfulMethods.getSetList();
            for(String val : list){
                out.print(val + "\t\t");
            }
            out.println("\n");
        }
        else if(response.equals("q")){
            out.println("Adios!\n");
        }
        else if(response.equals("o"))
        {
            printAvailableFiles();
            new FileCopier(askUser("Enter the name of the file containing the study set: "));
        }
        else{
            out.println("Unknown Command!");
        }
    }
    input.close();
}
    private static void clearScreen(){System.out.print("\033[H\033[2J");}
    private static String askUser(String question){
        Scanner scan = new Scanner(in);
        out.print(question);
        String response = scan.nextLine();
        clearScreen();
        return response;
    }
    private static void printAvailables() throws IOException{
        out.println("Available sets:\n");
        ArrayList<String> list = HelpfulMethods.getSetList();
        for(String val : list){
            out.print(val + "\t\t");    
        }
        out.println("\n");

    }
    private static void printAvailableFiles() throws IOException{
        
    }
}