package teste.lucasvegi.controlebluetooth.Util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Lucas on 24/02/2016.
 */
public class BluetoothUtil {
    public static void makeVisible(Context context, int durationSeconds) {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, durationSeconds);
        context.startActivity(discoverableIntent);
    }
}

