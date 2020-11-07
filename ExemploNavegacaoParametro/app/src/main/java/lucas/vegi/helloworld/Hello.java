package lucas.vegi.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Hello extends Activity {

   //private TextView txt;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);         
        setContentView(R.layout.view_principal);             
    }
	
	public void somar(View v){
        EditText txt1 = (EditText) findViewById(R.id.editValor1);
        EditText txt2 = (EditText) findViewById(R.id.editValor2);     
        int aux = Integer.parseInt(txt1.getText().toString()) + Integer.parseInt(txt2.getText().toString());  
        
        Intent it = new Intent(getBaseContext(),Tela2.class);
       
        //Valores via Intent
        it.putExtra("op", " + ");
        it.putExtra("valor1", Integer.parseInt(txt1.getText().toString()));
        it.putExtra("valor2", Integer.parseInt(txt2.getText().toString()));
        it.putExtra("resultado", aux);

        //Criar objeto carro
        Carro c = new Carro("Fusca",1970,"ABC1234");

        //Valores via Bundle
        Bundle bundle = new Bundle();
        bundle.putString("teste", "Valor enviado via Bundle");
        bundle.putSerializable("carro",c);
        it.putExtras(bundle);
        
        //PASSA OBJETO DE CLASSE QUE IMPLEMENTA SERIALIZABLE COMO PARAMETRO
        Pessoa p = new Pessoa("Lucas",27,1111,"M",1.7,65.5);
        it.putExtra("objetoPessoa", p);

        startActivity(it);
	}
	
	public void multiplicar(View v){        
        EditText txt1 = (EditText) findViewById(R.id.editValor1);
        EditText txt2 = (EditText) findViewById(R.id.editValor2);       
        int aux = Integer.parseInt(txt1.getText().toString()) * Integer.parseInt(txt2.getText().toString());
        
        Intent it = new Intent(getBaseContext(),Tela2.class);
        
        it.putExtra("op", " * ");
        it.putExtra("valor1", Integer.parseInt(txt1.getText().toString()));
        it.putExtra("valor2", Integer.parseInt(txt2.getText().toString()));
        it.putExtra("resultado", aux);
        
        startActivity(it);      
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_principal, menu);
        return true;
    }

    
}