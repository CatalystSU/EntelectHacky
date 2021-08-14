public class resource {
  private int id, numResources, quota;
  private char c;
  private int[][] clust;
  private boolean hasRes[];


  public resource(int id, int numResources) {
    this.id = id;
    c = (char) (id + 96);
    this.numResources = numResources;
  }

  public void setQuota(int q) {
    this.quota = q;
  }

  public int getId() {
    return this.id;
  }

  public int getResources() {
    return this.numResources;
  }

  /**
   * Getting the cluster for this resource
   *
   * @return 2D array of the cluster
   */
  public int[][] getCluser() {
    return clust;
  }

  /**
   * Changes the resources that have been picked up by the ship
   *
   * @param cluster - Which cluster the ship is at
   * @param taken   - The amount of resources the ship has taken.
   */
  public void setResources(int cluster, int taken) {
    clust[cluster][3] -= taken;
    if (clust[cluster][3] == 0) {
      hasRes[cluster] = false;
    }
  }

  /**
   * Seeing how many resources are available in the cluster
   * @param cluster  the cluster in question
   * @return the number of resources available
   */
  public int availableResources(int cluster) {
    return clust[cluster][3];
  }


  /**
   * sets the coordinates from the file for where the clusters are
   *
   * @param line The line of the coordinates of the clusters
   */
  public void setCoords(String line) {
    // assigning the clusters here
    clust = new int[numResources][4];
    hasRes = new boolean[numResources];
    line = line.substring(line.indexOf("|") + 1);
    for (int i = 0; i < numResources; i++) {
      hasRes[i] = true;
      line = line.substring(line.indexOf(",") + 1);
      clust[i][0] = Integer.parseInt(line.substring(0, line.indexOf(",")));
      line = line.substring(line.indexOf(",") + 1);
      clust[i][1] = Integer.parseInt(line.substring(0, line.indexOf(",")));
      line = line.substring(line.indexOf(",") + 1);
      clust[i][2] = Integer.parseInt(line.substring(0, line.indexOf(",")));
      line = line.substring(line.indexOf(",") + 1);
      if (i != numResources - 1)
        clust[i][3] = Integer.parseInt(line.substring(0, line.indexOf("|")));
      else clust[i][3] = Integer.parseInt(line);
    }
  }
}
