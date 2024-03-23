public class corporate_customer extends customer {
    private String company_name;
    /* Constructor for corporate_customer */
    public corporate_customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
        super(name, surname, address, phone, ID, operator_ID);
        this.company_name = company_name;
    }
    /* Getter for Company_Name */
    public String getCompany_Name()
    {
        return company_name;
    }
    
    public void print_customer() {
        System.out.println("Name & Surname : " + this.getName() + " " + this.getSurname());
        System.out.println("Address : " + this.getAddress());
        System.out.println("Phone : " + this.getPhone());
        System.out.println("ID : " + this.getID());
        System.out.println("Operator ID : " + this.getOperator_ID());
        System.out.println("Company Name : " + this.getCompany_Name());
        
    }
}
