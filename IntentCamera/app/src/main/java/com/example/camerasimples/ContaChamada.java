package com.example.camerasimples;

import java.io.File;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ContaChamada extends Activity {
	String dir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conta_chamada);

		//here,we are making a folder named picFolder to store pics taken by the camera using this application
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/IntentCamera/";
        File newdir = new File(dir);
        newdir.mkdirs();
	}

	public void fotoInterna(View v){
		Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(it, 5);
	}

	public void tirarFoto(View v){
		// here,counter will be incremented each time,and the picture taken by camera will be stored as 1.jpg,2.jpg and likewise.
		String file = "IMG_" + System.currentTimeMillis() + ".jpg";

        File newfile = new File(dir+file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {}

        Uri outputFileUri = Uri.fromFile(newfile);
        Log.i("TesteFoto","URI: "+ outputFileUri);

		Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		//it.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		//configuração de qualidade da imagem
		it.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 640*640);

		//configuração de persistencia em memoria externa - COMENTAR PARA onActivityResult receber uma intent não nula
		it.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		startActivityForResult(it, 10);
	}

	public void gravarVideo(View v){
		// cria arquivo MP4 dentro da pasta IntentCamera. Nome do arquivo contem a hora do sistema no nome.
		String file = "VID_" + System.currentTimeMillis() + ".mp4";

		File newfile = new File(dir+file);
		try {
			newfile.createNewFile();
		} catch (IOException e) {}

		Uri outputFileUri = Uri.fromFile(newfile);
		Log.i("TesteFotoVideo","URI: "+ outputFileUri);

		Intent it = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

		it.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
		it.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0); // 0 low 1 high
		it.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, 0); // não sai do video player quando acaba o video

		//configuração de persistencia em memoria externa - COMENTAR PARA onActivityResult receber uma intent não nula
		it.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		startActivityForResult(it, 20);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this,"chamei onActivityResult - Código: " + requestCode,Toast.LENGTH_LONG).show();

		switch (requestCode){
			case 5:
				Log.i("TesteFoto","Foto Interna");
				break;
			case 10:
				Log.i("TesteFoto","Foto Externa");
				break;
			case 20:
				Log.i("TesteFoto","Vídeo Externo");
				break;
		}

		if(data != null){
			Log.i("TesteFoto","Tem data");

			Bundle bundle = data.getExtras();

			if(bundle != null){
				Log.i("TesteFoto","Tem bundle");

				//recupera o bitmap retornado da camera
				Bitmap foto = (Bitmap) bundle.get("data");

				//atualiza imagem na tela para testar
				ImageView tela = (ImageView) findViewById(R.id.foto);
				tela.setImageBitmap(foto);
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conta_chamada, menu);
		return true;
	}

}
