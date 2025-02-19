package br.livro.android.cap7.canvas.touch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author ricardo
 * 
 */
public class ImageUtils {

	/**
	 * Retorna apenas o tamanho (width/height) da imagem sem alocar memória
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static BitmapFactory.Options getImageSize(Context context, int resId) {
		Resources resources = context.getResources();

		//Retorna as dimensões no objeto "Options"
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resources, resId, opts);

		//Width: outWidth
		//Heigth: outHeight
		return opts;
	}

	/**
	 * Retorna uma imagem em escala
	 * 
	 * @param context
	 * @param resId
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap getScaledImage(Context context, int resId, int width, int height) {
		Resources resources = context.getResources();

		BitmapFactory.Options opts = getImageSize(context, resId);

        opts.inJustDecodeBounds = false;
		opts.inSampleSize = Math.max(opts.outWidth / width, opts.outHeight / height);

		//Cria o BitMap normal
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resId, opts);

		Bitmap scaledBitMap = Bitmap.createScaledBitmap(bitmap, width, height, false);
		return scaledBitMap;
	}
	
	/*

	 */
}
