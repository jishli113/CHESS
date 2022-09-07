public class Bishop extends Piece {
    public Bishop(int xcord,int ycord,boolean side)
    {
        super(xcord,ycord,side);
        this.xcord=xcord;
        this.ycord=ycord;
        this.side=side;
        this.name="Bishop";
        if(side)
        {
            this.file="WhitePiecesIMG/WB.gif";
        }
        else
        {
            this.file="BlackPiecesIMG/BB.gif";
        }
    }
    public void move() {
        int x = this.xcord;
        int y = this.ycord;
        boolean test = true;
        //1st
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
    }
}
