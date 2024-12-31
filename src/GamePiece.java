import java.awt.Color;
import java.util.ArrayList;
import javalib.impworld.WorldScene;
import javalib.worldimages.*;

// represents a single game piece
class GamePiece implements ILightEmAllWorld {
  int row;
  int col;
  boolean left;
  boolean right;
  boolean top;
  boolean bottom;
  boolean powerStation; 
  boolean powered; 
  
  // constructor for testing
  GamePiece(boolean left, boolean right, boolean top, boolean bottom,
      int row, int col, boolean powerStation, boolean powered) {
    this.row = row;
    this.col = col;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.powerStation = powerStation;
    this.powered = powered;
  }
  
  // 'rotates' the GamePiece
  void rotate() {
    boolean tempLeft = this.left;
    boolean tempRight = this.right;
    boolean tempTop = this.top;
    boolean tempBottom = this.bottom;
    this.left = tempBottom;
    this.right = tempTop;
    this.top = tempLeft;
    this.bottom = tempRight;
    
  }
  
  // lights up connected Neighbors
  public void lightConnectedNeighbors(ArrayList<ArrayList<GamePiece>> board) {
    
    // when there is a top wire
    if (this.top && (this.row - 1) >= 0) {
      GamePiece top = board.get(this.row - 1).get(this.col);
      
      if (!top.powered && top.bottom && this.powered) {
        top.powered = true;
        top.lightConnectedNeighbors(board);
      }
    }
    
    // when there is a bottom wire
    if (this.bottom && (this.row + 1) < board.size()) {
      GamePiece bottom = board.get(this.row + 1).get(this.col);
      
      if (!bottom.powered && bottom.top && this.powered) {
        bottom.powered = true;
        bottom.lightConnectedNeighbors(board);
      }
    }
    
    // when there is a left wire
    if (this.left && (this.col - 1) >= 0) {
      GamePiece left = board.get(this.row).get(this.col - 1);
      left.powered = true;
      
      if (!left.powered && left.right && this.powered) {
        left.powered = true;
        left.lightConnectedNeighbors(board);
      }
      
      left.lightConnectedNeighbors(board);
    }
    
    // when there is a right wire
    if (this.right && (this.col + 1) < board.get(0).size()) {
      GamePiece right = board.get(this.row).get(this.col + 1);
      
      if (!right.powered && right.left && this.powered) {
        right.powered = true;
        right.lightConnectedNeighbors(board);
      }
    }
  }
  
  // finds all the neighbors of a GamePiece given a board
  public ArrayList<GamePiece> neighbor(ArrayList<ArrayList<GamePiece>> board) {
    ArrayList<GamePiece> neighbor = new ArrayList<GamePiece>();
    
    if (this.col - 1 >= 0) {
      neighbor.add(board.get(this.row).get(this.col - 1));
    }    
    
    if (this.col + 1 < board.get(0).size()) {
      neighbor.add(board.get(this.row).get(this.col + 1));
    }
    
    if (this.row - 1 >= 0) {
      neighbor.add(board.get(this.row - 1).get(this.col));
    }
    
    if (this.row + 1 < board.size()) {      
      neighbor.add(board.get(this.row + 1).get(this.col));
    }
   
    return neighbor;
  }
  
  
  // calls the tileImage method
  WorldScene draw(WorldScene scene) {
    if (this.powered) {
      scene.placeImageXY(this.tileImage(PIXEL_SIZE, WIREW, LITUP, this.powerStation), 
          this.col * PIXEL_SIZE + PIXEL_SIZE / 2, this.row * PIXEL_SIZE + PIXEL_SIZE / 2);
      return scene;
    }
    scene.placeImageXY(this.tileImage(PIXEL_SIZE,  WIREW, UNLIT, this.powerStation),
        this.col * PIXEL_SIZE + PIXEL_SIZE / 2, this.row * PIXEL_SIZE + PIXEL_SIZE / 2);
    return scene;
  }
  
  // Generate an image of this, the given GamePiece.
  // - size: the size of the tile, in pixels
  // - wireWidth: the width of wires, in pixels
  // - wireColor: the Color to use for rendering wires on this
  // - hasPowerStation: if true, draws a fancy star on this tile to represent the power station
  WorldImage tileImage(int size, int wireWidth, Color wireColor, boolean hasPowerStation) {
    // Start tile image off as a blue square with a wire-width square in the middle,
    // to make image "cleaner" (will look strange if tile has no wire, but that can't be)
    WorldImage image = new OverlayImage(
        new RectangleImage(wireWidth, wireWidth, OutlineMode.SOLID, wireColor),
        new RectangleImage(size, size, OutlineMode.SOLID, Color.DARK_GRAY));
    WorldImage vWire = new RectangleImage(wireWidth, (size + 1) / 2, OutlineMode.SOLID, wireColor);
    WorldImage hWire = new RectangleImage((size + 1) / 2, wireWidth, OutlineMode.SOLID, wireColor);
 
    if (this.top) {
      image = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP, vWire, 0, 0, image);
    }
    if (this.right) {
      image = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE, hWire, 0, 0, image);
    }
    if (this.bottom) {
      image = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM, vWire, 0, 0, image);
    }
    if (this.left) {
      image = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE, hWire, 0, 0, image);
    }
    if (hasPowerStation) {
      image = new OverlayImage(
                  new OverlayImage(
                      new StarImage(size / 3, 7, OutlineMode.OUTLINE, new Color(255, 128, 0)),
                      new StarImage(size / 3, 7, OutlineMode.SOLID, new Color(0, 255, 255))),
                  image);
    }
    return image;
  }
  
  // Creates a string representing the nodes' coords
  public String makeRep() {
    return this.row + ", " + this.col;
  }
  
  // changes the path
  public void changePath(GamePiece p) {
    if (this.col - 1 == p.col) {
      this.left = true;
    }

    if (this.col + 1 == p.col) {
      this.right = true;
    }

    if (this.row - 1 == p.row) {
      this.bottom = true;
    }

    if (this.row + 1 == p.row) {
      this.top = true;
    }
  }
}



