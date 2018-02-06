package ProjetSemetre4;

public class Position {

    private int ligne;
    private int colonne;

    public Position(int ligne, int colonne){
        this.ligne=ligne;
        this.colonne=colonne;
    }

    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
}
