package com.alonedroid.smartmemo.util;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.alonedroid.smartmemo.MainActivity;
import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.feature.memo.input.SmInputActivity;
import com.alonedroid.smartmemo.service.SmMemoInputService;

public class SmNotification {

    public static final int NOTIFICATION_MEMO_INPUT = 1001;

    public static final int NOTIFICATION_CUSTOM_LAYOUT_ID = 1001;

    public void notifyCustomLayout(Activity activity) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        RemoteViews customView = new RemoteViews(activity.getPackageName(), R.layout.notice_custom);
        customView.setImageViewResource(R.id.custom_main_image, R.mipmap.ic_launcher);
        builder.setContent(customView);

        NotificationManagerCompat manager = NotificationManagerCompat.from(activity);
        manager.notify(NOTIFICATION_CUSTOM_LAYOUT_ID, builder.build());
    }

    public static void notifyInputMemoNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(generateIntent(context, SmInputActivity.class));

        RemoteViews customView = new RemoteViews(context.getPackageName(), R.layout.notice_custom);
        customView.setImageViewResource(R.id.custom_main_image, R.mipmap.ic_launcher);
        builder.setContent(customView);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(NOTIFICATION_CUSTOM_LAYOUT_ID, builder.build());
    }

    private static PendingIntent generateIntent(Context context, Class intentClass) {
        Intent intent = new Intent(context, intentClass);
        intent.putExtra(SmMemoInputService.EXTRA_LAYOUT, SmMemoInputService.LAYOUT_MEMO);
        return PendingIntent.getActivity(
                context, MainActivity.REQUEST_CODE_MAIN_ACTIVITY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
