package lucas.vegi.ciclodevida;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class Tela2 extends Ciclo {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        
        //Log.i("Ciclo De Vida", getClassName() + ".onCreate() chamado.");
    }
    
    public void Tela1(View v){
    	Intent it = new Intent(this, Ciclo.class);
    	
    	//USADO PARA REORDENADAR A PILHA, LEVANDO PARA O TOPO A ACTIVITY DESEJADA
    	//it.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	
    	startActivity(it);
        //finish();
    }
    
    public void Tela3(View v){
        Intent it = new Intent(this, Tela3.class);
        startActivity(it);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_test, menu);
        return true;
    }

    
}
