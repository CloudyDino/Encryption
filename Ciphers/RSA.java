import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
  
  public static void main(String[] args) throws FileNotFoundException, IOException{
    File file_in;
    BufferedReader scan;
    PrintWriter writer;
    Scanner in = new Scanner(System.in);
    String line = "";
    while (!(line.equals("c") || line.equals("e") || line.equals("d"))) {
      System.out.print("(C)reate or (E)ncrypt or (D)ecrypt: ");
      line = in.next().trim().substring(0,1).toLowerCase();
    }
    char goal = line.charAt(0);
    BigInteger e = BigInteger.ONE;
    BigInteger d = BigInteger.ONE;
    BigInteger n = BigInteger.ONE;
    if (goal == 'c') {
      BigInteger p,q;
      System.out.print("Prime 1: ");
      p = new BigInteger(in.next());
      System.out.print("Prime 2: ");
      q = new BigInteger(in.next());
      n = p.multiply(q);
      BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
      e = p.or(q).nextProbablePrime();
      do {
        e = e.nextProbablePrime();
        while (e.compareTo(phi_n)>0) {
          int bl = phi_n.bitLength()-(int)(Math.random()*phi_n.bitLength())+1;
          e = BigInteger.probablePrime(bl, new Random());
        }
      } while (phi_n.gcd(e).compareTo(BigInteger.ONE)!=0);
      d = e.modInverse(phi_n);
      System.out.printf("------------%n%nPublic exponent: %s%nPublic modulus: %s%nPrivate exponent: %s%n",e.toString(),n.toString(),d.toString());
      System.exit(0);
    }
    else if (goal == 'e') {
      System.out.print("Public exponent: ");
      e = new BigInteger(in.next());
      System.out.print("Public modulus: ");
      n = new BigInteger(in.next());
    }
    else {
      System.out.print("Public modulus: ");
      n = new BigInteger(in.next());
      System.out.print("Private exponent: ");
      d = new BigInteger(in.next());
    }
    do {
      System.out.printf("File to %s: ",(goal=='e'?"encrypt":"decrypt"));
      line = in.next();
      file_in = new File(line);
    } while(!file_in.exists());
    scan = new BufferedReader(new FileReader(file_in));
    System.out.print("Output file name: ");
    writer = new PrintWriter(new File(in.next().trim()));
    in.close();
    while ((line = scan.readLine())!=null) {
      if (goal=='e') {
        for (int ind=0; ind<line.length(); ind++) {
          BigInteger rep = new BigInteger(""+(int)line.charAt(ind));
          rep = rep.modPow(e, n);
          writer.print(rep.toString() + " ");
        }
      }
      else {
        while (line.indexOf(" ")!=-1) {
          BigInteger rep = new BigInteger(line.substring(0, line.indexOf(" ")));
          line = line.substring(line.indexOf(" ")+1);
          rep = rep.modPow(d, n);
          writer.print((char)rep.intValue());
        }
      }
      writer.println();
    }
    scan.close();
    writer.close();
  }
}
