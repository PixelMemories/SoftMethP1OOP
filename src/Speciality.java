/*
 * @author Divit Shetty
 */
public enum Speciality {
    FAMILY(250),         // $250 per visit
    PEDIATRICIAN(300),   // $300 per visit
    ALLERGIST(350);      // $350 per visit

    private final int charge; // Charge per visit

    // Constructor to set the charge for each specialty
    Speciality(int charge) {
        this.charge = charge;
    }

    // Getter for the charge
    public int getCharge() {
        return charge;
    }

    // Testbed main method
    public static void main(String[] args) {
        // Test the getCharge method for each specialty
        System.out.println("Testing Specialty charges:");
        for (Speciality speciality : Speciality.values()) {
            System.out.println(speciality.name() + " charge: $" + speciality.getCharge());
        }
    }
}
