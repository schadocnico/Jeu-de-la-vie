/**
 * Created by nicolas.schad on 13/02/2018
 **/
public class Couple implements Comparable<Couple>, Addition<Object>, Copy<Couple>{
    private int x;
    private int y;
    private int nbVoisins;

    public Couple(int x, int y){
        this(x,y,0);
    }
    public Couple(int x, int y, int nbVoisins){
        this.x=x;
        this.y=y;
        this.nbVoisins=nbVoisins;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNbVoisins(){return nbVoisins;}

    public void setNbVoisins(int nbVoisins){this.nbVoisins = nbVoisins;}

    @Override
    public int compareTo(Couple o) {
        if (this.x==o.getX()){
            return this.y-o.getY();
        } else return this.x-o.getX();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Couple)obj).getX()==this.getX() && ((Couple)obj).getY()==this.getY();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + nbVoisins +")";
    }

    @Override
    public void addition(Object o) {
        if (o.getClass().getSimpleName().equals("Couple")){
            nbVoisins += ((Couple)o).getNbVoisins();
        } else if (o.getClass().getSimpleName().equals("Integer")){
            nbVoisins += (Integer)o;
            System.out.println("Int");
        } else {
            addition(o);
            System.out.println("Autre");
        }
    }

    @Override
    public Couple copy() {
        return new Couple(x,y,nbVoisins);
    }

}
