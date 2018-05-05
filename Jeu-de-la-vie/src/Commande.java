import java.io.BufferedReader;import java.io.File;import java.io.FileNotFoundException;import java.io.FileReader;import java.io.IOException;


class ArgumentException extends Exception{ 
  public ArgumentException(){
    System.out.println("L'argument entré est incorrecte, option -h pour avoir la liste des options");
  }  
}

public class Commande {
	
	throws  ArgumentException{
	if(args[1].equals("-name")){
		System.out.println("Arnaud Pasquier / Nicolas Schad / Thibaut Maziere");
	}
	else
	if(args[1].equals("-h")){
		System.out.println(" -name: affiche les noms et prénoms");
		System.out.println(" -h : affiche la liste des options du programme");
		System.out.println(" -s d fichier.lif : exécute une simulation du jeu d une durée d affichant les configurations du jeu avec le numéro de génération");
		System.out.println(" -c max fichier.lif : calcule le type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul. ");
		System.out.println(" -w max dossier : calcule le type d’évolution de tous les jeux contenus dans le dossier passé en paramètre");
	}
	else
	if(args[1].equals("-c") && args[2].equals("max")){
		
	}
	else
	if(args[1].equals("-w") && args[2].equals("max")){
		
	}
	else 
	if(args[1].equals("-s") && args[2].equals("d")){
		
	}
	else {
	throw new ArgumentException();
	}
	
	}
}
