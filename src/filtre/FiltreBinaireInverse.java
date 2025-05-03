package filtre;

import fichier.Fichier;
import fichier.FichierBinaire;

// Filtre qui inverse les bits d'un fichier bin
public class FiltreBinaireInverse extends Filtre {
    @Override
    public Fichier appliquer(Fichier entree) {
        // Verif si fichier bin, sinon retour sans modif
        if (!(entree instanceof FichierBinaire)) return entree;
        
        // conv en fichier bin
        FichierBinaire bin = (FichierBinaire) entree;
        
        // Recup contenu
        byte[] contenu = bin.getContenu();
        
        // Nouveau tab pour res
        byte[] inverse = new byte[contenu.length];
        
        // Inversion bit à bit de chaque octet
        for (int i = 0; i < contenu.length; i++) {
            inverse[i] = (byte) ~contenu[i];
        }
        
        // Nouveau fichier bin pour res
        FichierBinaire resultat = new FichierBinaire(entree.getNom());
        
        // set
        resultat.setContenu(inverse);
        
        // retour
        return resultat;
    }
}
