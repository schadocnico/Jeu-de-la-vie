/**
 * Created by nicolas.schad on 13/02/2018
 **/
public class Couple implements Comparable<Couple>{
    private int x;
    private int y;

    public Couple(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Couple o) {
        if (this.x==o.getX()){
            return this.y-getY();
        } else return this.x-getX();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }

}
