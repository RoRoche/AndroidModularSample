package com.guddy.android_modular_sample.di;

import android.support.annotation.NonNull;

import com.guddy.android_modular_sample.second.IDateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormatter implements IDateFormatter {

    //region Constants
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
    //endregion

    //region IDateFormatter
    @Override
    public String format(@NonNull final Date poDate) {
        return sSimpleDateFormat.format(poDate);
    }
    //endregion
}
