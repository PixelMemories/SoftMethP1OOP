/*
 * @author Divit Shetty
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
    Provider(Location location, Speciality specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    // Getter for location
    public Location getLocation() {
        return location;
    }

    // Getter for specialty
    public Speciality getSpecialty() {
        return specialty;
    }

    // Testbed main method
    public static void main(String[] args) {
        System.out.println("Testing Provider enum values:");
        for (Provider provider : Provider.values()) {
            System.out.println(provider.name() + " practices at " + provider.getLocation() + " with specialty " + provider.getSpecialty());
        }
    }
}
