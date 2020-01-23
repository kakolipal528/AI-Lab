import javax.swing.*;
import java.io.*;
import java.util.*;
public class TicTacToe {
    static final int N = 3;
    public int mat[][] = new int[N][N];
    TicTacToe(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = -1;
            }
        }
    }
    void swingwins(final int a) throws IOException {
        new JFrame();

        if (a == 1) {

            JOptionPane.showMessageDialog(null, "X wins!");

            final BufferedWriter writer = new BufferedWriter(new FileWriter("output_main.txt", true));
            writer.newLine();
            writer.write("X wins!");
            writer.newLine();
            writer.close();

            return;
        } else {

            JOptionPane.showMessageDialog(null, "O wins!");
            final BufferedWriter writer = new BufferedWriter(new FileWriter("output_main.txt", true));
            writer.newLine();
            writer.write("O wins!");
            writer.newLine();
            writer.close();
        }

    }

    void draw() throws IOException {
        new JFrame();

        JOptionPane.showMessageDialog(null, "Match draw !");
        final BufferedWriter writer = new BufferedWriter(new FileWriter("output_main.txt", true));
        writer.newLine();
        writer.write("Match draw !");
        writer.newLine();
        writer.close();

    }
    public Boolean checkDraw(int count) {
        int cnt = 0;
        if(count == 5 || count > 6){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(mat[i][j] == -1) {
                        mat[i][j] = 1;
                        if(checkX()) {
                            cnt++;
                        }
                        mat[i][j] = -1;
                    }
                }
            }
            if(cnt >= 1)
                return false;
            cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(mat[i][j] == -1){
                        mat[i][j]= 0;
                        if(checkO()){
                            cnt++;
                        }
                        mat[i][j] = -1;
                    }
                }
            }
            if(cnt >= 1)
                return false;
            return true;
        }
        return false;
    }
    public Boolean checkX() {
        Boolean flag = false;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                if(mat[i][j] != 1 || mat[i][j] != mat[i][j+1]){
                    flag = false;
                    break;
                }
                else
                    flag = true;
            }
            if(flag)
                return true;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                if (mat[j][i] != 1 || mat[j][i] != mat[j+1][i]){
                    flag = false;
                    break;
                }
                else
                    flag = true;
            }
            if(flag)
                return true;
        }
        flag = true;
        for (int i=0; i<N-1; i++) {
            if (mat[i][i] != mat[i+1][i+1] || mat[i][i] != 1) {
                flag = false;
                break;
            }
        }
        if(flag)
            return true;
        flag = true;
        for (int i = N-1; i > 0; i--) {
            int j = 0;
            if(mat[j][i] != mat[j+1][i-1] || mat[j][i] != 1) {
                flag = false;
                break;
            }
            else
                j++;
        }
        if(flag)
            return true;
        return false;
    }
    public Boolean checkO() {
        Boolean flag = false;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                if(mat[i][j] != mat[i][j+1] || mat[i][j] != 0){
                    flag = false;
                    break;
                }
                else
                    flag = true;
            }
            if(flag)
                return true;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                if (mat[j][i] != mat[j+1][i] || mat[j][i] != 0){
                    flag = false;
                    break;
                }
                else
                    flag = true;
            }
            if(flag)
                return true;
        }
        flag = true;
        for (int i=0; i<N-1; i++) {
            if (mat[i][i] != mat[i+1][i+1] || mat[i][i] != 0) {
                flag = false;
                break;
            }
        }
        if(flag)
            return true;
        flag = true;
        for (int i = N-1; i > 0; i--) {
            int j = 0;
            if(mat[j][i] != mat[j+1][i-1] || mat[j][i] != 0) {
                flag = false;
                break;
            }
            else
                j++;
        }
        if(flag)
            return true;
        return false;
    }
    public void print(){
        System.out.print("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(mat[i][j] == 0)
                    System.out.print("O");
                else if(mat[i][j] == 1)
                    System.out.print("x");
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
    public Boolean AddMove(int x,int y,int value) {
        if(mat[x-1][y-1] != -1 || x < 0 || y < 0 || x > N || y > N)
            return false;
        mat[x-1][y-1] = value;
        return true;
    }
    public static void main(String[] args) throws Exception{
        TicTacToe tt = new TicTacToe();
        int flag = 1;
        System.out.println("Welcome to Tic-Tac-Toe");
        tt.print();
        Scanner sc = new Scanner(System.in);
        int count = 0;
        do {
            int x,y;
            while(count%2 == 0) {
                System.out.println("Move for X");
                System.out.println("Enter the coordinates wherem you want to enter:");
                x = sc.nextInt();
                y = sc.nextInt();
                if(tt.AddMove(x, y, 1)){
                    ++count;
                    if(tt.checkDraw(count)){
                        tt.draw();
                        System.out.println("Match is Draw");
                        flag = 0;
                        break;
                    }
                    else if(tt.checkX()){
                        tt.swingwins(1);
                        System.out.println("X wins.");
                        flag = 0;
                        break;
                    }
                    tt.print();
                    break;
                }
                else
                    System.out.println("Wrong Input..");
            }
            if(flag == 0)
                break;
            while(count%2 != 0) {
                System.out.println("Move for O");
                System.out.println("Enter the coordinates wherem you want to enter:");
                x = sc.nextInt();
                y = sc.nextInt();
                if(tt.AddMove(x, y, 0)) {
                    ++count;
                    if(tt.checkDraw(count)) {
                        tt.draw();
                        System.out.println("Match is Draw.");
                        flag = 0;
                        break;
                    }
                    else if(tt.checkO()){
                        tt.swingwins(0);
                        System.out.println("O wins.");
                        flag = 0;
                        break;
                    }
                    tt.print();
                    break;
                }
                else
                    System.out.println("Wrong Input..");
            }

        }while(flag == 1);
    }
}
