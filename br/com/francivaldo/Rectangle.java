package br.com.francivaldo;
public class Rectangle{
    public int x,y,w,h;
    public Rectangle(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public boolean Contains(Rectangle rec){
        int cX0,cY0;
        int cX1,cY1;
        int allw,allh;
        cX0 = this.x + (this.w/2);
        cY0 = this.y + (this.h/2);
        cX1 = rec.x + (rec.w/2);
        cY1 = rec.y + (rec.h/2);
        allw = (rec.w + this.w)/2;
        allh = (rec.h + this.h)/2;
        if(Abs(cX0, cX1) > allw || Abs(cY0, cY1) > allh)
            return false;
        return true;
    }
    public boolean Contains(Vector2 vec){
        int cX0,cY0;
        int cX1,cY1;
        int allw,allh;
        cX0 = this.x + (this.w/2);
        cY0 = this.y + (this.h/2);
        cX1 = vec.x;
        cY1 = vec.y;
        allw = this.w/2;
        allh = this.h/2;
        if(Abs(cX0, cX1) > allw || Abs(cY0, cY1) > allh)
            return false;
        return true;
    }
    private int Abs(int n0,int n1){
        int res = n1 - n0;
        if(res < 0)
            return res * -1;
        return res;
    }
}