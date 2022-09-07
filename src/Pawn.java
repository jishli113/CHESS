import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean lENP=false;
    private boolean rENP=false;
    public Pawn(int xcord,int ycord,boolean side)
    {
        super(xcord,ycord,side);
        this.xcord=xcord;
        this.ycord=ycord;
        this.side=side;
        if(side)
        {
            this.file="WhitePiecesIMG/WP.gif";
        }
        else
        {
            this.file="BlackPiecesIMG/BP.gif";
        }
    }
    public void move()
    {
        if(this.lENP)
        {
            if(this.side)
            {
                this.moves.add(new Integer[]{this.xcord-1,this.ycord-1});
            }
            else
            {
                this.moves.add(new Integer[]{this.xcord+1,this.ycord-1});
            }
        }
        else if(this.rENP)
        {
            if(this.side)
            {
                this.moves.add(new Integer[]{this.xcord-1,this.ycord+1});
            }
            else {
                this.moves.add(new Integer[]{this.xcord + 1, this.ycord + 1});
            }
        }
        int x=this.xcord;
        int y=this.ycord;
        if(side&&xcord==6&&board[x-2][y]==null&&board[x-1][y]==null)
        {
            this.moves.add(new Integer[]{4,y});
        }
        else if(!side&&xcord==1&&board[x+2][y]==null&&board[x+1][y]==null)
        {
            this.moves.add(new Integer[]{3,y});
        }
        if(side&&board[x-1][y]==null)
        {
            this.moves.add(new Integer[]{x-1,y});
        }
        else if(!side&&board[x+1][y]==null)
        {
            this.moves.add(new Integer[]{x+1,y});
        }
        if(!side)
        {
            decideMove(x+1,y+1,board);
            decideMove(x+1,y-1,board);
        }
        else
        {
            decideMove(x-1,y+1,board);
            decideMove(x-1,y-1,board);
        }
    }
    public void decideMove(int x, int y, Piece[][] board) {
    if(x>=0&&y<=7&&x<=7&&y>=0&&board[x][y]!=null) {
            if (board[x][y].getSide() != this.side) {
                moves.add(new Integer[]{x, y});
            } else if (board[x][y].getSide() == this.side) {
                ArrayList<Integer> ret = new ArrayList<>();
                ret.add(x);
                ret.add(y);
                protect.add(ret);
            }
        }
    }
    public boolean getlENP()
    {
        return this.lENP;
    }
    public boolean getrENP()
    {
        return this.rENP;
    }
    public void setlENP(boolean set)
    {
        this.lENP=set;
    }
    public void setrENP(boolean set)
    {
        this.rENP=set;
    }


}
