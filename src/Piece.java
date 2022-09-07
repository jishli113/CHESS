import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {
    protected  ArrayList<Integer[]> moves=new ArrayList<>();
    protected static ArrayList<ArrayList<Integer>> whiteKingProtect=new ArrayList<>();
    protected static ArrayList<ArrayList<Integer>> blackKingProtect=new ArrayList<>();
    protected int xcord;
    protected int ycord;
    protected boolean side;
    protected static ArrayList<ArrayList<Integer>> protect=new ArrayList<>();
    protected static  Piece[][]board;
    protected String file;
    protected String name;
    protected static King black;
    protected static King white;
    public static void updateBoard(Piece[][] k)
    {
        board=k;
    }
    public Piece(int xcord,int ycord, boolean side)
    {
        this.xcord=xcord;
        this.ycord=ycord;
        this.side=side;
        this.file="";
    }
    public static void updateProtect()
    {
        protect.clear();
        whiteKingProtect.clear();
        blackKingProtect.clear();
        ArrayList<Integer> store=new ArrayList<>();
        store.add(6);
        store.add(4);
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(board[i][j]!=null)
                {
                    board[i][j].move();
                }
            }
        }
        clearAllMoves();
    }
    protected static void clearAllMoves() {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(board[i][j]!=null)
                {
                    board[i][j].clearMoves();
                }
            }
        }
    }
    public String getName()
    {
        return this.name;
    }
    public static ArrayList<Integer[]> getAllMoves(boolean side)
    {
        protect.clear();
        clearAllMoves(side);
        ArrayList<Integer[]> ret=new ArrayList<>();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(board[i][j]!=null)
                {
                    if(side==board[i][j].getSide()){
                        board[i][j].move();
                        ArrayList<Integer[]> store = board[i][j].getMoves();
                        if(store.size()>0) {
                            ret.addAll(store);
                            board[i][j].clearMoves();
                        }
                    }
                }
            }
        }
        return ret;
    }
    public static ArrayList<Integer[]> allMovesNoPawn(boolean side)
    {
        clearAllMoves(side);
        protect.clear();
        ArrayList<Integer[]> ret=new ArrayList<>();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(board[i][j]!=null&&(!board[i][j].equals(new King(0,0,true))&&!board[i][j].equals(new King(0,0,false))))
                {
                    if((side==board[i][j].getSide())){
                        board[i][j].move();
                            ArrayList<Integer[]> store = board[i][j].getMoves();
                            if (board[i][j].equals(new Pawn(0, 0, true)) || board[i][j].equals(new Pawn(0, 0, false))) {
                                for (int a = 0; a < store.size(); a++) {
                                    if (Arrays.equals(store.get(a), new Integer[]{i - 1, j}) || Arrays.equals(store.get(a), new Integer[]{i - 2, j})) {
                                        store.remove(a);
                                        a--;
                                    } else if (Arrays.equals(store.get(a), new Integer[]{i + 1, j}) || Arrays.equals(store.get(a), new Integer[]{i + 2, j})) {
                                        store.remove(a);
                                        a--;
                                    }
                                }
                            }
                            ret.addAll(store);
                            board[i][j].clearMoves();
                    }
                }
            }
        }
        return ret;
    }
    public boolean decideMove(int x,int y,Piece[][]board,int x2,int y2)
    {
        if(board[x][y]!=null) {
            if (board[x][y].getSide() != this.side) {
                if(!deepContains(this.moves,new Integer[]{x,y})) {
                    board[x2][y2].addMove(new Integer[]{x, y});
                }
            } else if (board[x][y].getSide() == this.side) {
                ArrayList<Integer> ret=new ArrayList<>();
                ret.add(x);
                ret.add(y);
                    protect.add(ret);
            }
            return false;
        }
        else if(board[x][y]==null)
        {
            return true;
        }
       return false;
    }
    public void addMove(Integer[] add)
    {
        this.moves.add(add);
    }
    abstract void move();
    public int getX(){
        return this.xcord;
    }
    public int getY()
    {
        return this.ycord;
    }
    public  ArrayList<Integer[]> getMoves()
    {
        this.move();
        ArrayList<Integer[]> store=this.moves;
        if(store.size()<=0)
        {
            return new ArrayList<>();
        }
        return store;
    }
    public String getFile()
    {
        return this.file;
    }
    public void setCord(int x,int y)
    {
        this.xcord=x;
        this.ycord=y;
    }
    public void clearMoves()
    {
        moves.clear();
    }
    public static void clearAllMoves(boolean side)
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(board[i][j]!=null&&board[i][j].getSide()==side)
                {
                    board[i][j].clearMoves();
                }
            }
        }
    }
    public static void setBlack(King b)
    {
        black=b;
    }
    public static void setWhite(King w)
    {
        white=w;
    }
    public boolean getSide()
    {
        return this.side;
    }
    public boolean equals(Object obj) {
        Piece k=(Piece) obj;
        if(k.getFile().equals(this.getFile()))
        {
            return true;
        }
        return false;
    }
    private boolean deepContains(ArrayList<Integer[]> k, Integer[] check)
    {
        for(int i=0;i<k.size();i++)
        {
            if(Arrays.deepEquals(k.get(i),check))
            {
                return true;
            }
        }
        return false;
    }
    public static void offAllENP(boolean side)
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(board[i][j]!=null&&board[i][j].equals(new Pawn(0,0,side)))
                {
                    Piece k=(Pawn)board[i][j];
                    ((Pawn) k).setlENP(false);
                    ((Pawn) k).setrENP(false);
                }
            }
        }
    }

}

