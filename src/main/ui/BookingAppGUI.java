package ui;

import exceptions.DateNotAvailable;
import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Clean cuts booking app GUI
public class BookingAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static BookAppointments booked;
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

    private JFrame frame;
    private JLabel title;
    private JLabel selectServices;
    private JPanel panel;
    private JPanel servicesPanel;
    private Border border;
    private JLabel lawnMowingLabel;
    private JLabel yardCleanupLabel;
    private JLabel gardenLabel;
    private JLabel flowerLabel;
    private JTextField serviceText;
    private JButton bookButton;
    private JLabel successfullyBookedService;
    private JPanel workerPanel;
    private JPanel datePanel;
    private JButton worker1Button;
    private JButton worker2Button;
    private JLabel successfullyBookedWorker;
    private JLabel selectWorkerLabel;
    private JButton date1;
    private JButton date2;
    private JButton date3;
    private JButton date4;
    private JButton date5;
    private JButton date6;
    private JButton date7;
    private JLabel successfullyBookedDate;
    private JButton viewBookedAppointmentsButton;
    private int appointmentCounter;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel savedLabel;
    private JLabel loadedLabel;
    private JButton removeLastAddedAppointmentButton;
    private JLabel successfullyRemovedAppointment;

    //EFFECTS: initializes booked appointments and all needed methods
    public BookingAppGUI() {

        init();
        frame = new JFrame();
        panel = new JPanel();
        servicesPanel = new JPanel();
        makeFrame();
        panel.setLayout(null);
        servicesPanel.setLayout(null);
        panel.setBounds(250, 0, 300, 50);
        panel.setBackground(Color.BLACK);

        makeTitle();
        makeWorkerTitle();

        border = BorderFactory.createLineBorder(Color.green, 2);
        servicesPanel.setBounds(150, 100, 450, 200);
        servicesPanel.setBorder(border);

        selectServices(); // adds the names of all the services as labels
        addBookButton();
        makeWorkerPanel();
        makeDatePanel();
        makeSaveAndLoadButtons();
        viewBookedAppointments();
        makeRemoveButton();
        printEventLog();
        frame.setVisible(true);

    }

    private void printEventLog() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            //EFFECTS: displays all the logged events when the window closes
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
            }
        });
    }


    //EFFECTS: creates a remove button
    private void makeRemoveButton() {
        removeLastAddedAppointmentButton = new JButton();
        removeLastAddedAppointmentButton.setText("Remove Appointment");
        removeLastAddedAppointmentButton.setBounds(0, 600, 200, 100);
        removeLastAddedAppointmentButton.addActionListener(this);

        frame.add(removeLastAddedAppointmentButton);

        successfullyRemovedAppointment = new JLabel("");
        successfullyRemovedAppointment.setBounds(0, 660, 300, 100);
        frame.add(successfullyRemovedAppointment);

    }

    //EFFECTS: creates a save and load button
    private void makeSaveAndLoadButtons() {
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        saveButton.setLayout(null);
        saveButton.setBounds(550, 600, 75, 75);
        saveButton.addActionListener(this);
        loadButton.setLayout(null);
        loadButton.setBounds(650, 600, 75, 75);
        loadButton.addActionListener(this);

        frame.add(saveButton);
        frame.add(loadButton);

        savedLabel = new JLabel("");
        savedLabel.setBounds(550, 660, 50, 50);
        loadedLabel = new JLabel("");
        loadedLabel.setBounds(650, 660, 50, 50);

        frame.add(savedLabel);
        frame.add(loadedLabel);
    }

    //EFFECTS: creates the button for viewing your booked appointments
    private void viewBookedAppointments() {
        viewBookedAppointmentsButton = new JButton();
        viewBookedAppointmentsButton.setText("View All Booked Appointments");
        viewBookedAppointmentsButton.setBounds(225, 600, 300, 100);
        viewBookedAppointmentsButton.addActionListener(this);

        frame.add(viewBookedAppointmentsButton);

    }

    //EFFECTS: creates needed title for worker functionality
    private void makeWorkerTitle() {
        selectWorkerLabel = new JLabel();
        selectWorkerLabel.setText("Select a worker: ");
        selectWorkerLabel.setLayout(null);
        selectWorkerLabel.setBounds(50, 335, 200, 50);
        frame.add(selectWorkerLabel);
        successfullyBookedWorker = new JLabel();
        successfullyBookedWorker.setText("");
        successfullyBookedWorker.setLayout(null);
        successfullyBookedWorker.setBounds(25, 525, 200, 50);

        successfullyBookedDate = new JLabel();
        successfullyBookedDate.setText("");
        successfullyBookedDate.setLayout(null);
        successfullyBookedDate.setBounds(360, 525, 200, 50);


        frame.add(successfullyBookedWorker);
        frame.add(successfullyBookedDate);
    }

    //EFFECTS: makes the JFrame that everything is put on.
    private void makeFrame() {
        frame.setTitle("Book Appointments");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(750, 750);
        frame.add(panel);
        frame.add(servicesPanel);
        frame.setLayout(null);
        frame.setResizable(false);
    }


    //EFFECTS: creates the title of the GUI
    private void makeTitle() {
        title = new JLabel();
        title.setText("Welcome to Clean Cuts!");
        title.setForeground(new Color(0x00A300));
        title.setFont(new Font("MV Boli", Font.PLAIN, 20));
        title.setBounds(30, 0, 300, 50);
        panel.add(title);
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    //MODIFIES: this
    //EFFECTS: initializes all services, workers and dates.
    private void init() {
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
        appointmentCounter = 0;
    }


    //MODIFIES: servicesPanel
    //EFFECTS: adds all services to the service panel
    public void selectServices() {
        initServices();

        selectServices.setBounds(10, 0, 200, 50);
        lawnMowingLabel.setBounds(10, 30, 200, 50);
        yardCleanupLabel.setBounds(10, 60, 200, 50);
        gardenLabel.setBounds(10, 90, 200, 50);
        flowerLabel.setBounds(10, 120, 200, 50);

        servicesPanel.add(lawnMowingLabel);
        servicesPanel.add(yardCleanupLabel);
        servicesPanel.add(gardenLabel);
        servicesPanel.add(flowerLabel);
        servicesPanel.add(selectServices);

        serviceText = new JTextField(20);
        serviceText.setBounds(5, 170, 200, 25);
        serviceText.addActionListener(this);

        servicesPanel.add(serviceText);

    }

    //EFFECTS: creates all the labels for all the services
    private void initServices() {
        selectServices = new JLabel();
        selectServices.setText("Select a Service: ");
        lawnMowingLabel = new JLabel();
        lawnMowingLabel.setText("LawnMowing&Trimming");
        yardCleanupLabel = new JLabel();
        yardCleanupLabel.setText("YardCleanup");
        gardenLabel = new JLabel();
        gardenLabel.setText("Garden&WeedControl");
        flowerLabel = new JLabel();
        flowerLabel.setText("FlowerServices");
    }

    //EFFECTS: creates a button to book services
    private void addBookButton() {
        bookButton = new JButton("Book");
        bookButton.setBounds(200, 170, 75, 25);
        bookButton.addActionListener(this);

        successfullyBookedService = new JLabel("");
        successfullyBookedService.setBounds(275, 170, 200, 25);

        servicesPanel.add(successfullyBookedService);
        servicesPanel.add(bookButton);
    }

    @Override
    //EFFECTS: performs all the specific methods when the ActionEvent happens
    public void actionPerformed(ActionEvent e) {
        checkIfCorrectService(e);
        checkIfCorrectWorker(e);
        try {
            checkIfCorrectDate(e);
        } catch (DateNotAvailable ex) {
            //
        }

        bookAppointmentButton(e);

        saveAndLoadButtonClicked(e);
        removeButtonClicked(e);

    }

    //MODIFIES: booked
    //EFFECTS: removes the last added appointment
    private void removeButtonClicked(ActionEvent e) {
        if (e.getSource() == removeLastAddedAppointmentButton) {
            booked.removeAppointment();
            successfullyRemovedAppointment.setText("Removed last added appointment");
        }
    }

    //EFFECTS: when the save and load buttons are pressed, the given methods are run
    private void saveAndLoadButtonClicked(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveBookAppointments();
            savedLabel.setText("Saved!");
            frame.repaint();
        } else if (e.getSource() == loadButton) {
            loadBookAppointments();
            loadedLabel.setText("Loaded!");
            frame.repaint();
        }
    }


    //EFFECTS: when the viewBookedAppointmentsButton is clicked, this method is called
    // and creates a new window displaying all the booked appointments
    private void bookAppointmentButton(ActionEvent e) {
        if (e.getSource() == viewBookedAppointmentsButton) {
            frame.dispose();
            BookedAppointmentsGUI myGUI = new BookedAppointmentsGUI(booked, appointmentCounter);
        }
    }

    //MODIFIES: booked
    //EFFECTS: adds the correct date to booked when the correct button is clicked
    private void checkIfCorrectDate(ActionEvent e) throws DateNotAvailable {
        if (e.getSource() == date1) {
            booked.bookDate(aug20);
            successfullyBookedDate.setText("Booked August 20th!");
        } else if (e.getSource() == date2) {
            booked.bookDate(aug21);
            successfullyBookedDate.setText("Booked August 21st!");
        } else if (e.getSource() == date3) {
            booked.bookDate(aug22);
            successfullyBookedDate.setText("Booked August 22nd!");
        } else if (e.getSource() == date4) {
            booked.bookDate(aug23);
            successfullyBookedDate.setText("Booked August 23rd!");
        } else if (e.getSource() == date5) {
            booked.bookDate(aug24);
            successfullyBookedDate.setText("Booked August 24th!");
        } else if (e.getSource() == date6) {
            booked.bookDate(aug25);
            successfullyBookedDate.setText("Booked August 25th!");
        } else if (e.getSource() == date7) {
            booked.bookDate(aug26);
            successfullyBookedDate.setText("Booked August 26th!");
        }
    }

    //MODIFIES: booked
    //EFFECTS: adds the correct worker to booked when the chosen worker is clicked
    private void checkIfCorrectWorker(ActionEvent e) {
        if (e.getSource() == worker1Button) {
            booked.bookWorker(worker1);
            appointmentCounter++;
            successfullyBookedWorker.setText("Booked Harman!");
        } else if (e.getSource() == worker2Button) {
            booked.bookWorker(worker2);
            appointmentCounter++;
            successfullyBookedWorker.setText("Booked Sahil!");
        }
    }

    //MODIFIES: booked
    //EFFECTS: adds the correct service to booked when the user clicks one of the service buttons
    private void checkIfCorrectService(ActionEvent e) {
        String serviceBox = serviceText.getText();

        if (serviceBox.equals(lawnMowing.getServiceDescription()) && e.getSource() == bookButton) {
            booked.bookService(lawnMowing);
            successfullyBookedService.setText("Booked LawnMowing!");
        } else if (serviceBox.equals(yardCleanup.getServiceDescription()) && e.getSource() == bookButton) {
            booked.bookService(yardCleanup);
            successfullyBookedService.setText("Booked YardCleanup!");
        } else if (serviceBox.equals(garden.getServiceDescription()) && e.getSource() == bookButton) {
            booked.bookService(garden);
            successfullyBookedService.setText("Booked Garden&Weed!");
        } else if (serviceBox.equals(flowers.getServiceDescription()) && e.getSource() == bookButton) {
            booked.bookService(flowers);
            successfullyBookedService.setText("Booked Flower Services!");
        } else {
            successfullyBookedService.setText("");
        }
    }


    //EFFECTS: creates the worker panel
    private void makeWorkerPanel() {
        workerPanel = new JPanel();
        workerPanel.setBorder(border);
        workerPanel.setLayout(new GridLayout(1, 1));
        workerPanel.setBounds(25, 375, 325, 150);
        frame.add(workerPanel);

        worker1Button = new JButton();
        worker1Button.setText(worker1.getName());
        worker1Button.setBounds(0, 0, 100, 100);
        worker1Button.addActionListener(this);
        worker2Button = new JButton();
        worker2Button.setText(worker2.getName());
        worker2Button.setBounds(0, 100, 100, 100);
        worker2Button.addActionListener(this);

        workerPanel.add(worker1Button);
        workerPanel.add(worker2Button);

    }

    //EFFECTS: creates the date panel
    private void makeDatePanel() {
        datePanel = new JPanel();
        datePanel.setBorder(border);
        datePanel.setLayout(new GridLayout(2, 3));
        datePanel.setBounds(350, 375, 400, 150);
        createDateButton();

        frame.add(datePanel);
        datePanel.add(date1);
        datePanel.add(date2);
        datePanel.add(date3);
        datePanel.add(date4);
        datePanel.add(date5);
        datePanel.add(date6);
        datePanel.add(date7);
    }

    //EFFECTS: creates all the date buttons
    private void createDateButton() {
        date1 = new JButton("August 20th");
        date2 = new JButton("August 21st");
        date3 = new JButton("August 22nd");
        date4 = new JButton("August 23rd");
        date5 = new JButton("August 24th");
        date6 = new JButton("August 25th");
        date7 = new JButton("August 26th");
        date1.addActionListener(this);
        date2.addActionListener(this);
        date3.addActionListener(this);
        date4.addActionListener(this);
        date5.addActionListener(this);
        date6.addActionListener(this);
        date7.addActionListener(this);
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // EFFECTS: saves the workroom to file
    private void saveBookAppointments() {
        try {
            jsonWriter.open();
            jsonWriter.write(booked);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Based on Teller app; link below
    // <https://github.students.cs.ubc.ca/CPSC210/TellerApp.git>
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadBookAppointments() {
        try {
            booked = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (DateNotAvailable e) {
            System.out.println("Can not choose that date");
        }
    }
}
