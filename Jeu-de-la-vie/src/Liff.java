import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;import java.io.IOException;

public class Liff {
    public static ListeChainee<Couple> lecture(String name) throws IOException {
        ListeChainee<Couple>listStart = new ListeChainee<>();  //liste pour enregistrer toutes les positions des *
        try
        {
            File f = new File (name);     //nom du fichier, pas de chemin car a la racine du projet
            FileReader fr = new FileReader (f);
            BufferedReader br = new BufferedReader (fr);

            try
            {
                String line = br.readLine();  //pour lire le fichier ligne à ligne
                int x=0;
                int y=0;
                int xdeux =0; //variable pour enregistrer x plus tard

                while (line != null)  //readLine renvoi null quand il n'y a plus de ligne a lire dans le fichier(ce qui permet del ire le fichier ligne a ligne)
                {
                    for (int i = 0 ; i < line.length() ;i++) {  //pour parcourir la ligne courante(line) caractère par caractère

                        if((line.charAt(i) == '#')&&(line.charAt(i+1) == 'P') ) {   //Dans le format LIF si la ligne commence par #P cela annonce la position initale du prochain pattern

                            String coor[]  = line.split(" ");   //permet de découper la ligne courante, pour récupérer seulement la dite position
                            x = Integer.parseInt(coor[1]);  //split renvoi un string, mais nous avons besoin d'un int pour effectuer des opérations élémentaires, simple converstion String --> int
                            xdeux=x;  //enregistrement de la valeur x, car elle sera incrémentée plus tard mais nous avons besoin de sa valeur de base a chaque nouvelle itération du while
                            y = Integer.parseInt(coor[2])+1;
                            //System.out.println(x);
                            //System.out.println(y-1);
                            break;  //permet de sortir du for, pour ne pas évaluer les autre caractère de cette ligne(economie de ressources) car une ligne commencanr par #P est un cas spécial, pas besoin de lire chaque caractère
                            // pour passer directement a la prochaine ligne
                        }
                        else{
                            if(line.charAt(i) == '*') {  //si a la lecture caractère par caractère, un * est détecter, il faut enregistrer sa "position"
                                //System.out.print(line.charAt(i));
                                //System.out.print("(" + x +"/"+ y +")");

                                listStart.add(new Couple(x,y));  //enregistre la position de l' * courante sous forme d'un couple (x,y)

                            }}
                        x++;}

                    //System.out.println("");
                    line = br.readLine();  //passage a la ligne suivante
                    y--;
                    x=xdeux;  //remise de x a sa valeur initiale
                }

                br.close();
                fr.close();
            }
            catch (IOException exception)
            {
                throw new IOException("Erreur lors de la lecture");
            }
        }
        catch (FileNotFoundException exception)
        {
            throw new FileNotFoundException("Le fichier n'a pas été trouvé.");
        }
        listStart = Generation2.calculeVoisins(listStart);// A LAISSER, PREMIER CALCULE
        return listStart;
    }
}
