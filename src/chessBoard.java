import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class chessBoard extends JFrame {
    private Pawn b=new Pawn(0,0,true);
    private Color[][] ogc=new Color[8][8];
    private JPanel[][] board=new JPanel[8][8];
    private Piece[][] board1=new Piece[8][8];
    private int xstore=0;
    private int ystore=0;
    private boolean turn =true;
    private boolean click=false;
    private Color color;
    private King white;
    private King black;
    private ArrayList<Integer[]> info=new ArrayList<>();
    private ArrayList<Integer[]> blinka=new ArrayList<>();
    private Piece[]whitePromotePawn=new Piece[]{new Knight(0,0,true),
            new Bishop(0,0,true),new Rook(0,0,true),
            new Queen(0,0,true)};
    private Piece[] blackPromotePawn=new Piece[]{new Knight(0,0,false),new Bishop(0,0,false),
            new Rook(0,0,false),new Queen(0,0,false)};
    private ActionListener blink= e -> {
            for (int i = 0; i < blinka.size(); i++) {
                if(sub(blinka.get(i)[0],blinka.get(i)[1])) {
                    if (board[blinka.get(i)[0]][blinka.get(i)[1]].getBackground().equals(ogc[blinka.get(i)[0]][blinka.get(i)[1]])) {
                        board[blinka.get(i)[0]][blinka.get(i)[1]].setBackground(Color.RED);
                    } else {
                        board[blinka.get(i)[0]][blinka.get(i)[1]].setBackground(ogc[blinka.get(i)[0]][blinka.get(i)[1]]);
                    }
                }
            }
    };
    private Timer moveTime=new Timer(500,blink);

    public chessBoard()
    {
        setBoard();
        setBoardOne();
    }
    private void setBoardOne()
    {
        board1[0][0]=new Rook(0,0,false);
        board1[0][1]=new Knight(0,1,false);
        board1[0][2]=new Bishop(0,2,false);
        board1[0][3]=new Queen(0,3,false);
        board1[0][4]=new King(0,4,false);
        black= (King)board1[0][4];
        Piece.setBlack((King)board1[0][4]);
        board1[0][7]=new Rook(0,7,false);
        board1[0][6]=new Knight(0,6,false);
        board1[0][5]=new Bishop(0,5,false);
        for(int i=1;i<8;i+=5)
        {
            for(int j=0;j<8;j++)
            {
                if(i==1)
                {
                    board1[i][j]=new Pawn(i,j,false);
                    if(j==4)
                    {
                        b=(Pawn)board1[i][j];
                    }
                }
                else
                {
                    board1[i][j]=new Pawn(i,j,true);
                }
            }
        }
        board1[7][0]=new Rook(7,0,true);
        board1[7][1]=new Knight(7,1,true);
        board1[7][2]=new Bishop(7,2,true);
        board1[7][3]=new Queen(7,3,true);
        board1[7][4]=new King(7,4,true);
        white=(King)board1[7][4];
        Piece.setWhite((King)board1[7][4]);
        board1[7][7]=new Rook(7,7,true);
        board1[7][6]=new Knight(7,6,true);
        board1[7][5]=new Bishop(7,5,true);
    }
    private void setBoard()
    {
        setLayout(new GridLayout(8,8,3,3));
        boolean store=true;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                JPanel a=new JPanel();
                event e=new event();
                JLabel c=new JLabel(detPiece(i,j));
                Color z=new Color(0,0,0);
                a.addMouseListener(e);
                if(store)
                {
                    a.setBackground(Color.WHITE);
                    z=Color.WHITE;
                }
                else
                {
                    a.setBackground(new Color(0,153,0));
                    z=new Color(0,153,0);
                }
                ogc[i][j]=z;
                a.add(c);
                add(a);
                board[i][j]=a;
                store=!store;
            }
            store=!store;
        }
        Piece.updateBoard(board1);
    }
    private ImageIcon detPiece(int x,int y)
    {
        if(x==1)
        {
            return new ImageIcon(getClass().getResource("BlackPiecesIMG/BP.gif"));
        }
        if(x==6)
        {
            return new ImageIcon(getClass().getResource("WhitePiecesIMG/WP.gif"));
        }
        if(x==0&&y==0||x==0&&y==7)
        {
            return new ImageIcon(getClass().getResource("BlackPiecesIMG/BR.gif"));
        }
        if(x==0&&y==1||x==0&&y==6)
        {
            return new ImageIcon(getClass().getResource("BlackPiecesIMG/BN.gif"));
        }
        if(x==0&&y==2||x==0&&y==5)
        {
            return new ImageIcon(getClass().getResource("BlackPiecesIMG/BB.gif"));
        }
        if(x==0&&y==3)
        {
            return new ImageIcon(getClass().getResource("BlackPiecesIMG/BQ.gif"));
        }
        if(x==0&&y==4)
        {
            return new ImageIcon(getClass().getResource("BlackPiecesIMG/BK.gif"));
        }
        if(x==7&&y==0||x==7&&y==7)
        {
            return new ImageIcon(getClass().getResource("WhitePiecesIMG/WR.gif"));
        }
        if(x==7&&y==1||x==7&&y==6)
        {
            return new ImageIcon(getClass().getResource("WhitePiecesIMG/WN.gif"));
        }
        if(x==7&&y==2||x==7&&y==5)
        {
            return new ImageIcon(getClass().getResource("WhitePiecesIMG/WB.gif"));
        }
        if(x==7&&y==3)
        {
            return new ImageIcon(getClass().getResource("WhitePiecesIMG/WQ.gif"));
        }
        if(x==7&&y==4)
        {
            return new ImageIcon(getClass().getResource("WhitePiecesIMG/WK.gif"));
        }
        return null;
    }
    private void castle(int x,int y) {
            if (x == 7 && y == 6) {
                board[7][4].remove(0);
                board[7][4].add(new JLabel());
                board[7][7].remove(0);
                board[7][7].add(new JLabel());
                board[7][6].remove(0);
                board[7][5].remove(0);
                board[7][6].add(new JLabel(new ImageIcon(getClass().getResource("WhitePiecesIMG/WK.gif"))));
                board[7][5].add(new JLabel(new ImageIcon(getClass().getResource("WhitePiecesIMG/WR.gif"))));
                board1[7][4].setCord(7, 6);
                board1[7][7].setCord(7, 5);
                board1[7][6]=board1[7][4];
                board1[7][5]=board1[7][7];
                board1[7][4] = null;
                board1[7][7] = null;
            }
            if (x == 7 && y == 2) {
                board[7][4].remove(0);
                board[7][4].add(new JLabel());
                board[7][0].remove(0);
                board[7][0].add(new JLabel());
                board[7][2].remove(0);
                board[7][3].remove(0);
                board[7][2].add(new JLabel(new ImageIcon(getClass().getResource("WhitePiecesIMG/WK.gif"))));
                board[7][3].add(new JLabel(new ImageIcon(getClass().getResource("WhitePiecesIMG/WR.gif"))));
                board1[7][4].setCord(7, 2);
                board1[7][0].setCord(7, 3);
                board1[7][2]=board1[7][4];
                board1[7][3]=board1[7][7];
                board1[7][4] = null;
                board1[7][0] = null;
            }
            if (x == 0 && y == 6) {
                board[0][4].remove(0);
                board[0][4].add(new JLabel());
                board[0][7].remove(0);
                board[0][7].add(new JLabel());
                board[0][5].remove(0);
                board[0][6].remove(0);
                board[0][6].add(new JLabel(new ImageIcon(getClass().getResource("BlackPiecesIMG/BK.gif"))));
                board[0][5].add(new JLabel(new ImageIcon(getClass().getResource("BlackPiecesIMG/BR.gif"))));
                board1[0][4].setCord(0, 6);
                board1[0][7].setCord(0, 5);
                board1[0][6]=board1[0][4];
                board1[0][5]=board1[0][7];
                board1[0][4] = null;
                board1[0][7] = null;
            }
            if (x == 0 && y == 2) {
                board[0][4].remove(0);
                board[0][4].add(new JLabel());
                board[0][0].remove(0);
                board[0][0].add(new JLabel());
                board[0][2].remove(0);
                board[0][3].remove(0);
                board[0][2].add(new JLabel(new ImageIcon(getClass().getResource("BlackPiecesIMG/BK.gif"))));
                board[0][3].add(new JLabel(new ImageIcon(getClass().getResource("BlackPiecesIMG/BR.gif"))));
                board1[0][4].setCord(0, 2);
                board1[0][0].setCord(0, 3);
                board1[0][2]=board1[0][4];
                board1[0][3]=board1[0][0];
                board1[0][4] = null;
                board1[0][0] = null;
            }
        resetTimer();
        repaint();
        revalidate();
            Piece.updateBoard(board1);
    }
    public class event implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = 9;
            int y = 9;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (e.getSource() == board[i][j]) {
                        x = i;
                        y = j;
                        break;
                    }
                }
                if (x < 9 && y < 9) {
                    break;
                }
            }
            if (click) {
                Piece.updateProtect();
                if (x == xstore && y == ystore) {
                    board1[xstore][ystore].clearMoves();
                    board[xstore][ystore].setBackground(color);
                    turn=!turn;
                    resetTimer();
                } else if (board1[xstore][ystore] != null) {
                    board1[xstore][ystore].move();
                    info = board1[xstore][ystore].getMoves();
                /*for(int i=0;i<info.size();i++)
                    {
                        System.out.println(info.get(i)[0]+" "+info.get(i)[1]);
                    }*/
                    if ((board1[xstore][ystore].equals(new King(0, 0, true)) || board1[xstore][ystore].equals(new King(0, 0, false))) && Math.abs(ystore - y) > 1 && deepContains(info,new Integer[]{x, y})) {
                        castle(x, y);
                    } else {
                        if (info.size() == 0) {
                            turn = !turn;
                        }
                        for (int i = 0; i < info.size(); i++) {
                            if (x == info.get(i)[0] && y == info.get(i)[1]) {
                                    if(sub(x,y)) {
                                        configure(x, y);
                                    }
                                    else
                                    {
                                        turn=!turn;
                                    }
                                    resetTimer();
                                break;
                            }
                            if((i == info.size() - 1))
                            {
                                turn=!turn;
                            }
                        }
                    }
                }
                turn = !turn;
                System.out.println(board1[0][3]);
                if (turn && white.inCheck(white.xcord, white.ycord)) {
                    if (white.checkMate()) {
                        JFrame checkMateMessage = new JFrame();
                        checkMateMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JOptionPane.showMessageDialog(checkMateMessage, "CheckMate! Black Wins!");
                    }
                }
                else if (!turn && black.inCheck(black.xcord, black.ycord)) {
                    if (black.checkMate()) {
                        JFrame checkMateMessage = new JFrame();
                        checkMateMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JOptionPane.showMessageDialog(checkMateMessage, "Checkmate! White Wins!");
                    }
                    System.out.println(board1[0][3]);
                } else if (white.staleMateCheck() || black.staleMateCheck()) {
                    JFrame stalemateMessage = new JFrame();
                    stalemateMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JOptionPane.showMessageDialog(stalemateMessage, "Stalemate! It's a tie!");
                }
                click = !click;
                board[xstore][ystore].setBackground(color);
            }
        else
            {
                if (board1[x][y] != null &&( (turn && board1[x][y].getSide()) || (!turn && !board1[x][y].getSide()))) {
                    color = board[x][y].getBackground();
                    board[x][y].setBackground(new Color(255, 0, 0));
                    blinka.addAll(board1[x][y].getMoves());
                    click = !click;
                    xstore = x;
                    ystore = y;
                    moveTime.start();
                }
            }
         /*   black.printKingProtect(!turn);
            System.out.println();
            System.out.println();*/
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
        private boolean sub(int x,int y)
        {
            Piece store=board1[xstore][ystore];
            Piece store2=board1[x][y];
            board1[x][y]=store;
            store.setCord(x,y);
            board1[xstore][ystore]=null;
                if((turn&&white.inCheck(white.xcord,white.ycord))||(!turn&&black.inCheck(black.xcord,black.ycord)))
                {
                    board1[xstore][ystore]=store;
                    board1[xstore][ystore].setCord(xstore,ystore);
                    board1[x][y]=store2;
                    return false;
                }
            board1[xstore][ystore]=store;
            board1[xstore][ystore].setCord(xstore,ystore);
            board1[x][y]=store2;
            return true;
        }
        public void configure(int x,int y)
        {
            if((x==7||x==0)&&(board1[xstore][ystore].equals(new Pawn(0,0,true))||board1[xstore][ystore].equals(new Pawn(0,0,false))))
            {
                class event1 implements ActionListener
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Piece[] promote;
                        if(!turn)
                        {
                            promote=whitePromotePawn;
                        }
                        else
                        {
                            promote=blackPromotePawn;
                        }
                        JButton store=(JButton)e.getSource();
                        for(int i=0;i<promote.length;i++)
                        {
                            if(promote[i].getName().equals(store.getText())) {
                                board1[xstore][ystore] = promote[i];
                                board[xstore][ystore].remove(0);
                                board[xstore][ystore].add(new JLabel(new ImageIcon(getClass().getResource(promote[i].getFile()))));
                                repaint();
                                revalidate();
                                configure(x,y);
                                break;
                            }
                        }
                    }
                }
                JFrame pickPiece=new JFrame();
                pickPiece.setLayout(new FlowLayout());
                pickPiece.setVisible(true);
                pickPiece.setSize(850,850);
                pickPiece.setTitle("Promote");
                pickPiece.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                event1 a=new event1();
                JButton knight=new JButton("Knight");
                knight.addActionListener(a);
                pickPiece.add(knight);
                JButton bishop=new JButton("Bishop");
                pickPiece.add(bishop);
                bishop.addActionListener(a);
                JButton queen=new JButton("Queen");
                queen.addActionListener(a);
                pickPiece.add(queen);
                JButton rook=new JButton("Rook");
                pickPiece.add(rook);
                rook.addActionListener(a);
                pickPiece.pack();
            }
            else {
                if((board1[xstore][ystore].equals(new Pawn(0,0,turn)))&&Math.abs(xstore-x)==2)
                {
                    if(y>=1&&board1[x][y-1]!=null&&board1[x][y-1].equals(new Pawn(0,0,!turn)))
                    {
                        Piece a=(Pawn)board1[x][y-1];
                            ((Pawn)a).setrENP(true);
                    }
                    else if(y<7&&board1[x][y+1]!=null&&board1[x][y+1].equals(new Pawn(0,0,!turn)))
                    {
                        Piece a=(Pawn)board1[x][y+1];
                            ((Pawn)a).setlENP(true);
                    }
                }
                board[xstore][ystore].remove(0);
                board[xstore][ystore].add(new JLabel());
                validate();
                Piece k=board1[xstore][ystore];
                if(k.equals(new Pawn(0,0,turn))&&Math.abs(xstore-x)==1&&Math.abs(ystore-y)==1&&board1[x][y]==null)
                {
                    if(turn)
                    {
                        board[x+1][y].remove(0);
                        board[x+1][y].add(new JLabel());
                        validate();
                        board1[x+1][y]=null;
                    }
                    else
                    {
                        board[x-1][y].remove(0);
                        board[x-1][y].add(new JLabel());
                        validate();
                        board1[x-1][y]=null;
                    }
                    repaint();
                    revalidate();
                }
                board[x][y].remove(0);
                board[x][y].add(new JLabel(new ImageIcon(getClass().getResource(board1[xstore][ystore].getFile()))));
                validate();
                k.setCord(x, y);
                board1[x][y] = k;
                board1[xstore][ystore] = null;
                Piece.offAllENP(turn);
                Piece.updateBoard(board1);
                resetTimer();
            }
        }
        private void resetTimer()
        {
            moveTime.stop();
            resetBoardSquares();
            blinka.clear();
        }
        private void resetBoardSquares()
        {
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    if(!ogc[i][j].equals(board[i][j].getBackground()))
                    {
                        board[i][j].setBackground(ogc[i][j]);
                    }
                }
            }
        }

        private boolean deepContains(ArrayList<Integer[]> list, Integer[] arr)
        {
            for(int i=0;i<list.size();i++)
            {
                if(Arrays.deepEquals(list.get(i),arr))
                {
                    return true;
                }
            }
            return false;
        }

    }
