package com.ufvmobile.pvanet.firebase.messaging;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ufvmobile.pvanet.presentation.ui.notifications.MyNotification;

import java.util.Map;

/**
 * @author Igor Vilela
 * @since 31/08/2016
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    public static final String ACTION_NEW_MESSAGE = "NEW_MESSAGE";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        logRemoteMessage(remoteMessage);

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        //#optimization - here we could test if the remoteMessage.getNotification() is null
        sendNotification(remoteMessage);

    }

    /**
     * method for log data from the received remote message
     * @param remoteMessage the message received
     */
    private void logRemoteMessage(RemoteMessage remoteMessage) {

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "onMessageReceived: From: " + remoteMessage.getFrom());
        Log.d(TAG, "onMessageReceived: To: "+remoteMessage.getTo());
        Log.d(TAG, "onMessageReceived: Message Id: "+remoteMessage.getMessageId());
        if(remoteMessage.getNotification().getBody() != null) {
            Log.d(TAG, "onMessageReceived: " + remoteMessage.getNotification().getBody());
        }
        if(remoteMessage.getData() != null) {
            Log.d(TAG, "onMessageReceived: Data: " + remoteMessage.getData());
        }

    }

    private void sendNotification(RemoteMessage remoteMessage) {

        if(remoteMessage.getData() == null) {
            return;
        }

        Map<String, String> data = remoteMessage.getData();
        if(!data.containsKey("type")) return;

        int type = Integer.parseInt(data.get("type"));

        Notification not1;

        switch (type) {

            case MyNotification.TYPE_CONTENT:

                not1 = new MyNotification.Builder(this).setOpenMain()
                        .setType(MyNotification.TYPE_CONTENT).build();
                break;
            case MyNotification.TYPE_NEWS:
                not1 = new MyNotification.Builder(this).setOpenMain()
                        .setType(MyNotification.TYPE_NEWS).build();
                break;
            case MyNotification.TYPE_ASSIGMENT:
                not1 = new MyNotification.Builder(this).setOpenMain()
                        .setType(MyNotification.TYPE_ASSIGMENT).build();
                break;
            default:
                not1 = new MyNotification.Builder(this).setOpenMain()
                        .setType(MyNotification.TYPE_CONTENT).build();
                break;

        }

        if(not1 == null) return;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, not1);

    }

}
