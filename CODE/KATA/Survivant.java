import java.util.ArrayList;
import java.util.List;

public class Survivant {
    private int x;
    private int y;
    private String orientation;
    private int sante;
    private List<Ressource> inventaire;
    private Carte carte;

    public Survivant(int x, int y, String orientation, int sante, Carte carte) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.sante = sante;
        this.inventaire = new ArrayList<>();
        this.carte = carte;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getSante() {
        return sante;
    }

    public List<Ressource> getInventaire() {
        return inventaire;
    }

    public void avancer() {
        switch (orientation) {
            case "nord":
                y--;
                break;
            case "sud":
                y++;
                break;
            case "est":
                x++;
                break;
            case "ouest":
                x--;
                break;
        }
    }

    public void tournerAGauche() {
        switch (orientation) {
            case "nord":
                orientation = "ouest";
                break;
            case "sud":
                orientation = "est";
                break;
            case "est":
                orientation = "nord";
                break;
            case "ouest":
                orientation = "sud";
                break;
        }
    }

    public void tournerADroite() {
        switch (orientation) {
            case "nord":
                orientation = "est";
                break;
            case "sud":
                orientation = "ouest";
                break;
            case "est":
                orientation = "sud";
                break;
            case "ouest":
                orientation = "nord";
                break;
        }
    }

    public void perdreSante(int degats) {
        sante -= degats;
    }

    public void ajouterRessource(Ressource ressource) {
        inventaire.add(ressource);
    }

    public void explorer(String commande) {
        switch (commande) {
            case "avancer":
                avancer();
                break;
            case "tournerAGauche":
                tournerAGauche();
                break;
            case "tournerADroite":
                tournerADroite();
                break;
        }

        if (!carte.estDansLaCarte(x, y)) {
            throw new RuntimeException("Survivant a quitté la zone sécurisée et est mort.");
        }

        Ressource ressource = carte.obtenirRessource(x, y);
        if (ressource != null) {
            ajouterRessource(ressource);
        }

        rencontrerZombie();
    }

    private void rencontrerZombie() {
        for (Zombie zombie : carte.obtenirZombies()) {
            if (zombie.getX() == x && zombie.getY() == y) {
                perdreSante(10); // Exemple de perte de santé
            }
        }
    }
}
