package com.tup.buensabor.mappers.utils;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Date date) {
        return date != null ? OffsetDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()) : null;
    }

    public Date asDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? Date.from(offsetDateTime.toInstant()) : null;
    }
}