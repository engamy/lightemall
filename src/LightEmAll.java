import java.util.ArrayList;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;
import java.util.HashMap;

// interface for lightemall
interface ILightEmAllWorld {
  int DEFAULT_WIDTH = 4;
  int DEFAULT_HEIGHT = 4;
  Color UNLIT = Color.LIGHT_GRAY;
  Color LITUP = Color.YELLOW;
  Color TEXT = Color.GREEN;
  int DEFAULT_TEXT = 20;
  int PIXEL_SIZE = 40;
  int WIREW = 10;
}

// lighteall world
class LightEmAllWorld extends World implements ILightEmAllWorld {
  ArrayList<ArrayList<GamePiece>> board; // a list of columns of GamePieces, (column-major order)
  ArrayList<GamePiece> nodes = new ArrayList<GamePiece>(); // a list of all nodes
  ArrayList<Edge> edges = new ArrayList<Edge>();
  ArrayList<Edge> mst = new ArrayList<Edge>(); // a list of edges of the minimum spanning tree
  int width; // width of the board
  int height; // height of the board
  // the current location of the power station,
  // as well as its effective radius
  int powerRow;
  int powerCol;
  int radius;
  
  // only takes in the width and height for big bang
  LightEmAllWorld(int width, int height) {
    this.width = width;
    this.height = height;
    this.powerRow = 0;
    this.powerCol = 0;
    this.board = this.initializeBoard();
    this.nodes = this.createNodeList();
    this.mst = kruskals();
    this.addPaths();
  }

  
  // takes in everything necessary for testing
  LightEmAllWorld(ArrayList<ArrayList<GamePiece>> board, int width, int height, 
      int powerRow, int powerCol) {
    this.board = board;
    this.width = width;
    this.height = height; 
    this.powerRow = powerRow;
    this.powerCol = powerCol;
  }
  
  // initialize the board
  public ArrayList<ArrayList<GamePiece>> initializeBoard() {
    ArrayList<ArrayList<GamePiece>> newBoard = new ArrayList<ArrayList<GamePiece>>();
    for (int i = 0; i < this.height; i ++) {
      ArrayList<GamePiece> row = new ArrayList<GamePiece>();
      for (int j = 0; j < this.width; j ++) {
        GamePiece g = new GamePiece(false, false, false, false, i, j, false, false);
        row.add(g);
      }
      newBoard.add(row);
    }
    return newBoard;
  }
  
  // handles the clicking to rotate of the GamePiece
  public void onMouseClicked(Posn pos, String button) {
    int x = pos.x / PIXEL_SIZE;
    int y = pos.y / PIXEL_SIZE;
    if (button.equals("LeftButton") && this.validClick(x, y)) {
      this.board.get(y).get(x).rotate();
      this.resetPower();
      this.electrocute();
    }
  }
  
  // lights up connected pieces
  public void electrocute() {
    GamePiece result = this.board.get(this.powerRow).get(this.powerCol);
    result.powerStation = true;
    result.powered = true;
    result.lightConnectedNeighbors(this.board);
  }
  
  // resets the powered field in every GamePiece
  public void resetPower() {
    for (ArrayList<GamePiece> row : this.board) {
      for (GamePiece col : row) {
        col.powered = false;
      }
    }
  }
  
  // determines if the click is on the board
  public boolean validClick(int x, int y) {
    return x >= 0 && y >= 0 && x < this.width && y < this.height;
  }
  
  // powerStation movement handler
  public void onKeyEvent(String key) {
    
    this.board.get(this.powerRow).get(this.powerCol).powerStation = false;
    
    GamePiece current = this.board.get(this.powerRow).get(this.powerCol);
    
    if (key.equals("up") && this.powerRow > 0) {
      GamePiece topP = this.board.get(this.powerRow - 1).get(this.powerCol);
      if (current.top && topP.bottom) {
        this.powerRow --;
      }
    }
    
    else if (key.equals("down") && this.powerRow < this.height - 1) {
      GamePiece bottomP = this.board.get(this.powerRow + 1).get(this.powerCol);
      if (current.bottom && bottomP.top) {
        this.powerRow ++;
      }
    }
    
    else if (key.equals("left") && this.powerCol > 0) {
      GamePiece leftP = this.board.get(this.powerRow).get(this.powerCol - 1);
      if (current.left && leftP.right) {
        this.powerCol --;
      }
    }
    
    else if (key.equals("right") && this.powerCol < this.width - 1) {
      GamePiece rightP = this.board.get(this.powerRow).get(this.powerCol + 1);
      if (current.right && rightP.left) {
        this.powerCol ++;
      }
    }
    
    this.resetPower();
    this.electrocute();
  }
  
  // draws the game board
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.width * PIXEL_SIZE, this.height * PIXEL_SIZE);
    
    for (ArrayList<GamePiece> row : this.board) {
      for (GamePiece col : row) {
        scene = col.draw(scene);
      }
    }
    
    return scene;
  }
  
  // ends the world
  public WorldEnd worldEnds() {
    if (this.isAllPowered()) {
      return new WorldEnd(true, this.youWon());
    }
    return new WorldEnd(false, this.makeScene());
  }
  
  // win scene generated by worldEnds()
  public WorldScene youWon() {
    WorldScene s = new WorldScene(this.width * PIXEL_SIZE, this.height * PIXEL_SIZE);
    s.placeImageXY(new TextImage("you won!", DEFAULT_TEXT, TEXT), 
        this.width * PIXEL_SIZE / 2, this.height * PIXEL_SIZE / 2);
    return s;
  }
  
  // checks if the whole board is lit
  public boolean isAllPowered() {
    for (ArrayList<GamePiece> row : this.board) {
      for (GamePiece col : row) {
        if (!col.powered) {
          return false;
        }
      }
    }
    return true;
  }
  
  // Creates unsorted list of all edges on the board
  public ArrayList<Edge> initEdges() {
    ArrayList<Edge> newEdges = new ArrayList<Edge>();
    ArrayList<GamePiece> currNrs = new ArrayList<GamePiece>();
    GamePiece currGP;

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        currGP = this.board.get(i).get(j);
        currNrs = currGP.neighbor(this.board);
        for (int k = 0; k < currNrs.size(); k++) {
          newEdges.add(new Edge(currGP, currNrs.get(k)));
          for (int l = 0; l < newEdges.size(); l++) {
            if (newEdges.get(l).checkRepeat(currGP, currNrs.get(k))) {
              newEdges.remove(l);
            }
          }
        }
      }
    }
    newEdges = this.sortWeight(newEdges);
    return newEdges;
  }
  
  // used to test initEdges, making sure the edge weights are not random
  public ArrayList<Edge> initEdgesTest() {
    ArrayList<Edge> newEdges = new ArrayList<Edge>();
    ArrayList<GamePiece> currNrs = new ArrayList<GamePiece>();
    GamePiece currGP;
    int weight = 0; // for testing purposes
    
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        currGP = this.board.get(i).get(j);
        currNrs = currGP.neighbor(this.board);
        for (int k = 0; k < currNrs.size(); k++) {
          newEdges.add(new Edge(weight, currGP, currNrs.get(k)));
          for (int l = 0; l < newEdges.size(); l++) {
            if (newEdges.get(l).checkRepeat(currGP, currNrs.get(k))) {
              newEdges.remove(l);
            }
          }
        }
      }
      weight++;
    }
    newEdges = this.sortWeight(newEdges);
    return newEdges;
  }
  
  // sorts the given list in ascending weight order
  public ArrayList<Edge> sortWeight(ArrayList<Edge> list) {
    
    int n = list.size();
    for (int i = 1; i < n; i++) {
      Edge key = list.get(i);
      int j = i - 1;
      
      while (j >= 0 && (list.get(j).weight > key.weight)) {
        list.set(j + 1, list.get(j));
        j = j - 1;
      }
      
      list.set(j + 1, key);
    }
    
    return list;
    
  }

  // adds all the gamepieces from the board into one arraylist
  public ArrayList<GamePiece> createNodeList() {
    ArrayList<GamePiece> list = new ArrayList<GamePiece>();

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        list.add(this.board.get(i).get(j));
      }
    }

    return list;
  }
  
  // kruskals algorithm, used to find mst
  // tested using duplicate method kruskalsTest()
  public ArrayList<Edge> kruskals() {
    HashMap<String, String> reps = new HashMap<String, String>();
    ArrayList<Edge> edgesInTree = new ArrayList<Edge>();
    ArrayList<Edge> worklist = initEdges(); //all edges in graph, sorted by edge weights;

    //initialize every node's representative to itself
    for (int h = 0; h < this.nodes.size(); h++) {
      reps.put(this.nodes.get(h).makeRep(), this.nodes.get(h).makeRep()); 
    }
    while (worklist.size() > 0) { //there's more than one tree
      // Pick the next cheapest edge of the graph: suppose it connects X and Y.
      Edge temp = worklist.get(0);

      if (temp.toNode.makeRep().equals(reps.get(temp.fromNode.makeRep())) 
          || temp.fromNode.makeRep().equals(reps.get(temp.toNode.makeRep()))) {
        worklist.remove(0);
      }

      else {
        if (edgesInTree.size() < this.nodes.size()) {
          edgesInTree.add(worklist.get(0));
        }
        reps.put(temp.toNode.makeRep(), temp.fromNode.makeRep());
      }
    }
    return edgesInTree;

  }
  
  
  // kruskals algorithm, used to find mst
  // duplicate method to eliminate randomness, used only for testing
  public ArrayList<Edge> kruskalsTest() {
    HashMap<String, String> reps = new HashMap<String, String>();
    ArrayList<Edge> edgesInTree = new ArrayList<Edge>();
    ArrayList<Edge> worklist = initEdgesTest(); //all edges in graph, sorted by edge weights;

    // initialize every node's representative to itself
    for (int h = 0; h < this.nodes.size(); h++) {
      reps.put(this.nodes.get(h).makeRep(), this.nodes.get(h).makeRep()); 
    }
    while (worklist.size() > 0) { //there's more than one tree
      // Pick the next cheapest edge of the graph: suppose it connects X and Y.
      Edge temp = worklist.get(0);

      if (temp.toNode.makeRep().equals(reps.get(temp.fromNode.makeRep())) 
          || temp.fromNode.makeRep().equals(reps.get(temp.toNode.makeRep()))) {
        worklist.remove(0);
      }

      else {
        if (edgesInTree.size() < this.nodes.size()) {
          edgesInTree.add(worklist.get(0));
        }
        reps.put(temp.toNode.makeRep(), temp.fromNode.makeRep());
      }
    }
    return edgesInTree;

  }
  
  // checks if all nodes have a path
  public boolean allEdge() {
    boolean result = true;

    for (GamePiece gp: this.nodes) {
      if (!gp.left && !gp.right && !gp.bottom && !gp.top) {
        gp.top = true;
        gp.left = true;
      }
    }
    return result;
  }
  
  // checks if every node is connected to another node
  public void addPaths() {
    for (Edge e: this.mst) {
      e.toNode.changePath(e.fromNode);
      e.fromNode.changePath(e.toNode);
    }

    if (!allEdge()) {
      this.board = this.initializeBoard();
      this.mst = this.kruskals();
      addPaths();
    }
  }
  
}