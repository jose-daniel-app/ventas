package com.business.ventas.ordenes.contracts;

import com.business.ventas.utils.PadreInteractor;
import com.business.ventas.utils.PadrePresenter;
import com.business.ventas.utils.PadreView;

public interface OrdenesContract {

    interface View extends PadreView {

    }

    interface Presenter extends PadrePresenter<Presenter, View> {

    }
    interface Interactor extends PadreInteractor<Interactor, Presenter> {
         
    }
}
