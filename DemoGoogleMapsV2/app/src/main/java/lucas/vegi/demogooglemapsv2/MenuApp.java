package lucas.vegi.demogooglemapsv2;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MenuApp extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menu);
        String palavras[] = {"Muriae", "Viçosa", "FAMINAS"};
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,palavras);
        setListAdapter(adp);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent it = new Intent(this,MainActivity.class);

        switch (position){
            case 0:
                it.putExtra("local", "Muriae");
                startActivityForResult(it,1);
                break;
            case 1:
                it.putExtra("local", "Viçosa");
                startActivityForResult(it,2);
                break;
            case 2:
                it.putExtra("local", "Faminas");
                startActivityForResult(it,3);
                break;
        }
    }
}
