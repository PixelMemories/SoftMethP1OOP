/**
 * Enum representing the available medical providers.
 * Each provider has its own location and a specialty.
 *
 * @author Divit Shetty (dps190)
 */
public enum Provider {
    PATEL(Location.BRIDGEWATER, Speciality.FAMILY),
    LIM(Location.BRIDGEWATER, Speciality.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Speciality.FAMILY),
    HARPER(Location.CLARK, Speciality.FAMILY),
    KAUR(Location.PRINCETON, Speciality.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Speciality.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Speciality.ALLERGIST),
    CERAVOLO(Location.EDISON, Speciality.PEDIATRICIAN);

    private final Location location;
    private final Speciality specialty;

    // Constructor for the enum with location and specialty
    /**
     * Constructs a Provider enum with the specified location and specialty.
     *
     * @param location the location where the provider practices
     * @param specialty the provider's medical specialty
     */
    Provider(Location location, Speciality specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    // Getter for location
    /**
     * Returns the location where the provider practices.
     *
     * @return the location of the provider
     */
    public Location getLocation() {
        return location;
    }

    // Getter for specialty
    /**
     * Returns the specialty of the provider.
     *
     * @return the provider's medical specialty
     */
    public Speciality getSpecialty() {
        return specialty;
    }

    // Testbed main method
    /**
     * Testbed main method to test the Provider enum
     */
    public static void main(String[] args) {
        System.out.println("Testing Provider enum values:");
        for (Provider provider : Provider.values()) {
            System.out.println(provider.name() + " practices at " + provider.getLocation() + " with specialty " + provider.getSpecialty());
        }
    }
}
