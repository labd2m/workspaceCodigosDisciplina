package orama.ufv.br.operacoesemandamento.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Fábio on 28/08/2017.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return App.mContext;
    }
}
