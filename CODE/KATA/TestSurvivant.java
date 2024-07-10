public class TestSurvivant {

    public static void main(String[] args) {
        testSurvivantTrouveRessource();
        testSurvivantRencontreZombie();
        testSurvivantQuitteZoneEtMeurt();
    }

    public static void testSurvivantTrouveRessource() {
        Carte carte = new Carte(10, 10);
        Ressource ressource = new Ressource("nourriture");
        carte.ajouterRessource(0, 1, ressource);
        Survivant survivant = new Survivant(0, 0, "sud", 100, carte);

        try {
            survivant.explorer("avancer");
            if (survivant.getInventaire().contains(ressource)) {
                System.out.println("Test Survivant Trouve Ressource: SUCCESS");
            } else {
                System.out.println("Test Survivant Trouve Ressource: FAIL");
            }
        } catch (RuntimeException e) {
            System.out.println("Test Survivant Trouve Ressource: FAIL - Exception: " + e.getMessage());
        }
    }

    public static void testSurvivantRencontreZombie() {
        Carte carte = new Carte(10, 10);
        Zombie zombie = new Zombie(0, 1);
        carte.ajouterZombie(zombie);
        Survivant survivant = new Survivant(0, 0, "sud", 100, carte);

        try {
            survivant.explorer("avancer");
            if (survivant.getSante() == 90) {
                System.out.println("Test Survivant Rencontre Zombie: SUCCESS");
            } else {
                System.out.println("Test Survivant Rencontre Zombie: FAIL");
            }
        } catch (RuntimeException e) {
            System.out.println("Test Survivant Rencontre Zombie: FAIL - Exception: " + e.getMessage());
        }
    }

    public static void testSurvivantQuitteZoneEtMeurt() {
        Carte carte = new Carte(10, 10);
        Survivant survivant = new Survivant(0, 0, "ouest", 100, carte);

        try {
            survivant.explorer("avancer");
            System.out.println("Test Survivant Quitte Zone Et Meurt: FAIL");
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Survivant a quitté la zone sécurisée et est mort.")) {
                System.out.println("Test Survivant Quitte Zone Et Meurt: SUCCESS");
            } else {
                System.out.println("Test Survivant Quitte Zone Et Meurt: FAIL - Exception: " + e.getMessage());
            }
        }
    }
}
