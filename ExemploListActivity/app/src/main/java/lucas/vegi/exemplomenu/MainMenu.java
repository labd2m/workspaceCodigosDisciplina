package lucas.vegi.exemplomenu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainMenu extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Ciclo De Vida", getClassName() + ".onCreate() chamado.");
        
        //Prepara Itens do Menu----------------------------------
        String menu [] = new String [] {"Opção 1","Opção 2","Opção 3", "OP4", "OP5", "OP6", "OP7", "OP8", "OP9","OP10","SAIR"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrayAdapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	Intent it = new Intent(getBaseContext(),MainMenu.class);

    	//Recuperando informações pelo ListView
    	String aux = l.getItemAtPosition(position).toString();
    	Log.i("ListItemClick",l.getAdapter().getItem(position).toString());

    	//Recuperando informações diretamente da View clicada
    	Log.i("ListItemClick","View clicada:		" + v.getClass().getName() +
								"\nConteudo:		" + ((TextView) v).getText().toString() +
								"\nRow ID:			" + id +
								"\nPosition:		" + position);

    	switch(position){
    		case 0:
    			Toast.makeText(getBaseContext(), aux, Toast.LENGTH_LONG).show();
    			startActivity(it);
    			break;
    		case 1:
    			Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
    			startActivity(it);
    			break;
    		case 2:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
				startActivity(it);
				break;
			case 3:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
				startActivity(it);
				break;
			case 4:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
				startActivity(it);
				break;
			case 5:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
				startActivity(it);
				break;
			case 6:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
				startActivity(it);
				break;
			case 7:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_LONG).show();
				startActivity(it);
				break;
			case 8:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
				startActivity(it);
				break;
			case 9:
				Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
				startActivity(it);
				break;
    		case 10:
    			//Encerra a activity
    			finish();
    	}
    }
    
    private String getClassName(){
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





