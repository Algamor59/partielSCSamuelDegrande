import java.util.ArrayList;
import java.util.List;

public class Carte {
    private final int largeur;
    private final int hauteur;
    private Ressource[][] ressources;
    private List<Zombie> zombies;

    public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.ressources = new Ressource[largeur][hauteur];
        this.zombies = new ArrayList<>();
    }

    public void ajouterRessource(int x, int y, Ressource ressource) {
        ressources[x][y] = ressource;
    }

    public Ressource obtenirRessource(int x, int y) {
        return ressources[x][y];
    }

    public void ajouterZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public List<Zombie> obtenirZombies() {
        return zombies;
    }

    public boolean estDansLaCarte(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }
}
