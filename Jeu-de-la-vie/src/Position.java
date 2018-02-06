package ProjetSemetre4;

public class Position implements Comparable<Position>{

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

    public int compareTo(Position o){

        //0 = égale;
        //1 = le parametre est plus grand
        //-1 = le parametre est plus petit

        if(this.getLigne() == o.getLigne() && this.getColonne() == o.getColonne()){
            return 0;
        }

        else if(this.getLigne()<o.getLigne()) { //le numéro de ligne du parametre est plus grand
            return 1;
        }

        else if(this.getLigne()>o.getLigne()){ // le numero du ligne du parametre est plus petit
            return -1;
        }

        else if(this.getLigne() == o.getLigne() && this.getColonne()<o.getColonne()){ //paramètre a un numero de colonne plus grand
            return 1;
        }


        return -1; //si aucune des conditions au dessus n'est verifié (même numero de ligne mais paramatre a un numero de colonne plus petit)

    }
}
