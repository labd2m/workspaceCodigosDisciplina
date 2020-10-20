package br.livro.android.cap6.layout.tab;

import br.livro.android.cap6.R;
import br.livro.android.cap6.R.layout;
import br.livro.android.cap6.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class Aba1 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_aba1);
        Log.i("Ciclo de vida", getClass().getName() + " onCreate()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_aba1, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo de vida", getClass().getName() + " onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo de vida", getClass().getName() + " onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de vida", getClass().getName() + " onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo de vida", getClass().getName() + " onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de vida", getClass().getName() + " onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de vida", getClass().getName() + " onSPause()");
    }
}
