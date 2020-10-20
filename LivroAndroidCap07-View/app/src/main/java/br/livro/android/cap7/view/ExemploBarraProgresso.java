package br.livro.android.cap7.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import br.livro.android.cap7.R;

/**
 * Demonstra a utilização do componente barra de progresso
 * 
 * @author ricardo
 * 
 */
public class ExemploBarraProgresso extends Activity {
	private static final int MAXIMO = 100;
	private static final String CATEGORIA = "livro";
	private ProgressBar mProgress;
	private int progresso = 0;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_barra_progresso);

		// Barra de Progresso
		mProgress = (ProgressBar) findViewById(R.id.barraProgresso);

		Button b = (Button) findViewById(R.id.btOK);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View view) {
				// Inicia uma thread
				new Thread(new Runnable() {
					public void run() {
						while (progresso < MAXIMO) {
							progresso = simularTarefa();

							// Update the progress bar
							handler.post(new Runnable() {
								public void run() {
									Log.i(CATEGORIA, ">> Progresso "
											+ progresso);
									// Seta o progresso de 0 a 100 porque foi
									// definido 100 como máximo no XML
									mProgress.setProgress(progresso);
								}
							});
						}
						Log.i(CATEGORIA, "Fim.");
					}
				}).start();
			}
		});
	}
	private int simularTarefa() {
		progresso++;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// incrementa o progresso
		return progresso;
	}
}
