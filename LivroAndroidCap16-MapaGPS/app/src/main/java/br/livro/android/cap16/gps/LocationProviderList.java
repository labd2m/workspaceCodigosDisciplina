package br.livro.android.cap16.gps;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import br.livro.android.cap16.R;

/**
 * Exibe numa lista os nomes de cada Location Provider disponivel
 * 
 * @author ricardo
 *
 */
public class LocationProviderList extends ListActivity
{
    static final String CATEGORIA = "livro";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);

        setContentView(R.layout.list_providers);

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //lista com os nomes dos providers
		List<String> nomes = lm.getAllProviders();
        
        // Cria um ArrayAdapter para exibir os nomes
        ArrayAdapter<String> notes = new ArrayAdapter<String>(this, R.layout.provider_row, nomes);

        setListAdapter(notes);      
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

		ListAdapter la = getListAdapter();
		String locProviderName = (String)la.getItem(position);

		Log.d( CATEGORIA, "Item selected: "+locProviderName );

        Intent i = new Intent(this,LogaCoordenadas.class);
    	i.putExtra( "provider",locProviderName );
    	startActivity( i );
    }
}
