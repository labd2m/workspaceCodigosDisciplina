package com.example.demoarquivotexto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SalvaLeArquivo extends Activity {
	private final String nomeArquivo = "arquivo.txt";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salva_le_arquivo);
    }

    public void Escrever(View v){
    	try {
			FileOutputStream out = openFileOutput(nomeArquivo, MODE_APPEND);

			EditText texto = (EditText) findViewById(R.id.editText1);
			String msg = texto.getText().toString();

			out.write("\n".getBytes());
			out.write(msg.getBytes());
			out.close();

			Log.i("Arquivo Texto", msg + " - escrito com sucesso");
			lerArquivo();
		} catch (Exception e) {
			Log.e("Arquivo Texto", e.getMessage(), e);
		} 
    }
    
    private void lerArquivo() {
		TextView texto = (TextView) findViewById(R.id.TextoArquivo);
		try {
			File f = getFileStreamPath(nomeArquivo);
			Log.i("Arquivo Texto", "Abrindo arquivo: " + f.getAbsolutePath());

			if(f.exists()){
				FileInputStream in = openFileInput(nomeArquivo);
				int tamanho = in.available();
				byte bytes[] = new byte[tamanho];
				in.read(bytes);
				String s = new String(bytes);
				texto.setText(s);
			}else{
				Log.i("Arquivo Texto", "Arquivo não existe ou excluído");
				texto.setText("");
			}
		} catch (Exception e) {
			Log.e("Arquivo Texto", "Arquivo não encontrado: " + e.getMessage(), e);
		} 
	}
    
    public void Excluir(View v){
    	boolean ok = deleteFile(nomeArquivo);
		Log.i("Arquivo Texto","Arquivo deletado ? " + ok);
		lerArquivo();
    }
    
}
