public class resource {
  private int id, numResources;

  public resource(int id, int numResources) {
    this.id = id;
    this.numResources = numResources;
  }

  public int getId() {
    return this.id;
  }

  public int getResources(){
    return this.numResources;
  }
}
