package android.teste.lucasvegi.exemplocustomtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clique(View view) {
        Toast toast = new Toast(this);

        //converter XML em objeto View
        LayoutInflater layout = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customToastView = layout.inflate(R.layout.custom_toast, null);

        toast.setView(customToastView);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}