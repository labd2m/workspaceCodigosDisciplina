package teste.lucasvegi.exemplolistasensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        //Obtém o gerenciador de sensores
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtém lista de sensores disponíveis
        List<Sensor> lista = sm.getSensorList(Sensor.TYPE_ALL);

        //Obtém a lista de caracteristicas dos sensores
        List<String> nomes = new ArrayList<String>();
        for(Sensor s : lista){
            nomes.add("Nome: " + s.getName()+ " \nFornecedor: " + s.getVendor() +
                    "\nTipo: " + s.getStringType());
        }

        //Atualiza ListView com informações dos sensores
        ListView lv = (ListView) findViewById(R.id.listaValores);
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, nomes);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
