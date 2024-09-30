/*
 * @author Richard Li - rl902
 */

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

        if (this.date == null || other.date == null) return false;

        return this.date.equals(other.date) &&
                this.timeslot.equals(other.timeslot) &&
                this.patient.equals(other.patient);
    }

    @Override
    public String toString() {
        String providerName = provider.toString();
        String providerTown = provider.getLocation().toString();
        String providerSpec = provider.getSpecialty().toString();
        String patientDetails = String.format("%s %s %s", patient.getFname(), patient.getLname(), patient.getDob().toString());
        return String.format("%s %s %s [%s, %s, %s]", date.toString(), timeslot.toString(), patientDetails, providerName, providerTown, providerSpec);
    }

    //Testbed Main for Appointment
//Want to see if it works properly with Date
    public static void main(String[] args) {
        // Create test Profile and Patient objects (Works)
        Profile profile1 = new Profile("John", "Doe", new Date(1989, Date.DECEMBER, 13));
        Profile profile2 = new Profile("Jane", "Smith", new Date(1990, Date.NOVEMBER, 5));

        System.out.println("Profile1: " + profile1);

        // Create test Appointments (Works)
        Appointment appointment1 = new Appointment(new Date(2024, Date.SEPTEMBER, 30), Timeslot.SLOT1, profile1, Provider.PATEL);
        Appointment appointment2 = new Appointment(new Date(2024, Date.SEPTEMBER, 30), Timeslot.SLOT2, profile2, Provider.LIM);
        Appointment appointment3 = new Appointment(new Date(2024, Date.OCTOBER, 1), Timeslot.SLOT3, profile1, Provider.ZIMNES);

        // Test the equals method (Works)
        System.out.println("Testing equals method:");
        System.out.println("appointment1 equals appointment2: " + appointment1.equals(appointment2)); //Expect: false
        System.out.println("appointment1 equals appointment1: " + appointment1.equals(appointment1)); //Expect: true

        // Test the compareTo method (Works)
        System.out.println("\nTesting compareTo method:");
        System.out.println("appointment1 compared to appointment2: " + appointment1.compareTo(appointment2)); //negative
        System.out.println("appointment2 compared to appointment3: " + appointment2.compareTo(appointment3)); //negative

        // Test toString method (Works)
        System.out.println("\nTesting toString method:");
        System.out.println(appointment1);
        System.out.println(appointment2);
        System.out.println(appointment3);
    }
}