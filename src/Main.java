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
        int id = Integer.parseInt(res.substring(0, res.indexOf(",")));
        int numRes = Integer.parseInt(res.substring(res.indexOf(",") + 1));
        uRes[i] = new resource(id, numRes);
      }
      scLine.close();

      // reading in the quota.
      for(int i = 0; i< ur; i++) {
        scFile.next();
        uRes[i].setQuota(scFile.nextInt());
      }

      for(int i = 0; i< ur; i ++) {
        uRes[i].setCoords(scFile.next());
      }
      int temp_cap = 0;
      int temp_quota = 0;
      for (int i = 0; i < s; i++) {
        temp_cap = 0;
        //while (temp_cap < c) {
        for (int j = uRes.length; j > 0; j--) {
          System.out.println(j);
          if (temp_cap >= c) {
            break;
          }
        }
        //}
      }

    } catch (FileNotFoundException e) {
      System.out.println("FIle is not found!");
    }
  }
}
