public class Person {                                                //Person class created with private attributes name,surname and email
    private String name;
    private String surname;
    private String email;

    //Parameterised constructor with all the attributes as input
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
//    Getters
//    Used to access private attributes of Person class in the print() method in the Ticket class
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }


}

