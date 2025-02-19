package lucas.vegi.radaresgps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

/**
 * Simples Overlay que recebe as coordenadas Latitude e Longitude
 * 
 * Desenha a imagem fornecida na coordenada
 * 
 * @author ricardo
 * 
 */
public class ImagemOverlay extends Overlay {

	protected Paint paint = new Paint();

	// recurso da imagem (R.drawable.?)
	protected final int imagemId;

	protected GeoPoint coordenada;

	public ImagemOverlay(GeoPoint geoPoint, int resId) {
		this.coordenada = geoPoint;
		this.imagemId = resId;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);

		// Converte as coordenadas para pixels
		Point p = mapView.getProjection().toPixels(coordenada, null);

		Bitmap bitmap = BitmapFactory.decodeResource(mapView.getResources(), this.imagemId);

		RectF r = new RectF(p.x, p.y, p.x + bitmap.getWidth(), p.y + bitmap.getHeight());

		canvas.drawBitmap(bitmap, null, r, paint);
	}
}
