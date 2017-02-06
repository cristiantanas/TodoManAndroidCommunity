package org.ct.example.todoman;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;

import java.util.Calendar;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    OnUserSetDate listener;

    public DatePicker() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnUserSetDate) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        listener.onDateSet(year, month, dayOfMonth);
    }

    public interface OnUserSetDate {
        void onDateSet(int year, int month, int dayOfMonth);
    }
}
