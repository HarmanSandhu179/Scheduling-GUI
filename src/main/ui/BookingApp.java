package ui;

import exceptions.DateNotAvailable;
import model.BookAppointments;
import model.Dates;
import model.Services;
import model.Workers;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Clean cuts booking app
public class BookingApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static BookAppointments booked;
    private Scanner input;
    private static ArrayList<Services> services;
    private static ArrayList<Workers> workers;
    private static ArrayList<Dates> date;

    private Services lawnMowing;
    private Services yardCleanup;
    private Services garden;
    private Services flowers;

    private Workers worker1;
    private Workers worker2;

    private Dates aug20;
    private Dates aug21;
    private Dates aug22;
    private Dates aug23;
    private Dates aug24;
    private Dates aug25;
    private Dates aug26;

    private int bookingAmount = 0;


    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    //EFFECTS: runs the booking app
    public BookingApp() {
        runBooking();

    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: processes user input
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void runBooking() {
        boolean keepGoing = true;
        String command = null;
        String command1 = null;
        String command2 = null;
        String command3 = null;
        String command4 = null;
        String command5 = null;

        init();

        while (keepGoing) {
            displayMenuForLoad();
            command5 = input.next();
            command5 = command5.toLowerCase();

            if (command5.equals("q")) {
                keepGoing = false;
                break;
            } else {
                processCommandForLoad(command5);
            }

            displayMenuForServices();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
                break;
            } else {
                processCommand(command);
            }

            displayMenuForWorkers();
            command1 = input.next();
            command1 = command1.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandForWorkers(command1);
            }

            displayMenuForDates();
            command2 = input.next();
            command2 = command2.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandForDates(command2);
            }

            displayMenuForBookedAppointments();
            command3 = input.next();
            command3 = command3.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandForBookedAppointments(command3);
            }

            displayMenuForSavingAndLoading();
            command4 = input.next();
            command4 = command4.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
                break;
            } else {
                processCommandForSavingAndLoading(command4);
            }

        }


        System.out.println("\nGoodbye!");
    }



    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("l")) {
            bookLawnMowing();
        } else if (command.equals("y")) {
            bookYardCleanup();
        } else if (command.equals("g")) {
            bookGardenService();
        } else if (command.equals("f")) {
            bookFlowerService();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(booked);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            booked = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (DateNotAvailable e) {
            System.out.println("Can not choose that date");
        }
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandForSavingAndLoading(String command4) {
        if (command4.equals("s")) {
            saveWorkRoom();
        } else if (command4.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandForWorkers(String command1) {
        if (command1.equals("h")) {
            bookHarman();
        } else if (command1.equals("s")) {
            bookSahil();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandForLoad(String command5) {
        if (command5.equals("l")) {
            loadWorkRoom();
        } else if (command5.equals("s")) {
            //do nothing just skip
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandForDates(String command2) {
        if (command2.equals("a")) {
            bookDate1();
        } else if (command2.equals("b")) {
            bookDate2();
        } else if (command2.equals("c")) {
            bookDate3();
        } else if (command2.equals("d")) {
            bookDate4();
        } else if (command2.equals("e")) {
            bookDate5();
        } else if (command2.equals("f")) {
            bookDate6();
        } else if (command2.equals("g")) {
            bookDate7();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandForBookedAppointments(String command) {
        if (command.equals("b")) {
            getBookedAppointments();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: prints out all the booked services, workers and dates.
    private void getBookedAppointments() {
        System.out.println("Printing All Booked Appointments: ");

        for (int i = 0; i < bookingAmount; i++) {
            System.out.print(booked.getServiceList().get(i).getServiceDescription() + " ");
            System.out.print("booked with " + booked.getWorkersList().get(i).getName());
            System.out.print(" on " + booked.getDatesList().get(i).getMonth()
                    + " " + booked.getDatesList().get(i).getDay());
            System.out.println("        ");
        }
    }

    //EFFECTS: books the given date
    private void bookDate1() {
        try {
            booked.bookDate(aug20);
            System.out.println("Booked: " + aug20.getMonth() + " " + aug20.getDay() + "th");
            bookingAmount++;
        } catch (DateNotAvailable e) {
            System.out.println("Sorry that date is already booked!");
            bookingAmount = 0;
            runBooking();
        }
    }

    //EFFECTS: books the given date
    private void bookDate2() {
        try {
            booked.bookDate(aug21);
            System.out.println("Booked: " + aug21.getMonth() + " " + aug21.getDay() + "st");
            bookingAmount++;
        } catch (DateNotAvailable e) {
            System.out.println("Sorry that date is already booked!");
            bookingAmount = 0;
            runBooking();
        }
    }

    //EFFECTS: books the given date
    private void bookDate3() {
        try {
            booked.bookDate(aug22);
            System.out.println("Booked: " + aug22.getMonth() + " " + aug22.getDay() + "nd");
            bookingAmount++;
        } catch (DateNotAvailable e) {
            System.out.println("Sorry that date is already booked!");
            bookingAmount = 0;
            runBooking();
        }
    }

    //EFFECTS: books the given date
    private void bookDate4() {
        try {
            booked.bookDate(aug23);
            System.out.println("Booked: " + aug23.getMonth() + " " + aug23.getDay() + "rd");
            bookingAmount++;
        } catch (DateNotAvailable e) {
            System.out.println("Sorry that date is already booked!");
            bookingAmount = 0;
            runBooking();
        }
    }


    //EFFECTS: books the given date
    private void bookDate5() {
        try {
            booked.bookDate(aug24);
            System.out.println("Booked: " + aug24.getMonth() + " " + aug24.getDay() + "th");
            bookingAmount++;
        } catch (DateNotAvailable e) {
            System.out.println("Sorry that date is already booked!");
            bookingAmount = 0;
            runBooking();
        }
    }

    //EFFECTS: books the given date
    private void bookDate6() {
        try {
            booked.bookDate(aug25);
            System.out.println("Booked: " + aug25.getMonth() + " " + aug25.getDay() + "th");
            bookingAmount++;
        } catch (DateNotAvailable e) {
            System.out.println("Sorry that date is already booked!");
            bookingAmount = 0;
            runBooking();
        }
    }

    //EFFECTS: books the given date
    private void bookDate7() {
        try {
            booked.bookDate(aug26);
            System.out.println("Booked: " + aug26.getMonth() + " " + aug26.getDay() + "th");
            bookingAmount++;
        } catch (DateNotAvailable e) {
            System.out.println("Sorry that date is already booked!");
            bookingAmount = 0;
            runBooking();
        }
    }

    //EFFECTS: books the given worker
    private void bookSahil() {
        System.out.println("Booked Sahil! ");
        booked.bookWorker(worker2);
    }

    //EFFECTS: books the given worker
    private void bookHarman() {
        System.out.println("Booked Harman! ");
        booked.bookWorker(worker1);
    }

    //EFFECTS: books the given service
    private void bookFlowerService() {
        System.out.println("Booked: " + flowers.getServiceDescription());
        booked.bookService(flowers);
    }

    //EFFECTS: books the given service
    private void bookGardenService() {
        System.out.println("Booked: " + garden.getServiceDescription());
        booked.bookService(garden);
    }

    //EFFECTS: books the given service
    private void bookYardCleanup() {
        System.out.println("Booked: " + yardCleanup.getServiceDescription());
        booked.bookService(yardCleanup);
    }

    //EFFECTS: books the given service
    private void bookLawnMowing() {
        System.out.println("Booked: " + lawnMowing.getServiceDescription());
        booked.bookService(lawnMowing);
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    //MODIFIES: this
    //EFFECTS: initializes all services, workers and dates.
    private void init() {
        // make no lists in services workers dates
        // have lists in BookAppointments
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        services = new ArrayList<Services>();
        workers = new ArrayList<Workers>();
        date = new ArrayList<Dates>();

        lawnMowing = new Services("LawnMowing&Trimming");
        yardCleanup = new Services("YardCleanup");
        garden = new Services("Garden&WeedControl");
        flowers = new Services("FlowerServices");

        worker1 = new Workers("Harman");

        worker2 = new Workers("Sahil");

        aug20 = new Dates("August", 20);
        aug21 = new Dates("August", 21);
        aug22 = new Dates("August", 22);
        aug23 = new Dates("August", 23);
        aug24 = new Dates("August", 24);
        aug25 = new Dates("August", 25);
        aug26 = new Dates("August", 26);

        booked = new BookAppointments(services, workers, date);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // EFFECTS: displays menu of options to user
    private void displayMenuForServices() {
        System.out.println("\nWelcome to Clean Cuts!");
        System.out.println("\nSelect from these services:");
        System.out.println("\tl -> LawnMowing&Trimming");
        System.out.println("\ty -> YardCleanup");
        System.out.println("\tg -> Garden&WeedControl");
        System.out.println("\tf -> FlowerServices");
        System.out.println("\tq -> Quit");
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // EFFECTS: displays menu of options to user
    private void displayMenuForSavingAndLoading() {
        System.out.println("\nSelect: ");
        System.out.println("\ts -> Save");
        System.out.println("\tl -> Load");
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // EFFECTS: displays menu of options to user
    private void displayMenuForWorkers() {
        System.out.println("\nSelect workers: ");
        System.out.println("\th -> Harman");
        System.out.println("\ts -> Sahil");
        ;
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // EFFECTS: displays menu of options to user
    private void displayMenuForDates() {
        System.out.println("\nSelect date: ");
        System.out.println("\ta -> August 20th");
        System.out.println("\tb -> August 21st");
        System.out.println("\tc -> August 22nd");
        System.out.println("\td -> August 23rd");
        System.out.println("\te -> August 24th");
        System.out.println("\tf -> August 25th");
        System.out.println("\tg -> August 26th");
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // EFFECTS: displays menu of options to user
    private void displayMenuForBookedAppointments() {
        System.out.println("\nView Booked Appointments: ");
        System.out.println("\tb -> Booked Appointments");
    }


    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // EFFECTS: displays menu of options to user
    private void displayMenuForLoad() {
        System.out.println("\nWelcome to Clean Cuts: ");
        System.out.println("\tl -> Load saved appointments!");
        System.out.println("\ts -> Skip loading");
    }

}
