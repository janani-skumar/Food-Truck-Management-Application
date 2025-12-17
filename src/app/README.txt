Food Truck Project
------------------

How to Run:
1. Open terminal and navigate to project folder
2. Compile the project:
    javac --module-path lib \
      --add-modules javafx.controls,javafx.fxml \
      -d bin \
      src/app/*.java
3. Run the project:
    javac --module-path lib \
      --add-modules javafx.controls,javafx.fxml \
      -d bin \
      src/app/*.java

Project Description: 
This JavaFX based desktop application project is one that simulates a food truck ordering system. The application
includes an interactive customer interface for purchasing foods, applying discounts, and checking out. 
The interface also includes a separate admin panel for managing inventory, keeping track of sales, and identifying
popular and unpopular drink items.

The customer interface allows the user to select food items such as grilled cheese, sweet drinks, soda, and water. 
The user is able to see all the items they order as they use the interface. It shows how much of each item the customer
wishes to purchase and the total amount they owe. If elligible, the user may apply discounts to their order and remove any 
items from their cart before checkout.

The admin interface provides functionality to restock inventory slots. It also monitors total items sold and revenue generated
per item, as well as, total items sold and total revenue. Third and last feature the admin panel provides is displaying the monitors
and least popular drinks based on sales. 

Additional Features:
1. Undo Button
    - Allows users to undo their most recent purchase in backwards order that they are added to their cart
2. Discount Buttons
    - Two separate discount buttons become available for the user if the discount can be applied to their purchase
3. Admin Panel
    - Separate window for managers to restock item, track sales, and identify popular selling items

Author:
Janani Sivakumar