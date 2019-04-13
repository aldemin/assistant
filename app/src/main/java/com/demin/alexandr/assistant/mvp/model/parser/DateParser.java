package com.demin.alexandr.assistant.mvp.model.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {

    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public String dateToString(Date date) {
        return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date);
    }

    public String dateToString(long date) {
        return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(new Date(date));
    }

    public Date stringToDate(String date) {
        try {
            return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
