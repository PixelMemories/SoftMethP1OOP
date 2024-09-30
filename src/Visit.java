/**
 * @author Richard Li - rl902
 * Represents a visit by a patient, which includes the appointment details and a linked list of visits.
 * Each Visit object can be linked to the next visit in the list.
 */

public class Visit {
    private Appointment appointment;
    private Visit next;

    /**
     * Constructs a Visit object with the specified appointment.
     *
     * @param appointment the appointment details for the visit
     */
    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    /**
     * Returns the appointment associated with this visit.
     *
     * @return the appointment for this visit
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Returns the next visit in the linked list.
     *
     * @return the next Visit, or null if there is no next visit
     */
    public Visit getNext() {
        return next;
    }

    /**
     * Sets the next visit in the linked list.
     *
     * @param next the next visit to be linked
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    //might need toString to return appointment details
    /**
     * Returns a string representation of the visit, consisting of the appointment details.
     *
     * @return a string representing the appointment details
     */
    @Override
    public String toString() {
        return appointment.toString();
    }
}
