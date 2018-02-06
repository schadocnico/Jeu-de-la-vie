package ProjetSemetre4;

public class Maillon {

     Position valeur;
     Maillon suivant;

    public Maillon(Position valeur, Maillon suivant){
        this.valeur=valeur;
        this.suivant=suivant;
    }

    public Maillon getSuivant(){
        return suivant;
    }

    public void setSuivant(Maillon suivant){
        this.suivant=suivant;
    }

    public Position getValeur(){
        return valeur;
    }

    public void setValeur(Position valeur) {
        this.valeur = valeur;
    }
}
