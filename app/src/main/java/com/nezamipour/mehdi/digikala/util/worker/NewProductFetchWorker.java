package com.nezamipour.mehdi.digikala.util.worker;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.data.model.product.Product;
import com.nezamipour.mehdi.digikala.data.repository.ProductRepository;
import com.nezamipour.mehdi.digikala.util.ImageUtil;
import com.nezamipour.mehdi.digikala.view.activity.MainActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewProductFetchWorker extends Worker {

    public static final String TAG = "NewProductFetchWorker";
    private static final String WORK_TAG_POLL = "productPollWorker";

    private static final int REQUEST_CODE_NOTIFICATION = 1;
    private static final int NOTIFICATION_ID = 0;

    public NewProductFetchWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        // Do the work here--in this case, fetch last product
        pullFromServerAndNotify(getApplicationContext());

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }


    public static void pullFromServerAndNotify(Context context) {
        ProductRepository productRepository = ProductRepository.getInstance();
        productRepository.fetchLatest();
        List<Product> productList;
        productList = productRepository.getLatestProductsLiveData().getValue();

        if (productList == null || productList.isEmpty()) {
            Log.d(TAG, "pullFromServerAndNotify: " + productList);
            return;
        }
        Log.d(TAG, "pullFromServerAndNotify: " + "notification showing");
        showNotification(context, productList.get(0));

    }

    private static void showNotification(Context context, Product product) {
        String channelId = context.getResources().getString(R.string.channel_id);

        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        PendingIntent pendingIntent = new NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.productDetailFragment)
                .setArguments(bundle)
                .createPendingIntent();


     /*   PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                REQUEST_CODE_NOTIFICATION,
                MainActivity.newIntent(context),
                0);
*/

        Notification notification = null;
        try {
            notification = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentTitle(context.getResources().getString(R.string.new_pictures_title))
                    .setContentText(product.getName())
                    .setLargeIcon(Picasso.get().load(ImageUtil.getFirstImageUrlOfProduct(product)).get())
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(Picasso.get().load(ImageUtil.getFirstImageUrlOfProduct(product)).get())
                            .bigLargeIcon(null))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }

    public static void scheduleWork(Context context, int repeatingTime) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();

        PeriodicWorkRequest workRequest =
                new PeriodicWorkRequest.Builder(NewProductFetchWorker.class, repeatingTime, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();

        Log.d(TAG, "enqueue work");
        WorkManager
                .getInstance(context)
                .enqueueUniquePeriodicWork(
                        WORK_TAG_POLL,
                        ExistingPeriodicWorkPolicy.KEEP,
                        workRequest);
    }
}