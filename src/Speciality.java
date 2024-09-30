/**
 * Enum representing different medical specialties.
 * Each specialty has its own associated charge for visits.
 *
 * @author Divit Shetty (dps190)
 */
public enum Speciality {
    FAMILY(250),         // $250 per visit
    PEDIATRICIAN(300),   // $300 per visit
    ALLERGIST(350);      // $350 per visit

    private final int charge; // Charge per visit

    // Constructor to set the charge for each specialty
    /**
     * Constructs a Speciality with the specified charge per visit.
     *
     * @param charge the charge for a visit
     */
    Speciality(int charge) {
        this.charge = charge;
    }

    // Getter for the charge
    /**
     * Returns the charge associated with this specialty.
     *
     * @return the charge per visit
     */
    public int getCharge() {
        return charge;
    }

    // Testbed main method
    /**
     * Testbed main method to test the Specialty enum.
     */

    public static void main(String[] args) {
        // Test the getCharge method for each specialty
        System.out.println("Testing Specialty charges:");
        for (Speciality speciality : Speciality.values()) {
            System.out.println(speciality.name() + " charge: $" + speciality.getCharge());
        }
    }
}
