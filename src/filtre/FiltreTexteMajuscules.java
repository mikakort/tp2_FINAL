package filtre;

import fichier.Fichier;
import fichier.FichierTexte;

// Filtre qui convertit tout le texte en majuscules
public class FiltreTexteMajuscules extends Filtre {
    @Override
    public Fichier appliquer(Fichier entree) {
        // Verif si fichier texte, sinon retour sans modif
        if (!(entree instanceof FichierTexte)) return entree;
        
        // conv en fichier texte
        FichierTexte texte = (FichierTexte) entree;
        
        // Recup contenu
        String contenu = texte.getContenu();
        
        // nouveau fichier texte pour res
        FichierTexte resultat = new FichierTexte(entree.getNom());
        
        // set
        resultat.setContenu(contenu.toUpperCase());
        
        // retour
        return resultat;
    }
}
