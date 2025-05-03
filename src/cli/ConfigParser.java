package cli;

import java.io.*;
import java.util.*;

// Classe pour analyser fichier config
public class ConfigParser {
    // Classe interne pour stocker config d'un filtre
    public static class FiltreConf {
        public String nom;
        public Map<String, Object> parametres;
        // Constructeur avec nom et params
        public FiltreConf(String nom, Map<String, Object> parametres) {
            this.nom = nom;
            this.parametres = parametres;
        }
    }
    // Fichiers d'entrée et sortie
    public String fichierEntree;
    public String fichierSortie;
    // Liste des filtres à appliquer
    public List<FiltreConf> filtres;

    // Constructeur qui parse le fichier config
    public ConfigParser(String chemin) throws IOException {
        // Lecture du fichier en une seule string
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line.trim());
        }
        String json = sb.toString();
        
        // Extraction des parties principales
        String[] parts = json.split("\"filtres\"\\s*:\\s*\\[");
        String beforeFilters = parts[0];
        String filtersPart = parts[1].substring(0, parts[1].lastIndexOf("]"));
        
        // Récup des chemins fichiers
        fichierEntree = extractValue(beforeFilters, "fichier_entree");
        fichierSortie = extractValue(beforeFilters, "fichier_sortie");
        
        // Analyse des filtres
        filtres = new ArrayList<>();
        String[] filterStrings = filtersPart.split("\\}\\s*,\\s*\\{");
        
        // Traitement de chaque filtre
        for (int i = 0; i < filterStrings.length; i++) {
            String filterStr = filterStrings[i];
            // Nettoyage de la string
            if (i == 0) filterStr = filterStr.replaceAll("^\\s*\\{", "");
            if (i == filterStrings.length - 1) filterStr = filterStr.replaceAll("\\}\\s*$", "");
            
            // Récup du nom du filtre
            String nom = extractValue(filterStr, "nom");
            Map<String, Object> params = new HashMap<>();
            
            // Extraction des params si présents
            if (filterStr.contains("parametres")) {
                String paramsStr = filterStr.split("\"parametres\"\\s*:\\s*")[1];
                // Traitement liste de mots
                if (paramsStr.contains("mots")) {
                    List<String> mots = new ArrayList<>();
                    String motsStr = paramsStr.split("\\[")[1].split("\\]")[0];
                    for (String mot : motsStr.split(",")) {
                        mots.add(mot.replace("\"", "").trim());
                    }
                    params.put("mots", mots);
                }
                // Traitement param décalage
                if (paramsStr.contains("decalage")) {
                    String decalageStr = paramsStr.split("decalage")[1].split(":")[1].split("[},]")[0].trim();
                    int decalage = Integer.parseInt(decalageStr);
                    params.put("decalage", decalage);
                }
            }
            
            // Ajout du filtre à la liste
            filtres.add(new FiltreConf(nom, params));
        }
    }
    
    // Méthode pour extraire valeur d'une clé dans json
    private String extractValue(String json, String key) {
        String[] parts = json.split("\"" + key + "\"\\s*:\\s*\"");
        if (parts.length < 2) return null;
        return parts[1].split("\"", 2)[0];
    }
}
