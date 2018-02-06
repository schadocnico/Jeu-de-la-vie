package ProjetSemetre4;

public class Main {

    public static void main (String[] args){

        ListeChainee liste = new ListeChainee();

        Position cellule1= new Position(12,3);
        Position cellule2= new Position(12,8);
        //Position cellule3= new Position(15,10);


        liste.add(cellule1);
        liste.add(cellule2);
       // liste.add(cellule3);









        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++) {


                    if (liste.isEmpty()== false && liste.getTete().getLigne() == i && liste.getTete().getColonne() == j) {
                        System.out.print("[X]");
                        liste.suivant(); //penser a trier les maillons
                    } else {
                        System.out.print("[ ]");
                    }



            }
            System.out.println("");
        }
    }
}
