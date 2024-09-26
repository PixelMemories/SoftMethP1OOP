import java.util.Date;

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
        return this.fname.compareTo(other.fname);
    }

    @Override
    public String toString() {
        return lname + ", " + fname + " (DOB: " + dob + ")";
    }

}
