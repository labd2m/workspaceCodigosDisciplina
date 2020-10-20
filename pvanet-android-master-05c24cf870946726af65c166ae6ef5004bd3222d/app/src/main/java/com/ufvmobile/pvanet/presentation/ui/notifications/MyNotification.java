package com.ufvmobile.pvanet.presentation.ui.notifications;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.presentation.ui.activities.MainActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Igor Vilela
 * @since 31/08/2016
 */
public class MyNotification extends NotificationCompat {

    public static final int TYPE_CONTENT = 1;
    public static final int TYPE_NEWS = 2;
    public static final int TYPE_ASSIGMENT = 3;

    @IntDef({TYPE_CONTENT, TYPE_NEWS, TYPE_ASSIGMENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {}


    public static class Builder extends NotificationCompat.Builder {


        /**
         * Constructor.
         * <p/>
         * Automatically sets the when field to {@link System#currentTimeMillis()
         * System.currentTimeMillis()} and the audio stream to the
         * {@link MyNotification#STREAM_DEFAULT}.
         *
         * @param context A {@link Context} that will be used to construct the
         *                RemoteViews. The Context will not be held past the lifetime of this
         *                Builder object.
         */
        public Builder(Context context) {
            super(context);

            this.setSmallIcon(R.mipmap.ic_launcher);
            Bitmap largeIcon = BitmapFactory.decodeResource(mContext.getResources(),
                    R.mipmap.ic_launcher);
            this.setLargeIcon(largeIcon);
        }

        public Builder setType(@Type int type) {

            switch (type) {
                case TYPE_CONTENT:
                    this.setContentTitle(mContext.getString(R.string.not_content_title));
                    this.setContentText(mContext.getString(R.string.not_content_body));
                    break;
                case TYPE_NEWS:
                    this.setContentTitle(mContext.getString(R.string.not_news_title));
                    this.setContentText(mContext.getString(R.string.not_news_body));
                    break;
                case TYPE_ASSIGMENT:
                    this.setContentTitle(mContext.getString(R.string.not_assigments_title));
                    this.setContentText(mContext.getString(R.string.not_assigments_body));
                    break;
            }

            return this;
        }


        /**
         * the notification opens the main activity when the user clicks on it
         */
        public Builder setOpenMain() {

            Intent it = new Intent(mContext, MainActivity.class);

            // Because clicking the notification opens a new ("special") activity, there's
            // no need to create an artificial back stack.
            PendingIntent pit =
                    PendingIntent.getActivity(
                            mContext,
                            0,
                            it,
                            PendingIntent.FLAG_UPDATE_CURRENT);

            this.setContentIntent(pit);

            return this;
        }
    }
}
