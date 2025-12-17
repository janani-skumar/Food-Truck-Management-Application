package app;

import java.io.IOException;
import java.util.Stack;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {
    
    // buttons
    @FXML private Button grilledCheeseBtn, sweetTeaBtn, lemonadeBtn, sodaBtn, waterBtn;
    @FXML private Button undoBtn, checkoutBtn;
    @FXML private Button sweetTeaDiscBtn, grilledCheeseSodaDiscBtn;
    @FXML private Button adminBtn;

    // stock labels 
    @FXML private Label grilledCheeseLabel1, grilledCheeseLabel2, sweetTeaLabel1, sweetTeaLabel2, lemonadeLabel1, lemonadeLabel2, sodaLabel, waterLabel;

    // order labels 
    @FXML private Label grilledCheeseOrderLabel, sweetTeaOrderLabel, lemonadeOrderLabel, sodaOrderLabel, waterOrderLabel, totalCostLabel;

    // order tracking
    private Stack<OrderHistory> orderHistory = new Stack<>();

    // stock counts
    private int grilledCheeseStock1 = 20, grilledCheeseStock2 = 20;
    private int sweetTeaStock1 = 16, sweetTeaStock2 = 16;
    private int lemonadeStock1 = 16, lemonadeStock2 = 16;
    private int sodaStock = 8, waterStock = 8;

    // order counts 
    private int grilledCheeseCount = 0, sweetTeaCount = 0, lemonadeCount = 0, sodaCount = 0, waterCount = 0;

    // total cost
    private double totalCost = 0.0;

    // discount flags 
    private boolean sweetTeaDiscountUsed = false, grilledCheeseSodaDiscountUsed = false;

    // sales tracking
    private int grilledCheeseSold = 0, sweetTeaSold = 0, lemonadeSold = 0, sodaSold = 0, waterSold = 0, totalSold = 0;
    private double grilledCheeseRevenue = 0.0, sweetTeaRevenue = 0.0, lemonadeRevenue = 0.0, sodaRevenue = 0.0, waterRevenue = 0.0, totalRevenue = 0.0;


    @FXML
    private void initialize() {
        updateLabels();
    }

    // function when buying grilled cheese
    @FXML
    private void handleGrilledCheesePurchase() {
        int slotUsed = 0;
        if (grilledCheeseStock1 >= grilledCheeseStock2 && grilledCheeseStock1 > 0) {
            grilledCheeseStock1--;
            slotUsed = 1;
        } else if (grilledCheeseStock2 > 0) {
            grilledCheeseStock2--;
            slotUsed = 2;
        } else {
            grilledCheeseBtn.setDisable(true);
            return;
        }

        grilledCheeseCount++;
        totalCost += 5.0;

        orderHistory.push(new OrderHistory("Grilled Cheese", 5.00, slotUsed));
        updateLabels();
    }

    // function when buying sweet tea
    @FXML
    private void handleSweetTeaPurchase() {
        int slotUsed = 0;
        if (sweetTeaStock1 >= sweetTeaStock2 && sweetTeaStock1 > 0) {
            sweetTeaStock1--;
            slotUsed = 1;
        } else if (sweetTeaStock2 > 0) {
            sweetTeaStock2--;
            slotUsed = 2;
        } else {
            sweetTeaBtn.setDisable(true);
            return;
        }

        sweetTeaCount++;
        totalCost += 3.0;

        orderHistory.push(new OrderHistory("Sweet Tea", 3.00, slotUsed));
        updateLabels();
    }

    // function when buying lemonade
    @FXML
    private void handleLemonadePurchase() {
        int slotUsed = 0;
        if (lemonadeStock1 >= lemonadeStock2 && lemonadeStock1 > 0) {
            lemonadeStock1--;
            slotUsed = 1;
        } else if (lemonadeStock2 > 0) {
            lemonadeStock2--;
            slotUsed = 2;
        } else {
            lemonadeBtn.setDisable(true);
            return;
        }

        lemonadeCount++;
        totalCost += 3.0;

        orderHistory.push(new OrderHistory("Lemonade", 3.00, slotUsed));
        updateLabels();
    }

    // function when buying soda
    @FXML
    private void handleSodaPurchase() {
        if (sodaStock == 0) {
            sodaBtn.setDisable(true);
            return;
        }
    
        sodaStock--;
        sodaCount++;
        totalCost += 2.0;
    
        orderHistory.push(new OrderHistory("Soda", 2.00, 1));
        updateLabels();
    }

    // function when buying water
    @FXML
    private void handleWaterPurchase() {
        if (waterStock == 0) {
            waterBtn.setDisable(true);
            return;
        }
    
        waterStock--;
        waterCount++;
        totalCost += 1.0;
    
        orderHistory.push(new OrderHistory("Water", 1.00, 1));
        updateLabels();
    }

    // function to check if discount can be applied
    @FXML
    private void applySweetTeaDiscount() {
        if (!sweetTeaDiscountUsed && sweetTeaCount >= 2) {
            totalCost -= 1.00;
            sweetTeaDiscountUsed = true;
            sweetTeaDiscBtn.setDisable(true);
            updateLabels();
        }
    }

    // function to check if discount can be applied
    @FXML
    private void applyGrilledCheeseSodaDiscount() {
        if (!grilledCheeseSodaDiscountUsed && grilledCheeseCount >= 1 && sodaCount >= 1) {
            totalCost -= 2.00;
            grilledCheeseSodaDiscountUsed = true;
            grilledCheeseSodaDiscBtn.setDisable(true);
            updateLabels();
        }
    }

    // function that updates discount eligibility based on factors 
    private void updateDiscountEligibility() {
        sweetTeaDiscBtn.setDisable(sweetTeaDiscountUsed || sweetTeaCount < 2);
        grilledCheeseSodaDiscBtn.setDisable(grilledCheeseSodaDiscountUsed || grilledCheeseCount < 1 || sodaCount < 1);
    }

    // function that handles the undo feature
    @FXML
    private void handleUndo() {
        if (!orderHistory.isEmpty()) {
            OrderHistory lastPurchase = orderHistory.pop();
    
            switch (lastPurchase.getItemName()) {
                case "Grilled Cheese":
                    grilledCheeseCount--;
                    totalCost -= lastPurchase.getPricePerItem();
                    if (lastPurchase.getSlot() == 1) {
                        grilledCheeseStock1++;
                    } else {
                        grilledCheeseStock2++;
                    }
                    break;
    
                case "Sweet Tea":
                    sweetTeaCount--;
                    totalCost -= lastPurchase.getPricePerItem();
                    if (lastPurchase.getSlot() == 1) {
                        sweetTeaStock1++;
                    } else {
                        sweetTeaStock2++;
                    }
                    break;
    
                case "Lemonade":
                    lemonadeCount--;
                    totalCost -= lastPurchase.getPricePerItem();
                    if (lastPurchase.getSlot() == 1) {
                        lemonadeStock1++;
                    } else {
                        lemonadeStock2++;
                    }
                    break;
    
                case "Soda":
                    sodaCount--;
                    totalCost -= lastPurchase.getPricePerItem();
                    sodaStock++;
                    break;
    
                case "Water":
                    waterCount--;
                    totalCost -= lastPurchase.getPricePerItem();
                    waterStock++;
                    break;
            }
            updateLabels();
        }
    }

    // resetting the order details portion 
    private void resetOrder() {
        grilledCheeseCount = sweetTeaCount = lemonadeCount = sodaCount = waterCount = 0;
        totalCost = 0.00;
    
        sweetTeaDiscountUsed = false;
        grilledCheeseSodaDiscountUsed = false;
    
        updateLabels();
    }

    // function that handles the checkout aspect 
    @FXML
    private void handleCheckout() {

        grilledCheeseSold += grilledCheeseCount;
        grilledCheeseRevenue += (grilledCheeseCount * 5);
        sweetTeaSold += sweetTeaCount;
        sweetTeaRevenue += (sweetTeaCount * 3);
        lemonadeSold += lemonadeCount;
        lemonadeRevenue += (lemonadeCount * 3);
        sodaSold += sodaCount;
        sodaRevenue += (sodaCount * 2);
        waterSold += waterCount;
        waterRevenue += waterCount;
        totalSold += (grilledCheeseCount + sweetTeaCount + lemonadeCount + sodaCount + waterCount);
        totalRevenue += totalCost;

        resetOrder();
        orderHistory.clear();
        // Show confirmation alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checkout Successful");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for your purchase! We hope to see you again!");
        alert.showAndWait();
    }

    // function that updates each label to keep up with current values of items  
    private void updateLabels() {
        grilledCheeseLabel1.setText("Stock: " + grilledCheeseStock1);
        grilledCheeseLabel2.setText("Stock: " + grilledCheeseStock2);
        sweetTeaLabel1.setText("Stock: " + sweetTeaStock1);
        sweetTeaLabel2.setText("Stock: " + sweetTeaStock2);
        lemonadeLabel1.setText("Stock: " + lemonadeStock1);
        lemonadeLabel2.setText("Stock: " + lemonadeStock2);
        sodaLabel.setText("Stock: " + sodaStock);
        waterLabel.setText("Stock: " + waterStock);

        grilledCheeseOrderLabel.setText("Grilled Cheese: " + grilledCheeseCount);
        sweetTeaOrderLabel.setText("Sweet Tea: " + sweetTeaCount);
        lemonadeOrderLabel.setText("Lemonade: " + lemonadeCount);
        sodaOrderLabel.setText("Soda: " + sodaCount);
        waterOrderLabel.setText("Water: " + waterCount);

        totalCostLabel.setText(String.format("$%.2f", totalCost));

        updateDiscountEligibility();
        undoBtn.setDisable(orderHistory.isEmpty());
    }

    // function that opens admin panel when button is pressed
    @FXML
    private void openAdminPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root = loader.load();

            // Pass current stock values if needed
            AdminController adminController = loader.getController();
            adminController.setMainController(this);
            adminController.setSalesData(grilledCheeseSold, sweetTeaSold, lemonadeSold, sodaSold, waterSold, totalSold,
                grilledCheeseRevenue, sweetTeaRevenue, lemonadeRevenue, sodaRevenue, waterRevenue, totalRevenue);

            Stage stage = new Stage();
            stage.setTitle("Admin Panel");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // functions that restock each item when corressponding button is pressed 
    public void restockGrilledCheese() {
        grilledCheeseStock1 = 20;
        grilledCheeseStock2 = 20;
        grilledCheeseBtn.setDisable(false);
        updateLabels();
    }    

    public void restockSweetTea() {
        sweetTeaStock1 = 16;
        sweetTeaStock2 = 16;
        sweetTeaBtn.setDisable(false);
        updateLabels();
    }  

    public void restockLemonade() {
        lemonadeStock1 = 16;
        lemonadeStock2 = 16;
        lemonadeBtn.setDisable(false);
        updateLabels();
    }

    public void restockSoda() {
        sodaStock = 16;
        sodaBtn.setDisable(false);
        updateLabels();
    }

    public void restockWater() {
        waterStock = 16;
        waterBtn.setDisable(false);
        updateLabels();
    }
}