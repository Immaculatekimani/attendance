package com.emma.app.utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {
    public LocalTime timeDisplay() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime displayTime = LocalTime.parse(currentTime.format(formatter), formatter);
        return displayTime;
    }
}
