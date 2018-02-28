import java.lang.annotation.ElementType;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe d'une liste chainée avec un constructeur vide.
 * La classe ajoute les éléments dans l'ordre croissant grâce à l'interface Comparable.
 * Pour utiliser la liste avec un élément T, T doit avoir une implémentation de l'interface Comparator.
 * La sélection de plusieurs éléments marche grâce à l'interface fonctionnelle Sélection.
 * On ne peut pas supprimer d'éléments une fois ajoutés dans la liste.
 * @see Selection
 * @see java.util.Comparator
 * @param <T>
 */
public class ListeChainee<T extends Comparable>{ // A CHAGER EN <T>, COMMENT SAVOIR QUE T IMPLEMENTE COMPARABLE?
    private Maillon tete;
    private Maillon last;
    private int size;

    /**
     *  Construit une liste vide.
     */
    public ListeChainee(){
        this.tete = null;
        this.last = null;
        this.size=0;
    }
    /**
     * Retourne un <B>True</B> si la liste est vide sinon <B>False</B>.
     * @return
     */
    public boolean isEmpty(){
        return this.tete == null;
    }

    /**
     * Ajoute un objet à la liste chainée. L'ajout se fait par ordre croissant.
     *
     * @param cellule l'objet a ajouter
     **/

    public void add(T cellule){
        if (isEmpty()) {
            this.tete = new Maillon<>(cellule, null);
            this.last = this.tete;
            this.size++;
            return;
        }
        Maillon<T> selection = tete;
        //On regarde si le maillon suivant n'est pas null et que la value suivante est inferieur
        while (selection.getSuivant()!=null && (selection.getSuivant().getValeur().compareTo(cellule)<=0)) selection=selection.getSuivant();

        selection.setSuivant(new Maillon<>(cellule, selection.getSuivant()));
        if (selection.getSuivant() == null)
            this.last = selection;
        size++;
    }

    /**
     *  Retourne une <B>ListeChainee</B> qui répertorie tous les éléments dont l'interface retourne Vrai.
     * @param fun
     * @return une ListeChainee
     */

    public ListeChainee<T> selection(Selection<T> fun){
        Maillon<T> selection = tete;
        ListeChainee<T> liste = new ListeChainee<>();
        while (selection.getSuivant()!=null){
            if(fun.compareTo(selection.getValeur()))
                liste.add(selection.getValeur());
            selection = selection.getSuivant();
        }
        return liste;
    }

    public T premierElement(){
        if (tete == null)
            throw new IllegalArgumentException();
        return (T) this.tete.getValeur();
    }

    public T dernierElement(){
        if (last == null)
            throw new IllegalArgumentException();
        return (T) this.last.getValeur();
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        Maillon<T> selection = tete;
        String str = "[";
        while (selection.getSuivant()!=null){
            str += " " + selection.getValeur();
            selection = selection.getSuivant();
        }
        str+= " " + selection.getValeur()+ " ]";
        return str;
    }

    /**
     * Retourne un iterator des elements de la liste.
     * @return un iterator des elements de la liste.
     */
    public Iterator<T> iterator(){
        return new Itr();
    }

    // inner class Iterator >>>>>>
    private class Itr implements Iterator<T>{
        Maillon<T> maillon_cursor;// element to return

        public Itr(){
            maillon_cursor = ListeChainee.this.tete;
        }

        @Override
        public boolean hasNext() {
            return maillon_cursor != null;
        }

        @Override
        public T next() {
            if(maillon_cursor == null)
                throw new NoSuchElementException();
            T temp = maillon_cursor.getValeur(); // save element to return
            maillon_cursor = maillon_cursor.getSuivant(); // add next element into maillon_cursor
            return temp;
        }
    }

    // inner class Maillon >>>>>>
    private class Maillon<T extends Comparable> {

        private T value;
        private Maillon<T> next;

        public Maillon(T value, Maillon<T> next){
            this.value=value;
            this.next=next;
        }

        public T getValeur(){
            return value;
        }

        public void setValeur(T value) {
            this.value = value;
        }

        public void setSuivant(Maillon<T> next) {
            this.next = next;
        }

        public Maillon<T> getSuivant() {
            return next;
        }

    }
    //========
}
