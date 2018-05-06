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
public class ListeChainee<T extends Comparable> implements Copy<ListeChainee<T>>{ // A CHAGER EN <T>, COMMENT SAVOIR QUE T IMPLEMENTE COMPARABLE?
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

    public void add(T cellule){
        if (isEmpty()) {
            this.tete = new Maillon<T>(cellule, null);
            this.last = this.tete;
            this.size++;
            return;
        }
        Maillon<T> selection = tete;

        if (tete.getValeur().compareTo(cellule)>0){
            this.tete = new Maillon<T>(cellule, tete);
            size++;
            return;
        } else if (tete.getValeur().equals(cellule)){
            ((Addition) tete.getValeur()).addition(cellule);
            return;
        }
        //On regarde si le maillon suivant n'est pas null et que la value suivante est inferieur
        while (selection.getSuivant()!=null && (selection.getSuivant().getValeur().compareTo(cellule)<0)){
            if (selection.getValeur().equals(cellule)) {
                ((Addition) selection.getValeur()).addition(cellule);
                return;
            }
            selection=selection.getSuivant();
        }
        if (selection.getSuivant()!=null && selection.getSuivant().getValeur().equals(cellule)) {
            ((Addition) selection.getSuivant().getValeur()).addition(cellule);
            return;
        }
        selection.setSuivant(new Maillon(cellule, selection.getSuivant()));
        if (selection.getSuivant() == null)
            this.last = selection;
        size++;
    }

    public ListeChainee<T> copy(){
        if (isEmpty()) return null;
        ListeChainee<T> list = new ListeChainee<>();
        list.size = this.size;

        Maillon<T> selection = new Maillon((T) ((Copy) tete.getValeur()).copy(), null);
        list.tete = selection;
        Maillon<T> suivant = this.tete.getSuivant();
        Maillon<T> newSuivant = null;
        while (suivant != null){
            newSuivant = new Maillon((T) ((Copy) suivant.getValeur()).copy(), null);
            selection.setSuivant(newSuivant);
            selection = newSuivant;
            suivant = suivant.getSuivant();
        }
        list.last = newSuivant;

        // on renvoie le clone
        return list;

    }

    public void supprimer(Selection<T> fun){
        if (this.tete==null)
            return;
        while (fun.compare((T) tete.getValeur())){
            if (tete.getSuivant()==null)
                return;
            tete = tete.getSuivant();
            size--;
        }
        Maillon<T> pre = tete;
        Maillon<T> selection = tete.getSuivant();
        ListeChainee<T> liste = new ListeChainee<>();
        while (selection!=null){
            if(fun.compare(selection.getValeur())){
                pre.setSuivant(selection.getSuivant());
                selection = tete.getSuivant();
                size--;
            }else {
                pre = selection;
                selection = selection.getSuivant();
            }
        }
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
        if (selection==null) return str+"]";
        while (selection.getSuivant()!=null){
            str += " " + selection.getValeur();
            selection = selection.getSuivant();
        }
        str+= " " + selection.getValeur()+ " ]";
        return str;
    }

    @Override
    public boolean equals(Object o){
        ListeChainee<T> list = (ListeChainee<T>)o;
        Iterator<T> it = ((ListeChainee<T>) o).iterator();
        Iterator<T> it2 = this.iterator();

        while (it.hasNext()){
            if (!it2.hasNext())
                return false;
            if (!it.next().equals(it2.next()))
                return false;
        }

        return true;
    }

    /**
     * Retourne un iterator des elements de la liste.
     * @return un iterator des elements de la liste.
     */
    public Itr iterator(){
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
    private class Maillon<T extends Comparable>{

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
