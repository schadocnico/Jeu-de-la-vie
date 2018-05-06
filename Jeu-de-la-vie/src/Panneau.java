/*
  Created by nicolas.schad on 27/02/2018
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    private ListeChainee<Couple> list = new ListeChainee();
    private int taille_cellule = 10; // VARIER SELON LE NOMBRE DE CELLULE D'UNE GENERATION ???

    public Panneau() {
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.darkGray);
        int middle_x = this.round(this.getWidth() / 2);
        g.drawLine(middle_x + 1, 0, middle_x + 1, this.getHeight());
        g.drawLine(middle_x - 1, 0, middle_x - 1, this.getHeight());
        int middle_y = this.round(this.getHeight() / 2);
        g.drawLine(0, middle_y + 1, this.getWidth(), middle_y + 1);
        g.drawLine(0, middle_y - 1, this.getWidth(), middle_y - 1);

        int y;
        for(y = 0; y <= this.getWidth(); y += this.taille_cellule + 1) g.drawLine(y, 0, y, this.getHeight());

        for(y = 0; y <= this.getHeight(); y += this.taille_cellule + 1) g.drawLine(0, y, this.getWidth(), y);

        g.setColor(Color.white);
        Iterator it = this.list.iterator();
        g.setColor(Color.BLUE);
        g.fillRect(Generation2.gauche * this.taille_cellule + 1 + Generation2.gauche + middle_x, Generation2.haut * this.taille_cellule + 1 + Generation2.haut + middle_x, this.taille_cellule, this.taille_cellule);
        g.fillRect(Generation2.droite * this.taille_cellule + 1 + Generation2.droite + middle_x, Generation2.bas * this.taille_cellule + 1 + Generation2.bas + middle_x, this.taille_cellule, this.taille_cellule);
        while(it.hasNext()) {
            Couple c = (Couple)it.next();
            if (c.getNbVoisins()>=10){
                g.setColor(Color.WHITE);
                int pos_x = c.getX();
                int pos_y = c.getY();
                g.fillRect(pos_x * this.taille_cellule + 1 + pos_x + middle_x, pos_y * this.taille_cellule + 1 + pos_y + middle_y, this.taille_cellule, this.taille_cellule);
            }


        }

    }

    private int round(int nb) {
        return nb - nb % (this.taille_cellule + 1);
    }

    public void setList(ListeChainee<Couple> list) {
        this.list = list;
    }
}

