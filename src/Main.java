import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
  private resource[] uRes;

  public static void main(String[] args) {
    new Main(args[0]);
  }

  public Main(String fName) {
    // Reading in the file.
    try {
      Scanner scFile = new Scanner(new File(fName + ".txt"));
      String line = scFile.nextLine();
      System.out.println(line);
      Scanner scLine = new Scanner(line);

      int ur = scLine.nextInt();
      int s = scLine.nextInt();
      int c = scLine.nextInt();
      int l = scLine.nextInt();
      int t = scLine.nextInt();
      int nq = scLine.nextInt();

      System.out.println(scFile.nextLine());

    } catch (FileNotFoundException e) {
      System.out.println("FIle is not found!");
    }
  }

  // fName - filename
  public static void save(String fName, String out) {
    try {
      Files.writeString(Paths.get(fName + ".txt"), out, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.out.println("Could not save file.");
    }
  }
}
