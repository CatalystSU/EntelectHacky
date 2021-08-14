import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
  private resource[] uRes;
  private String fName;

  public static void main(String[] args) {
    new Main(args[0]);
  }

  public Main(String fName) {
    this.fName = fName;
    // Reading in the file.
    try {
      Scanner scFile = new Scanner(new File(fName + ".txt"));
      String line = scFile.nextLine();
      Scanner scLine = new Scanner(line);

      // Reading in the first line
      int ur = scLine.nextInt();
      int s = scLine.nextInt();
      int c = scLine.nextInt();
      int l = scLine.nextInt();
      int t = scLine.nextInt();
      int nq = scLine.nextInt();
      scLine.close();

      // reading in the second line
      uRes = new resource[ur];
      line = scFile.nextLine();
      scLine = new Scanner(line);
      for (int i = 0; i < ur; i++) {
        String res = scLine.next();
        System.out.println(res + " resource");
      }
      System.out.println(scLine.next());


    } catch (FileNotFoundException e) {
      System.out.println("FIle is not found!");
    }
  }

  // fName - filename
  public void save(String out) {
    try {
      Files.writeString(Paths.get(fName + "Out.txt"), out, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.out.println("Could not save file.");
    }
  }
}
