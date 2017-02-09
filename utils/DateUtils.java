public class DateUtils
{
    public static final String DATE_FORMAT_SIMPLE_DATE_TIME = "MM/dd 'at' hh:mm a";
    public static final String DATE_FORMAT_SIMPLE_DATE = "dd-MM-yyyy";
    public static final String DATE_FORMAT_SIMPLE_TIME = "hh:mm a";
    public static final String DATE_FORMAT_24_HOURS_TIME = "HH:mm";
    public static final String DATE_FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
    public static final String DATE_FORMAT_RFC_2616 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String DATE_FORMAT_CHAT = "EEE, d MMM yyyy h:mm a";
    public static final String DATE_FORMAT_START_DATE_MESSAGE = "MMMM d 'at' h:mm a zzz";
    public static final String DATE_FORMAT_START_DATE_DIALOG = "EEEE, MMMM d, yyyy 'at' h:mm a";
    @SuppressLint("SimpleDateFormat") private static final DateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    /**
     * Generic date to string converter
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format)
    {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    /**
     * Generic string to date converter
     *
     * @param value
     * @param format
     * @return
     */
    public static Date dateFromString(String value, String format)
    {
        try
        {
            return new SimpleDateFormat(format, Locale.getDefault()).parse(value);
        } catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * Get Date from ISO8601 string
     *
     * @param iso8601Date
     * @return Date
     */
    public static Date ISO8601toDate(String iso8601Date) throws ParseException
    {
        Date parsedDate = dateFromString(iso8601Date, DATE_FORMAT_ISO_8601);
        return parsedDate == null ? new Date() : parsedDate;
    }

    /**
     * Convert date formats
     *
     * @param iso8601Date
     * @param format
     * @return String The converted date
     */
    public static String ISO8601toDateTimeString(String iso8601Date, final String format)
    {
        try
        {
            // Get date object from original format
            Date date = ISO8601toDate(iso8601Date);
            return dateToString(date, format);
        } catch (ParseException e)
        {
            e.printStackTrace();
            return dateToString(new Date(), format);
        }
    }

    /**
     * Generate list hours string [1-24]
     *
     * @return
     */
    public static String[] generateHours()
    {
        int i = 0;
        String[] hours = new String[24];
        while (i < 24)
        {
            hours[i] = String.valueOf(i + 1);
            i++;
        }
        return hours;
    }

    /**
     * Generate list hours string [0-24]
     *
     * @return
     */
    public static String[] generateHoursWithZero()
    {
        int i = 0;
        String[] hours = new String[25];
        while (i < 25)
        {
            hours[i] = String.valueOf(i);
            i++;
        }
        return hours;
    }

    public static boolean isAfterToday(Calendar chooseCalendar)
    {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, -1);
        if (chooseCalendar.after(today))
        {
            return true;
        }
        return false;
    }

    public static String toISO8601Date(Date date)
    {
        return ISO_8601_DATE_FORMAT.format(date);
    }
}

