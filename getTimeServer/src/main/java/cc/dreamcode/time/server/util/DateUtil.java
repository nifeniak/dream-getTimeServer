package cc.dreamcode.time.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static long timeFromDate(String s) {
        Date date;
        try {
            FORMAT.setTimeZone(TimeZone.getTimeZone("CET"));
            date = FORMAT.parse(s);
        } catch (ParseException e) {
            return 0L;
        }
        return date.getTime();
    }

}
