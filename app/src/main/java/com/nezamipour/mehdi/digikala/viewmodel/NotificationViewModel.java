package com.nezamipour.mehdi.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nezamipour.mehdi.digikala.util.worker.NewProductFetchWorker;

public class NotificationViewModel extends AndroidViewModel {

    public NotificationViewModel(@NonNull Application application) {
        super(application);
    }


    //time in minute unit
    // minimum time is 15 minutes in work manger
    public void scheduleWork(int time) {
        NewProductFetchWorker.scheduleWork(getApplication(), time);
    }

}
