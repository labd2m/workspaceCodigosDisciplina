package br.livro.android.cap6.layout.tab;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author ricardo
 *
 */
public class Tab3 extends Activity
{
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);

		//cria o layout
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		layout.setPadding(10, 10, 10, 10);//pixels
		layout.setBackgroundColor(Color.RED);

		TextView nome = new TextView(this);
		nome.setText("Texto da Tab 3C");
		nome.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		layout.addView(nome);
		
		setContentView(layout);
	}
}
