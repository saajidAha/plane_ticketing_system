//    Task 07
public class Person {
//    Instance variables
    private String name;
    private String surname;
    private String email;

//    Constructor
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

//    Getters
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }

//    Setters
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setEmail(String email){
        this.email = email;
    }

//    Methods

//    Returns passenger info
    public String getDetails(){
        return "\n\nPassenger Details:\n" + "Name: " + name +"\nSurname: " + surname + "\nEmail: " + email + "\n";
    }

//    Prints passenger info
    public void viewDetails(){
        System.out.println(getDetails());
    }


}
