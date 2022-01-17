import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
  private resource[] uRes;
  private int ur, s, c, l, t, nq;

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
      ur = scLine.nextInt();
      s = scLine.nextInt();
      c = scLine.nextInt();
      l = scLine.nextInt();
      t = scLine.nextInt();
      nq = scLine.nextInt();

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
      for (int i = 0; i < ur; i++) {
        scFile.next();
        uRes[i].setQuota(scFile.nextInt());
      }

      for (int i = 0; i < ur; i++) {
        uRes[i].setCoords(scFile.next());
      }

      // Calling the brute force method
      //bruteForce();

      // nearest Neighbour attempt
      nearestNeighbour();

      // Closest insertion attempt


    } catch (FileNotFoundException e) {
      System.out.println("FIle not found!");
    }
  }

  // Nearest Neighbour Heuristic
  public void nearestNeighbour() {
    StringBuilder[] sb = new StringBuilder[s];
    int count = 0;
    boolean depleted = false;
    int softCap = 0;
    // Using a counter to check if there are any resources left and if there arent
    // come back to the depot
    int totalClust = 0;
    for (int i = 0; i < uRes.length; i++) {
      totalClust += uRes[i].getCluster().length;
    }

    for (int i = 0; i < sb.length; i++) {
      // Initializing the new ships cap and the String for that ship.
      sb[i] = new StringBuilder("");
      int tempCap = 0;

      // initializing the first nodes
      int currentNode[] = {0, 0, 0};

      int maxDistance = 0;
      int clust = 0, res = 0;

      // do this while the ship is not full
      while (tempCap < c && !depleted && softCap < t) {
        // get the one with the max distance first and then the shortest (so that the first one isn't the shortest the whole time
        for (int x = 0; x < uRes.length; x++) {
          for (int y = 0; y < uRes[x].getResources(); y++) {
            int tempDistance = dis(uRes[x].getCluster()[y][0], uRes[x].getCluster()[y][1], uRes[x].getCluster()[y][2], currentNode[0], currentNode[1], currentNode[2]);
            if (tempDistance > maxDistance) {
              maxDistance = tempDistance;
              clust = y;
              res = x;
            }
          }
        }
        int minDistance = maxDistance;
        count = 0;
        // Looping through all the resources to find the min distance for the next node;
        for (int x = 0; x < uRes.length; x++) {
          // Loop through the number of clusters there are.
          for (int y = 0; y < uRes[x].getResources(); y++) {
            // Find the min of the resources
            if (uRes[x].getCluster()[y][4] == 0 &&
                !(uRes[x].getCluster()[y][0] == currentNode[0] && uRes[x].getCluster()[y][1] == currentNode[1] && uRes[x].getCluster()[y][2] == currentNode[2])) {
              int tempDistance = dis(uRes[x].getCluster()[y][0], uRes[x].getCluster()[y][1], uRes[x].getCluster()[y][2], currentNode[0], currentNode[1], currentNode[2]);
              if (tempDistance < minDistance) {
                minDistance = tempDistance;
                clust = y;
                res = x;
              }
            } else if (uRes[x].getCluster()[y][4] != 0) {
              count++;
            }
          }
        }


        // Should have the position that the ship will be going to.
        sb[i].append(uRes[res].getC()).append(clust).append(",");

        // Pick up the resources
        if (count >= totalClust) {
          sb[i].append("0,");
          currentNode = new int[3];
          depleted = true;
        } else if ((c - tempCap) >= uRes[res].getCluster()[clust][3]) {
          // add all the resources available to the ship
          tempCap += uRes[res].getCluster()[clust][3];
          uRes[res].setResources(clust, uRes[res].getCluster()[clust][3]);
          currentNode = uRes[res].getCluster()[clust];
        } else {
          // Taking the capacity that the ship can take.
          uRes[res].setResources(clust, (c - tempCap));
          tempCap = c;
          currentNode = new int[3];
          sb[i].append("0,");
          break;
        }
      }
    }

    // Printing the solution
    for (int i = 0; i < s; i++) {
      // removing the comma at the end of the solution
      if (sb[i].length() > 0) {
        sb[i].setLength(sb[i].length() - 1);
      }
      System.out.println(sb[i].toString());
    }

  }

  public int dis(int x1, int y1, int z1, int x0, int y0, int z0) {
    int distance = (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0) + (z1 - z0) * (z1 - z0);
    distance = (int) Math.sqrt(distance);
    return distance;
  }

  // The brute force algo that we used in the comp
  public void bruteForce() {
    int temp_cap = 0;
    int temp_quota = 0;
    int soft_cap = t / s;
    boolean limit = false;

    // string array for each ship
    String[] paths = new String[s];
    for (int i = 0; i < s; i++) {
      paths[i] = "";
    }

    for (int x = 0; x < 50; x++) {
      for (int i = 0; i < s; i++) {
        temp_cap = 0;
        boolean tmp = true;

        // for each resource type loop
        for (int j = uRes.length - 1; j >= 0; j--) {
          // for each cluster loop
          if (j == 0) {
            tmp = false;
          } else if (uRes[j].getCompleted() && tmp) {
            j--;
          }

          for (int k = 0; k < uRes[j].getCluster().length; k++) {
            // 0 to 100
            // go to each cluster and attempt to collect said resource
            // at 5000 go back to the station
            if (uRes[j].availableResources(k) > 0) {
              if (temp_cap - uRes[j].getCluster()[k][3] < c) {
                // attempt to gather resources
                temp_cap += uRes[j].getCluster()[k][3];
                uRes[j].setResources(k, uRes[j].getCluster()[k][3]);
                paths[i] = paths[i].concat(uRes[j].getC() + "" + k + ",");
              } else {
                paths[i] = paths[i].concat("0,");
                //soft_cap += temp_cap;
                limit = true;
                break;
              }
            }

          }
          if (limit) {
            limit = false;
            break;
          }
        }
      }
    }
    for (int i = 0; i < s; i++) {
      if (!paths[i].equals("")) {

//          if (paths[i].charAt(paths[i].length() - 1) != '0') {
//            //paths[i] = paths[i].concat("0");
//          } else {
        paths[i] = paths[i].substring(0, paths[i].length() - 1);
        //  }
      }

      System.out.println(paths[i]);
    }
  }
}