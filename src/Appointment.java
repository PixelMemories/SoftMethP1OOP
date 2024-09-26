import java.util.Date;

public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    // Constructor
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    // Getters (optional, in case you need them)
    public Date getDate() {
        return date;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public Profile getPatient() {
        return patient;
    }

    public Provider getProvider() {
        return provider;
    }

    public int getCost() {
        Speciality specialty = provider.getSpecialty();
        return specialty.getCharge();
    }

    @Override
    public int compareTo(Appointment other) {
        // Compare by date first
        int dateCompare = this.date.compareTo(other.date);
        if (dateCompare != 0) return dateCompare;

        // If dates are the same, compare by timeslot
        int timeslotCompare = this.timeslot.compareTo(other.timeslot);
        if (timeslotCompare != 0) return timeslotCompare;

        // If timeslot is the same, compare by patient
        return this.patient.compareTo(other.patient);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Appointment other = (Appointment) obj;
        return this.date.equals(other.date) &&
                this.timeslot.equals(other.timeslot) &&
                this.patient.equals(other.patient);
    }

    @Override
    public String toString() {
        String providerDetails = provider.toString(); // Assuming provider has an appropriate toString method
        String patientDetails = String.format("%s %s %s", patient.getFname(), patient.getLname(), patient.getDob().toString());
        return String.format("%tD %s %s [%s]", date, timeslot.toString(), patientDetails, providerDetails);
    }
}