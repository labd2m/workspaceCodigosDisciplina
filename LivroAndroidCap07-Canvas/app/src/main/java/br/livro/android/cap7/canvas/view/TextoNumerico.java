package br.livro.android.cap7.canvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Exemplo de View customizada
 * 
 * <pre>
<br.livro.android.cap7.canvas.view.TextoNumerico
		android:layout_width="fill_parent"
    	android:layout_height="fill_parent"
    	android:numeric="true" 
/>
 * </pre>
 * 
 * @author ricardo
 *
 */
public class TextoNumerico extends EditText {
	//Construtor utilizado para utilizar com a API (java)
	public TextoNumerico(Context context) {
		super(context);
	}
	//Construtor utilizado para construir com XML.
	public TextoNumerico(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	//Retorna o numero digitado
	public Integer getNumero(){
		String s = getText().toString();
		if(s != null){
			Integer valor;
			try {
				valor = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				//Apenas um exemplo, uma aplicação real deve tratar o erro
				return 0;
			}
			return valor;
		}
		return null;
	}
}
