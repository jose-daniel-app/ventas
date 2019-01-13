package com.business.ventas.search;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.business.ventas.R;

public class SearchToolbarProducto implements View.OnClickListener {

    View view;
    Context context;
    RelativeLayout searchLayout;
    EditText searchEditText;
    ImageButton arrowBackBtn;
    ImageButton barcodeBtn;
    ImageButton clearBtn;
    private InputMethodManager imm;
    OnSearchToolbarQueryTextListner listner;


    public SearchToolbarProducto(Context context, OnSearchToolbarQueryTextListner listner, View view) {
        this.view = view;
        this.context = context;
        searchLayout = view.findViewById(R.id.search_producto);
        searchEditText = view.findViewById(R.id.searchEditText);
        arrowBackBtn = view.findViewById(R.id.ic_arrowBack);
        barcodeBtn = view.findViewById(R.id.ic_barcode_scan);
        clearBtn = view.findViewById(R.id.ic_clear);
        this.listner = listner;

        arrowBackBtn.setOnClickListener(this);
        barcodeBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);

    }


    /* Jb Toolbar open ho to:
     *  - focus editText pr ho aur keyboard open ho aur editText null ho
     *  - jb user Type krna shuru ho to mic to hide kr k clear btn ho show krna hy,
     *  - clearButton Press ho to editText clear ho jaey
     *  - jb keyboard k submit button pr click ho to agr Edittext empty hy to keyboar hide na ho aur agr edittext empty
     *    nahi hy to user ko query show kr k editText ko null kr dyna hy
     * */
    public void openSearchToolbar() { // Open the SearchToolbarProducto

        searchLayout.setVisibility(View.VISIBLE);

        /**  focus editText pr ho aur keyboard open ho aur editText null ho*/
        searchEditText.setText(null);
        searchEditText.requestFocus();
        openKeyboard();
/*
        searchEditText.setOnKeyListener((view1, i, keyEvent) -> {
            if(keyEvent.getAction() == KeyEvent.ACTION_UP){
                closeSearchToolbar();
            }
            return false;
        });
*/
        /**  jb user Type krna shuru ho to mic to hide kr k clear btn ho show krna hy,*/
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listner.onQueryTextChange(editable.toString());
                if (editable.length() > 0) {

                    clearBtn.setVisibility(View.VISIBLE);
                    barcodeBtn.setVisibility(View.GONE);
                } else {

                    clearBtn.setVisibility(View.GONE);
                    barcodeBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView.getText().toString().length() < 1) {
                    return true;
                } else {
                    listner.onQueryTextSubmit(textView.getText().toString());
                    closeSearchToolbar();
                    return false;
                }


            }
        });


    }


    /* Close the tooolbar:
     * jb toolbar close ho to searchLayout hide ho jaey aur keyboar b close ho jaey*/
    public void closeSearchToolbar() {
        searchLayout.setVisibility(View.GONE);
        closeKeyboard();
    }


    public void openKeyboard() {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void closeKeyboard() {
        imm.hideSoftInputFromWindow(arrowBackBtn.getWindowToken(), 0);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ic_arrowBack:
                closeSearchToolbar();
                break;

            case R.id.ic_clear:
                searchEditText.setText(null);
                break;
/*
            case R.id.ic_mic:
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    closeKeyboard();
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    ((Activity) context).startActivityForResult(intent, VOICE_INTENT_REQUEST_CODE);
                    closeSearchToolbar();
                } else {
                    Toast.makeText(context, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }
                break;*/
        }

    }


    public interface OnSearchToolbarQueryTextListner {
        void onQueryTextSubmit(String query);

        void onQueryTextChange(String editable);
    }


}
