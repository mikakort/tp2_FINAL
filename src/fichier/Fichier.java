package fichier;

// Classe abstraite pour représenter un fichier
public abstract class Fichier {
    // Nom
    protected String nom;
    // Constructeur avec param nom
    public Fichier(String nom) { this.nom = nom; }

    // get
    public String getNom() { return nom; }

    // ouvrir
    public abstract void ouvrir() throws Exception;
    // enregistrer
    public abstract void enregistrer(String chemin) throws Exception;
}
