package ProjetSemetre4;

public class ListeChainee {

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

        else if( this.tete.getSuivant()==null ){ //cas ou il y a un seul maillon dans la liste

            if(cellule.getLigne()>this.tete.getValeur().getLigne()){ //si les lignes sont identique
                this.tete.suivant= new Maillon(cellule,null);
            }

            else if(cellule.getLigne()== this.tete.getValeur().getLigne() && cellule.getColonne()> this.tete.getValeur().getColonne()){ //si les lignes ne sont pas identique
                this.tete.suivant= new Maillon(cellule,null);
            }

            else {
                this.tete =  new Maillon(cellule, this.tete);
            }

        }


        //la suite de la methode add ne marche pas
        else if(this.tete.suivant!=null){ //cas ou il a deja plusieurs maillons dans la liste

            Maillon vMaillon = this.tete;
            Maillon vMaillon2 = null;

            while(vMaillon!=null) {


                if(cellule.getLigne() > vMaillon.getValeur().getLigne()) {
                    Maillon vMaillon3 = new Maillon(cellule,vMaillon);
                    vMaillon = vMaillon2;
                    vMaillon.setSuivant(vMaillon3);

                }

                vMaillon2 = vMaillon;
                vMaillon = vMaillon.suivant;

            }

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
