/*
* @author: Divit Shetty (dps190)
*/
public class MedicalRecord {

    private Patient[] patients; //Array of patients
    private int size; //Number of patients in the array

    //Constructor to initialize the medical record with the max size
    public MedicalRecord(){
        patients = new Patient[0];  // Start with an empty array
        size = 0;// Initially no patients
    }

    //Method to dynamically grow the array
    // It'll start empty then double in size when needed
    private void grow() {
        if (patients.length == 0) {
            patients = new Patient[1];  // Initially grow to size 1 from empty
        } else {
            Patient[] temp = new Patient[patients.length * 2];  // Double the array size
            System.arraycopy(patients, 0, temp, 0, size);       // Copy old array to new one
            patients = temp;                                    // Reassign to the new array
        }
    }

    //Method to add a patient to the record
    //Named addPatient() because had another add method for Visits
    public boolean addPatient(Profile profile){
        if(size == patients.length){
            grow(); // grow the array if it's full
        }
        patients[size] = new Patient(profile);
        size++;
        return true;
    }

    //Method to find a patient via profile
    public Patient findPatient(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (patients[i].getProfile().equals(profile)) {
                return patients[i];
            }
        }
        return null;
    }

    //Method to add a visit for a patient
    public boolean addVisit(Profile profile, Appointment appointment){
        //Search for patients using findPatient
        Patient patient = findPatient(profile);
        if(patient == null){
            return false; //patient not found
        }
        patient.addVisit(new Visit(appointment)); // Add a visit to that patient's visit list
        return true;
    }

    //Method to remove a Patient
    public boolean removePatient(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (patients[i].getProfile().equals(profile)) {
                // Shift the rest of the patients down to remove the patient
                for (int j = i; j < size - 1; j++) {
                    patients[j] = patients[j + 1];
                }
                size--;
                return true;
            }
        }
        return false; // not found
    }

    //Method to print the visit history for a specific patient
    public void printPatientHistory(Profile profile) {
        Patient patient = findPatient(profile);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Visit history for " + profile.getFname() + " " + profile.getLname() + ":");
        Visit current = patient.getVisits(); // Assuming you have a getFirstVisit method in Patient
        while (current != null) {
            System.out.println(current); // Calls toString() on Visit
            current = current.getNext();
        }
    }

    //Testbed main for MedicalRecord. Not needed, just wanna make sure it works
    public static void main(String[] args) {

        // Create the MedicalRecord system with an initially empty array
        MedicalRecord medicalRecord = new MedicalRecord();

        // Create test sample Profiles for the patients
        Profile profile1 = new Profile("John", "Doe", new Date(1989, Date.DECEMBER, 13));
        Profile profile2 = new Profile("Jane", "Smith", new Date(1990, Date.JANUARY, 20));
        Profile profile3 = new Profile("Alice", "Johnson", new Date(1985, Date.FEBRUARY, 15));
        Profile profile4 = new Profile("Bob", "Brown", new Date(1975, Date.MARCH, 5));
        Profile profile5 = new Profile("Clara", "Jones", new Date(2000, Date.APRIL, 25));

        // Test adding patients to the medical record
        System.out.println("Adding patients:");
        System.out.println("Add John Doe: " + medicalRecord.addPatient(profile1));  // Should print true
        System.out.println("Add Jane Smith: " + medicalRecord.addPatient(profile2)); // Should print true
        System.out.println("Add Alice Johnson: " + medicalRecord.addPatient(profile3)); // Should print true
        System.out.println("Add Bob Brown: " + medicalRecord.addPatient(profile4)); // Should print true
        System.out.println("Add Clara Jones: " + medicalRecord.addPatient(profile5)); // Should print true

        // Create test Appointments for the patients
        Appointment appointment1 = new Appointment(new Date(2024, Date.SEPTEMBER, 30), Timeslot.SLOT1, profile1, Provider.PATEL);
        Appointment appointment2 = new Appointment(new Date(2024, Date.OCTOBER, 1), Timeslot.SLOT2, profile2, Provider.LIM);
        Appointment appointment3 = new Appointment(new Date(2024, Date.OCTOBER, 2), Timeslot.SLOT3, profile1, Provider.ZIMNES);

        // Test adding visits for the patients
        // All should print true
        System.out.println("\nAdding visits:");
        System.out.println("Add appointment for John Doe: " + medicalRecord.addVisit(profile1, appointment1));
        System.out.println("Add appointment for Jane Smith: " + medicalRecord.addVisit(profile2, appointment2));
        System.out.println("Add another appointment for John Doe: " + medicalRecord.addVisit(profile1, appointment3));

        // Test printing patient history for each patient
        System.out.println("\nPrinting visit history for John Doe:");
        medicalRecord.printPatientHistory(profile1);

        System.out.println("\nPrinting visit history for Jane Smith:");
        medicalRecord.printPatientHistory(profile2);

        //should be empty
        System.out.println("\nPrinting visit history for Alice Johnson:");
        medicalRecord.printPatientHistory(profile3);
    }
}
