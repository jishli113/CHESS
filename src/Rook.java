public class Rook extends Piece {
    private boolean ifMoved=false;
    private int ogx;
    private int ogy;
    public Rook(int xcord,int ycord,boolean side)
    {
        super(xcord,ycord,side);
        this.xcord=xcord;
        this.ycord=ycord;
        this.ogy=ycord;
        this.ogx=xcord;
        this.side=side;
        this.name="Rook";
        if(side)
        {
            this.file="WhitePiecesIMG/WR.gif";
        }
        else
        {
            this.file="BlackPiecesIMG/BR.gif";
        }
    }
    public boolean getIfMoved()
    {
        return this.ifMoved;
    }
    public void checkMoved()
    {
        if(!this.ifMoved)
        {
            if(this.xcord!=ogx||this.ycord!=ogy)
            {
                this.ifMoved=true;
            }
        }
    }
    public void move()
    {
        if(this.xcord!=this.ogx&&this.ycord!=this.ogy)
        {
            this.ifMoved=true;
        }
        int x=this.xcord;
        int y=this.ycord;
        boolean check=true;
        //1st
        x--;
        while(x>=0&&check)
        {
            check=decideMove(x,y,board,this.xcord,this.ycord);
            if(check)
            {
                this.moves.add(new Integer[]{x,y});
            }
            x--;
        }
        check=true;
        x=this.xcord;
        //2nd
        x++;
        while(x<=7&&check)
        {
            check=decideMove(x,y,board,this.xcord,this.ycord);
            if(check)
            {
                this.moves.add(new Integer[]{x,y});
            }
            x++;
        }
        x=this.xcord;
        check=true;
        y--;
        //3rd
        while(y>=0&&check)
        {
            check=decideMove(x,y,board,this.xcord,this.ycord);
            if(check)
            {
                this.moves.add(new Integer[]{x,y});
            }
            y--;
        }
        y=this.ycord;
        y++;
        check=true;
        //4th
        while(y<=7&&check)
        {
            check=decideMove(x,y,board,this.xcord,this.ycord);
            if(check)
            {
                this.moves.add(new Integer[]{x,y});
            }
            y++;
        }
    }

    public void setMoved(boolean k)
    {
        k=this.ifMoved;
    }
}
