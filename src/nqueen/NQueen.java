package nqueen;
import java.util.*;
/**
 *
 * @author panupongsangaphunchai
 */
public class NQueen {
    int N; //size of board

    public void printSolution(int board[][])
    {
        System.out.printf("%8s 1  2  3  4"," ");
        for(int i=0;i<N;i++)
        {
            System.out.printf("row %d | ",N);
            for(int j=0;j<N;j++)
            {
                System.out.print(" " + board[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        System.out.println("nQueen!!!");
    }
    
}
