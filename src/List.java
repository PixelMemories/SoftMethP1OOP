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

    // Print appointments ordered by patient profile, then date/timeslot
    public void printByPatient() {
        Arrays.sort(appointments, 0, size, (a, b) -> {
            int profileCompare = a.getPatient().compareTo(b.getPatient());
            if (profileCompare != 0) return profileCompare;

            int dateCompare = a.getDate().compareTo(b.getDate());
            if (dateCompare != 0) return dateCompare;

            return a.getTimeslot().compareTo(b.getTimeslot());
        });

        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

    // Print appointments ordered by location (provider's county), then date/timeslot
    public void printByLocation() {
        Arrays.sort(appointments, 0, size, (a, b) -> {
            int locationCompare = a.getProvider().getLocation().compareTo(b.getProvider().getLocation());
            if (locationCompare != 0) return locationCompare;

            int dateCompare = a.getDate().compareTo(b.getDate());
            if (dateCompare != 0) return dateCompare;

            return a.getTimeslot().compareTo(b.getTimeslot());
        });

        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

    // Print appointments ordered by date/timeslot, then provider name
    public void printByAppointment() {
        Arrays.sort(appointments, 0, size, (a, b) -> {
            int dateCompare = a.getDate().compareTo(b.getDate());
            if (dateCompare != 0) return dateCompare;

            int timeslotCompare = a.getTimeslot().compareTo(b.getTimeslot());
            if (timeslotCompare != 0) return timeslotCompare;

            //return a.getProvider().getName().compareTo(b.getProvider().getName());
            return 0; // need to talk abt provider
        });

        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }
}