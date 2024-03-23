public class person {
    private String name;
    private String surname;
    private String address;
    private String phone;
    private int ID;

    public person(String name, String surname, String address, String phone, int ID) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
    }
    
     // Getters
  public String getName() {
    return name;
}

public String getSurname() {
    return surname;
}

public String getAddress() {
    return address;
}

public String getPhone() {
    return phone;
}

public int getID() {
    return ID;
}
}

