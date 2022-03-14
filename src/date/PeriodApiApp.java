package date;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class PeriodApiApp {

    public static void main(String[] args) {
        // 사람용 기간
        LocalDate today = LocalDate.now();
        LocalDate yearFirstDay = LocalDate.of(2022,1,1);

        Period period = Period.between(yearFirstDay, today);
        System.out.println(period.getDays());

        Period until = yearFirstDay.until(today);
        System.out.println(until.getDays());
        System.out.println(until.get(ChronoUnit.DAYS));

        // 기계용 기간
        Instant now = Instant.now();
        Instant plusNow = now.plus(10,ChronoUnit.SECONDS);

        Duration duration = Duration.between(now, plusNow);
        System.out.println(duration.getSeconds());

        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(nowTime.format(MMddyyyy));

        LocalDate parse = LocalDate.parse("06/25/1990", MMddyyyy);
        System.out.println(parse);

        Date date = new Date();
        Instant instant = date.toInstant();
        Date instantDate = Date.from(instant);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
    }

}
