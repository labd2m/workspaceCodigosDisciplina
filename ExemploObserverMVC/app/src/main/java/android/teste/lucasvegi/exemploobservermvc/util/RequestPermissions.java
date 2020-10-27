package android.teste.lucasvegi.exemploobservermvc.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RequestPermissions {
    public static final int LOCATION_PERMISSION = 1;

    public static boolean requestLocationPermission(Activity activity, Context ctx){
        // verifica a necessidade de pedir a permissão
        if ((ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            // verifica se precisa explicar para o usuário a necessidade da permissão
            if ((ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION))) {

                //explica para o usuário a necessidade da permissão caso ele já tenha negado pelo menos uma vez
                Toast.makeText(ctx,"Permita o uso do serviço de localização para rastrear este aparelho!",Toast.LENGTH_LONG).show();

                //pede permissão
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_PERMISSION);

                Log.i("PERMISSION","Devo dar explicação");

            } else {

                // Pede a permissão direto a primeira vez que o usuário tentar usar o recurso.
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_PERMISSION);

                Log.i("PERMISSION","Pede a permissão");
                // LOCATION_PERMISSION é uma constante declarada para ser usada no callback da resposta da permissão
            }

            //uso do recurso deverá começar no callback
            return false;

        }else{
            Log.i("PERMISSION","Já tenho essa permissão");

            //uso do recurso já pode ser feito no local que pediu a permissão
            return true;
        }
    }
}
