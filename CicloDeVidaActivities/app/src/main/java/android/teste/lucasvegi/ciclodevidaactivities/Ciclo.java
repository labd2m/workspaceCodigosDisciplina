package android.teste.lucasvegi.ciclodevidaactivities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Ciclo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.i("Ciclo De Vida", getClassName() + ".onCreate() chamado.");
        
        setContentView(R.layout.activity_main);
    }
    
    public void Navegar(View v){
    	Intent it = new Intent(this,Tela2.class);
    	//it.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	startActivity(it);
        //finish();
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


    @Override
    public void onBackPressed() {
        Log.d("Ciclo", "tentei fechar a tela...");
        super.onBackPressed();
    }
}
