import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SimpleSubstitution {
  public static void main(String[] args) {
    List<Integer> sub = new ArrayList<Integer>();
    File file_in;
    Scanner scan;
    PrintWriter writer;
    
    try {
      Scanner in = new Scanner(System.in);
      String line = "";
      while (!(line.equals("e") || line.equals("d"))) {
        System.out.print("(E)ncrypt or (D)ecrypt: ");
        line = in.next().trim().substring(0,1).toLowerCase();
      }
      boolean encrypt = line.equals("e");
      do {
        System.out.print("Substitution Key: ");
        line = in.nextLine().toLowerCase();
        Set<String> test = new HashSet<String>(Arrays.asList(line.split("\\s+")));
        if (test.size() != 26) {line="";}
      } while (line.length()==0);
      for (String letter : line.split("\\s+")) {
        sub.add(new Integer((int)letter.charAt(0)));
      }
      do {
        System.out.printf("File to %s: ",(encrypt?"encrypt":"decrypt"));
        line = in.next();
        file_in = new File(line);
      } while(!file_in.exists());
      scan = new Scanner(file_in);
      System.out.print("Output file name: ");
      writer = new PrintWriter(new File(in.next().trim()));
      in.close();
      while (scan.hasNextLine()) {
        line = scan.nextLine();
        for (int ind=0; ind<line.length(); ind++) {
          int rep = (int)line.charAt(ind);
          if (rep>=65 && rep<=90) {
            if (encrypt) {
              rep -= 65;
              rep = sub.get(rep).intValue();
              rep -= 32;
            }
            else {
              rep += 32;
              rep = sub.indexOf(new Integer(rep));
              rep+=65;
            }
          }
          else if (rep>=97 && rep<=122) {
            if (encrypt) {
              rep -= 97;
              rep = sub.get(rep).intValue();
            }
            else {
              rep = sub.indexOf(new Integer(rep));
              rep += 97;
            }
          }
          writer.print((char)rep);
        }
        if (scan.hasNextLine()) {writer.println();}
      }
      scan.close();
      writer.close();
    }
    catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    }
  }
}

