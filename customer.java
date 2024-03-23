public class customer extends person {
    private order[] orders;
    private int operator_ID;
    /* Constructor for customer */
    public customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID);
        this.operator_ID = operator_ID;
        this.orders = new order[100]; // Assuming a maximum of 100 orders for simplicity
    }
    /* Getter for orders array */
    public order[] getOrders()
    {
        return orders;
    }
    /* Getter for Operator_ID */
    public int getOperator_ID()
    {
        return operator_ID;
    }
    
    public void print_customer() {
        System.out.println("Name & Surname : " + this.getName() + " " + this.getSurname());
        System.out.println("Address : " + this.getAddress());
        System.out.println("Phone : " + this.getPhone());
        System.out.println("ID : " + this.getID());
        System.out.println("Operator ID: " + this.getOperator_ID());
    }
   
    public void print_orders() {
        int i = 1; // Initialize counter for order numbering
        for (int j = 0; j < orders.length; j++) { //  for loop to iterate over the array
            if (orders[j] != null) { // Check if the current order is not null
                System.out.print("Order #" + i + " => ");
                orders[j].print_order(); // Call print_order method on the current order
                i++; // Increment the order number for the next non-null order
            }
        }
        System.out.println("-------------------------");
    }
    
    public void define_orders(order[] allOrders) {
        int orderIndex = 0;
        for (order ord : allOrders) {
            if (ord != null && ord.getCustomerID() == this.getID()) { /* Check if ID's matches */
                if (orderIndex < this.orders.length) {
                    this.orders[orderIndex++] = ord;  /* If so add orders array */
                } else {
                    System.out.println("Maximum order capacity reached for customer ID: " + this.getID());
                    break;
                }
            }
        }
    
}
}
