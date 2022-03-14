package date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateApiApp {
    public static void main(String[] args) {
        //<기계용 시간 api>
        Instant instant = Instant.now(); //지금의 기계시간
        System.out.println(instant); // 실제 시간 - 9 = 기준시(UTC/GMT)로 출력됨

        //instant.atZone(ZoneId.systemDefault());
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);

        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);

        //<사람용 시간 api>
        LocalDateTime now = LocalDateTime.now(); // 배포되는 서버의 local 시간을 따라가게 됨
        System.out.println(now);

        LocalDateTime plus10Days = now.plus(10, ChronoUnit.DAYS); // ChronoUnit이라는 키워드 기억
        System.out.println(plus10Days); // 기본적으로 immutable하기 때문에 변경한 것을 새롭게 받아줘야함(변경만 하면 변화 x)
        System.out.println(now); // 그대로

        LocalDateTime birthDay= LocalDateTime.of(1990, 6, 25,0,0,0); // 6을 바로 6월로 받음음
        System.out.println(birthDay);

        ZonedDateTime nowKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowKorea);
    }
}
