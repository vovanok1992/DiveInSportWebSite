package tk.vovanok.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IdGenerator {
    public static String generateId() {
        Calendar cal = Calendar.getInstance();    
        long id = Long.parseLong(new SimpleDateFormat("yyMMddHHmmss").format(cal.getTime()));
        return Long.toString(id,30)+Integer.toString(new Object().hashCode(),30);
    }
}
