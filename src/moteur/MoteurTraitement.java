package moteur;

import fichier.Fichier;
import filtre.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// Gère le traitement des fichiers avec différents filtres
// Applique des filtres en séquence et gère l'historique des modifications
public class MoteurTraitement {
    private final ArrayList<Filtre> filtres;      
    private final Stack<EtatFichier> historique; 

    public MoteurTraitement() {
        filtres = new ArrayList<>();
        historique = new Stack<>();
    }

    // Ajoute un filtre à la liste
    public void ajouterFiltre(String nom, Map<String, Object> parametres) {
        Filtre f = creerFiltre(nom, parametres);
        if (f != null) {
            filtres.add(f);
        }
    }

    // Nouveau filtre selon le nom et ses params
    private Filtre creerFiltre(String nom, Map<String, Object> parametres) {
        switch (nom) {
            case "SuppressionVoyelles" -> {
                return new SuppressionVoyelles();
            }
            case "MotsInterdits" -> {
                List<String> mots = (List<String>) parametres.get("mots");
                return new MotsInterdits(mots);
            }
            case "ChiffrementCesar" -> {
                int decalage = (Integer) parametres.get("decalage");
                return new ChiffrementCesar(decalage);
            }
            case "FiltreTexteMajuscules" -> {
                return new FiltreTexteMajuscules();
            }
            case "InversionOctets" -> {
                return new InversionOctets();
            }
            case "XOR" -> {
                int cle = (Integer) parametres.get("cle");
                return new XOR((byte)cle);
            }
            case "CompressionRLE" -> {
                return new CompressionRLE();
            }
            case "FiltreBinaireInverse" -> {
                return new FiltreBinaireInverse();
            }
            default -> {
                return null;
            }
        }
    }

    // appliquer tous les filtres au fichier et enregistrer l'état de début et de fin
    public void appliquerFiltres(Fichier fichier) {
        historique.push(new EtatFichier(fichier));
        Fichier courant = fichier;
        for (Filtre f : filtres) {
            courant = f.appliquer(courant);
        }
        historique.push(new EtatFichier(courant));
    }

    // annuler
    public EtatFichier annuler() {
        if (historique.size() > 1) historique.pop();
        return historique.peek();
    }

    // get
    public EtatFichier getEtatCourant() {
        return historique.peek();
    }

    // reinit
    public void reset() { 
        historique.clear();
        filtres.clear();
    }
}
