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
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // Method to display the time in a readable format
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

    // Getter for the hour
    public int getHour() {
        return hour;
    }

    // Getter for the minute
    public int getMinute() {
        return minute;
    }
    
    // Static method to get Timeslot by index
    public static Timeslot getIndex(int index) {
        switch (index) {
            case 1: return SLOT1;
            case 2: return SLOT2;
            case 3: return SLOT3;
            case 4: return SLOT4;
            case 5: return SLOT5;
            case 6: return SLOT6;
            default: throw new IllegalArgumentException("Invalid timeslot index.");
        }
    }

    // Testbed main
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
