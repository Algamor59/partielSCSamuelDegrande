import java.util.*;

public class OrderSystem {
    private Map<Integer, List<String>> orderList; // Liste des commandes


    //Il fallait initialiser la map  
    public OrderSystem() {
        orderList = new HashMap<>();
    }

    //Prévoir l'exception en cas de paramètre(s) nul(s)
    public void addNewOrder(Integer customerID, String itemName) {
        if (customerID == null || itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Invalid customerID or itemName");
        }
        List<String> items = orderList.getOrDefault(customerID, new ArrayList<>());
        items.add(itemName);
        orderList.put(customerID, items);
    }

    //Il faut vérifier que l'index des items soit bien compris dans les limites et encore une fois que les paramètres ne soient pas nuls
    public void modifyOrder(Integer customerID, Integer itemIndex, String newItemName) {
        if (customerID == null || itemIndex == null || newItemName == null || newItemName.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        List<String> items = orderList.get(customerID);
        if (items != null && itemIndex >= 0 && itemIndex < items.size()) {
            items.set(itemIndex, newItemName);
        } else {
            throw new IndexOutOfBoundsException("Invalid item index");
        }
    }

    //Même principe ici
    public void removeOrder(Integer customerID, Integer itemIndex) {
        if (customerID == null || itemIndex == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        List<String> items = orderList.get(customerID);
        if (items != null && itemIndex >= 0 && itemIndex < items.size()) {
            items.remove(itemIndex);
        } else {
            throw new IndexOutOfBoundsException("Invalid item index");
        }
    }


    //Cette méthode était plutôt bonne, elle peut tout de même être optimisée de cette manière
    public void printOrders() {
        for (Map.Entry<Integer, List<String>> entry : orderList.entrySet()) {
            Integer customerID = entry.getKey();
            List<String> items = entry.getValue();
            System.out.println("Customer ID: " + customerID);
            System.out.println("Items: " + String.join(", ", items));
            System.out.println();
        }
    }

    // Il faut penser aux tests, c'est très important
    public static void main(String[] args) {
        OrderSystem orderSystem = new OrderSystem();
        orderSystem.addNewOrder(1, "Apple");
        orderSystem.addNewOrder(1, "Banana");
        orderSystem.addNewOrder(2, "Orange");

        orderSystem.printOrders();

        orderSystem.modifyOrder(1, 1, "Grapes");
        orderSystem.removeOrder(2, 0);

        orderSystem.printOrders();
    }
}
