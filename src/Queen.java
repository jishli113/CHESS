public class Queen extends Piece {
    public Queen(int xcord,int ycord,boolean side)
    {
        super(xcord,ycord,side);
        this.xcord=xcord;
        this.ycord=ycord;
        this.side=side;
        this.name="Queen";
        if(side)
        {
            this.file="WhitePiecesIMG/WQ.gif";
        }
        else
        {
            this.file="BlackPiecesIMG/BQ.gif";
        }
    }
    public void move()
    {
        int x=this.xcord;
        int y=this.ycord;
        boolean test=true;
        x--;
        y++;
        while (x >= 0 && y <= 7 && test) {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            x--;
            y++;
        }
        x = this.xcord;
        y = this.ycord;
        x--;
        y--;
        test = true;
        //2nd
        while (x >= 0 && y >=0 && test) {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            x--;
            y--;
        }
        x = this.xcord;
        y = this.ycord;
        test = true;
        //3rd
        x++;
        y++;
        while (x <= 7 && y <= 7 && test) {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            x++;
            y++;
        }
        x = this.xcord;
        y = this.ycord;
        test = true;
        //4th
        x++;
        y--;
        while (x <= 7 && y >= 0 && test) {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            x++;
            y--;
        }
        test=true;
        x = this.xcord;
        y = this.ycord;
        x--;
        while(x>=0&&test)
        {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            x--;
        }
        test=true;
        x=this.xcord;
        //2nd
        x++;
        while(x<=7&&test)
        {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            x++;
        }
        x=this.xcord;
        test=true;
        y--;
        //3rd
        while(y>=0&&test)
        {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            y--;
        }
        y=this.ycord;
        y++;
        test=true;
        //4th
        while(y<=7&&test)
        {
            test=decideMove(x,y,board,this.xcord,this.ycord);
            if(test) {
                this.moves.add(new Integer[]{x, y});
            }
            y++;
        }
    }
}
