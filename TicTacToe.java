import javax.swing.*;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class TicTacToe {
    public static final int N = 3;
    public static int c;
    public int mat[][] = new int[N][N];
    TicTacToe(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = -1;
            }
        }
        c = 0;
    }
    void swingwins(final int a) throws IOException {
        new JFrame();

        if (a%2 == 1) {

            JOptionPane.showMessageDialog(null, "Player1 wins!");

            final BufferedWriter writer = new BufferedWriter(new FileWriter("output_main.txt", true));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            writer.newLine();
            writer.write("Player1 wins! at "+ dtf.format(now));
            writer.newLine();
            writer.close();

            return;
        } else {

            JOptionPane.showMessageDialog(null, "Player2 wins!");
            final BufferedWriter writer = new BufferedWriter(new FileWriter("output_main.txt", true));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            writer.newLine();
            writer.write("Player2 wins! at "+dtf.format(now));
            writer.newLine();
            writer.close();
        }

    }

    void draw() throws IOException {
        new JFrame();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        JOptionPane.showMessageDialog(null, "Match draw !");
        final BufferedWriter writer = new BufferedWriter(new FileWriter("output_main.txt", true));
        writer.newLine();
        writer.write("Match draw! at "+dtf.format(now));
        writer.newLine();
        writer.close();

    }
    public Boolean isSafe(int grid[][], int num) 
    { 
	    return !UsedInBox(grid, num); 
    }
    public Boolean UsedInBox(int grid[][], int num) 
    { 
	    for (int i = 0; i < N; i++) 
		    for (int j = 0; j < N; j++) 
			    if (grid[i][j] == num) 
				    return true; 
	    return false; 
    }
    public Boolean checkDraw(int count) {
        int arr2[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr2[i][j] = mat[i][j];
            }
        }
        if(count == 9)
            return true;
        return false;
    }
    public Boolean check(int mat[][]) {
        Boolean flag = true;
        int count = 0;
        for(int i = 0; i < N; i++) {
            flag = true;
            for (int j = 0; j < N; j++) {
                if(mat[i][j] != -1){
                    count += mat[i][j];
                }
                else{
                    flag = false;
                    break;
                }
            }
            if(count == 15 && flag) 
                return true;
            count = 0;
        }
        count = 0;
        flag = true;
        for (int i = 0; i < N; i++) {
            flag = true;
            for (int j = 0; j < N; j++) {
                if (mat[j][i] != -1){
                    count += mat[j][i];    
                }
                else {
                    flag = false;
                    break;
                }
            }
            if(count == 15 && flag)
                return true;
            count = 0;
        }
        count = 0;
        flag = true;
        for (int i = 0; i < N; i++) {
            if (mat[i][i] != -1) {
                count += mat[i][i];
            }
            else{
                flag = false;
                break;
            }
        }
        if(count == 15 && flag)
            return true;
        count = 0;
        flag = true;
        int j = 0;
        for (int i = N-1; i >= 0; i--) {
            if(mat[j][i] != -1) {
                count += mat[j][i];
                j++;
            }
            else {
                flag = false;
                break;
            }
        }
        if(count == 15 && flag)
            return true;
        return false;
    }
    public void print(int mat[][]){
        System.out.print("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(mat[i][j] != -1)
                    System.out.print(mat[i][j]);
                else
                    System.out.print(" ");
                if(j!=N-1)
                    System.out.print(" | ");
            }
            if(i!=N-1)
                System.out.println("\n----------");
            else
                System.out.print("\n");
        }
    }
    public Boolean AddMove(int x,int y,int value,int count) throws Exception{
        if(mat[x][y] != -1 || x < 0 || y < 0 || x >= N || y >= N || value > 9 || value < 1)
            return false;
        final BufferedWriter writer = new BufferedWriter(new FileWriter("output_main.txt", true));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        writer.newLine();
        if(count%2 == 0)
            writer.write("Player1 moves "+x+","+y+" at "+dtf.format(now));
        if(count%2 != 0)
            writer.write("Player2 moves "+x+","+y+" at "+dtf.format(now));
        writer.newLine();
        writer.close();
        mat[x][y] = value;
        return true;
    }
    public static void main(String[] args) throws Exception{
        TicTacToe tt = new TicTacToe();
        int flag = 1;
        System.out.println("Welcome to Tic-Tac-Toe");
        tt.print(tt.mat);
        Scanner sc = new Scanner(System.in);
        int count = 0;
        do {
            int x, y, value;
            while(true) {
                System.out.println("Move for Player"+(count%2+1));
                System.out.println("Enter the coordinates where you want to enter:");
                x = sc.nextInt();
                y = sc.nextInt();
                System.out.println("Enter the number between 1-9:");
                value = sc.nextInt();
                if(!tt.isSafe(tt.mat, value))
                    System.out.println(value+" already exists in the Game, choose another number.");
                else if(tt.AddMove(x, y, value,count)){
                    ++count;
                    if(tt.check(tt.mat)){
                        tt.swingwins(count);
                        System.out.println("Player"+count%2+" wins!");
                        flag = 0;
                        break;
                    }
                    else if(tt.checkDraw(count)){
                        tt.draw();
                        System.out.println("Match is Draw");
                        flag = 0;
                        break;
                    }
                    tt.print(tt.mat);
                    break;
                }
                else
                    System.out.println("Wrong Input..");
            }
            if(flag == 0)
                break;
        }while(flag == 1);
    }
}
