import java.io.FileNotFoundException;
import java.util.*;

import javax.management.InstanceNotFoundException;

public class test {
  static Dictionary dict = new Dictionary();

  public static void main(String[] args) throws InstanceNotFoundException, FileNotFoundException
      {
          start();
      }

  public static void start() throws InstanceNotFoundException, FileNotFoundException {

    Scanner sc = new Scanner(System.in);
    System.out.println("Please choose an option (f, i, l, p, r, s, or x):");
    String input = sc.nextLine();
    dict.run(input);
          start();
      }
}