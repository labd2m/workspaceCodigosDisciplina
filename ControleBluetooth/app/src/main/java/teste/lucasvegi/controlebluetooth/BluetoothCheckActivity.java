package teste.lucasvegi.controlebluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class BluetoothCheckActivity extends Activity {
    protected BluetoothAdapter btfAdapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Bluetooth adapter
        btfAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btfAdapter == null) {
            Toast.makeText(this, "Bluetooth não disponível neste dispositivo.", Toast.LENGTH_LONG).show();
            // Vamos fechar a activity neste caso
            finish();
            return;
        }

        // Se o bluetooth não está ligado
        if (btfAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth está ligado!", Toast.LENGTH_LONG).show();
        } else {
            // Pede pro usuário ligar o bluetooth
            // <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (btfAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth foi ligado!", Toast.LENGTH_LONG).show();
        }
    }
}
