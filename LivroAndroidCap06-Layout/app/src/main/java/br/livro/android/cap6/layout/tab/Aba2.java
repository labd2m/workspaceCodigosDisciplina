package br.livro.android.cap6.layout.tab;

import br.livro.android.cap6.R;
import br.livro.android.cap6.R.layout;
import br.livro.android.cap6.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class Aba2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_aba2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_aba2, menu);
        return true;
    }

    
}
