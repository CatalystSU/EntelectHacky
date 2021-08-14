public class resource {
  private int id, numResources, quota;


  public resource(int id, int numResources) {
    this.id = id;
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
}
