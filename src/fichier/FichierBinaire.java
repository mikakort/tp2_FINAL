package fichier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// Fichier binaire
public class FichierBinaire extends Fichier {
    private byte[] contenu;
    
    public FichierBinaire(String nom) { super(nom); }
    
    @Override
    public void ouvrir() throws IOException {
        // read
        contenu = Files.readAllBytes(Paths.get(nom));
    }
    
    @Override
    public void enregistrer(String chemin) throws IOException {
        // write
        Files.write(Paths.get(chemin), contenu);
    }
    
    // get
    public byte[] getContenu() { return contenu; }
    
    // set
    public void setContenu(byte[] c) { contenu = c; }
}
