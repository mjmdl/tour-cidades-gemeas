package edu.uniuv.grupo2.tourgemeas;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {
	public static OffsetDateTime offsetDateTimeFromDate(Date date) {
        return OffsetDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
    }
}
