package Assets;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import static java.lang.System.*;

public class FileCopier {
    public FileCopier(String filename) throws IOException {
        File filee = new File("Assets\\Imported Study Sets\\" + filename);
        try{
            Scanner scan = new Scanner(filee);
        }
        catch (Exception e){
            out.println("Invalid file name!");
        }
        FileWriter file = new FileWriter("sets.txt", true);
        String textFile = makeTextString(filee);
        file.write(textFile);
        file.close();

    }
    private String makeTextString(File file) throws IOException{
        Scanner scan = new Scanner(file);
        String textString = "";
        while(scan.hasNext()) textString += scan.nextLine();
        return textString;
    }
}
