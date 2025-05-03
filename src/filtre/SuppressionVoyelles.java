package filtre;

import fichier.Fichier;
import fichier.FichierTexte;

// filtre qui supprime toutes les voyelles d'un texte
public class SuppressionVoyelles extends Filtre {
    @Override
    public Fichier appliquer(Fichier entree) {
        // Verif si fichier texte, sinon retour sans modif
        if (!(entree instanceof FichierTexte)) return entree;
        
        // conv en fichier texte
        FichierTexte texte = (FichierTexte) entree;
        
        // recup contenu
        String contenu = texte.getContenu();
        
        // Supprime voyelles (FR + accents)
        String modifie = contenu.replaceAll("[aeiouyAEIOUYàâäéèêëîïôöùûüÿ]", "");
        
        // nouveau fichier texte pour res
        FichierTexte resultat = new FichierTexte(entree.getNom());
        
        // set
        resultat.setContenu(modifie);
        
        // retour
        return resultat;
    }
}
