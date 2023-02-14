package ui;

import model.BookAppointments;

import javax.swing.*;
import java.awt.*;


// Clean cuts booked appointments GUI
public class BookedAppointmentsGUI {
    private JFrame frame;
    private JLabel newServiceButton;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private ImageIcon cleanCutsIcon;


    //EFFECTS: creates the frame and calls needed methods
    public BookedAppointmentsGUI(BookAppointments ba, int counter) {
        frame = new JFrame();
        imagePanel = new JPanel();
        imageLabel = new JLabel();
        makeFrame();
        displayAppointments(ba, counter);
        makeImage();
        frame.add(imagePanel);
        frame.setVisible(true);
    }

    //EFFECTS: creates and adds the image to the frame
    private void makeImage() {
        cleanCutsIcon = new ImageIcon("data/CleanCuts.png");
        imagePanel.setBounds(0,0,50,50);
        imagePanel.add(imageLabel);
        imageLabel.setIcon(cleanCutsIcon);
    }

    //EFFECTS: creates labels for all the booked appointments and displays them
    private void displayAppointments(BookAppointments ba, int counter) {

        for (int i = 0; i < ba.getServiceList().size(); i++) {
            newServiceButton = new JLabel();
            newServiceButton.setText(ba.getServiceList().get(i).getServiceDescription() + " booked with "
                    + ba.getWorkersList().get(i).getName() + " on "
                    + ba.getDatesList().get(i).getMonth() + " " + ba.getDatesList().get(i).getDay());

            frame.add(newServiceButton);
        }

    }


    //EFFECTS: creates the frame
    private void makeFrame() {
        frame.setTitle("Booked Appointments");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout());
        frame.setResizable(true);
    }

}
