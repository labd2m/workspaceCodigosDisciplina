package com.example.intentsms;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.telephony.gsm.SmsManager;

public class Mensagem extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);
    }
    
 
	public void Enviar(View v){
    	EditText edt = (EditText) findViewById(R.id.texto);
    	
    	String url = edt.getText().toString();
    	
    	Uri uri = Uri.parse("sms:5556");
    	
    	Intent it = new Intent(Intent.ACTION_SENDTO, uri);
    	it.putExtra("sms_body", url);
    	
    	//Envia a mensagem para o sistema operacional
    	startActivity(it);	 
    
    }
    
}


/* AUTOMATIZAÇÃO DE ENVIO
 * @SuppressWarnings("deprecation")
SmsManager sms = SmsManager.getDefault();
sms.sendTextMessage("5556", null, url, null, null);*/