package fichier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// Fichier texte
public class FichierTexte extends Fichier {
    private String contenu;

    public FichierTexte(String nom) {
        super(nom);
    }
    
    @Override
    public void ouvrir() throws IOException {
        // read
        contenu = new String(Files.readAllBytes(Paths.get(nom)), StandardCharsets.UTF_8);
    }
    
    @Override
    public void enregistrer(String chemin) throws IOException {
        // write
        Files.write(Paths.get(chemin), contenu.getBytes(StandardCharsets.UTF_8));
    }
    
    // get
    public String getContenu() { return contenu; }
    
    // set
    public void setContenu(String c) { contenu = c; }
}
