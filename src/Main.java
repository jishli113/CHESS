import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        chessBoard gui=new chessBoard();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(900,1700);
        gui.setVisible(true);
        gui.setTitle("chess");
    }
}
