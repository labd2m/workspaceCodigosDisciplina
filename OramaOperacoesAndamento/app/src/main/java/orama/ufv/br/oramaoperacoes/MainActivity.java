package orama.ufv.br.oramaoperacoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import orama.ufv.br.operacoesemandamento.view.OperationsInProgressActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operationsInProgress(View v){
        Intent it = new Intent(getBaseContext(), OperationsInProgressActivity.class);
        startActivity(it);
        finish();
    }
}
