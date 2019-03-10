package com.business.ventas.requerimiento.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
//import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    VentasLog log = LogFactory.createInstance().setTag(DatePickerFragment.class.getSimpleName());
    ListenerDatePickerFragment listener;
    private int anio;
    private int mes;
    private int dia;
    private int hour;
    private int minute;

    public static DatePickerFragment newInstance() {
        return new DatePickerFragment();
    }

    public DatePickerFragment configEvent(ListenerDatePickerFragment listener) {
        this.listener = listener;
        return this;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //----------- automatico-------------//
        //  Locale locale = getResources().getConfiguration().locale;
        // Locale.setDefault(locale);
        //--------------español-------------//
        Locale spanish = new Locale("es");
        Locale.setDefault(spanish);

        final Calendar c = Calendar.getInstance();
        //agregando 1 dia a la fecha actual
        c.add(Calendar.DAY_OF_MONTH, 1);
        // c.add(Calendar.HOUR_OF_DAY, 24);
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dp = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, anio, mes, dia);
        dp.getDatePicker().setMinDate(c.getTimeInMillis());

        return dp;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        log.info("El año -> %d, el mes -> %d, y el dia -> %d",year, month, day);
        this.anio = year;
        this.mes = month;
        this.dia = day;

        final Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 8);
        c.set(Calendar.MINUTE, 00);

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        //log.info("la fecha selecionad es => %s", Fechas.dateAsString(c.getTime()));
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this,
                hour, minute, DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar calendar = new GregorianCalendar(this.anio, this.mes, this.dia, hourOfDay, minute, 0);
        listener.onFechaSelect(calendar.getTime());
    }

    interface ListenerDatePickerFragment {
        void onFechaSelect(Date fecha);
    }
}


