/*
 * @author Richard Li - rl902
 */
public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

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

    @Override
    public String toString() {
        return lname + ", " + fname + " (DOB: " + dob + ")";
    }

    //Need a testbed main()
    //Need test cases for compareTo and a testing pdf for this method
    public static void main(String[] args) {
        Profile profile1 = new Profile("John", "Doe", new Date(1989, Date.DECEMBER, 13));
        Profile profile2 = new Profile("Jane", "Smith", new Date(1990, Date.NOVEMBER, 5));
        System.out.println("Testing equals method:");
        System.out.println("1 equals 2: " + profile1.compareTo(profile2)); //Expect: false
        System.out.println("appointment1 equals appointment1: " + profile1.equals(profile1)); //Expect: true

    }
}
