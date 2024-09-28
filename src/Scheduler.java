/*
* @author: Divit Shetty (dps190) & Richard Li (rl902)
 */
import java.util.Scanner;

public class Scheduler {

    private MedicalRecord medicalRecord;

    //Constructor to initialize MedicalRecord
    //starts empty
    public Scheduler() {
        medicalRecord = new MedicalRecord();
    }

    // Method to process each command in command line
    private void processCommand(String input) {
        String[] tokens = input.split(",");
        String command = tokens[0];

        switch (command) {
            case "S":
                scheduleAppointment(tokens);
                break;
            // "Q" quits the program
            case "Q":
                System.out.println("Scheduler terminated.");
                System.exit(0);
            default:
                System.out.println("Invalid command.");
        }
    }


    // Helper method to parse a date string into a Date object
    private Date parseDate(String dateStr) {
        String[] parts = dateStr.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(year, month, day);
    }

    // Method to handle scheduling an appointment (S command)
    private void scheduleAppointment(String[] tokens) {
        try {
            //S,9/30/2024,1,John,Doe,12/13/1989,Patel
            // Parse the date of the appointment
            Date appointmentDate = parseDate(tokens[1]);
            // Parse the timeslot
            Timeslot timeslot = Timeslot.getIndex(Integer.parseInt(tokens[2]));
            // Create the Profile using the first name, last name, and date of birth then add it to "bag"
            Profile profile = new Profile(tokens[3], tokens[4], parseDate(tokens[5]));
            medicalRecord.addPatient(profile);
            // Get the provider based on input
            Provider provider = Provider.valueOf(tokens[6].toUpperCase());
            //System.out.println("Parsed provider: "+provider);

            // Create an Appointment object with the parsed data
            Appointment appointment = new Appointment(appointmentDate, timeslot, profile, provider);

            //Debug statement. Should print true if appointment was added properly
            //System.out.println("Testing: " + medicalRecord.addVisit(profile, appointment));
            // Add the appointment to the medical record
            if (medicalRecord.addVisit(profile, appointment)) {
                System.out.println("Appointment scheduled.");
            } else {
                System.out.println("Appointment couldn't be scheduled.");
                /*
                System.out.println("\nPrinting visit history for John Doe:");
                medicalRecord.printPatientHistory(profile);
                 */
            }
        } catch (Exception e) {
            System.out.println("Error processing appointment: " + e.getMessage());
        }
    }

    // The run method to process user input commands
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scheduler is running:");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            processCommand(input);
        }
    }
}
