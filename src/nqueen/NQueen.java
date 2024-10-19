/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nqueen;
import java.util.*;
/**
 *
 * @author panupongsangaphunchai
 */
class Queen
{
    private int []row;
    private int size;
    
    public Queen()
    {
        size = 0;
        row = new int[size];
    }
    public void print()
    {
        for(int i = 0 ;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(row[i]==j)
                {
                    System.out.printf("Q ");
                }else System.out.printf(". ");
            }
            System.out.println();
        }
    }
    public void solution()
    {
        System.out.println("Enter N");
        Scanner ans = new Scanner(System.in);
        this.size = ans.nextInt();
        this.row = new int[size];
        for(int i = 0;i<size;i++)row[i]=-1;
        for(int i = 0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                System.out.printf(". ");
            }
            System.out.println();
        }
        System.out.println("Manually(y/other)?");
        Scanner option = new Scanner(System.in);
        String choice = option.nextLine();
        switch(choice)
        {
            case "Y":
            case "y":
            System.out.println("Enter row");
            Scanner user = new Scanner(System.in);
            int user_row = user.nextInt();
            System.out.println("Enter column");
            Scanner user2 = new Scanner(System.in);
            int user_column = user2.nextInt();
            this.row[user_row] = user_column;
            print();
            backtrack(0,user_row,user_column);
            case "N":
            case "n":
       
               
                    
            
            
              
        }
        
    }
    public boolean isSafe(int row_ind, int column_ind, int user_row,int user_column)
            {
                if(row[row_ind]==user_column||
                   Math.abs(row[row_ind]-user_column)==Math.abs(row_ind-user_row))
                    return false;
                for(int i=0;i<row_ind;i++)
                {
                    if(row[i]==column_ind||Math.abs(row[i]-column_ind)==Math.abs(row_ind-i))
                    {
                        return false;
                    }
                }
                return true;
            }
    
    public void backtrack(int n,int user_row,int user_column)
    {
        if(n>=this.size)
        {
            print();
        }else if(n==user_row)
        {
            backtrack(n+1,user_row,user_column);
        }
        else
        {
            for(int i = 0;i < this.size;i++)
            {
                row[n] = i;
                if(isSafe(n,i,user_row,user_column)==true)
                {
                    
                    backtrack(n+1,user_row,user_column);
                }
                row[n] = -1;
            }
        }
    }
    
}
public class NQueen {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Queen myQueen = new Queen();
        myQueen.solution();
    }
    
}
