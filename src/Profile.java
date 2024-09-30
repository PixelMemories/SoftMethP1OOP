/**
 * @author Richard Li - rl902
 */

/**
 * Represents a profile for a person, including first name, last name, and date of birth.
 * The profile can be compared to other profiles based on last name, first name, and date of birth.
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructs a Profile instance with the specified first name, last name, and date of birth.
     *
     * @param fname the first name of the person
     * @param lname the last name of the person
     * @param dob the date of birth of the person
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Returns the first name of the profile.
     *
     * @return the first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * Returns the last name of the profile.
     *
     * @return the last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * Returns the date of birth of the profile.
     *
     * @return the date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Compares this profile to another profile based on last name, first name, and date of birth.
     *
     * @param other the other Profile to compare to
     * @return a negative integer, zero, or a positive integer as this profile is less than,
     *         equal to, or greater than the specified profile
     */
    @Override
    public int compareTo(Profile other) {
        int lastNameComparison = this.lname.compareTo(other.lname);
        if (lastNameComparison != 0) {
            return Integer.signum(lastNameComparison);  // Will return -1, 0, or 1
        }

        int firstNameComparison = this.fname.compareTo(other.fname);
        if (firstNameComparison != 0) {
            return Integer.signum(firstNameComparison);
        }

        // Compare date of birth last
        return Integer.signum(this.dob.compareTo(other.dob));
    }

    /**
     * Returns a string representation of the profile, including the first name, last name, and date of birth.
     *
     * @return a string in the format "FirstName LastName DateOfBirth"
     */
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob;
    }

    /**
     * Testbed main method to demonstrate the functionality of the Profile class.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Profile profile1 = new Profile("John", "Doe", new Date(1989, Date.DECEMBER, 13));
        Profile profile2 = new Profile("Jane", "Smith", new Date(1990, Date.NOVEMBER, 5));
        Profile profile3 = new Profile("Alice", "Doe", new Date(1995, Date.JANUARY, 1));
        Profile profile4 = new Profile("John", "Doe", new Date(1995, Date.MAY, 30));

        System.out.println("Testing compareTo method:");

        // Test 1: profile1 vs profile2 (Expect: -1)
        System.out.println("Test 1: " + profile1.compareTo(profile2));

        // Test 2: profile3 vs profile1 (Expect: -1)
        System.out.println("Test 2: " + profile3.compareTo(profile1));

        // Test 3: profile1 vs profile4 (Expect: -1)
        System.out.println("Test 3: " + profile1.compareTo(profile4));

        // Test 4: profile2 vs profile1 (Expect: 1)
        System.out.println("Test 4: " + profile2.compareTo(profile1));

        // Test 5: profile1 vs profile3 (Expect: 1)
        System.out.println("Test 5: " + profile1.compareTo(profile3));

        // Test 6: profile4 vs profile1 (Expect: 1)
        System.out.println("Test 6: " + profile4.compareTo(profile1));

        // Test 7: profile1 vs profile1 (Expect: 0)
        System.out.println("Test 7: " + profile1.compareTo(profile1));
    }
}
