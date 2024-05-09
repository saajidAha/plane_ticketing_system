//    Task 08
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
//    Instance variables
    private char row;
    private int seat;
    private double price;
    private Person person;

//    Constructor
    public Ticket(char row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

//    Getters
    public int getRow(){
        return row;
    }
    public int getSeat(){
        return seat;
    }
    public double getPrice(){
        return price;
    }
    public Person getPerson(){
        return person;
    }

//    Setters
    public void setRow(char row){
        this.row = row;
    }
    public void setSeat(int seat){
        this.seat = seat;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setPerson(Person person){
        this.person = person;
    }

//    Methods

//    Returns ticket info
    public String getDetails(){
        return "Ticket Details:\n" + "Row: " + row + "\nSeat: " + seat + "\nPrice: " + price;
    }

//    Prints ticket & passenger info
    public void viewDetails(){
        System.out.println( "########TICKET#########\n"  + getDetails() + person.getDetails() + "##########END##########\n");
    }

//    Saves ticket & passenger info in a text file
    public void save(String seatNum, Person person){
        try{
            FileWriter writeFile = new FileWriter(seatNum + ".txt");
            writeFile.write( getDetails() + person.getDetails());
            writeFile.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
