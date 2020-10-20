package br.livro.android.cap7.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import br.livro.android.cap7.R;

/**
 * Demonstra um alerta de confirmação;
 * 
 * É possível definir um "DialogInterface.OnClickListener" para executar o
 * código dependendo se o usuário escolheu Sim ou Não
 * 
 * @author ricardo
 * 
 */
public class ExemploAlertaConfirmacao extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.alert_dialog);
	}
	
	public void Teste(View v){
		//v.getTag().toString();
		/** AlertDialog com Sim e Não **/
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Saida!");
		alerta.setMessage("Você está certo que deseja sair?");
		
		// Método executado se escolher Sim
		alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				finish();
			}
		});
		
		// Método executado se escolher Não
		alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		
		alerta.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Toast.makeText(getBaseContext(), "cancelei", Toast.LENGTH_SHORT).show();
				Log.i("teste", "cancelei");
			}
		});
	
		
		// Exibe o alerta de confirmação
		alerta.show();
	}
}
