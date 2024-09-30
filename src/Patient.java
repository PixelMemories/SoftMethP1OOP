/**
 * Patient class represents a patient with a profile and a list of visits.
 * It supports adding visits, calculating charges, and comparing patients based on their profile.
 *
 *
 * @author Richard Li - rl902
 */

public class Patient implements Comparable<Patient>{

    private Profile profile;
    private Visit visits;  // Start of linked list of visits

    /**
     * Constructs a Patient object with the given profile.
     *
     * @param profile the profile of the patient
     */
    public Patient(Profile profile) {
        this.profile = profile;
        this.visits = null;  // No visits initially
    }

    // Add a visit to the patient's visit list
    /**
     * Adds a visit to the patient's visit list.
     *
     * @param visit the visit to be added to the list
     */
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

    //Method to get the first visit (needed for MedicalRecord)
    /**
     * Returns the head of the visit list.
     *
     * @return the first visit in the visit list
     */
    public Visit getVisits(){
        return visits; // gives access to the visit list (from the head)
    }

    // Calculate total charge from all visits
    /**
     * Calculates the total charge from all visits.
     *
     * @return the total charge based on the cost of all visits
     */
    public int charge() {
        int totalCharge = 0;
        Visit current = visits;
        while (current != null) {
            totalCharge += current.getAppointment().getCost();  // Assuming Appointment has a getCost() method
            current = current.getNext();
        }
        return totalCharge;
    }

    /**
     * Returns the profile of the patient.
     *
     * @return the patient's profile
     */

    public Profile getProfile() {
        return profile;
    }

    /**
     * Compares this patient to another patient based on their profile.
     *
     * @param other the other patient to compare
     * @return a negative integer, zero, or a positive integer as this patient
     *         is less than, equal to, or greater than the other patient
     */
    @Override
    public int compareTo(Patient other) {
        return this.profile.compareTo(other.getProfile());
    }

    /**
     * Returns a string representation of the patient, with their profile and the total amount due.
     *
     * @return a string representation of the patient
     */
    @Override
    public String toString() {
        return profile.toString() + " [amount due: $" + charge()+"]";
    }


}
