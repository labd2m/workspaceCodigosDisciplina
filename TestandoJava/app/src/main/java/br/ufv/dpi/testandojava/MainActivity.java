package br.ufv.dpi.testandojava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TestTool", Calendar.getInstance().getTime() + " - Log de Debug");
        Log.w("TestTool", Calendar.getInstance().getTime() + " - Log de Warning");
        Log.i("TestTool", Calendar.getInstance().getTime() + " - Log de Informação");
        Log.v("TestTool", Calendar.getInstance().getTime() + " - Log de Verbose");
        Log.e("TestTool", Calendar.getInstance().getTime() + " - Log de Erro");
    }

    public void cliquePrimo(View view) {
        EditText editText = findViewById(R.id.edtEhPrimo);
        TextView resultado = findViewById(R.id.txtResultado);

        int valor = Integer.parseInt(editText.getText().toString());
        Funcoes f = new Funcoes();

        if(f.ehPrimo(valor)){
            resultado.setText("Eh primo");
        }else{
            resultado.setText("Nao eh primo");
        }
    }

    public void cliqueFibo(View view) {
        EditText editText = findViewById(R.id.edtFibo);
        TextView resultado = findViewById(R.id.txtResultado);

        Funcoes f = new Funcoes();
        int valor = Integer.parseInt(editText.getText().toString());

        int result = f.fibo(valor);
        resultado.setText("Resultado: " + result);
    }

    public void cliqueClassifica(View view) {
        EditText editText = findViewById(R.id.edtClassifica);
        TextView resultado = findViewById(R.id.txtResultado);

        Funcoes f = new Funcoes();
        int valor = Integer.parseInt(editText.getText().toString());

        String result = f.classificaFibo(valor);
        resultado.setText(result);
    }
}
