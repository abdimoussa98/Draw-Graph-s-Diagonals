import java.util.Scanner;
import java.util.*;
import java.lang.Math;
import java.util.stream.*;

class Main {
  public static void main(String[] args) {
    getUserInput();

  }

  static void getUserInput() {
    Scanner input = new Scanner(System.in);
    Graph graph;
    try {
      System.out.print("Enter graph Size: ");
      int graphSize = input.nextInt();

      graph = new Graph(graphSize);
      
      input.nextLine(); // This line required (It consumes the \n character)
      System.out.print("Enter starting node (like this x,y): "); 
      
      Point start = new Point(Arrays.stream(input.nextLine().split(",", 2)).mapToInt(Integer::parseInt).toArray());
      
      start.print();
      
      graph.print();
      graph.chartBackwardDiagonal(start);
      graph.chartForwardDiagonal(start);
      graph.chartHorizontal(start);
      graph.chartVertical(start);
      graph.debugGraph(start);
      
    } catch (Exception exception) {
      System.out.println(exception);
      getUserInput();
    } catch (Error err) {
      System.out.println(err);
      getUserInput();
    }
  }

}
 
class Graph {
  private int[][] graph;

  Graph(int graphSize) {
    this.graph = new int[graphSize][graphSize];
  }

  void chartBackwardDiagonal(Point start){
    System.out.println("\nPrinting Graph with backward diagonal: ");
    LoopGraphToPrint((row,column)->{
        if ((row - column) == (start.row - start.column)) {
          System.out.print("X");
        } else {
              System.out.print('.');
        }
    });  
  }
  
  void chartForwardDiagonal(Point start){
    System.out.println("\nPrinting Graph with forward diagonal: ");
    LoopGraphToPrint((row,column)->{
      if (row + column == (start.row + start.column)) {
          System.out.print("X");
        } else {
              System.out.print('.');
        }
   });
  }

  void chartHorizontal(Point start){
    System.out.println("\nPrinting Graph with horizontal line: ");
    LoopGraphToPrint((row,column)->{
      if (row == start.row) {
          System.out.print("X");
        } else {
              System.out.print('.');
        }
   });
  }

  void chartVertical(Point start){
    System.out.println("\nPrinting Graph with vertical line: ");
    LoopGraphToPrint((row,column)->{
      if (column == start.column) {
          System.out.print("X");
        } else {
              System.out.print('.');
        }
   });
  }
  
  void print(){
    System.out.println("\nPrinting Graph with EMPTY diagonal: ");
    LoopGraphToPrint((row,column)->System.out.print("."));
  }

  void debugGraph(Point start){
    LoopGraphToPrint((row,column)->{
      if (Math.abs(row - column) == Math.abs(start.row - start.column)) {
          System.out.println("Current point: (" + row + "," + column +") Starting point: (" + start.row + "," + start.column + ")");
          System.out.print("Current diff: " + (row - column) +" Starting diff: "+ (start.row - start.column));
        }
    });
  }
  
  interface PrintCondition {
    void calculate(int x, int y);
  }

  private void LoopGraphToPrint(PrintCondition printCondition){
    for(int i = 0; i < this.graph[0].length; i++){
      System.out.print("[");
      for(int j = 0; j < this.graph[0].length; j++){  
        printCondition.calculate(i,j);
      }
      System.out.println("]");
    }
    System.out.println("");
  }
    
}


class Point{
  int row;
  int column;
  Point(int[] start){
    this.row = start[0];
    this.column = start[1];
  }
  void print(){
    System.out.println("Point: " + this.row +","+ this.column);
  }
}
