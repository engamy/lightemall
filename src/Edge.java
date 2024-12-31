import java.util.Random;

// represents an edge
class Edge {
  GamePiece fromNode;
  GamePiece toNode;
  int weight;
  
  // for testing
  Edge(int weight, GamePiece fromNode, GamePiece toNode) {
    this.weight = weight;
    this.fromNode = fromNode;
    this.toNode = toNode;
  }
  
  // for randomized weight
  Edge(GamePiece fromNode, GamePiece toNode) {
    this.weight = randWeight();
    this.fromNode = fromNode;
    this.toNode = toNode;
  }
  
  // generates a random weight
  public int randWeight() {
    Random rand = new Random();
    return 1 + rand.nextInt(99); // random num from 1-99
  }
  
  // generates a random weight; duplicate method for testing
  public int randWeight(Random rand) {
    return 1 + rand.nextInt(99); // random num from 1-99
  }
  
  // checks if this edge repeats
  public boolean checkRepeat(GamePiece p1, GamePiece p2) {
    return ((this.toNode == p1) && (this.fromNode == p2));
  } 
}

