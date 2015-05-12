import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CaesarCipher {
  
  public static void main(String[] args) {
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
      System.out.print("Shift size: ");
      int shift = in.nextInt();
      shift %= 26;
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
              rep += shift;
              if (rep>90) {rep-=26;}
            }
            else {
              rep -= shift;
              if (rep<65) {rep+=26;}
            }
          }
          else if (rep>=97 && rep<=122) {
            if (encrypt) {
              rep += shift;
              if (rep>122) {rep-=26;}
            }
            else {
              rep -= shift;
              if (rep<97) {rep+=26;}
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