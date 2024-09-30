/*
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
            return lastNameComparison;
        }

        int firstNameComparison = this.fname.compareTo(other.fname);
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        // Compare date of birth last
        return this.dob.compareTo(other.dob);
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
        Profile profile3 = new Profile("Jane", "Doe", new Date(1990, Date.NOVEMBER, 5));
        Profile profile4 = new Profile("Alice", "Smith", new Date(1995, Date.JANUARY, 1)); // 1995-02-01
        Profile profile5 = new Profile("Jane", "Alloy", new Date(1988, Date.JUNE, 20)); // 1988-07-20
        Profile profile6 = new Profile("Jane", "Doe", new Date(1995, Date.MAY, 30)); // 1995-06-30

        System.out.println("Testing compareTo method:");

        // Test 1: Different names, different DOB
        System.out.println("Test 1: profile1 vs profile2: " + profile1.compareTo(profile2)); // Expect: < 0

        // Test 2: Same last name, different first names, same DOB
        System.out.println("Test 2: profile1 vs profile3: " + profile2.compareTo(profile3)); // Expect: < 0

        // Test 3: Same last name, same first name, different DOB
        System.out.println("Test 3: profile3 vs profile6: " + profile3.compareTo(profile6)); // Expect: < 0

        // Test 4: Different last names, same first names, different DOB
        System.out.println("Test 4: profile5 vs profile2: " + profile5.compareTo(profile2)); // Expect: < 0

        // Test 5: Same profile
        System.out.println("Test 5: profile1 vs profile1: " + profile1.compareTo(profile1)); // Expect: 0

        // Test 6: Different last names, different FirstNames, different DOB
        System.out.println("Test 6: profile1 vs profile4: " + profile1.compareTo(profile4)); // Expect: < 0
    }
}
