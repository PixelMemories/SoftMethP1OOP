/*
 * @author Richard Li - rl902
 */

import java.util.Date;
import java.util.Objects;

public class Patient implements Comparable<Patient>{

    private Profile profile;
    private Visit visits;  // Start of linked list of visits

    public Patient(Profile profile) {
        this.profile = profile;
        this.visits = null;  // No visits initially
    }

    // Add a visit to the patient's visit list
    public void addVisit(Visit visit) {
        if (visits == null) {
            visits = visit;
        } else {
            Visit current = visits;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(visit);
        }
    }

    // Calculate total charge from all visits
    public int charge() {
        int totalCharge = 0;
        Visit current = visits;
        while (current != null) {
            totalCharge += current.getAppointment().getCost();  // Assuming Appointment has a getCost() method
            current = current.getNext();
        }
        return totalCharge;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public int compareTo(Patient other) {
        return this.profile.compareTo(other.getProfile());
    }

    @Override
    public String toString() {
        return "Patient: " + profile.toString() + ", Total Charges: " + charge();
    }
}