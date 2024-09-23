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
    
    // Constants for months and their respective days
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    // Leap year constants
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int LEAP_YEAR_FEB_DAYS = 29;
    
    // Day boundaries
    public static final int MIN_DAY = 1;
    
    //isLeapYear checks if the year is a leap year and works in isValid
    // Check if the year is a leap year
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            } else {
                return true;
            }
        }
        return false; // Not a leap year
    }


    //isValid to check if the date is a valid calendar date
    //works with isLeapYear to check if it's a leap year
    public boolean isValid() {
        if (month < JANUARY || month > DECEMBER) {
            return false;
        }

        // Days in each month
        final int[] DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Check for leap year in February
        if (isLeapYear() && month == FEBRUARY) {
            DAYS_IN_MONTH[FEBRUARY] = 29;
        }

        // Check for valid day in the month
        return day >= MIN_DAY && day <= DAYS_IN_MONTH[month];
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
        return false;
        }
        Date other = (Date) obj;
        return year == other.year && month == other.month && day == other.day;
    }

    // Testbed main() method
    public static void main(String[] args) {
        // Valid dates test cases
        System.out.println("Testing valid dates:");
        
        // Test case 1: Leap year date (valid)
        Date validDate1 = new Date(2020, FEBRUARY, 29); // Leap year
        System.out.println(validDate1 + " is valid: " + validDate1.isValid());

        // Test case 2: Non-leap year date (valid)
        Date validDate2 = new Date(2021, DECEMBER, 31); // Valid date
        System.out.println(validDate2 + " is valid: " + validDate2.isValid());

        // Invalid dates test cases
        System.out.println("\nTesting invalid dates:");

        // Test case 3: Non-leap year February 29 (invalid)
        Date invalidDate1 = new Date(2019, FEBRUARY, 29); // Non-leap year
        System.out.println(invalidDate1 + " is valid: " + invalidDate1.isValid());

        // Test case 4: April 31st (invalid)
        Date invalidDate2 = new Date(2023, APRIL, 31); // April has only 30 days
        System.out.println(invalidDate2 + " is valid: " + invalidDate2.isValid());

        // Test case 5: Invalid month (greater than 12)
        Date invalidDate3 = new Date(2022, 13, 1); // Invalid month
        System.out.println(invalidDate3 + " is valid: " + invalidDate3.isValid());

        // Test case 6: Invalid negative day
        Date invalidDate4 = new Date(2023, FEBRUARY, -1); // Invalid negative day
        System.out.println(invalidDate4 + " is valid: " + invalidDate4.isValid());

        // Test equality
        System.out.println("\nTesting equals() method:");
        Date date1 = new Date(2020, FEBRUARY, 29);
        Date date2 = new Date(2020, FEBRUARY, 29);
        Date date3 = new Date(2021, DECEMBER, 31);
        System.out.println(date1 + " equals " + date2 + ": " + date1.equals(date2));
        System.out.println(date1 + " equals " + date3 + ": " + date1.equals(date3));
    }
}