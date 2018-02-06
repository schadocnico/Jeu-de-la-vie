package ProjetSemetre4;

public class ListeChainee<T> {

    private Maillon tete;

    public ListeChainee(){
        this.tete = null;
    }

    public boolean isEmpty(){
        return this.tete == null;
    }


    public void add(Position cellule){

        if (isEmpty()==true) { //cas ou la liste est vide
            this.tete =  new Maillon(cellule, this.tete);
        }


    }



    public boolean contains(Position cellule){

        Maillon vMaillon = this.tete;
        while(vMaillon != null){

            if(vMaillon.getValeur().getLigne() == cellule.getLigne() && vMaillon.getValeur().getColonne() == cellule.getColonne()){
                return true;
            }

            vMaillon = vMaillon.suivant;
        }

        return false;
    }



    public void removeTete(){
        if(this.tete == null){
            System.out.println("la liste est vide");
        } else {
            this.tete = this.tete.suivant;
        }

    }

    public  Position getTete(){
        return this.tete.valeur;
    }

    public void suivant(){
        this.tete=tete.getSuivant();
    }
}
