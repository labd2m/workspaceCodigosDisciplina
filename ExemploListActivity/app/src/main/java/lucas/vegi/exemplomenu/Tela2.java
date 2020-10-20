package lucas.vegi.exemplomenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class Tela2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tela2);
        Log.w("Ciclo De Vida", getClassName() + ".onCreate() chamado.");
    }

    public void Navegar(View v){
    		finish(); 		
    }
    
    private String getClassName(){
    	String s = getClass().getName();
    	return s;
    }

    protected void onStart(){
    	super.onStart();
    	Log.w("Ciclo De Vida", getClassName() + ".onStart() chamado.");
    }
    
    protected void onRestart(){
    	super.onRestart();
    	Log.w("Ciclo De Vida", getClassName() + ".onRestart() chamado.");
    }
    
    protected void onResume(){
    	super.onResume();
    	Log.w("Ciclo De Vida", getClassName() + ".onResume() chamado.");
    }
    
    protected void onPause(){
    	super.onPause();
    	Log.w("Ciclo De Vida", getClassName() + ".onPause() chamado.");
    }
    
    protected void onStop(){
    	super.onStop();
    	Log.w("Ciclo De Vida", getClassName() + ".onStop() chamado.");
    }
    
    protected void onDestroy(){
    	super.onDestroy();
    	Log.w("Ciclo De Vida", getClassName() + ".onDestroy() chamado.");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_tela2, menu);
        return true;
    }

    
}
