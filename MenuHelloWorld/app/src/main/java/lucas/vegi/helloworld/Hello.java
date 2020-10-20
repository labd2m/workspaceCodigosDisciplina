package lucas.vegi.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Hello extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
    

	@Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		 MenuItem novo = menu.add(0,0,0,"Novo");
		 //novo.setIcon(R.drawable.novo);
		
		 return super.onCreateOptionsMenu(menu);
	 }
	
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 if(item.getItemId() == 0){
			 Toast.makeText(getBaseContext(), "você clicou no item do menu!", Toast.LENGTH_LONG).show();
		 }
		 return super.onOptionsItemSelected(item);
	 }




    
}
