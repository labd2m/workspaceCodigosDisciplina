package vegi.jogo.da.velha;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Inicio extends Activity {

	final CharSequence[] choiceList = {"Iniciante", "Avançado"};
	int selecao = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_inicio);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		selecao = 0;
	};

	public void JogarAndroid(View v){	
		    AlertDialog.Builder builder = new AlertDialog.Builder(Inicio.this);
		    builder.setTitle("Nível de dificuldade:");
		    	   
		    builder.setSingleChoiceItems(choiceList, selecao, new DialogInterface.OnClickListener() {	        
		        public void onClick(DialogInterface dialog, int which) {
		        	selecao = which;
		        }
		    })
		    
		    .setCancelable(false)
		    
		    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){		
		    	public void onClick(DialogInterface dialog, int which) {
		    		Toast.makeText(Inicio.this,"Jogar Sozinho no nível "+  choiceList[selecao], Toast.LENGTH_SHORT).show();	    		
		           	Intent it = new Intent(Inicio.this, Main.class);
	            	
	            	if(selecao == 0)
	        			it.putExtra("tipoJogo", "sozinho");
	        		else if(selecao == 1)
	        			it.putExtra("tipoJogo", "dificil");
	            	
	            	startActivity(it);
		    	}
		    })
		    
		    .setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){	
		    	public void onClick(DialogInterface dialog,int which) {
	
		    	}
		 	});
		    
		    AlertDialog alert = builder.create();
		    alert.show();
	}
	
	public void JogarPlayer(View v){
		Intent it = new Intent(this, Main.class);
		it.putExtra("tipoJogo", "multiplayer");
		startActivity(it);
		Toast.makeText(this, "Multi Jogadores", Toast.LENGTH_SHORT).show();
	}

}
