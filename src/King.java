import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class King extends Piece {
    private int ogx;
    private int ogy;
    private boolean ifMoved=false;
    public King(int xcord,int ycord,boolean side)
    {
        super(xcord,ycord,side);
        this.xcord=xcord;
        this.ycord=ycord;
        this.side=side;
        this.ogy=ycord;
        this.ogx=xcord;
        if(side)
        {
            file="WhitePiecesIMG/WK.gif";
        }
        else
        {
            file="BlackPiecesIMG/BK.gif";
        }
    }
    public boolean checkMate()
    {
        this.move();
        if(this.moves.size()==0)
        {
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getSide() == this.side) {
                    Piece store = board[i][j];
                    if (board[i][j].getMoves().size()>0) {
                        for (int a = 0; a < moves.size(); a++) {
                            int x = moves.get(a)[0];
                            int y = moves.get(a)[1];
                            Piece store2 = board[x][y];
                            board[x][y] = store;
                            store.setCord(x, y);
                            board[i][j]=null;
                            if (!inCheck(this.xcord, this.ycord)) {
                                board[x][y] = store2;
                                board[i][j] = store;
                                board[i][j].setCord(i, j);
                                updateProtect();
                                return false;
                            }
                            board[x][y] = store2;
                            board[i][j] = store;
                            board[i][j].setCord(i, j);
                        }
                    }
                }
            }
        }
            updateProtect();
            return true;
        }
        this.clearMoves();
        updateProtect();
        return false;
    }
    @Override
    void move() {
            if(this.xcord!=this.ogx||this.ycord!=this.ogy)
            {
                this.ifMoved=true;
            }
        if(side&&!ifMoved)
        {
            if(board[7][0]!=null&&board[7][0].equals(new Rook(0,0,true)))
            {
                Rook store=(Rook)board[7][0];
                if(!store.getIfMoved()&&board[7][1]==null&&board[7][2]==null&&board[7][3]==null&&!inCheck(7,1)&&!inCheck(7,2)&&!inCheck(7,3))
                {
                    moves.add(new Integer[]{7,2});
                }
            }
            if(board[7][7]!=null&&board[7][7].equals(new Rook(0,0,true)))
            {
                Rook store=(Rook)board[7][7];
                if(!store.getIfMoved()&&board[7][6]==null&&board[7][5]==null&&!inCheck(7,6)&&!inCheck(7,5))
                {
                    moves.add(new Integer[]{7,6});
                }
            }
        }
        else if(!ifMoved)
        {
            if(board[0][0]!=null&&board[0][0].equals(new Rook(0,0,false)))
            {
                Rook store=(Rook)board[0][0];
                if(!store.getIfMoved()&&board[0][1]==null&&board[0][2]==null&&board[0][3]==null&&!inCheck(0,1)&&!inCheck(0,2)&&!inCheck(0,3))
                {
                    moves.add(new Integer[]{0,2});
                }

            }
            if(board[0][7]!=null&&board[0][7].equals(new Rook(0,0,false)))
            {
                Rook store=(Rook)board[0][7];
                if(!store.getIfMoved()&&board[0][6]==null&&board[0][5]==null&&!inCheck(0,6)&&!inCheck(0,5))
                {
                    moves.add(new Integer[]{0,6});
                }

            }
        }
        Queue<Integer[]> q=new LinkedList<>();
        q.add(new Integer[]{this.xcord+1,this.ycord});
        q.add(new Integer[]{this.xcord-1,this.ycord});
        q.add(new Integer[]{this.xcord+1,this.ycord+1});
        q.add(new Integer[]{this.xcord+1,this.ycord-1});
        q.add(new Integer[]{this.xcord-1,this.ycord+1});
        q.add(new Integer[]{this.xcord-1,this.ycord-1});
        q.add(new Integer[]{this.xcord,this.ycord+1});
        q.add(new Integer[]{this.xcord,this.ycord-1});
        while(!q.isEmpty())
        {
            int x=q.element()[0];
            int y=q.element()[1];
            q.remove();
            ArrayList<Integer> store=new ArrayList<>();
            store.add(x);
            store.add(y);
            if(x<0||x>=8||y<0||y>=8)
            {
                continue;
            }
            if(board[x][y]==null||board[x][y].getSide()==this.side)
            {
                if(side) {
                    whiteKingProtect.add(store);
                }
                else
                {
                    blackKingProtect.add(store);
                }
            }
                if((!inCheck(x,y))&&((board[x][y]==null)||(board[x][y]!=null&&board[x][y].getSide()!=this.side))&&((this.side&&!blackKingProtect.contains(store)||(!this.side&&!whiteKingProtect.contains(store))))&&!protect.contains(store)&&kingDecideMove(x,y)) {
                    moves.add(new Integer[]{x, y});
                }
        }
    }
    public boolean inCheck(int x,int y)
    {
            ArrayList<Integer[]> store=Piece.allMovesNoPawn(!this.side);
            for(int i=0;i<store.size();i++)
            {
                if(store.get(i)[0]==x&&store.get(i)[1]==y)
                {
                    clearAllMoves(!this.side);
                    return true;
                }
            }
            clearAllMoves(!this.side);
            return false;
    }
    public boolean staleMateCheck()
    {
        ArrayList<Integer[]> store=Piece.getAllMoves(this.side);
        if(store.size()==0&&!inCheck(this.xcord,this.ycord)) {
            return true;
        }
        Piece.clearAllMoves();
        return false;
    }

    public boolean kingDecideMove(int x,int y)
    {
        if(board[x][y]!=null&&board[x][y].getSide()==this.side)
        {
            return false;
        }
        Piece p1=board[x][y];
        Piece king=board[this.xcord][this.ycord];
        int kx=this.xcord;
        int ky=this.ycord;
        board[x][y]=king;
        board[kx][ky]=null;
        king.setCord(x,y);
        boolean ret=!inCheck(x,y);
        board[x][y] = p1;
        board[kx][ky] = king;
        king.setCord(kx, ky);
        return ret;
    }

}
