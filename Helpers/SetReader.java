package Helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import static java.lang.System.*;

public class SetReader {
    TreeMap<String,String> set;
    public SetReader(String name) throws IOException{
        set = HelpfulMethods.getSet(name);
        if(set == null) out.println("Invalid Set Name!");
        else{
            promptActivity();
        }
    }
    public SetReader(TreeMap<String,String> seT){
        set = seT;        
        promptActivity();
    }
    private void promptActivity(){
        Scanner scan = new Scanner(in);
        String toBePlayed = askUser("Would you like to print the set, learn, test, open flash cards, or write? (p,l,t,f,w): ");
        switch (toBePlayed){
            case "p":
                out.println("Current set:");
                HelpfulMethods.printSet(set);
                askUser("Hit Enter to continue.");
                break;
            case "l":
                learn();
                break;
            case "t":
                test();
                break;
            case "f":
                flashCards();
                break;
            case "w":
                write();
                break;
            default:
                out.println("Error- invalid input!");
                break;
            
        }
    }
    private void learn(){
        ArrayList<ValuePair> unlearned = new ArrayList<>();
        ArrayList<ValuePair> reference = new ArrayList<>();
        ArrayList<ValuePair> halfway = new ArrayList<>();
   
        for(String val : set.keySet()){
            unlearned.add(new ValuePair(val, set.get(val)));
            reference.add(new ValuePair(val, set.get(val)));
            
        }
        while(unlearned.size() > 0 || halfway.size() > 0){
            wipeScreen();
            int totalSize = unlearned.size() + halfway.size();

            int chosenSet = (int)(Math.random() * totalSize);
            if(chosenSet < unlearned.size()){
                int randomIndex = (int)(Math.random() * unlearned.size());
                out.println(unlearned.get(randomIndex).getDefinition());

                //eventually- check dictionary for synonyms?
                ArrayList<Integer> randomValues = new ArrayList<Integer>();
                randomValues.add(randomIndex);
                int correctAnswer = (int)(Math.random() * Math.min(5, reference.size()));
                for(int x = 0; x < 5 && x < set.keySet().size(); x++){
                    if(x != correctAnswer){
                    int answerChoice = (int)(Math.random() * reference.size());
                    if(!randomValues.contains(answerChoice)){
                    out.println((char)(x + 97) + ") " + reference.get(answerChoice).getTerm());
                    randomValues.add(answerChoice);
                    }
                    else
                    {
                        x--;
                        answerChoice = (int)(Math.random() * reference.size());
                    }
                    }
                    else{
                        out.println((char)(x + 97) + ") " + unlearned.get(randomIndex).getTerm());
                    }

                }
                
                String response = askUser("Enter term here (not answer choice letter): ");
                if(response.equals(unlearned.get(randomIndex).getTerm())){
                    out.println("Correct!");
                    halfway.add(unlearned.remove(randomIndex));
                }
                else
                    out.println("Incorrect reponse, adding word back to list");
            }
            else{
                int randomIndex = (int)(Math.random() * halfway.size());
                out.println(halfway.get(randomIndex).getDefinition());
                String response = askUser("Enter term here: ");
                if(response.equals(halfway.get(randomIndex).getTerm())){
                    out.println("Correct!");
                    halfway.remove(randomIndex);

                }
                else
                    out.println("Incorrect reponse, adding word back to list");

            }
            
        }
        out.println("All done!");
    }
    private void wipeScreen(){
        for(int x = 0; x < 50; x++) out.println("\n");
    }
    private void write(){
        ArrayList<ValuePair> reference = new ArrayList<>();
        for(String val : set.keySet())
            reference.add(new ValuePair(val, set.get(val)));
        while(reference.size() > 0){
            wipeScreen();
            int randomIndex = (int)(Math.random() * reference.size());
            out.println(reference.get(randomIndex).getDefinition());
            String response = askUser("Enter term here: ");
            if(response.equals(reference.get(randomIndex).getTerm())){
                out.println("Correct!");
                reference.remove(randomIndex);
            }
            else
                out.println("Incorrect reponse, adding word back to list");
        
    }
        out.println("Great job! That's all the words!");
    }
    private void flashCards(){
        Scanner scan = new Scanner(in);
        ArrayList<ValuePair> reference = new ArrayList<>();
        for(String val : set.keySet())
            reference.add(new ValuePair(val, set.get(val)));
            while(reference.size() > 0){
                wipeScreen();
                int randomIndex = (int)(Math.random() * reference.size());
                out.println("Definition (hit Enter to see term): " + reference.get(randomIndex).getDefinition());
                scan.nextLine();
                out.println("The term is: " + reference.get(randomIndex).getTerm());
                
                String input = askUser("Input \"r\" to put card back into the deck, or hit Enter to continue: ");
                if(!input.equals("r"))
                    reference.remove(randomIndex);
                

            }
        out.println("That's all the flash cards!");
    }
    private void test(){
        
    }
    public String askUser(String question){
        Scanner scan = new Scanner(in);
        out.print(question);
        String response = scan.nextLine();
        out.println();
        wipeScreen();
        return response;
    }
}

class ValuePair{
    String term;
    String definition;
    public ValuePair(String t, String d){
        term = t;
        definition = d;
    }
    public String getTerm() {return term;}
    public String getDefinition() {return definition;}
}
