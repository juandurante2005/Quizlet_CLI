package Helpers;

import java.util.Scanner;
import java.util.TreeMap;


import static java.lang.System.*;

import java.io.IOException;



public class Editor {
    private String name;
    private TreeMap<String,String> set = new TreeMap<String,String>();
    public Editor(String nm) throws IOException{
        name = nm;
        set = HelpfulMethods.getSet(name);
        editSet();
    }
    public void editSet() throws IOException{
        Scanner scan = new Scanner(in);
        out.println("Current Set: ");
        HelpfulMethods.printSet(set);

        String input = askUser("Enter term number to edit, or \"c\" to cancel: ");
        out.println();
        if(!input.equals("c")){
            int termNumber = -1;
            try{
                termNumber = Integer.parseInt(input);
                
            }
            catch (Exception e){
                out.println("Invalid input! Returning to menu.\n");
                
            }
            if(termNumber > -1){
                int counter = 1;
                String value = "";
                for(String valueToBeEdited : set.keySet()){
                    if(counter < termNumber) counter++;
                    else{
                        value = valueToBeEdited;
                    }
                }
                    input = askUser("Would you like to edit the term, definition, both, or delete the term? (t, d, td, D): ");
                    out.println();
                    if(!input.equals("t") && !input.equals("d") && !input.equals("td") && !input.equals("D")) out.println("Invalid input! Returning to menu.\n");
                    else{
                        String newTerm = value;
                        String newDef = set.get(value);
                        if(input.contains("t")){
                            newTerm = askUser("Enter new term: ");
                            out.println();
                            }
                        if(input.contains("d")){
                            newDef = askUser("Enter new definition: ");
                            out.println();
                            }
                        if(input.equals("D")){
                            set.remove(value, set.get(value));
                        }
                        input = askUser("Confirm? (y/n): ");
                        out.println();
                        set.remove(value);
                        set.put(newTerm, newDef);  
                        }
                        
                    
                }
            }
            input = askUser("Continue editing? (y/n): ");
            out.println();
            if(input.equals("y")) editSet();
            else{
                HelpfulMethods.writeToFile(set,name);
                out.println("Saved!"); 
            }
            
        }
        
        
    public String askUser(String question){
        Scanner scan = new Scanner(in);
        out.print(question);
        String response = scan.nextLine();
        out.println();
        return response;
    }
    }
