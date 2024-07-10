import java.util.List;

public class OrderProcessor {
    private Database database;
    private EmailService emailService;
    private InventorySystem inventorySystem;

    //Utiliser des Interfaces est plus adapté que d'instancier directement dans le constructeur
    public OrderProcessor(Database database, EmailService emailService, InventorySystem inventorySystem) {
        this.database = database;
        this.emailService = emailService;
        this.inventorySystem = inventorySystem;
    }

    //ProcessOrder effectuait beaucoup trop de tâches différentes, il faut mieux respecter le Single Responsibility Principle. ProcessOrder pourrait plutôt être utilisée pour appeler les autres méthodes 
    public void processOrder(Order order) {
        checkItemAvailability(order.getItems());
        saveOrder(order);
        sendConfirmationEmail(order);
        updateInventory(order.getItems());
        applyDiscountIfLoyalCustomer(order);
    }

    //Les nouvelles méthodes utilisent des noms explicites sur leurs fontions.

    private void checkItemAvailability(List<Item> items) {
        for (Item item : items) {
            if (!inventorySystem.isItemAvailable(item)) {
                throw new ItemNotAvailableException("Item not available in inventory: " + item.getName());
            }
        }
    }

    private void saveOrder(Order order) {
        database.saveOrder(order);
    }

    private void sendConfirmationEmail(Order order) {
        String message = "Your order has been received and is being processed.";
        emailService.sendEmail(order.getCustomerEmail(), "Order Confirmation", message);
    }

    private void updateInventory(List<Item> items) {
        for (Item item : items) {
            inventorySystem.updateInventory(item, item.getQuantity() * -1);
        }
    }

    private void applyDiscountIfLoyalCustomer(Order order) {
        if (order instanceof LoyalCustomerOrder) {
            LoyalCustomerOrder loyalCustomerOrder = (LoyalCustomerOrder) order;
            loyalCustomerOrder.applyDiscount();
        }
    }
}


//Dans le cadre du partiel je préfère mettre le code d'autre classes ici pour que tout soit regroupé dans un seul fichier "exercie2"
public class LoyalCustomerOrder extends Order {
    @Override
    public void applyDiscount() {
        // Appliquer une remise de 10%
        setTotalPrice(getTotalPrice() * 0.9);
    }
}

// Exception spécifique pour les articles non disponibles afin que les erreurs soient plus claires 
public class ItemNotAvailableException extends RuntimeException {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}
