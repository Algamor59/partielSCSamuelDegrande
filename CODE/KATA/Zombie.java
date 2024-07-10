import java.util.Random;

public class Zombie {
    private int x;
    private int y;
    private Random random;

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void deplacer(Carte carte) {
        int direction = random.nextInt(4);
        switch (direction) {
            case 0: // nord
                if (carte.estDansLaCarte(x, y - 1)) y--;
                break;
            case 1: // sud
                if (carte.estDansLaCarte(x, y + 1)) y++;
                break;
            case 2: // est
                if (carte.estDansLaCarte(x + 1, y)) x++;
                break;
            case 3: // ouest
                if (carte.estDansLaCarte(x - 1, y)) x--;
                break;
        }
    }
}
