package com.example.intentcontactavancado;

import com.example.intentbrowser.R;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Toast;

public class ContatoAvancado extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);
    }
    
    public void Contato(View v){    
      	 //Visualizar o contato 1 da lista de contatos
       	Uri uri = Uri.parse("content://com.android.contacts/contacts/");
       
       	//Cria a Intent com o contato
       	Intent it = new Intent(Intent.ACTION_PICK, uri);
       
       	//Envia a mensagem para o sistema operacional identificada como 1
       	startActivityForResult(it,10);  
    }
    
    @Override
    protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent it) {
    	//URI para visualizar o contato escolhido
    	Uri uri = it.getData();
    	
    	//Busca as informações do contato selecionado
    	Cursor c =  managedQuery(uri, null, null, null, null);
    	c.moveToNext();

    	//Recupera o ID do Contato selecionado e o valor booleano que informa se 
    	//esse contato possui pelo menos um telefone cadastrado
    	String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
    	String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

        //Testa se o contato possui pelo menos um telefone
    	//entra somente se ele tiver um telefone
    	if (Integer.parseInt(hasPhone) > 0) {
           
    		//faz uma busca pelos telefones do contato selecionado
    		//repare que ele busca pelo ID recuperado anteriormente
    		Cursor phones = managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
        		   									  null, 
        		   									  ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
        		   									  null, 
        		   									  null);
    		phones.moveToNext();
          
    		int telColumn = phones.getColumnIndex("data1");  //Recupera id da coluna da tupla da tabela de telefones
    		String TelNumber = phones.getString(telColumn);  //Recupera o telefone do contato
    	
    		//Exibe telefone do contato
    		Toast.makeText(this, "CONTATO:\n" + TelNumber, Toast.LENGTH_LONG).show();   
        }
    }   
}


