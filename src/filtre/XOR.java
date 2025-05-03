package filtre;

import fichier.Fichier;
import fichier.FichierBinaire;

// filtre XOR sur octets d'un fichier bin
public class XOR extends Filtre {
    // cle pour XOR
    private final byte cle;
    
    // Constructeur avec init cle
    public XOR(byte cle) {
        this.cle = cle;
    }
    
    @Override
    public Fichier appliquer(Fichier entree) {
        // verif si fichier bin, sinon retour sans modif
        if (!(entree instanceof FichierBinaire)) return entree;
        
        // conv en fichier bin
        FichierBinaire bin = (FichierBinaire) entree;
        
        // recup contenu
        byte[] contenu = bin.getContenu();
        
        // nouveau tab pour res
        byte[] chiffre = new byte[contenu.length];
        
        // XOR sur chaque octet
        for (int i = 0; i < contenu.length; i++) {
            chiffre[i] = (byte)(contenu[i] ^ cle);
        }
        
        // nouveau fichier bin pour res
        FichierBinaire resultat = new FichierBinaire(entree.getNom());
        
        // set
        resultat.setContenu(chiffre);
        
        // retour
        return resultat;
    }
}
