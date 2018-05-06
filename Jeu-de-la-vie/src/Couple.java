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
    
    /**
     *  Cree un couple avec un valeur X, Y et un nombre de voisins passé en parametre.
     * @param x
     * @param y
     * @param nbVoisins
     * @return une ListeChainee
     */
    public Couple(int x, int y, int nbVoisins){
        this.x=x;
        this.y=y;
        this.nbVoisins=nbVoisins;
    }

     /**
     *  Retourne le position de X d'une cellule.
     * @return un int
     */
    public int getX() {
        return x;
    }
    
    /**
     *  Change la valeur X d'une cellule.
     * @param x
     */
    public void setX(int x) {
        this.x=x;
    }

    /**
     *  Retourne le position Y d'une cellule.
     * @return un int
     */
    public int getY() {
        return y;
    }

    /**
     *  Change la valeur de Y d'une cellule.
     * @param y
     */
    public void setY(int y) {
        this.y=y;
    }

    /**
     *  Retourne le position le nombre de voisins d'une cellule.
     * @return un int
     */
    public int getNbVoisins(){return nbVoisins;}

    /**
     *  Change la valeur du nombre de voisins pour une cellule.
     * @param nbVoisins
     */
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
