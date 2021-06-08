package utilities;
// date util is to check if the String date with format // 10-04-2020 is a valid date or not
public class DateUtil {
    public static boolean canParseDate(String dateString) {
        int[] daysForMonth = { 31, 28, 31, 30, 31, 30 ,31 ,31 ,30 , 31, 30, 31};
        int[] parsedDate = {0,0,0}; // stores parsed date ...
        // ... this date can be returned to a Data() Object in future requirements
        try {
            String[] dateArr = dateString.split("-", 3);
            if (dateArr.length != 3 || dateArr[0].length() != 2 || dateArr[1].length() != 2 || dateArr[2].length() != 4) {
                return false;
            }

            // check if argument includes Non Number values // removing leading zeros and parsing to integer
            parsedDate[0] = Integer.parseInt(dateArr[0].replaceAll("^0*", ""));  // day
            parsedDate[1] = Integer.parseInt(dateArr[1].replaceAll("^0*", ""));  // month
            parsedDate[2] = Integer.parseInt(dateArr[2].replaceAll("^0*", ""));  // year

            if (isLeapYear(parsedDate[2]))
                daysForMonth[1] = 29; // february limit will be 29 in leap years

            // Football season will be 2020 - 2021
            if (parsedDate[2] > 2021 || parsedDate[2] < 2020) {
                return false;
            }
            return parsedDate[0] >= 1 && parsedDate[0] <= daysForMonth[parsedDate[1] - 1];

        } catch (Exception e) {
            return false;
        }
    }
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
    }

}