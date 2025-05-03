package filtre;

import fichier.Fichier;
import fichier.FichierBinaire;

// Filtre qui inverse l'ordre des octets d'un fichier bin
public class InversionOctets extends Filtre {
    @Override
    public Fichier appliquer(Fichier entree) {
        // Verif si fichier bin, sinon retour sans modif
        if (!(entree instanceof FichierBinaire)) return entree;
        
        // conv en fichier bin
        FichierBinaire bin = (FichierBinaire) entree;
        
        // Recup contenu
        byte[] contenu = bin.getContenu();
        
        // nouveau tab pour res inversé
        byte[] inverse = new byte[contenu.length];
        
        // Inversion des octets (dernier devient premier, etc)
        for (int i = 0; i < contenu.length; i++) {
            inverse[i] = contenu[contenu.length - 1 - i];
        }
        
        // nouveau fichier bin pour res
        FichierBinaire resultat = new FichierBinaire(entree.getNom());
        
        // set
        resultat.setContenu(inverse);
        
        // retour
        return resultat;
    }
}
