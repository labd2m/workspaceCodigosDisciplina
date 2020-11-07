package orama.ufv.br.operacoesemandamento.view.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Lucas on 10/07/2017.
 */

public class NavegacaoUtil {
    public static void navegar(Activity ctx, Class tela){
        Intent intent = new Intent(ctx,tela);
        ctx.startActivity(intent);
        ctx.finish();
    }
    public static void navegar(Activity ctx, Class tela, int spinnerSelectedPosition){
        Intent intent = new Intent(ctx,tela);
        intent.putExtra("spinner",spinnerSelectedPosition);
        ctx.startActivity(intent);
        ctx.finish();
    }
}
