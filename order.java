public class order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public order(String product_name, int count, int total_price, int status, int customer_ID) {
        this.product_name = product_name;
        this.count = count;
        this.total_price = total_price;
        this.status = status;
        
        this.customer_ID = customer_ID;
    }

    // Getter for product_name
    public String getProductName() {
        return product_name;
    }

    // Getter for count
    public int getCount() {
        return count;
    }

    // Getter for total_price
    public int getTotalPrice() {
        return total_price;
    }

    // Getter for status
    public int getStatus() {
        return status;
    }

    // Getter for customer_ID
    public int getCustomerID() {
        return customer_ID;
    }

    public void print_order() {
        String statusString = convertStatusToString(this.getStatus()); // Convert status to string
        System.out.println( "Product name: " + this.getProductName() + 
                           " - Count: " + this.getCount() + " - Total Price: " + this.getTotalPrice() + 
                           " - Status: " + statusString + ".");
    }

    private String convertStatusToString(int status) {
        // Logic to convert status to string
        switch (status) {
            case 0: return "Initialized";
            case 1: return "Processing";
            case 2: return "Completed";
            case 3: return "Cancelled";
            default: return "Unknown";
        }
    }
}
