/**
 * Created by nicolas.schad on 06/05/2018
 **/
public enum Comportement {
    //Objets directement construits
    Mort ("Mort : le jeu atteint la configuration sans cellule vivante."),
    Stable ("Stable : le jeu atteint une configuration qui n’évolue plus."),
    Oscillateur ("Oscillateur : le jeu atteint une configuration que l’on retrouve à intervalle régulier."),
    Vaisseau ("Vaisseau : le jeu atteint une configuration que l’on retrouve à intervalle régulier mais déplacé d’un certain nombre de lignes et de colonnes."),
    Inconnu ("Inconnu : type inconnu.");

    private String name = "";
    private int queue;
    private int periode;
    private Couple deplacement;

    //Constructeur
    Comportement(String name){
        this.name = name;
        queue=0;
        periode=0;
        deplacement=new Couple(0,0);
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public void setDeplacement(Couple deplacement) {
        this.deplacement = deplacement;
    }

    public String toString(){
        return name + " Queue : " + queue + " Periode : " + periode + " Deplacement : (" + deplacement.getX() + ","+ deplacement.getY()+")";
    }
}
