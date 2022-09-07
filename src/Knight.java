import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int xcord,int ycord,boolean side)
    {
        super(xcord,ycord,side);
        this.xcord=xcord;
        this.ycord=ycord;
        this.side=side;
        this.name="Knight";
        if(side)
        {
            this.file="WhitePiecesIMG/WN.gif";
        }
        else
        {
            this.file="BlackPiecesIMG/BN.gif";
        }
    }
    public void move()
    {
        int x=this.xcord;
        int y=this.ycord;
        //1st
        x=x-2;
        y=y+1;
        if(x>=0&&y<=7)
        {
            decideMove(x,y,board);
        }
        //2nd
        x=this.xcord;
        y=this.ycord;
        x=x-2;
        y=y-1;
        if(x>=0&&y>=0)
        {
            decideMove(x,y,board);
        }
        x=this.xcord;
        y=this.ycord;
        //3rd
        x=x-1;
        y=y-2;
        if(x>=0&&y>=0)
        {
            decideMove(x,y,board);
        }
        x=this.xcord;
        y=this.ycord;
        y=y+2;
        x=x-1;
        //4th
        if(x>=0&&y<=7)
        {
            decideMove(x,y,board);
        }
        x=this.xcord;
        y=this.ycord;
        x=x+1;
        y=y+2;
        //5th
        if(x<=7&&y<=7)
        {
            decideMove(x,y,board);
        }
        x=this.xcord;
        y=this.ycord;
        y=y-2;
        x=x+1;
        //6th
        if(x<=7&&y>=0)
        {
            decideMove(x,y,board);
        }
        x=this.xcord;
        y=this.ycord;
        x=x+2;
        y=y+1;
        //7th
        if(x<=7&&y<=7)
        {
            decideMove(x,y,board);
        }
        x=this.xcord;
        y=this.ycord;
        x=x+2;
        y=y-1;
        //8th
        if(x<=7&&y>=0)
        {
            decideMove(x,y,board);
        }
    }
    public void decideMove(int x, int y, Piece[][] board) {
        if((board[x][y]==null)||(board[x][y].getSide()!=this.side))
        {
            moves.add(new Integer[]{x,y});
        }
        else if (board[x][y].getSide() == this.side) {
            ArrayList<Integer> ret=new ArrayList<>();
            ret.add(x);
            ret.add(y);
            protect.add(ret);
        }
    }
}
