//Floyd-Warshall Algorithm

import java.util.*;
import java.lang.*;

class Main {

  public static void main(String [] args) {
    //Populate the graph
    Random randomNum = new Random();
    int[][] graph = new int[26][26];
    int[][] path = new int[26][26];
    int nodeOne;
    int nodeTwo;
    Scanner input = new Scanner(System.in);


    //Row
    for (int x = 0; x < graph.length; x++) {
      //Column
      for (int y = 0; y < graph[x].length; y++) {
        if (x == y) {
          path[x][y] = -1;
          graph[x][y] = 0;
        } else {
          path[x][y] = x;
          graph[x][y] = randomNum.nextInt(10) + 1;
        }
      }
    }//End of population for loop

    //Solution
    for (int k = 0; k < graph.length; k++) {
      //Vertices
      for (int i = 0; i < graph.length; i++) {
        for (int j = 0; j < graph.length; j++) {
          if (graph[i][k] + graph[k][j] < graph[i][j]) {
            graph[i][j] = graph[k][j] + graph[i][k];
            path[i][j] = path[k][j];
          }
        }
      }
    }

    //Print out solution
    System.out.println("Matrix shows shortest path:");
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph.length; j++) {
        System.out.print(graph[i][j] + " ");
      }
      System.out.println();
    }

    try {
      //Determine the path taken based off of the index you choose
      System.out.println("Please enter an integer(0-26) corresponding to the alphabet for the first node:");
      nodeOne = input.nextInt();
      System.out.println("Please enter an integer(0-26) corresponding to the alphabet for the second node:");
      nodeTwo = input.nextInt();

      LinkedList<Character> node = getPath(nodeOne, nodeTwo, graph, path);

      for (Character aNode : node) {
        System.out.print(aNode + " ");
      }
    } catch (InputMismatchException ime) {
      ime.printStackTrace();
    }
  }

  //Convert node to Letter
  public static char getVertex(int i){
    return(char)(i + 97);
  }


  public static LinkedList<Character> getPath(int source, int target, int[][] graph, int[][] pathMatrix){
    if(graph[source][target] == 0) {
      return null;
    }

    LinkedList<Character> path = new LinkedList<>();
    path.addFirst(getVertex(target));

    while(target != source){
      target = pathMatrix[source][target];
      path.addFirst(getVertex(target));
    }

    return path;
  }
}
