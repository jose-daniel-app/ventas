package com.business.ventas.requerimiento.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
//import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    ListenerDatePickerFragment listener;

    private int anio;
    private int mes;
    private int dia;

    private int hour;
    private int minute;

    public static DatePickerFragment newInstance(){
        return new DatePickerFragment();
    }

    public DatePickerFragment configEvent(ListenerDatePickerFragment listener){
        this.listener = listener;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        anio = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, anio, mes, dia);
        //DatePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        final Calendar c = Calendar.getInstance();
         hour = c.get(Calendar.HOUR_OF_DAY);
         minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this,
                hour, minute, DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();

        // Do something with the date chosen by the user
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        listener.onFechaSelect(anio + "/" + mes + "/" + dia + " " + hour + ":" + minute);
    }

    interface ListenerDatePickerFragment {
        void onFechaSelect(String fecha);
    }
}


