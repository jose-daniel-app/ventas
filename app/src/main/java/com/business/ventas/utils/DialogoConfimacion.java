package com.business.ventas.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

@SuppressLint("ValidFragment")
public class DialogoConfimacion extends DialogFragment {

    private String mensaje;
    private String descripcionConfirmar;
    private String descripcionCancelar;
    private AccionConfirmar accionConfirmar;
    private AccionCancelar accionCancelar;
    private FragmentManager fragmentManager;

    @SuppressLint("ValidFragment")
    public DialogoConfimacion(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(mensaje)
                .setPositiveButton(descripcionConfirmar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        if (accionConfirmar != null)
                            accionConfirmar.onConfirmar();
                    }
                })
                .setNegativeButton(descripcionCancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (accionCancelar != null)
                            accionCancelar.onCancelar();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    public DialogoConfimacion setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public DialogoConfimacion setDescripcionConfirmar(String descripcionConfirmar) {
        this.descripcionConfirmar = descripcionConfirmar;
        return this;
    }

    public DialogoConfimacion setDescripcionCancelar(String descripcionCancelar) {
        this.descripcionCancelar = descripcionCancelar;
        return this;
    }

    public DialogoConfimacion setAccionConfirmar(AccionConfirmar accionConfirmar) {
        this.accionConfirmar = accionConfirmar;
        return this;
    }

    public DialogoConfimacion setAccionCancelar(AccionCancelar accionCancelar) {
        this.accionCancelar = accionCancelar;
        return this;
    }

    public void show() {
        super.show(fragmentManager, "confirmacion");
    }

    @FunctionalInterface
    public interface AccionConfirmar {
        void onConfirmar();
    }

    @FunctionalInterface
    public interface AccionCancelar {
        void onCancelar();
    }
}
