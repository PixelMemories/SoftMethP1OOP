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

    private boolean providerAvailableForTimeslot(Provider provider, Date date, Timeslot timeslot) {
        for (int i = 0; i < appointmentsList.getSize(); i++) {
            Appointment appointment = appointmentsList.get(i);
            // Check if the appointment's provider is the same and the appointment is on the same date and timeslot
            if (appointment.getProvider().equals(provider) &&
                    appointment.getDate().equals(date) &&
                    appointment.getTimeslot().equals(timeslot)) {
                return false; // The provider is not available at this timeslot because of a conflict
            }
        }
        return true; // The provider is available
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
            if (providerAvailableForTimeslot(provider, appointmentDate, timeslot)){
                if (medicalRecord.addVisit(profile, appointment)) {
                    appointmentsList.add(appointment);
                    System.out.println("Appointment scheduled.");
                } else {
                    System.out.println("Appointment couldn't be scheduled.");
                }
            } else {
                System.out.println("Appointment couldn't be scheduled. Appointment already exists");
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
            Date appointmentDate = parseDate(tokens[1]);
            Timeslot currentTimeslot = Timeslot.getIndex(Integer.parseInt(tokens[2]));
            Profile profile = new Profile(tokens[3], tokens[4], parseDate(tokens[5]));
            Timeslot newTimeslot = Timeslot.getIndex(Integer.parseInt(tokens[6]));

            Appointment tempAppointment = new Appointment(appointmentDate, currentTimeslot, profile, null);
            boolean appointmentFound = false;

            // Loop through the appointments list to find the matching appointment
            for (int i = 0; i < appointmentsList.getSize(); i++) {
                Appointment appointment = appointmentsList.get(i);

                // Check if this appointment matches by date, timeslot, and profile (ignore provider for search)
                if (tempAppointment.compareTo(appointment)==0) {

                    appointmentFound = true;

                    // Check if provider is available for the new timeslot
                    if (providerAvailableForTimeslot(appointment.getProvider(), appointmentDate, newTimeslot)) {
                        // Create a new appointment with the new timeslot
                        Appointment updatedAppointment = new Appointment(appointmentDate, newTimeslot, profile, appointment.getProvider());

                        // Remove the old appointment and add the updated one
                        appointmentsList.remove(appointment);
                        appointmentsList.add(updatedAppointment);

                        System.out.println("Appointment rescheduled.");
                    } else {
                        System.out.println("Provider is not available for the new timeslot.");
                    }
                    break;
                }
            }

            if (!appointmentFound) {
                System.out.println("Appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error processing reschedule: " + e.getMessage());
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
