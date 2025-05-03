package filtre;

import fichier.Fichier;

// Classe abstraite pour tous les filtres
public abstract class Filtre {
    // Methode abstraite pour appliquer le filtre sur le fichier
    public abstract Fichier appliquer(Fichier entree);
}
