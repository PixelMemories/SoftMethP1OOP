/**
 * Enum representing the available timeslots during the clinic's working hours.
 * Each timeslot contains an hour and a minute to specify the time.
 *
 * @author Divit Shetty (dps190)
 */
public enum Timeslot {
    SLOT1(9, 0),
    SLOT2(10, 45),
    SLOT3(11, 15),
    SLOT4(13, 30),
    SLOT5(15, 0),
    SLOT6(16, 15);

    private final int hour;
    private final int minute;

    // Constructor for the enum
    /**
     * Constructs a timeslot with the specified hour and minute.
     *
     * @param hour   the hour of the timeslot
     * @param minute the minute of the timeslot
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // Method to display the time in a readable format
    /**
     * Returns a string representation of the timeslot in HH:mm format.
     *
     * @return formatted string representing the timeslot
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

    // Getter for the hour
    /**
     * Gets the hour of the timeslot.
     *
     * @return the hour of the timeslot
     */
    public int getHour() {
        return hour;
    }

    // Getter for the minute
    /**
     * Gets the minute of the timeslot.
     *
     * @return the minute of the timeslot
     */
    public int getMinute() {
        return minute;
    }
    
    // Get Timeslot by index
    /**
     * Returns the timeslot corresponding to the specified index.
     *
     * @param index the index of the timeslot (1 to 6)
     * @return the corresponding Timeslot
     * @throws IllegalArgumentException if the index is invalid
     */
    public static Timeslot getIndex(int index) {
        switch (index) {
            case 1: return SLOT1;
            case 2: return SLOT2;
            case 3: return SLOT3;
            case 4: return SLOT4;
            case 5: return SLOT5;
            case 6: return SLOT6;
            default: throw new IllegalArgumentException(index + " isn't a valid time slot.");
        }
    }

    // Testbed main
    /**
     * Testbed main method to test the Timeslot enum.
     */
    public static void main(String[] args) {
        // Test valid timeslot retrieval by index
        System.out.println("Testing timeslot by index:");
        Timeslot timeslot1 = Timeslot.getIndex(1);
        System.out.println("Timeslot 1: " + timeslot1);

        // Test invalid timeslot retrieval
        try {
            Timeslot invalidTimeslot = Timeslot.getIndex(7); // Invalid index
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
