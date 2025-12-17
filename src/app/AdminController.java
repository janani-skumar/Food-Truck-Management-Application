package app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminController {

    // labels 
    @FXML private Label grilledCheeseSoldLabel, sweetTeaSoldLabel, lemonadeSoldLabel, sodaSoldLabel, waterSoldLabel, totalSoldLabel;
    @FXML private Label grilledCheeseRevLabel, sweetTeaRevLabel, lemonadeRevLabel, sodaRevLabel, waterRevLabel, totalRevLabel;
    @FXML private Label mostPopularDrinkLabel, leastPopularDrinkLabel;

    // reference to main controller to access shared variables 
    private Controller mainController;

    // links adminController to Controller
    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    // functions that handle the restock aspect of each item 
    @FXML
    private void handleRestockGrilledCheese() {
        mainController.restockGrilledCheese();
    }

    @FXML
    private void handleRestockSweetTea() {
        mainController.restockSweetTea();
    }

    @FXML
    private void handleRestockLemonade() {
        mainController.restockLemonade();
    }

    @FXML
    private void handleRestockSoda() {
        mainController.restockSoda();
    }

    @FXML
    private void handleRestockWater() {
        mainController.restockWater();
    }

    // function that updates the sales tracking portion of the admin panel
    public void setSalesData(int grilledCheeseCt, int sweetTeaCt, int lemonadeCt, int sodaCt, int waterCt, int totalCt,
        double grilledCheeseRev, double sweetTeaRev, double lemonadeRev, double sodaRev, double waterRev, double revenue) {

        grilledCheeseSoldLabel.setText("" + grilledCheeseCt);
        sweetTeaSoldLabel.setText("" + sweetTeaCt);
        lemonadeSoldLabel.setText("" + lemonadeCt);
        sodaSoldLabel.setText("" + sodaCt);
        waterSoldLabel.setText("" + waterCt);
        totalSoldLabel.setText("" + totalCt);
        grilledCheeseRevLabel.setText(String.format("$%.2f", grilledCheeseRev));
        sweetTeaRevLabel.setText(String.format("$%.2f", sweetTeaRev));
        lemonadeRevLabel.setText(String.format("$%.2f", lemonadeRev));
        sodaRevLabel.setText(String.format("$%.2f", sodaRev));
        waterRevLabel.setText(String.format("$%.2f", waterRev));
        totalRevLabel.setText(String.format("$%.2f", revenue));

        updatePopularDrinks(sweetTeaCt, lemonadeCt, sodaCt, waterCt);
    }

    // function that updates the most and least popular drink based on quantity sold
    private void updatePopularDrinks(int sweetTeaCt, int lemonadeCt, int sodaCt, int waterCt) {

        int[] drinkCt = {sweetTeaCt, lemonadeCt, sodaCt, waterCt};
        String[] drinkName = {"Sweet Tea", "Lemonade", "Soda", "Water"};

        int mostPopularIndex = 0;
        int leastPopularIndex = 0;
        for (int i = 1; i < drinkCt.length; i++) {
            if (drinkCt[i] > drinkCt[mostPopularIndex]) {
                mostPopularIndex = i;
            }
            if (drinkCt[i] < drinkCt[leastPopularIndex]) {
                leastPopularIndex = i;
            }
        }

        mostPopularDrinkLabel.setText("Most Popular Drink: " + drinkName[mostPopularIndex]);
        leastPopularDrinkLabel.setText("Least Popular Drink: " + drinkName[leastPopularIndex]);
    }
}