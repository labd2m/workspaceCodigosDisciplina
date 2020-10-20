package com.example.lucasvegi.menuandroidnovo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_sobre was selected
            case R.id.action_sobre:
                Toast.makeText(this, "Cliquei em sobre", Toast.LENGTH_SHORT).show();
                break;

            // action with ID action_teste was selected
            case R.id.action_teste:
                Toast.makeText(this, "Cliquei em teste", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        return true;
    }

}
