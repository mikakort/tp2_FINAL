package filtre;

import fichier.Fichier;
import fichier.FichierTexte;
import java.util.List;

// Filtre qui remplace les mots interdits par des étoiles
public class MotsInterdits extends Filtre {
    // liste des mots interdits
    private final List<String> mots;
    
    public MotsInterdits(List<String> mots) {
        this.mots = mots;
    }
    
    @Override
    public Fichier appliquer(Fichier entree) {
        // Verif si fichier texte, sinon retour sans modif
        if (!(entree instanceof FichierTexte)) return entree;
        
        // conv en fichier texte
        FichierTexte texte = (FichierTexte) entree;
        
        // Recup contenu
        String contenu = texte.getContenu();
        
        // remplace chaque mot interdit par ****
        for (String mot : mots) {
            contenu = contenu.replaceAll("\\b" + mot + "\\b", "****");
        }
        
        // nouveau fichier texte pour res
        FichierTexte resultat = new FichierTexte(entree.getNom());
        
        // set
        resultat.setContenu(contenu);
        
        // retour
        return resultat;
    }
}
