/*
 * @author: Divit Shetty (dps190) & Richard Li (rl902)
 */
import java.util.Scanner;

public class Scheduler {

    private MedicalRecord medicalRecord;
    private List appointmentsList;

    // Constructor to initialize MedicalRecord
    public Scheduler() {
        medicalRecord = new MedicalRecord();
        appointmentsList = new List();
    }

    // Method to process each command in command line
    private void processCommand(String input) {
        String[] tokens = input.split(",");
        String command = tokens[0].toUpperCase();  // commands are case-sensitive (upper case)

        switch (command) {
            case "S":
                scheduleAppointment(tokens);
                break;
            case "C":
                cancelAppointment(tokens);
                break;
            case "R":
                rescheduleAppointment(tokens);
                break;
            case "PA":
                appointmentsList.printByAppointment();
                break;
            case "PP":
                appointmentsList.printByPatient();
                break;
            case "PL":
                appointmentsList.printByLocation();
                break;
            case "PS":
                //medicalRecord.printBillingHistory();
                break;
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
            // S,9/30/2024,1,John,Doe,12/13/1989,Patel
            Date appointmentDate = parseDate(tokens[1]);
            Timeslot timeslot = Timeslot.getIndex(Integer.parseInt(tokens[2]));
            Profile profile = new Profile(tokens[3], tokens[4], parseDate(tokens[5]));
            medicalRecord.addPatient(profile);
            Provider provider = Provider.valueOf(tokens[6].toUpperCase());

            Appointment appointment = new Appointment(appointmentDate, timeslot, profile, provider);

            if (medicalRecord.addVisit(profile, appointment)) {
                appointmentsList.add(appointment);
                System.out.println("Appointment scheduled.");
            } else {
                System.out.println("Appointment couldn't be scheduled.");
            }
        } catch (Exception e) {
            System.out.println("Error processing appointment: " + e.getMessage());
        }
    }

    // Method to handle canceling an appointment (C command)
    private void cancelAppointment(String[] tokens) {
        try {
            // C,9/30/2024,1,John,Doe,12/13/1989
            Date appointmentDate = parseDate(tokens[1]);
            Timeslot timeslot = Timeslot.getIndex(Integer.parseInt(tokens[2]));
            Profile profile = new Profile(tokens[3], tokens[4], parseDate(tokens[5]));

            // We create a temporary appointment object without a provider since it’s not needed for comparison.
            Appointment tempAppointment = new Appointment(appointmentDate, timeslot, profile, null);

            // We need to manually check for a match in the appointments list by comparing date, timeslot, and profile.
            Appointment appointmentToCancel = null;
            for (int i = 0; i < appointmentsList.getSize(); i++) {
                Appointment current = appointmentsList.get(i);
                if (current.compareTo(tempAppointment)==0) {
                    appointmentToCancel = current;
                    break;
                }
            }

            if (appointmentToCancel != null) {
                appointmentsList.remove(appointmentToCancel);
                System.out.println("Appointment canceled.");
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }

    // Method to handle rescheduling an appointment (R command)
    // does not work
    private void rescheduleAppointment(String[] tokens) {
        try {
            // R,9/30/2024,1,John,Doe,12/13/1989,2
            Date appointmentDate = parseDate(tokens[1]);
            Timeslot oldTimeslot = Timeslot.getIndex(Integer.parseInt(tokens[2]));
            Profile profile = new Profile(tokens[3], tokens[4], parseDate(tokens[5]));
            Timeslot newTimeslot = Timeslot.getIndex(Integer.parseInt(tokens[6]));

            Appointment oldAppointment = new Appointment(appointmentDate, oldTimeslot, profile, null);

            if (appointmentsList.contains(oldAppointment)) {
                Appointment newAppointment = new Appointment(appointmentDate, newTimeslot, profile, oldAppointment.getProvider());
                if (medicalRecord.addVisit(profile, newAppointment)) {
                    appointmentsList.remove(oldAppointment);
                    appointmentsList.add(newAppointment);
                    System.out.println("Appointment rescheduled.");
                } else {
                    System.out.println("Timeslot not available for the provider.");
                }
            } else {
                System.out.println("Original appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error rescheduling appointment: " + e.getMessage());
        }
    }

    // The run method to process user input commands
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scheduler is running:");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                processCommand(input);
            }
        }
    }
}
