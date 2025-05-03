package moteur;

import fichier.Fichier;

// Classe qui représente un état du fichier
// Pour implémenter l'historique des modifs
public class EtatFichier {
    // fichier
    private final Fichier fichier;
    
    public EtatFichier(Fichier fichier) {
        this.fichier = fichier;
    }
    
    // get
    public Fichier getFichier() {
        return fichier;
    }
}
