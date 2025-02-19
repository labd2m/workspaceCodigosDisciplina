package br.livro.android.cap8.filtro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

public class TelaContatos extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Uri uri = ContactsContract.Contacts.CONTENT_URI;

		Intent it = new Intent(Intent.ACTION_VIEW,uri);

		startActivity(it);
		
		finish();
	}
}
