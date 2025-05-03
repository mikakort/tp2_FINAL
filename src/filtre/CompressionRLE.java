package filtre;

import fichier.Fichier;
import fichier.FichierBinaire;
import java.util.ArrayList;

// Filtre qui compresse les données avec algo RLE (Run Length Encoding)
public class CompressionRLE extends Filtre {
    @Override
    public Fichier appliquer(Fichier entree) {
        // Verif si fichier bin, sinon retour sans modif
        if (!(entree instanceof FichierBinaire)) return entree;
        
        // Conv en fichier bin
        FichierBinaire bin = (FichierBinaire) entree;
        
        byte[] contenu = bin.getContenu();
        
        ArrayList<Byte> compresse = new ArrayList<>();
        
        // Parcours du contenu
        int i = 0;
        while (i < contenu.length) {
            // Octet courant
            byte courant = contenu[i];
            
            // compteur
            int count = 1;
            
            // compte nb d'octets identiques qui se suivent (max 255)
            while (i + count < contenu.length && contenu[i + count] == courant && count < 255) {
                count++;
            }
            
            // Ajoute valeur + nb rep
            compresse.add(courant);
            compresse.add((byte) count);
            
            // Avance au prochain groupe
            i += count;
        }
        
        // Conv ArrayList en tableau d'octets
        byte[] resultatBytes = new byte[compresse.size()];
        for (int j = 0; j < compresse.size(); j++) resultatBytes[j] = compresse.get(j);
        
        // nouveau fichier bin pour res
        FichierBinaire resultat = new FichierBinaire(entree.getNom());
        
        // set
        resultat.setContenu(resultatBytes);
        
        // retour
        return resultat;
    }
}
