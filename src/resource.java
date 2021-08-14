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

  /**
   * Sets the quota for instantiating the amount of this resource needed
   *
   * @param q integer from the text file.
   */
  public void setQuota(int q) {
    this.quota = q;
  }

  /**
   * Gets the quota
   *
   * @return the quota
   */
  public int getQuota() {
    return this.quota;
  }

  /**
   * calculates the total number of resource that is required to be fetched.
   *
   * @return The number of resources required according to the quota
   */
  public int getTotalRequiredResources() {
    int total = 0;
    for (int i = 0; i < clust.length; i++) {
      total += clust[i][3];
    }
    return total * (quota / 100);
  }

  /**
   * Gets the ID of the cluster
   *
   * @return the id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Get the number of different clusters there are.
   *
   * @return number of clusters
   */
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
   *
   * @param cluster the cluster in question
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
