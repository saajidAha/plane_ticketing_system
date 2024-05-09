import java.util.InputMismatchException;
import java.util.Scanner;

public class w2052929_PlaneManagement {

    public static void main(String[] args) {
        startMainProgram();
    }

    //    Initialization of a class-level variables
    private static final int numOfTickets = 5;
    private static final Ticket[] tickets = new Ticket[numOfTickets];
    private static final String[] bookedTicketIndexes = new String[numOfTickets];

    // Method to buy seat (Task 3)
    private static void buy_seat(int[][] seats){
        int[] seatIndexes = validateInput(seats);
        int rowIndex = seatIndexes[0], rowNumber = rowIndex + 1;
        int colIndex = seatIndexes[1], colNumber = colIndex + 1;

        if (seats[rowIndex] [colIndex] == 1){
            System.out.println("Unfortunately. the seat is already booked.");
        }
        else{
            boolean maxTicketsIssued = addTicket(rowNumber, colNumber, tickets);
            if(!maxTicketsIssued){
                seats[rowIndex] [colIndex] = 1;
                System.out.println("Booked successfully.");
            }else{
                System.out.println("Cannot book anymore tickets. please try another option.");
            }
        }
    }

    // Method to cancel seat (Task 4)
    private static void cancel_seat(int[][] seats){
        int[] seatIndexes = validateInput(seats);
        int rowIndex = seatIndexes[0];
        int colIndex = seatIndexes[1];

        if(seats[rowIndex][colIndex] == 1){
            seats[rowIndex] [colIndex] = 0;
            String strRowColIndex = Integer.toString(rowIndex) + Integer.toString(colIndex);
            for(int i=0; i<bookedTicketIndexes.length; i++){
                if( bookedTicketIndexes[i].equals(strRowColIndex) ){
                    tickets[i] = null;
                    bookedTicketIndexes[i] = "";
                    break;
                }
            }
            System.out.println("Seat has been cancelled successfully.");
        }
        else{
            System.out.println("Seat is already available. No seat has been cancelled.");
        }
    }

    //    Method to find the first available seat (Task 5)
    private static void find_first_available(int[][] seats){
        boolean notFound = true;
        for(int i=0; i<seats.length && notFound; i++){
            for(int j=0; j<seats[i].length; j++){
                if(seats[i][j] == 0){
                    char rowLetter = getRowLetter(i+1);
                    System.out.println("First available seat is at: " + rowLetter + (j + 1));
                    notFound = false;
                    break;
                }
            }
        }
    }

    //    Method to show the seating plan (Task 6)
    private static void show_seating_plan(int[][] seats){
        for(int[] row : seats){
            for (int seat : row){
                if (seat == 1){
                    System.out.print("X ");
                }
                else{
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }
//    Method to print info of tickets (Task 10)
    private static void print_tickets_info() {
        double totalPrice = 0;
        for(Ticket ticket : tickets){
            try{
                ticket.viewDetails();
                totalPrice += ticket.getPrice();
            }
            catch(NullPointerException e){
                System.out.print("");
            }
        }
        System.out.println("Total price of tickets sold during this session: £" + totalPrice);
    }

//    Method to search tickets (Task 11)
    private static void search_tickets(int[][] seats) {
        int[] seatIndexes = validateInput(seats);
        boolean seatAvailable = true;
        String strBookedIndex = Integer.toString(seatIndexes[0]) + Integer.toString(seatIndexes[1]);

        try{
            for(int i=0; i<bookedTicketIndexes.length && seatAvailable; i++){
                if( bookedTicketIndexes[i].equals(strBookedIndex) ){
                    tickets[i].viewDetails();
                    seatAvailable = false;
                }
            }
            if (seatAvailable){
                System.out.println("This seat is available");
            }
        }catch(Exception e){
            System.out.println("This seat is available");
        }
    }

//    Method to display the welcome message & the menu
    private static void displayMenu(){
        System.out.println(
                "\n  Welcome to the Plane Management application!\n\n" +
                "************************************************\n" + "*                Menu Options                  *\n" + "************************************************\n\n" +
                "    1) Buy a seat\n" + "    2) Cancel a seat\n" + "    3) Find first available seat \n" + "    4) Show seating plan\n" + "    5) Print tickets information and total sales \n" + "    6) Search ticket \n" + "    0) Quit \n\n" + "************************************************\n" + "NOTE: ONLY " +  numOfTickets + " tickets can be issued per session. Thank you." );
        }

//    Method to validate & return the chosen menu number
    private static int getMenuNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select an option: ");
        int option = scanner.nextInt();

        if (option < 0 || option >= 9) {
            System.out.println("Invalid option choice.");
            getMenuNumber();
        }
        return option;
    }

//    Method to validate user inputs and to return the validated rows & column indexes of the plane seating arrangement
    private static int[] validateInput(int[][] seats){
        int rowIndex = 0, colIndex = 0;
        boolean rowIndexValid = false;
        boolean colIndexValid = false;

        while(!rowIndexValid){
            try{
                int rowNumber = 0;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the row Letter: ");
                String rowLetter = scanner.nextLine();
                rowNumber = getRowNumber(rowLetter);

                if (rowNumber >= 1 && rowNumber <= 4){
                    rowIndex = rowNumber - 1;
                    rowIndexValid = true;
                }
                else {
                    System.out.println("Invalid row letter. try again.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid value. Enter an integer and try again.");
            }
        }

        while (!colIndexValid){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the seat number: ");
                int colNumber = scanner.nextInt();

                if ( (colNumber >= 1 && colNumber <= seats[rowIndex].length)) {
                    colIndex = colNumber - 1;
                    colIndexValid = true;
                } else {
                    System.out.println("Invalid seat number. try again.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid value. Enter an integer and try again.");
            }
        }
        return new int[]{rowIndex, colIndex};
    }

//    Method to determine price of the seat and return the price
    private static int getSeatPrice(int colNumber){
        int price = 0;
        if(colNumber==1 || colNumber==2 || colNumber==3 || colNumber==4 || colNumber==5){
                price = 200;
        }
        else if(colNumber==6 || colNumber==7 || colNumber==8 || colNumber==9 ){
                price = 150;
        }
        else if(colNumber==10 || colNumber==11 || colNumber==12 || colNumber==13 || colNumber==14){
                price = 180;
        }
        return price;
    }

//    Method to create a ticket, add ticket to the array of Tickets & to return the status of if the maximum number of tickets are issued or not.
    public static boolean addTicket(int rowNumber, int colNumber, Ticket[] tickets){
        boolean maxTicketsIssued = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the person's first name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the person's surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter the person's email: ");
        String email = scanner.nextLine();

        char rowLetter = getRowLetter(rowNumber);
        int seatPrice = getSeatPrice(colNumber);

        Person person = new Person(name,surname,email);
        Ticket ticket = new Ticket(rowLetter,colNumber,seatPrice,person);

        for(int i=0; i<tickets.length; i++){
            if (i == tickets.length-1 && !(tickets[i] == null) ) {
                System.out.println("Maximum number of tickets has already been issued in the previous attempt. (Max " + tickets.length + " tickets).");
                maxTicketsIssued = true;
            }
            else if ( (tickets[i] == null) ){
                tickets[i] = ticket;
                bookedTicketIndexes[i] = Integer.toString(rowNumber-1) + Integer.toString(colNumber-1);
                String seatNum = rowLetter + Integer.toString(colNumber);
                ticket.save(seatNum, person);
                break;
            }
        }
        return maxTicketsIssued;
    }

//    Method to obtain the row letter based on the row number.
    private static char getRowLetter(int rowNumber){
        char rowLetter = ' ';
        if (rowNumber == 1){
            rowLetter = 'A';
        }
        else if (rowNumber == 2){
            rowLetter = 'B';
        }
        else if (rowNumber == 3){
            rowLetter = 'C';
        }
        else if (rowNumber == 4){
            rowLetter = 'D';
        }
        return rowLetter;
    }

//   Method to obtain the row number based on the row letter.
    private static int getRowNumber(String rowLetter){
        int rowNumber = 0;
        if (rowLetter.equalsIgnoreCase("A")){
            rowNumber = 1;
        }
        else if (rowLetter.equalsIgnoreCase("B")){
            rowNumber = 2;
        }
        else if (rowLetter.equalsIgnoreCase("C")){
            rowNumber = 3;
        }
        else if (rowLetter.equalsIgnoreCase("D")){
            rowNumber = 4;
        }
        return rowNumber;
    }

//    Method to initiate the main program logic.
    private static void startMainProgram(){
        int[][] seats = new int[4][]; // initialization of the seating array
        seats[0] = new int[14]; seats[3] = new int[14];
        seats[1] = new int[12]; seats[2] = new int[12];

        boolean quit = false;
        do{
            try{
                displayMenu();
                int menuNum = getMenuNumber();
                switch (menuNum){ // calling the methods based on the chosen menu number
                    case 1 : buy_seat(seats);
                        break;
                    case 2 : cancel_seat(seats);
                        break;
                    case 3 : find_first_available(seats);
                        break;
                    case 4 : show_seating_plan(seats);
                        break;
                    case 5 : print_tickets_info();
                        break;
                    case 6 : search_tickets(seats);
                        break;
                    case 0 : quit = true;
                        break;
                }
                if (!quit){
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Do you want to continue?\nEnter 'Y' to continue or 'N' to quit: ");
                    String playAgain = scanner.next();
                    if(playAgain.equalsIgnoreCase("N")){
                        break;
                    }
                }
            }
            catch(InputMismatchException ie){
                System.out.println("Invalid input. please try again.");
            }
        } while(!quit);
    }
}











