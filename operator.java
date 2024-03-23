public class operator extends person {
    private int wage;
    private customer[] customers;
    /* Getter for operator */
    public operator(String name, String surname, String address, String phone, int ID, int wage) {
        super(name, surname, address, phone, ID);
        this.wage = wage;
        this.customers = new customer[100]; // Assuming a maximum of 100 customers for simplicity
    }
 
    /* Getter for wage */
    public int getWage()
    {
        return wage;
    }
    /* Getter for customers array */
    public customer[] getCustomers()
    {
        return customers;
    }
    
    public void print_operator() {
        System.out.println("*** Operator Screen ***");
        System.out.println("-------------------------");
        System.out.println("Name & Surname : " + this.getName() + " " + this.getSurname());
        System.out.println("Address : " + this.getAddress());
        System.out.println("Phone : " + this.getPhone());
        System.out.println("ID : " + this.getID());
        System.out.println("Wage : " + this.getWage());
        System.out.println("-------------------------");
  
    }
   
    public void print_customers() {
        int count = 0; // Use count to determine if any customers have been printed
        for (int j = 0; j < customers.length; j++) {
            if (customers[j] != null) {
                count++; // Increment count if a customer is found
                String customerType = customers[j] instanceof corporate_customer ? "(a corporate customer)" : "(a retail customer)";/* determine type of customer */
                System.out.println("Customer #" + count + " " + customerType);
                customers[j].print_customer();
                customers[j].print_orders();
            }
        }
    
        if (count == 0) {
            // If count is still 0, no customers have been printed
            System.out.println("This operator doesn't have any customers.");
        }
    }
    
    
    public void define_customers(customer[] allCustomers) {
        int customerIndex = 0;
        for (customer cust : allCustomers) {
            if (cust != null && cust.getOperator_ID() == this.getID()) {
                if (customerIndex < this.customers.length) {
                    this.customers[customerIndex++] = cust;
                } else {
                    System.out.println("Maximum customer capacity reached for operator ID: " + this.getID());
                    break;
                }
            }
        }
    }
    
    
    
}
