/*
 * @author Richard Li - rl902
 */

public class Visit {
    private Appointment appointment;
    private Visit next;

    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Visit getNext() {
        return next;
    }

    public void setNext(Visit next) {
        this.next = next;
    }
}
