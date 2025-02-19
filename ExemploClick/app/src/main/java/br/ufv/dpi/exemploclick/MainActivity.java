package br.ufv.dpi.exemploclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBotao(View V){

        String tag = V.getTag().toString();

        TextView txt = (TextView) findViewById(R.id.labelTeste);
        EditText edt = (EditText) findViewById(R.id.editText);

        String valor = edt.getText().toString();

        if(tag.equals("1"))
            txt.setText(valor);
        else
            txt.setText(valor+ " 2");
    }
}
