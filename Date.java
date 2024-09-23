public class Date implements Comparable <Date>{
    private int year;
    private int month;
    private int day;

    //Constructor to initialize the date
    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    //isLeapYear checks if the year is a leap year and works in isValid
    private boolean isLeapYear() {
        //check if the year is a leap year
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true; 
            }
        }
        return false; // Not a leap year
    }

    //isValid to check if the date is a valid calendar date
    //works with isLeapYear to check if it's a leap year
    public boolean isValid() {
        if (month < 1 || month > 12) {
            return false;
        }
        
        // Days in each month
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // Check for leap year in February
        if (isLeapYear() && month == 2) {
            daysInMonth[2] = 29;
        }
        
        // Check for valid day in the month
        return day >= 1 && day <= daysInMonth[month];
    }

    // Override compareTo()
    // Intended to sort appointments by date
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    // Override toString()
    // Formate the date
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}