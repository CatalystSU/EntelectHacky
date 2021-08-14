public class resource {
  private int id, numResources, quota;
  private char c;
  private int[][] clust;


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

  public void setResources(int taken) {
    this.numResources -= taken;
  }

  public void setCoords(String line) {
    // assigning the clusters here
    clust = new int[numResources][4];
    line = line.substring(line.indexOf("|") + 1);
    for (int i = 0; i < numResources; i++) {
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
