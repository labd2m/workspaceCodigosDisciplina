package br.livro.android.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.opengl.Visibility;
import android.support.v4.app.NotificationCompat;

import br.livro.android.cap20.push.R;

/**
 * @author Ricardo Lecheta
 *
 */
public class NotificationUtil {
	private static int ID = 0;	//ID de cada notificação

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 *
	 * @param notificationIntent
	 */
	@SuppressLint("NewApi")
	public static void generateNotification(Context context, String message, Intent notificationIntent) {

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		//PendingIntent.FLAG_UPDATE_CURRENT atualiza somente os extras da nova intent
		//Usa ID dinâmico
		PendingIntent intent = PendingIntent.getActivity(context, ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		//som padrão de notificação
		Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		String title = "Notificação GCM";
		Notification.Builder builder = new Notification.Builder(context)
				.setContentTitle(message).setContentText(title)
				.setContentIntent(intent).setSmallIcon(R.drawable.ic_launcher)
				.setSound(alarmSound).setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

		Notification notification = builder.build();

		// Configura a intent para abrir a activity no topo, somente se não
		// estiver aberta
		// notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
		// Intent.FLAG_ACTIVITY_SINGLE_TOP);

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(ID, notification);	//usa ID dinâmico

		//MUDA O ID A CADA NORIFICAÇÃO
		ID++;
	}
}
