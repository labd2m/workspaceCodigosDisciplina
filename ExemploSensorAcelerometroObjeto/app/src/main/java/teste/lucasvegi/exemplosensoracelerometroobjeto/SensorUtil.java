package teste.lucasvegi.exemplosensoracelerometroobjeto;

import android.content.Context;
import android.hardware.SensorEvent;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;


/**
 * Created by Lucas on 26/02/2016.
 */
public class SensorUtil {
    public static float[] compensaAcelerometro(Context ctx, SensorEvent event) {
        float x = 0;
        float y = 0;
        float z = 0;

        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        // Verifica a rotação do aparelho
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                // Vertical
                x = event.values[0];
                y = event.values[1];
                break;
            case Surface.ROTATION_90:
                // Horizontal (botões para a direita)
                x = -event.values[1]; //x = -y
                y = event.values[0]; //y = x
                break;
            case Surface.ROTATION_180:
                // Vertical: de ponta-cabeça
                x = -event.values[0];
                y = -event.values[1];
                break;
            case Surface.ROTATION_270:
                // Horizontal (botões para a esquerda)
                x = event.values[1]; //x = y
                y = -event.values[0]; //y = -x
                break;
        }
        // Troca os valores de x e y
        float[] values = new float[3];
        values[0] = x;
        values[1] = y;
        values[2] = z;
        return values;
    }

    public static String getRotationString(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        // Verifica a rotação do aparelho
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                return "ROTATION_0";
            case Surface.ROTATION_90:
                return "ROTATION_90";
            case Surface.ROTATION_180:
                return "ROTATION_180";
            case Surface.ROTATION_270:
                return "ROTATION_270";
        }
        return null;
    }
}