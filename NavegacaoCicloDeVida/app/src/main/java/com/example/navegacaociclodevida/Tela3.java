package com.example.navegacaociclodevida;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Tela3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_tela3);
		Log.i("Ciclo De Vida", getClassName() + ".onCreate() chamado.");
	}
	
	public void voltar(View v){
		finish();
	}
	
	public void navegaTela1(View v){
		Intent it = new Intent(this, Tela1.class);
		//permite ir para uma instancia já criada de Tela1.class caso ela existir
		it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
		
		startActivity(it);
	}
	
	protected String getClassName(){
    	String s = getClass().getName();
    	return s;
    }

    protected void onStart(){
    	super.onStart();
    	Log.i("Ciclo De Vida", getClassName() + ".onStart() chamado.");
    }
    
    protected void onRestart(){
    	super.onRestart();
    	Log.i("Ciclo De Vida", getClassName() + ".onRestart() chamado.");
    }
    
    protected void onResume(){
    	super.onResume();
    	Log.i("Ciclo De Vida", getClassName() + ".onResume() chamado.");
    }
    
    protected void onPause(){
    	super.onPause();
    	Log.i("Ciclo De Vida", getClassName() + ".onPause() chamado.");
    }
    
    protected void onStop(){
    	super.onStop();
    	Log.i("Ciclo De Vida", getClassName() + ".onStop() chamado.");
    }
    
    protected void onDestroy(){
    	super.onDestroy();
    	Log.i("Ciclo De Vida", getClassName() + ".onDestroy() chamado.");
    }

}
