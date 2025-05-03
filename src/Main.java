import cli.ConfigParser;
import fichier.*;
import java.util.Scanner;
import moteur.MoteurTraitement;

public class Main {
    public static void main(String[] args) {
        // Scanner pour lire les entrées de l'utilisateur
        try (Scanner sc = new Scanner(System.in)) {
            // Init du moteur de traitement
            MoteurTraitement moteur = new MoteurTraitement();
            // fichier à traiter et chemin de la config
            Fichier fichier = null;
            String configPath;

            OUTER:
            while (true) {
                // Affichage du menu
                System.out.println("1. Charger une configuration JSON");
                System.out.println("2. Appliquer les filtres");
                System.out.println("3. Annuler le dernier filtre");
                System.out.println("4. Sauvegarder le résultat");
                System.out.println("5. Quitter");
                System.out.print("> ");
                // Lecture du choix de l'utilisateur
                int choix = Integer.parseInt(sc.nextLine());
                try {
                    // Traitement du choix
                    switch (choix) {
                        case 1 -> {
                            // Chargement d'une configuration JSON
                            System.out.print("Chemin du fichier JSON : ");
                            configPath = sc.nextLine();
                            // Création du parser de configuration
                            ConfigParser conf = new ConfigParser(configPath);
                            String fichierEntree = conf.fichierEntree;
                            // Ouverture du fichier selon son type
                            if (fichierEntree.endsWith(".txt")) {
                                // fichier texte
                                fichier = new FichierTexte(fichierEntree);
                                fichier.ouvrir();
                            } else {
                                // fichier binaire
                                fichier = new FichierBinaire(fichierEntree);
                                fichier.ouvrir();
                            }   
                            // Création d'un nouveau moteur
                            moteur = new MoteurTraitement();
                            // Ajout des filtres dans le config
                            for (ConfigParser.FiltreConf fc : conf.filtres) {
                                System.out.println("Processing filter config: " + fc.nom);
                                moteur.ajouterFiltre(fc.nom, fc.parametres);
                            }   
                            System.out.println("Configuration chargée.");
                        }
                        case 2 -> {
                            // Application des filtres
                            moteur.appliquerFiltres(fichier);
                            System.out.println("Filtres appliqués.");
                        }
                        case 3 -> {
                            // Annulation des configs
                            moteur.annuler();
                            System.out.println("Annulation effectuée.");
                        }
                        case 4 -> {
                            // Sauvegarde res
                            System.out.print("Chemin du fichier de sortie : ");
                            String sortie = sc.nextLine();
                            moteur.getEtatCourant().getFichier().enregistrer(sortie);
                            System.out.println("Résultat sauvegardé.");
                        }
                        case 5 -> {
                            // Sortie au OUTER
                            break OUTER;
                        }
                        default -> {
                            // Invalide
                        }
                    }
                } catch (Exception e) {
                    // err
                    System.out.println("Erreur : " + e.getMessage());
                }
            }
        }
    }
} 
