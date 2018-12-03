package com.business.ventas.utils;

import android.content.Context;

public interface PadrePresenter<P, V> {
    P setView(V view);

    P setContext(Context context);

    Context getContext();

    V getView();
}
