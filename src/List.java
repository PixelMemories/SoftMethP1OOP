/*
 * @author Richard Li - rl902
 */
import java.util.Arrays;

public class List {
    private Appointment[] appointments;
    private int size;

    // Constructor
    public List() {
        this.appointments = new Appointment[4];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public Appointment get(int index) {
        if (index >= 0 && index < size) {
            return appointments[index];
        }
        throw new IndexOutOfBoundsException("Invalid index.");
    }

    // Helper method to find the index of an appointment
    private int find(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
        }
        return -1;
    }

    // Helper method to grow the array size by 4
    private void grow() {
        appointments = Arrays.copyOf(appointments, appointments.length + 4);
    }

    // Check if the list contains an appointment
    public boolean contains(Appointment appointment) {
        return find(appointment) != -1;
    }

    // Add an appointment to the list
    public void add(Appointment appointment) {
        if (contains(appointment)) {
            System.out.println("Appointment already exists.");
            return;
        }

        if (size == appointments.length) {
            grow();
        }
        appointments[size] = appointment;
        size++;
    }

    // Remove an appointment from the list
    public void remove(Appointment appointment) {
        int index = find(appointment);
        if (index == -1) {
            System.out.println("Appointment not found.");
            return;
        }

        // Shift elements to the left to remove the appointment
        for (int i = index; i < size - 1; i++) {
            appointments[i] = appointments[i + 1];
        }
        appointments[size - 1] = null;
        size--;
    }

    public void printByPatient() {
        System.out.println("** Appointments ordered by patient/date/time **");
        for (int i = 1; i < size; i++) {
            Appointment temp = appointments[i];
            int j = i - 1;
            while (j >= 0 && appointments[j].getPatient().compareTo(temp.getPatient()) > 0) {
                appointments[j + 1] = appointments[j];
                j--;
            }
            appointments[j + 1] = temp;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
        System.out.println("** end of list **");
    }

    public void printByLocation() {
        System.out.println("** Appointments ordered by county/date/time **");
        for (int i = 1; i < size; i++) {
            Appointment temp = appointments[i];
            int j = i - 1;
            while (j >= 0 && appointments[j].getProvider().getLocation().compareTo(temp.getProvider().getLocation()) > 0) {
                appointments[j + 1] = appointments[j];
                j--;
            }
            appointments[j + 1] = temp;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
        System.out.println("** end of list **");
    }


    public void printByAppointment() {
        System.out.println("** Appointments ordered by date/time/provider **");
        for (int i = 1; i < size; i++) {
            Appointment temp = appointments[i];
            int j = i - 1;
            while (j >= 0 && appointments[j].compareTo(temp) > 0) {
                appointments[j + 1] = appointments[j];
                j--;
            }
            appointments[j + 1] = temp;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
        System.out.println("** end of list **");
    }

    //Testbed main to see if it's working well with other classes
    public static void main(String[] args) {

        // Create test Profiles (works)
        Profile profile1 = new Profile("John", "Doe", new Date(1989, Date.DECEMBER, 13));
        Profile profile2 = new Profile("Jane", "Smith", new Date(1990, Date.NOVEMBER, 5));
        Profile profile3 = new Profile("Joe", "Johnson", new Date(1987, Date.OCTOBER, 15));
        Profile profile4 = new Profile("Phoenix", "Wright", new Date(1987, Date.DECEMBER, 16));

        // Create test Appointments (works)
        Appointment appointment1 = new Appointment(new Date(2024, Date.SEPTEMBER, 30), Timeslot.SLOT1, profile1, Provider.PATEL);
        Appointment appointment2 = new Appointment(new Date(2024, Date.SEPTEMBER, 30), Timeslot.SLOT2, profile2, Provider.LIM);
        Appointment appointment3 = new Appointment(new Date(2024, Date.OCTOBER, 1), Timeslot.SLOT3, profile3, Provider.ZIMNES);
        Appointment appointment4 = new Appointment(new Date(2024, Date.OCTOBER, 1), Timeslot.SLOT1, profile4, Provider.ZIMNES);

        // Create the List object (works)
        List appointmentList = new List();

        // Test the add method (works)
        System.out.println("Adding appointments:");
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        appointmentList.add(appointment3);
        appointmentList.add(appointment4);
        appointmentList.printByAppointment(); // Expect: Print all 4 appointments sorted by date/timeslot/provider

        // Test the contains method (Works)
        System.out.println("\nChecking if appointments exist:");
        System.out.println("List contains appointment1: " + appointmentList.contains(appointment1)); //Expect: true
        System.out.println("List contains appointment3: " + appointmentList.contains(appointment3)); //Expect: true
        System.out.println("List contains appointment4: " + appointmentList.contains(appointment4)); //Expect: true

        // Test the remove method (works)
        System.out.println("\nRemoving appointment2:");
        appointmentList.remove(appointment2);
        appointmentList.printByAppointment(); // Expect to print only appointment1 and appointment3

        // Test the printByPatient method (works)
        System.out.println("\nAppointments sorted by patient:");
        appointmentList.printByPatient(); // Expect to print appointments sorted by patient name

        // Test the printByLocation method (Works)
        System.out.println("\nAppointments sorted by location:");
        appointmentList.printByLocation(); // Expect to print appointments sorted by provider's location
    }
}