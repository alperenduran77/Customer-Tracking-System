import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            File file = new File("content.txt");
            Scanner scanner = new Scanner(file);
            operator[] operators = new operator[100];
            customer[] customers = new customer[100];
            order[] orders = new order[100];
            int operatorsCount = 0;
            int customersCount = 0;
            int ordersCount = 0;

            while (scanner.hasNextLine()) {
               
                String line = scanner.nextLine().trim();
                /* LINE DOES NOT CONTAIN SEMICOLON */
                if (!line.contains(";")) {

                    continue; // Skip to the next line if no semicolons are found
                }
                String[] parts = line.split(";");

                /* IF LINE ENDS WITH ; */
                if (line.endsWith(";")) {
                    continue; // Skip to the next line
                }

                /* CHECKS WHETHER STRING IS EMPTY */
                int hasEmptyField = 0;
                for (String part : parts) {
                    if (part.trim().isEmpty()) { // Trim each part to check for empty strings effectively
                        hasEmptyField = 1;
                        break; // Exit the loop as soon as an empty field is found
                    }
                }

                /* IF LINE HAVE EMPTY STRING */
                if (hasEmptyField == 1) {

                    continue; // Skip to the next iteration of the loop
                }
                /* LINE DOES NOT HAVE VALID FORMAT (LINE DOES NOT CONTAIN ENOUGH SEMICOLONS) */
                int isValidFormat = 0; // Assume the format is invalid initially

                switch (parts[0]) {
                    case "order":
                        if (parts.length == 6)
                            isValidFormat = 1; // Example: "order" records should have 6 parts
                        break;
                    case "retail_customer":
                        if (parts.length == 7)
                            isValidFormat = 1; // Example: "retail_customer" records should have 7 parts
                        break;
                    case "corporate_customer":
                        if (parts.length == 8)
                            isValidFormat = 1; // Adjust according to your data structure
                        break;
                    case "operator":
                        if (parts.length == 7)
                            isValidFormat = 1; // Adjust according to your data structure
                        break;
                    default:
                        continue; // Skip processing this line entirely
                }
                /* SKIP THE LINE IF LINE DOES NOT HAVE ENOUGH SEMICOLONS */
                if (isValidFormat == 0) {

                    continue; // Skip this line as it doesn't have a valid format
                }

                // Based on the first part, create the corresponding objects
                switch (parts[0]) {
                    case "order":
                        try {
                            int count = Integer.parseInt(parts[2]);
                            int totalPrice = Integer.parseInt(parts[3]);
                            int status = Integer.parseInt(parts[4]);
                            int customerID = Integer.parseInt(parts[5]);
                            
                            if (count < 1 || totalPrice < 1 || status < 0 || customerID < 1 || status > 3) {
                                continue;
                            }
 
                            if (count >= 1 || parts.length == 6 && isIDUsed(parts[5], operators, customers) == 0) { // Check if count is greater than or equal to 1
                                order order = new order(
                                        parts[1], // product_name
                                        count, // Use the parsed count
                                        Integer.parseInt(parts[3]), // total_price
                                        Integer.parseInt(parts[4]), // status
                                        Integer.parseInt(parts[5])); // customer_ID
                                orders[ordersCount++] = order;
                            }
                        } catch (Exception e) {

                            continue;
                        }

                        // If count is less than 1, this block is skipped and nothing is added
                        break;

                    case "retail_customer":
                        try {
                            int id = Integer.parseInt(parts[5]);
                            int operatorID = Integer.parseInt(parts[6]);
                          
                            if (id < 1 || operatorID < 1) {

                                continue;
                            }
                            if((parts.length == 7 || parts.length == 8) && isIDUsed(parts[5], operators, customers) == 0)
                            {
                                customers[customersCount++] = new retail_customer(parts[1], parts[2], parts[3], parts[4],
                                id, operatorID);
                            }
                           
                        } catch (Exception e) {

                            continue;
                        }
                        break;
                    case "corporate_customer":
                        try {
                            int id = Integer.parseInt(parts[5]);
                            int operatorID = Integer.parseInt(parts[6]);
                            
                            if (id < 1 || operatorID < 1) {

                                continue;
                            }
                            // Assuming parts[7] is the company name for corporate_customer
                            String companyName = parts[7];
                            if((parts.length == 7 || parts.length == 8) && isIDUsed(parts[5], operators, customers) == 0)
                            {
                                customers[customersCount++] = new corporate_customer(parts[1], parts[2], parts[3], parts[4],
                                id, operatorID, companyName);
                            }
                            
                        } catch (Exception e) {

                            continue;
                        }
                        break;
                    case "operator":
                        try {
                            int id = Integer.parseInt(parts[5]);
                            int wage = Integer.parseInt(parts[6]);
                            
                          
                            if (id < 1 || wage < 1) {

                                continue;
                            }
                            if (parts.length == 7 && isIDUsed(parts[5], operators, customers) == 0) {
                                operators[operatorsCount++] = new operator(parts[1], parts[2], parts[3], parts[4], id,
                                    wage);
                            }
                            
                        } catch (Exception e) {

                            continue;
                        }
                        break;
                    default:
                        
                        break;
                }
            }
            scanner.close();
            // customer to Operator
            for (operator op : operators) {
                if (op != null) {
                    op.define_customers(customers);
                }
            }
            // order to customer
            for (customer cust : customers) {
                if (cust != null) {
                    cust.define_orders(orders);
                }
            }

            // Take input from the user for the ID
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Please enter your ID... ");
            String input = inputScanner.nextLine(); // Read the input as a string
            int inputID;

            try {
                inputID = Integer.parseInt(input);
                if (inputID < 1) {
                    System.out.println("Invalid input. Program will terminate.");
                    inputScanner.close(); // Close the scanner before exiting
                    System.exit(1); // Exit the program
                }
                int found = 0;
                for (int i = 0; i < operators.length; i++) {
                    if (operators[i] != null && operators[i].getID() == inputID) {
                        operators[i].print_operator();
                        operators[i].print_customers(); // This method should already print all customers of the
                                                        // operator
                        found = 1;
                        break; // Found the operator, no need to continue searching
                    }
                }

                if (found == 0) { // If not found in operators, search in customers
                    for (int j = 0; j < customers.length; j++) {
                        if (customers[j] != null && customers[j].getID() == inputID) {
                            System.out.println("*** Customer Screen ***");
                            customers[j].print_customer();
                            customers[j].print_orders(); // This should print all orders for the customer
                            found = 1;
                            break; // Found the customer, no need to continue searching
                        }
                    }
                }

                if (found == 0) { // If still not found
                    System.out.println("No operator/customer found with ID: " + inputID + ". Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Program terminated.");
                inputScanner.close(); // Close the scanner before exiting
                System.exit(1); // Exit the program with a non-zero status to indicate error
            }

            inputScanner.close();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
    private static int isIDUsed(String idStr, operator[] operators, customer[] customers) {
        try {
            int id = Integer.parseInt(idStr);
            for (operator op : operators) {
                if (op != null && op.getID() == id) return 1;
            }
            for (customer cust : customers) {
                if (cust != null && cust.getID() == id) return 1;
            }
        } catch (NumberFormatException e) {
            // If ID is not a valid integer, it's considered as "used" to prevent further processing.
            return 1;
        }
        return 0; // ID is not used
    }
    


}
