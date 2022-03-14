package date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateApp {

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        System.out.println(date);

        // Date에서 time이 나오고 나온 값도 불분명(에폭 타임)
        long time = date.getTime();
        System.out.println(time);

        Thread.sleep(3000);

        Date after3Seconds = new Date();
        System.out.println(after3Seconds);

        // mutable하다(변경이 가능하다) -> multiThread 환경에서 safe하지 못하다
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);

        // type safe 하지 않다 음수나 100과 같은 값도 가능하고 달은 0이 1월을 의미
        Calendar minwooBirthDay = new GregorianCalendar(1990, Calendar.JULY, 25);
        System.out.println(minwooBirthDay.getTime());

        minwooBirthDay.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(minwooBirthDay.getTime());
    }
}
