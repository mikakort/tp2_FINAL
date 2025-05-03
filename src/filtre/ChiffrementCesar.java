package filtre;

import fichier.Fichier;
import fichier.FichierTexte;

// Filtre qui applique le chiffrement César sur un texte
public class ChiffrementCesar extends Filtre {
    private final int decalage;
    
    public ChiffrementCesar(int decalage) {
        this.decalage = decalage;
    }
    
    @Override
    public Fichier appliquer(Fichier entree) {
        // Verif si fichier texte, sinon retour sans modif
        if (!(entree instanceof FichierTexte)) return entree;
        
        // Conv en fichier texte
        FichierTexte texte = (FichierTexte) entree;
        
        // Recup contenu
        String contenu = texte.getContenu();
        StringBuilder sb = new StringBuilder();
        
        // Parcours chaque char du texte
        for (char c : contenu.toCharArray()) {
            if (Character.isLetter(c)) {
                // Déterm base selon maj/min
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                
                // Calc pos dans alphabet (0-25)
                int originalPos = c - base;
                
                // Applic décalage avec modulo pour rester dans l'alphabet
                int newPos = (originalPos + decalage) % 26;
                
                // Ajust si négatif
                if (newPos < 0) newPos += 26;
                
                // Ajout char décalé
                sb.append((char)(base + newPos));
            } else {
                sb.append(c);
            }
        }
        
        FichierTexte resultat = new FichierTexte(entree.getNom());
        
        // set
        resultat.setContenu(sb.toString());
        
        // Retour résultat
        return resultat;
    }
}
