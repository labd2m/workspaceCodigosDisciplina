package br.livro.android.utils;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.ContactsContract.Intents.Insert;
import android.util.Log;

/**
 * Classe de Intent utilitária
 * 
 * @author rlecheta
 * 
 */
public class IntentUtils {

	private static final String TAG = "IntentUtils";

	/**
	 * http://developer.android.com/resources/articles/can-i-use-this-intent.
	 * html
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		try {
			final PackageManager packageManager = context.getPackageManager();
			final Intent intent = new Intent(action);
			List<ResolveInfo> list = packageManager.queryIntentActivities(
					intent, PackageManager.MATCH_DEFAULT_ONLY);
			return list.size() > 0;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			return false;
		}
	}

	/**
	 * http://developer.android.com/resources/articles/can-i-use-this-intent.
	 * html
	 */
	public static boolean isReceiverAvailable(Context context, String action) {
		try {
			final PackageManager packageManager = context.getPackageManager();
			final Intent intent = new Intent(action);
			List<ResolveInfo> list = packageManager.queryBroadcastReceivers(
					intent, PackageManager.MATCH_DEFAULT_ONLY);
			return list.size() > 0;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Instala o APK que está no sdcard no celular
	 * 
	 * @param context
	 * @param sdcardFile
	 * @param requestCode
	 */
	public static void installApk(Activity context, File sdcardFile,
			int requestCode) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(sdcardFile),
				"application/vnd.android.package-archive");
		context.startActivityForResult(intent, requestCode);
	}

	public static void insertContact(Context context, String fone) {
		Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
		intent.setType("vnd.android.cursor.item/contact");
		intent.putExtra(Insert.PHONE, fone);
		context.startActivity(intent);
	}

	public static void insertContact(Context context, String fone, String nome) {
		Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
		intent.setType("vnd.android.cursor.item/contact");
		intent.putExtra(Insert.PHONE, fone);
		intent.putExtra(Insert.NAME, nome);
		context.startActivity(intent);
	}

	/**
	 * Método que verifica se existe o pacote instalado
	 * 
	 * @param context
	 * @param pacote
	 * @return
	 */
	public static boolean validarPacote(Context context, String pacote) {
		PackageManager pm = context.getPackageManager();
		try {
			int retorno = pm.getApplicationEnabledSetting(pacote);
			boolean ok = PackageManager.COMPONENT_ENABLED_STATE_DEFAULT == retorno
					|| PackageManager.COMPONENT_ENABLED_STATE_ENABLED == retorno;
			return ok;

		} catch (Exception e) {
			// Log.e(TAG, e.getMessage());
		}
		return false;
	}
}
