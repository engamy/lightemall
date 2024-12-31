import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;


// examples class
class ExamplesLightEmAll {
  
  // for world1
  
  GamePiece r0c0;
  GamePiece r0c1;
  GamePiece r0c2;
  GamePiece r1c0;
  GamePiece r1c1;
  GamePiece r1c2;
  GamePiece r2c0;
  GamePiece r2c1;
  GamePiece r2c2;
  
  
  ArrayList<GamePiece> w1r0;
  ArrayList<GamePiece> w1r1;
  ArrayList<GamePiece> w1r2;
  
  ArrayList<ArrayList<GamePiece>> w1Board1;
  
  LightEmAllWorld world1;
  
  // for vertical line world (4x4)
  GamePiece v1r0c0;
  GamePiece v1r0c1;
  GamePiece v1r0c2;
  GamePiece v1r0c3;
  GamePiece v1r1c0;
  GamePiece v1r1c1;
  GamePiece v1r1c2;
  GamePiece v1r1c3;
  GamePiece v1r2c0;
  GamePiece v1r2c1;
  GamePiece v1r2c2;
  GamePiece v1r2c3;
  GamePiece v1r3c0;
  GamePiece v1r3c1;
  GamePiece v1r3c2;
  GamePiece v1r3c3;
  
  ArrayList<GamePiece> v1r0;
  ArrayList<GamePiece> v1r1;
  ArrayList<GamePiece> v1r2;
  ArrayList<GamePiece> v1r3;
  
  ArrayList<ArrayList<GamePiece>> v1board;
  
  LightEmAllWorld v1;
  
  LightEmAllWorld w2;
  ArrayList<GamePiece> w2r1;
  ArrayList<GamePiece> w2r2;
  ArrayList<GamePiece> w2r3;
  ArrayList<ArrayList<GamePiece>> w2Board;
  GamePiece w2r1c1;
  GamePiece w2r1c2;
  GamePiece w2r1c3;
  GamePiece w2r2c1;
  GamePiece w2r2c2;
  GamePiece w2r2c3;
  GamePiece w2r3c1;
  GamePiece w2r3c2;
  GamePiece w2r3c3;
  
  Edge e1;
  Edge e2;
  Edge e3;
  Edge e4;
  ArrayList<Edge> loE1;
  ArrayList<Edge> loE2;
  Edge e5;
  Edge e6;
  
  LightEmAllWorld bigBangTest;
  ArrayList<Edge> kruskaltestResult1;
  ArrayList<Edge> kruskaltestResult2;
  ArrayList<Edge> kruskaltestResult3;
  
  // resetting values for testing
  void reset() {
    
    bigBangTest = new LightEmAllWorld(5, 5);
    
    this.r0c0 = new GamePiece(false, false, true, true, 0, 0, false, false);
    this.r0c1 = new GamePiece(true, true, true, false, 0, 1, false, false);
    this.r0c2 = new GamePiece(true, true, false, true, 0, 2, false, false);
    this.r1c0 = new GamePiece(true, false, false, false, 1, 0, false, false);
    this.r1c1 = new GamePiece(true, true, true, true, 1, 1, true, true);
    this.r1c2 = new GamePiece(true, true, false, false, 1, 2, false, false);
    this.r2c0 = new GamePiece(true, false, true, true, 2, 0, false, false);
    this.r2c1 = new GamePiece(true, true, false, true, 2, 1, false, false);
    this.r2c2 = new GamePiece(true, false, true, true, 2, 2, false, false);
    
    this.w1r0 = new ArrayList<GamePiece>(Arrays.asList(this.r0c0, this.r0c1,
        this.r0c2));
    this.w1r1 = new ArrayList<GamePiece>(Arrays.asList(this.r1c0, this.r1c1,
        this.r1c2));
    this.w1r2 = new ArrayList<GamePiece>(Arrays.asList(this.r2c0, this.r2c1,
        this.r2c2));
    
    this.w1Board1 = new ArrayList<ArrayList<GamePiece>>(Arrays.asList(
        this.w1r0, this.w1r1, this.w1r2));
    
    this.world1 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    
    this.v1r0c0 = new GamePiece(false, true, false, false, 0, 0, true, true);
    this.v1r0c1 = new GamePiece(false, true, false, false, 0, 1, false, false);
    this.v1r0c2 = new GamePiece(false, true, false, false, 0, 2, false, false);
    this.v1r0c3 = new GamePiece(false, true, false, false, 0, 3, false, false);
   
    this.v1r1c0 = new GamePiece(true, true, false, true, 1, 0, false, false);
    this.v1r1c1 = new GamePiece(true, true, true, true, 1, 1, false, false);
    this.v1r1c2 = new GamePiece(true, true, true, true, 1, 2, false, false);
    this.v1r1c3 = new GamePiece(true, true, true, false, 1, 3, false, false);
    
    this.v1r2c0 = new GamePiece(true, true, false, false, 2, 0, false, false);
    this.v1r2c1 = new GamePiece(true, true, false, false, 2, 1, false, false);
    this.v1r2c2 = new GamePiece(true, true, false, false, 2, 2, false, false);
    this.v1r2c3 = new GamePiece(true, true, false, false, 2, 3, false, false);
    
    this.v1r3c0 = new GamePiece(true, false, false, false, 3, 0, false, false);
    this.v1r3c1 = new GamePiece(true, false, false, false, 3, 1, false, false);
    this.v1r3c2 = new GamePiece(true, false, false, false, 3, 2, false, false);
    this.v1r3c3 = new GamePiece(true, false, false, false, 3, 3, false, false);
    
    this.v1r0 = new ArrayList<GamePiece>(Arrays.asList(this.v1r0c0, this.v1r0c1, 
        this.v1r0c2, this.v1r0c3));
    this.v1r1 = new ArrayList<GamePiece>(Arrays.asList(this.v1r1c0, this.v1r1c1, 
        this.v1r1c2, this.v1r1c3));
    this.v1r2 = new ArrayList<GamePiece>(Arrays.asList(this.v1r2c0, this.v1r2c1, 
        this.v1r2c2, this.v1r2c3));
    this.v1r3 = new ArrayList<GamePiece>(Arrays.asList(this.v1r3c0, this.v1r3c1, 
        this.v1r3c2, this.v1r3c3));
    
    this.v1board = new ArrayList<ArrayList<GamePiece>>(Arrays.asList(this.v1r0, this.v1r1,
        this.v1r2, this.v1r3));
    
    this.v1 = new LightEmAllWorld(this.v1board, 4, 4, 0, 0);
    
    
        
    w2r1c1 = new GamePiece(true, false, false, false, 0, 0, true, true);
    w2r1c2 = new GamePiece(true, true, false, false, 0, 1, false, false);
    w2r1c3 = new GamePiece(true, false, false, false, 0, 2, false, false);
    
    w2r2c1 = new GamePiece(true, false, true, false, 1, 0, false, false);
    w2r2c2 = new GamePiece(false, false, false, true, 1, 1, false, false);
    w2r2c3 = new GamePiece(true, true, true, false, 1, 2, false, false);
    
    w2r3c1 = new GamePiece(true, false, false, false, 2, 0, false, false);
    w2r3c2 = new GamePiece(false, false, true, false, 2, 1, false, false);
    w2r3c3 = new GamePiece(false, false, false, true, 2, 2, false, false);
    
    w2r1 = new ArrayList<GamePiece>(Arrays.asList(this.w2r1c1, this.w2r1c2, this.w2r1c3));
    w2r2 = new ArrayList<GamePiece>(Arrays.asList(this.w2r2c1, this.w2r2c2, this.w2r2c3));
    w2r3 = new ArrayList<GamePiece>(Arrays.asList(this.w2r3c1, this.w2r3c2, this.w2r3c3));
    
    w2Board = new ArrayList<ArrayList<GamePiece>>(Arrays.asList(this.w2r1, this.w2r2, this.w2r3));
    
    w2 = new LightEmAllWorld(this.w2Board, 3, 3, 0, 0);
    
    e1 = new Edge(30, this.r0c0, this.r0c1);
    e2 = new Edge(3, this.r0c1, this.r0c2);
    e3 = new Edge(0, this.r0c2, this.r1c2);
    e4 = new Edge(14, this.r1c2, this.r1c1);
    
    loE1 = new ArrayList<Edge>(Arrays.asList(this.e1, this.e2, this.e3, this.e4));
    
    e5 = new Edge(10, this.w2r1c1, this.w2r1c2);
    e6 = new Edge(2, this.w2r3c1, this.w2r1c2);
    
    loE2 = new ArrayList<Edge>(Arrays.asList(this.e1, this.e2, this.e3, this.e4,
        this.e5, this.e6));
    
    kruskaltestResult1 = new ArrayList<Edge>();
    kruskaltestResult2 = new ArrayList<Edge>();
    kruskaltestResult3 = new ArrayList<Edge>();
  }
  
  
  // testing initializeBoard
  void testInitializeBoard(Tester t) {
    reset();
    
    t.checkExpect(this.world1.initializeBoard(), 
        new ArrayList<ArrayList<GamePiece>>(Arrays.asList(
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, false, false, 0, 0, false, false),
                new GamePiece(false, false, false, false, 0, 1, false, false),
                new GamePiece(false, false, false, false, 0, 2, false, false))),
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, false, false, 1, 0, false, false),
                new GamePiece(false, false, false, false, 1, 1, false, false),
                new GamePiece(false, false, false, false, 1, 2, false, false))),
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, false, false, 2, 0, false, false),
                new GamePiece(false, false, false, false, 2, 1, false, false),
                new GamePiece(false, false, false, false, 2, 2, false, false))))));
    
    t.checkExpect(this.v1.initializeBoard(),
        new ArrayList<ArrayList<GamePiece>>(Arrays.asList(
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, false, false, 0, 0, false, false),
                new GamePiece(false, false, false, false, 0, 1, false, false),
                new GamePiece(false, false, false, false, 0, 2, false, false),
                new GamePiece(false, false, false, false, 0, 3, false, false))),
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, false, false, 1, 0, false, false),
                new GamePiece(false, false, false, false, 1, 1, false, false),
                new GamePiece(false, false, false, false, 1, 2, false, false),
                new GamePiece(false, false, false, false, 1, 3, false, false))),
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, false, false, 2, 0, false, false),
                new GamePiece(false, false, false, false, 2, 1, false, false),
                new GamePiece(false, false, false, false, 2, 2, false, false),
                new GamePiece(false, false, false, false, 2, 3, false, false))),
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, false, false, 3, 0, false, false),
                new GamePiece(false, false, false, false, 3, 1, false, false),
                new GamePiece(false, false, false, false, 3, 2, false, false),
                new GamePiece(false, false, false, false, 3, 3, false, false))))));
  }
  
  // testing createNodeList
  void testCreateNodeList(Tester t) {
    reset();
    t.checkExpect(this.world1.createNodeList(),
        new ArrayList<GamePiece>(Arrays.asList(
            this.r0c0, this.r0c1, this.r0c2,
            this.r1c0, this.r1c1, this.r1c2,
            this.r2c0, this.r2c1, this.r2c2)));
    t.checkExpect(this.v1.createNodeList(),
        new ArrayList<GamePiece>(Arrays.asList(
            this.v1r0c0, this.v1r0c1, this.v1r0c2, this.v1r0c3,
            this.v1r1c0, this.v1r1c1, this.v1r1c2, this.v1r1c3,
            this.v1r2c0, this.v1r2c1, this.v1r2c2, this.v1r2c3,
            this.v1r3c0, this.v1r3c1, this.v1r3c2, this.v1r3c3)));
  }
  
  // testing changePath
  
  // testing addPaths
  void testAddPaths(Tester t) {
    reset();
    this.v1.addPaths();
    
    t.checkExpect(this.world1.mst, new ArrayList<Edge>());
    t.checkExpect(this.world1.board, 
         new ArrayList<ArrayList<GamePiece>>(Arrays.asList(
             new ArrayList<GamePiece>(Arrays.asList(
                 new GamePiece(false, false, true, true, 0, 0, false, false),
                 new GamePiece(true, true, true, false, 0, 1, false, false),
                 new GamePiece(true, true, false, true, 0, 2, false, false))),
             new ArrayList<GamePiece>(Arrays.asList(
                 new GamePiece(true, false, false, false, 1, 0, false, false),
                 new GamePiece(true, true, true, true, 1, 1, true, true),
                 new GamePiece(true, true, false, false, 1, 2, false, false))),
             new ArrayList<GamePiece>(Arrays.asList(
                 new GamePiece(true, false, true, true, 2, 0, false, false),
                 new GamePiece(true, true, false, true, 2, 1, false, false),
                 new GamePiece(true, false, true, true, 2, 2, false, false))))));
  }
  
  // testing changePath
  void testChangePath(Tester t) {
    reset();
    this.r0c0.changePath(this.r1c0);
    t.checkExpect(this.r0c0, 
        new GamePiece(false, false, true, true, 0, 0, false, false));
    this.v1r0c3.changePath(this.v1r3c0);
    t.checkExpect(this.v1r0c3, 
        new GamePiece(false, true, false, false, 0, 3, false, false));
  }
 
  
  // testing allEdge
  void testAllEdge(Tester t) {
    reset();
    t.checkExpect(this.world1.allEdge(), true);
    t.checkExpect(this.w2.allEdge(), true);
  }
  
  // testing kruskal's
  void testKruskals(Tester t) {
    reset();
    
    t.checkExpect(this.world1.kruskalsTest(), this.kruskaltestResult1);
    t.checkExpect(this.v1.kruskalsTest(), this.kruskaltestResult2);
    t.checkExpect(this.w2.kruskalsTest(), this.kruskaltestResult3);
  }
  
  
  // test initEdges
  void testInitEdges(Tester t) {
    reset();
    t.checkExpect(this.world1.initEdgesTest(),
        new ArrayList<Edge>(Arrays.asList(
            new Edge(0, this.r0c1, this.r0c0),
            new Edge(0, this.r0c2, this.r0c1),
            new Edge(1, this.r1c0, this.r0c0),
            new Edge(1, this.r1c1, r1c0),
            new Edge(1, this.r1c1, this.r0c1),
            new Edge(1, this.r1c2, this.r1c1),
            new Edge(1, this.r1c2, this.r0c2),
            new Edge(2, this.r2c0, this.r1c0),
            new Edge(2, this.r2c1, this.r2c0),
            new Edge(2, this.r2c1, this.r1c1),
            new Edge(2, this.r2c2, this.r2c1),
            new Edge(2, this.r2c2, this.r1c2))));
                
  }
  
  // test sortWeight
  void testSortWeight(Tester t) {
    reset();
    
    t.checkExpect(this.world1.sortWeight(loE1),
        new ArrayList<Edge>(Arrays.asList(this.e3, this.e2, this.e4, this.e1)));
    t.checkExpect(this.world1.sortWeight(loE2),
        new ArrayList<Edge>(Arrays.asList(this.e3, this.e6, this.e2, this.e5, 
            this.e4, this.e1)));
  }
  
  // test checkRepeat
  void testCheckRepeat(Tester t) {
    reset();
    
    t.checkExpect(this.e1.checkRepeat(this.r0c1, this.r0c0), true);
    t.checkExpect(this.e4.checkRepeat(this.r1c2, this.r1c1), false);
  }  
  
  // testing makeReps
  void testMakeReps(Tester t) {
    reset();
    t.checkExpect(this.w2r1c1.makeRep(), "0, 0");
    t.checkExpect(this.v1r2c1.makeRep(), "2, 1");
    t.checkExpect(this.r2c0.makeRep(), "2, 0");
  }
  
  
  // testing randWeight
  void testRandWeight(Tester t) {
    reset();
    
    t.checkExpect(e1.randWeight(new Random(14)), 57);
    t.checkExpect(e1.randWeight(new Random(23)), 62);
    t.checkExpect(e1.randWeight(new Random(7)), 80);
  }
  
  // testing draw
  void testDraw(Tester t) {
    WorldScene scene = new WorldScene(120, 120);
    WorldScene result = new WorldScene(120, 120);
    // powerstation
    reset();
    result.placeImageXY(this.w2r1c1.tileImage(40, 10, Color.YELLOW, true), 
        20, 20);
    t.checkExpect(this.w2r1c1.draw(scene), result);
    
    // powered gamepiece
    scene = new WorldScene(120, 120);
    result = new WorldScene(120, 120);
    reset();
    
    GamePiece litUp = new GamePiece(true, true, true, true, 0, 0, false, true);
    
    result.placeImageXY(litUp.tileImage(40, 10, Color.YELLOW, false), 
        20, 20);
    
    t.checkExpect(litUp.draw(scene), result);
    
    // unpowered gamepiece
    scene = new WorldScene(120, 120);
    result = new WorldScene(120, 120);
    reset();
    
    GamePiece notLit = new GamePiece(true, true, true, true, 0, 0, false, false);
    
    result.placeImageXY(notLit.tileImage(40, 10, Color.LIGHT_GRAY, false), 
        20, 20);
    
    t.checkExpect(notLit.draw(scene), result);
  }
  
  // testing neighbor
  void testNeighbor(Tester t) {
    reset();
    
    t.checkExpect(this.r1c1.neighbor(this.w1Board1), 
        new ArrayList<GamePiece>(Arrays.asList(this.r1c0, this.r1c2, 
            this.r0c1, this.r2c1)));
    t.checkExpect(this.w2r1c1.neighbor(this.w2Board),
        new ArrayList<GamePiece>(Arrays.asList(this.w2r1c2, this.w2r2c1)));
  }
  
  // testing lightConnectedNeighbors
  void testLightConnectedNeighbors(Tester t) {
    reset();
    
    GamePiece gp1 = new GamePiece(false, true, false, false, 0, 0, true, true);
    GamePiece gp2 = new GamePiece(false, true, false, false, 0, 1, false, false);
    GamePiece gp3 = new GamePiece(false, true, false, false, 0, 2, false, false);
    GamePiece gp4 = new GamePiece(false, true, false, false, 0, 3, false, false);
   
    GamePiece gp5 = new GamePiece(true, true, false, true, 1, 0, false, false);
    GamePiece gp6 = new GamePiece(true, true, true, true, 1, 1, false, false);
    GamePiece gp7 = new GamePiece(true, true, true, true, 1, 2, false, false);
    GamePiece gp8 = new GamePiece(true, true, true, false, 1, 3, false, false);
    
    GamePiece gp9 = new GamePiece(true, true, false, false, 2, 0, false, false);
    GamePiece gp10 = new GamePiece(true, true, false, false, 2, 1, false, false);
    GamePiece gp11 = new GamePiece(true, true, false, false, 2, 2, false, false);
    GamePiece gp12 = new GamePiece(true, true, false, false, 2, 3, false, false);
    
    GamePiece gp13 = new GamePiece(true, false, false, false, 3, 0, false, false);
    GamePiece gp14 = new GamePiece(true, false, false, false, 3, 1, false, false);
    GamePiece gp15 = new GamePiece(true, false, false, false, 3, 2, false, false);
    GamePiece gp16 = new GamePiece(true, false, false, false, 3, 3, false, false);
    
    ArrayList<ArrayList<GamePiece>> result = new ArrayList<ArrayList<GamePiece>>(
        Arrays.asList(
            new ArrayList<GamePiece>(Arrays.asList(gp1, gp2, gp3, gp4)),
            new ArrayList<GamePiece>(Arrays.asList(gp5, gp6, gp7, gp8)),
            new ArrayList<GamePiece>(Arrays.asList(gp9, gp10, gp11, gp12)),
            new ArrayList<GamePiece>(Arrays.asList(gp13, gp14, gp15, gp16))));
    
    this.v1r0c0.lightConnectedNeighbors(this.v1.board);
    
    t.checkExpect(this.v1.board, result);
  }
  
  // testing electrocute
  void testElectrocute(Tester t) {
    reset();
    
    GamePiece gp1 = new GamePiece(false, true, false, false, 0, 0, true, true);
    GamePiece gp2 = new GamePiece(false, true, false, false, 0, 1, false, false);
    GamePiece gp3 = new GamePiece(false, true, false, false, 0, 2, false, false);
    GamePiece gp4 = new GamePiece(false, true, false, false, 0, 3, false, false);
   
    GamePiece gp5 = new GamePiece(true, true, false, true, 1, 0, false, false);
    GamePiece gp6 = new GamePiece(true, true, true, true, 1, 1, false, false);
    GamePiece gp7 = new GamePiece(true, true, true, true, 1, 2, false, false);
    GamePiece gp8 = new GamePiece(true, true, true, false, 1, 3, false, false);
    
    GamePiece gp9 = new GamePiece(true, true, false, false, 2, 0, false, false);
    GamePiece gp10 = new GamePiece(true, true, false, false, 2, 1, false, false);
    GamePiece gp11 = new GamePiece(true, true, false, false, 2, 2, false, false);
    GamePiece gp12 = new GamePiece(true, true, false, false, 2, 3, false, false);
    
    GamePiece gp13 = new GamePiece(true, false, false, false, 3, 0, false, false);
    GamePiece gp14 = new GamePiece(true, false, false, false, 3, 1, false, false);
    GamePiece gp15 = new GamePiece(true, false, false, false, 3, 2, false, false);
    GamePiece gp16 = new GamePiece(true, false, false, false, 3, 3, false, false);
    
    ArrayList<ArrayList<GamePiece>> result = new ArrayList<ArrayList<GamePiece>>(
        Arrays.asList(
            new ArrayList<GamePiece>(Arrays.asList(gp1, gp2, gp3, gp4)),
            new ArrayList<GamePiece>(Arrays.asList(gp5, gp6, gp7, gp8)),
            new ArrayList<GamePiece>(Arrays.asList(gp9, gp10, gp11, gp12)),
            new ArrayList<GamePiece>(Arrays.asList(gp13, gp14, gp15, gp16))));
    
    this.v1.electrocute();
    
    t.checkExpect(this.v1.board, result);
  }
  
  // testing worldEnds
  void testWorldEnds(Tester t) {
    reset();
    
    // making all the cells in w1 powered on
    this.r0c0.powered = true;
    this.r0c1.powered = true;
    this.r0c2.powered = true;
    this.r1c0.powered = true;
    this.r1c1.powered = true;
    this.r1c2.powered = true;
    this.r2c0.powered = true;
    this.r2c1.powered = true;
    this.r2c2.powered = true;
    
    // world is over
    t.checkExpect(this.world1.worldEnds(), new WorldEnd(true, this.world1.youWon()));
    
    reset();
    
    // make all the cells in v1 powered on
    
    this.v1r0c0.powered = true;
    this.v1r0c1.powered = true;
    this.v1r0c2.powered = true;
    this.v1r0c3.powered = true;
    this.v1r1c0.powered = true;
    this.v1r1c1.powered = true;
    this.v1r1c2.powered = true;
    this.v1r1c3.powered = true;
    this.v1r2c0.powered = true;
    this.v1r2c1.powered = true;
    this.v1r2c2.powered = true;
    this.v1r2c3.powered = true;
    this.v1r3c0.powered = true;
    this.v1r3c1.powered = true;
    this.v1r3c2.powered = true;
    this.v1r3c3.powered = true;
    
    // world is over
    t.checkExpect(this.v1.worldEnds(), new WorldEnd(true, this.v1.youWon()));
    
    reset();
    
    // world is not over
    t.checkExpect(this.world1.worldEnds(), new WorldEnd(false, this.world1.makeScene()));
  }
  
  // testing makeScene
  
  // testing youWon
  void testYouWon(Tester t) {
    reset();
    WorldScene world1Result = new WorldScene(120, 120);
    
    world1Result.placeImageXY(new TextImage("you won!", 20, Color.GREEN), 
        60, 60);
    
    t.checkExpect(this.world1.youWon(), world1Result);
    
    WorldScene v1Result = new WorldScene(160, 160);
    
    v1Result.placeImageXY(new TextImage("you won!", 20, Color.GREEN), 
        80, 80);
    
    reset();
    
    t.checkExpect(this.v1.youWon(), v1Result);
  }
  
  // testing isAllPowered
  void testIsAllPowered(Tester t) {
    reset();
    
    // making all the cells in w1 powered on
    this.r0c0.powered = true;
    this.r0c1.powered = true;
    this.r0c2.powered = true;
    this.r1c0.powered = true;
    this.r1c1.powered = true;
    this.r1c2.powered = true;
    this.r2c0.powered = true;
    this.r2c1.powered = true;
    this.r2c2.powered = true;
    
    // all are powered
    t.checkExpect(this.world1.isAllPowered(), true);
    
    reset();
    
    // make all the cells in v1 powered on
    
    this.v1r0c0.powered = true;
    this.v1r0c1.powered = true;
    this.v1r0c2.powered = true;
    this.v1r0c3.powered = true;
    this.v1r1c0.powered = true;
    this.v1r1c1.powered = true;
    this.v1r1c2.powered = true;
    this.v1r1c3.powered = true;
    this.v1r2c0.powered = true;
    this.v1r2c1.powered = true;
    this.v1r2c2.powered = true;
    this.v1r2c3.powered = true;
    this.v1r3c0.powered = true;
    this.v1r3c1.powered = true;
    this.v1r3c2.powered = true;
    this.v1r3c3.powered = true;
    
    // all our powered
    t.checkExpect(this.v1.isAllPowered(), true);
    
    reset();
    
    // not all are powered
    t.checkExpect(this.world1.isAllPowered(), false);
  
  }
  
  // testing makeScene
  void testMakeScene(Tester t) {
    
    reset();
    
    WorldScene world1Result = new WorldScene(120, 120);
    this.r0c0.draw(world1Result);
    this.r0c1.draw(world1Result);
    this.r0c2.draw(world1Result);
    this.r1c0.draw(world1Result);
    this.r1c1.draw(world1Result);
    this.r1c2.draw(world1Result);
    this.r2c0.draw(world1Result);
    this.r2c1.draw(world1Result);
    this.r2c2.draw(world1Result);
    
    t.checkExpect(this.world1.makeScene(), world1Result);
    
    reset();
    
    WorldScene v1Result = new WorldScene(160, 160);
    this.v1r0c0.draw(v1Result);
    this.v1r0c1.draw(v1Result);
    this.v1r0c2.draw(v1Result);
    this.v1r0c3.draw(v1Result);
    this.v1r1c0.draw(v1Result);
    this.v1r1c1.draw(v1Result);
    this.v1r1c2.draw(v1Result);
    this.v1r1c3.draw(v1Result);
    this.v1r2c0.draw(v1Result);
    this.v1r2c1.draw(v1Result);
    this.v1r2c2.draw(v1Result);
    this.v1r2c3.draw(v1Result);
    this.v1r3c0.draw(v1Result);
    this.v1r3c1.draw(v1Result);
    this.v1r3c2.draw(v1Result);
    this.v1r3c3.draw(v1Result);
    
    t.checkExpect(this.v1.makeScene(), v1Result);
  }
  
  // **tileImage() tests omitted because tileImage was given**
  
  // testing rotate
  void testRotate(Tester t) {
    reset();
    
    // Rotating 4 path tile
    t.checkExpect(this.r1c1, new GamePiece(true, true, true, true, 1, 1, true, true));
    this.r1c1.rotate();
    t.checkExpect(this.r1c1, new GamePiece(true, true, true, true, 1, 1, true, true));
    // Rotating horizontal path tile
    t.checkExpect(this.r1c2, new GamePiece(true, true, false, false, 1, 2, false, false));
    this.r1c2.rotate();
    t.checkExpect(this.r1c2, new GamePiece(false, false, true, true, 1, 2, false, false));
    // Rotating 3 path tile
    t.checkExpect(this.v1r1c3, new GamePiece(true, true, true, false, 1, 3, false, false));
    this.v1r1c3.rotate();
    t.checkExpect(this.v1r1c3, new GamePiece(false, true, true, true, 1, 3, false, false));
    
  }
  
  // testing validClick
  void testValidClick(Tester t) {
    reset();
    
    t.checkExpect(this.world1.validClick(0, 0), true);
    t.checkExpect(this.world1.validClick(2, 1), true);
    t.checkExpect(this.world1.validClick(4, 2), false);
    t.checkExpect(this.world1.validClick(2, 4), false);
  }
  
  // testing resetPower
  void testResetPower(Tester t) {
    reset();
    
    ArrayList<ArrayList<GamePiece>> boardResult = new ArrayList<ArrayList<GamePiece>>(
        Arrays.asList(
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(false, false, true, true, 0, 0, false, false),
                new GamePiece(true, true, true, false, 0, 1, false, false),
                new GamePiece(true, true, false, true, 0, 2, false, false))),
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(true, false, false, false, 1, 0, false, false),
                new GamePiece(true, true, true, true, 1, 1, true, false),
                new GamePiece(true, true, false, false, 1, 2, false, false))),
            new ArrayList<GamePiece>(Arrays.asList(
                new GamePiece(true, false, true, true, 2, 0, false, false),
                new GamePiece(true, true, false, true, 2, 1, false, false),
                new GamePiece(true, false, true, true, 2, 2, false, false)))));
                
    this.world1.electrocute(); // electrocute to turn all GamePieces on
    this.world1.resetPower(); // resetPower to test method
    
    t.checkExpect(this.world1.board, boardResult);
  }
  
  // testing onMouseClicked
  void testOnMouseClicked(Tester t) {
    
    // invalid left click
    reset();
    LightEmAllWorld save1 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    this.world1.onMouseClicked(new Posn(1000, 1000), "LeftButton");
    t.checkExpect(this.world1, save1);
    
    // valid right click
    reset();
    LightEmAllWorld save2 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    this.world1.onMouseClicked(new Posn(1000, 1000), "RightButton");
    t.checkExpect(this.world1, save2);
    
    // valid left click
    reset();
    LightEmAllWorld save3 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    this.world1.onMouseClicked(new Posn(10, 40), "LeftButton");
    t.checkExpect(this.world1, save3);
  }
  
  // testing onKeyEvent
  void testOnKeyEvent(Tester t) {
    // up
    reset();
    
    LightEmAllWorld result1 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    
    this.world1.onKeyEvent("up");
    
    t.checkExpect(this.world1, result1);
    
    // down
    reset();
    LightEmAllWorld result2 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    
    this.world1.onKeyEvent("down");
    
    t.checkExpect(this.world1, result2);
    
    // left
    reset();
    LightEmAllWorld result3 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    
    this.world1.onKeyEvent("left");
    
    t.checkExpect(this.world1, result3);
    
    // right
    reset();
    LightEmAllWorld result4 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 2);
    
    this.world1.onKeyEvent("right");
    
    t.checkExpect(this.world1, result4);
    
    // invalid key
    reset();
    LightEmAllWorld result5 = new LightEmAllWorld(this.w1Board1, 3, 3, 1, 1);
    
    this.world1.onKeyEvent("w");
    
    t.checkExpect(this.world1, result5);
  }
  
  
  // play the game!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  void testBigBang(Tester t) {
    reset();
    
    int width = 500;
    int height = 500;
    double tickRate = 0.1;
    this.bigBangTest.bigBang(width, height, tickRate);
  }
  
}



