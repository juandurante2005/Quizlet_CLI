package Assets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class HelpfulMethods{
    private static File file = new File( "sets.txt");
    public static void writeToFile(TreeMap<String,String> set, String name) throws IOException{
        String textFile = HelpfulMethods.makeTextString();
        
        
        HelpfulMethods.clearFile();
        FileWriter writer = new FileWriter(file, true);
        writer.write("");
        if(!textFile.contains("<~!n!~>" + name +"<~!N!~>")){
            writer.append(textFile);
            HelpfulMethods.addChunk(set, name);
         //   writer.close();
        }
        else{
            int index = textFile.indexOf("<~!n!~>" +name+ "<~!N!~>");
            out.println("Index is: " + index);
            
            textFile = textFile.substring(0,index) + (textFile.indexOf("<~!n!~>",index + 14 + name.length()) > 0 ? textFile.substring(index + 14 + name.length()) : "");
            writer.append(textFile);
            addChunk(set, name);
        
        }
        writer.append("\n");
        writer.close();

        
    }
    public static void deleteSet(String name) throws IOException{
        String textFile = HelpfulMethods.makeTextString();
        HelpfulMethods.clearFile();
        FileWriter writer = new FileWriter(file, true);
        if(!textFile.contains("<~!n!~>" + name + "<~!N!~>")) {
            out.println("Sorry, file not found!");
            writer.write(textFile);
            writer.close();
    }
        else
        {
            int index = textFile.indexOf("<~!n!~>" +name+ "<~!N!~>");
            textFile = textFile.substring(0,index) + (textFile.indexOf("<~!n!~>",index + 14 + name.length()) > 0 ? textFile.substring(textFile.indexOf("<~!n!~>",index + 14 + name.length())) : "");
            writer.append(textFile);
        }
        writer.close();

    }

    private static void clearFile()
{ 
    try{
        
    FileWriter fw = new FileWriter(file, false);
    PrintWriter pw = new PrintWriter(fw, false);
    pw.flush();
    pw.close();
    fw.close();
    }catch(Exception exception){
        System.out.println("Error occured!");
    }

}

    private static void addChunk(TreeMap<String, String> set, String name) throws IOException{
        
        FileWriter writer = new FileWriter(file, true);
        writer.append("<~!n!~>" + name + "<~!N!~>");
            for(String val : set.keySet()){
                writer.append("<~!t!~>" + val +"<~!T!~>");
                writer.append("<~!d!~>" + set.get(val) + "<~!D!~>");

            }
        writer.close();
    }
    public static void printSet(TreeMap<String,String> studySet){
        int counter = 1;
        for(String val : studySet.keySet()){
            out.println("Term " + counter +": " + val);
            out.println("Definition "+ counter + ": " + studySet.get(val));
            out.println();
            counter++;
        }
    }

    public static String makeTextString() throws IOException{
        Scanner scan = new Scanner(file);
        String textString = "";
        while(scan.hasNext()) textString += scan.nextLine();
        return textString;
    }
    public static TreeMap<String,String> getSet(String name) throws IOException{
        TreeMap<String,String> set = new TreeMap<>();
        String text = HelpfulMethods.makeTextString();

        if(!text.contains("<~!n!~>" + name + "<~!N!~>")) return null;
        else
        {
            int indice = text.indexOf("<~!n!~>", text.indexOf("<~!n!~>" + name + "<~!N!~>") + 14 + name.length());
            String setOnly = text.substring(text.indexOf("<~!n!~>" + name + "<~!N!~>") + 14 + name.length(), indice >= 0? indice: text.length());
            Scanner scan = new Scanner(setOnly);
            scan.useDelimiter("(<~!n!~>)|(<~!N!~>)|(<~!t!~>)|(<~!T!~>)|(<~!d!~>)|(<~!D!~>)");
            ArrayList<String> toBeAdded = new ArrayList<>();
            while(scan.hasNext()){ 
                toBeAdded.add(scan.next());
                }
            for(int x = toBeAdded.size() - 2; x > 0; x -=  2) toBeAdded.remove(x);
            for(int x = 0; x < toBeAdded.size(); x+= 2){
                set.put(toBeAdded.get(x), toBeAdded.get(x + 1));
            }
        }
        return set;
    }
    public static ArrayList<String> getSetList() throws IOException{
        String textString = makeTextString();
        ArrayList<String> list = new ArrayList<>();
        
        while(textString.contains("<~!n!~>")){
            int openingInequalitySign = textString.indexOf("<~!n!~>");
            int closingInequalitySign = textString.indexOf("<~!N!~>");
            list.add(textString.substring(openingInequalitySign + 7, closingInequalitySign));
            textString = textString.substring(closingInequalitySign + 7);

        }
        list.sort(String::compareToIgnoreCase);

        return list;

    }
}
