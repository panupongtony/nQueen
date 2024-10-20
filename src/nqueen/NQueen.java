package nqueen;

import java.util.*;

/**
 *
 * @author1 Akkhrawin Nair 6580013
 * @author2 Nitchayanin Thamkunanon 6580081
 * @author3 Panupong Sangaphunchai 6580587
 * @author4 Sukumarn Srimai 6581085
 */
class Queen {

    //collect array of column indexes, where the indexes are row indexes
    private ArrayList<Integer> row = new ArrayList<>(); //ArrayList is in collection "List"
    private int size;
    private int solutionCount = 0;
    //private int[][] solutions;
    private ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();

    public Queen() {
        size = 0;
        row = new ArrayList<Integer>(size);
    }

    public void printEmptyBoard() {
        System.out.println();
        for (int j = 0; j < 40; j++) {
            System.out.printf("=");
        }
        System.out.printf("\n%8s", " ");
        for (int i = 0; i < size; i++) {
            System.out.printf("%d  ", i + 1);
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.printf("row %d | ", i + 1);
            for (int j = 0; j < size; j++) {
                System.out.printf(".  ");
            }
            System.out.println();
        }
        for (int j = 0; j < 40; j++) {
            System.out.printf("=");
        }
    }

    public void printBoard() {
        System.out.printf("%8s", " ");
        for (int i = 0; i < size; i++) {
            System.out.printf("%d  ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.printf("row %d | ", i + 1);
            for (int j = 0; j < size; j++) {
                if (row.get(i) == j) {
                    System.out.printf("Q  ");
                } else {
                    System.out.printf(".  ");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < 40; j++) {
            System.out.printf("=");
        }
        System.out.println();
    }

    public void printSolution(ArrayList<Integer> solution) {
        System.out.printf("%8s", " ");
        for (int i = 0; i < size; i++) {
            System.out.printf("%d  ", i + 1);
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.printf("row %d | ", i + 1);
            for (int j = 0; j < size; j++) {
                if (solution.get(i) == j) {
                    System.out.printf("Q  ");
                } else {
                    System.out.printf(".  ");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < 40; j++) {
            System.out.printf("=");
        }
        System.out.println();
    }

    public void placeQueen() {
        while (true) {
            solutionCount = 0;
            boolean Lessthan4 = true; //if user insert size less than 4, its false
            boolean MorethanSize = true; 
//if user input row/column that are more than their defined board size, MorethanSize = true
            while (Lessthan4 == true) {
                System.out.println("Enter N for N*N board (N must be at least 4)");
                Scanner ans = new Scanner(System.in);
                this.size = ans.nextInt();
                
                if(this.size >=4) Lessthan4 = false; //not less than 4
                else 
                {
                    System.out.println("=========================================");
                    System.out.println("N should be at least 4, insert again!\n");
                }
                //if user insert less than 4
            }

            this.row = new ArrayList<>(size);
            this.solutions = new ArrayList<ArrayList<Integer>>(100);//initialized
            for (int i = 0; i < size; i++) {
                row.add(-1);
            }
            printEmptyBoard();

            System.out.println("\nManually place the First Queen? (y for yes, others for no)");
            Scanner option = new Scanner(System.in);
            String choice = option.nextLine();
            switch (choice) {
                case "Y":
                case "y":
                    int user_row = 0, user_column = 0; //initialized
                    while(MorethanSize == true){
                       
                        System.out.println("Enter row");
                        Scanner user = new Scanner(System.in);
                        user_row = user.nextInt();
                        System.out.println("Enter column");
                        user_column = user.nextInt();
                        if(user_column > size || user_row > size)
                        {
                            System.out.println("\n=========================================");
                            System.out.println("Enter row and column again!!");
                            System.out.println("They should not exceed N");
                            System.out.println("=========================================\n");
                            
                        }
                        else MorethanSize = false; //user entered the correct row and column
                    }
                    this.row.set(user_row-1,user_column-1); //index, value you insert
                    System.out.println("\n=========================================");
                    System.out.println("Initial Board");
                    
                    printBoard();
                    backtrack(0, user_row - 1, user_column - 1);
                    randomSolution();
                    break;

                default:
                    //normal backtracking. user doesnt insert anything
                    backtrack(0, -2, -2);
                    randomSolution();
                    break;
            }
            String Continue = "A"; //initialize
            boolean continueorNot = false; //false = not continue. true = continue
            while (!Continue.equals("Y") && !Continue.equals("N") && !Continue.equals("y") && !Continue.equals("n")) {
                System.out.println("Continue playing? (Y/N)");
                Continue = option.nextLine();
                if (Continue.equals("Y") || Continue.equals("y")) {
                    System.out.println("\nYou choose to continue");
                    continueorNot = true;

                } else if (Continue.equals("N")||Continue.equals("n")) {
                    System.out.println("\n========================================");
                    System.out.println("Goodbye");
                    break;
                } else {
                    System.out.println("\nYou should insert Y or N!!");
                }
            }
            if (continueorNot == true) {
                continue;
            } 
            else {
                break;
            }

        }

    }

    //check if the queen can be placed 
    public boolean isSafe(int row_ind, int column_ind, int user_row, int user_column) {
        if (user_row >= 0 && user_column >= 0) {
            if (column_ind == user_column
                    || Math.abs(column_ind - user_column) == Math.abs(row_ind - user_row)) {
                return false;
            }
        }

        for (int i = 0; i < row_ind; i++) {
            if (row.get(i) == column_ind || Math.abs(row.get(i) - column_ind) == Math.abs(i - row_ind)) {
                return false;
            }
        }
        return true;
    }

    //backtrack algorithm
    public void backtrack(int n, int user_row, int user_column) {

        if (n >= this.size) {
            solutions.add(new ArrayList<>(row));
            solutionCount++;
        } else if (n == user_row) {
            backtrack(n + 1, user_row, user_column);
        } else {
            for (int i = 0; i < this.size; i++) {
                if (isSafe(n, i, user_row, user_column) == true) {
                    row.set(n,i);
                    backtrack(n + 1, user_row, user_column);
                }
               
            }
        }
    }

    public void randomSolution() {

        Random rand = new Random();

        if (solutionCount == 0) {
            System.out.println("No solution");
        } else {
            int Finalsolution = rand.nextInt(solutionCount);
            System.out.println("Solution");
            printSolution(solutions.get(Finalsolution));
        }

    }
}

public class NQueen {
//MAIN   

    public static void main(String[] args) {
        Queen myQueen = new Queen();
        myQueen.placeQueen();

    }

}
