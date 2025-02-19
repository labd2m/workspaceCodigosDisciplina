package br.livro.android.cap16.overlay;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * Implementação de OverlayItem
 * 
 * @author ricardo
 *
 */
@SuppressWarnings("unchecked")
public class ImagensOverlay extends ItemizedOverlay {
	private final List<OverlayItem> imagens;
	private final Context context;
	public ImagensOverlay(Context context,List<OverlayItem> imagens,Drawable drawable) {
		super(drawable);
		this.context = context;
		this.imagens = imagens;
		// Define os limites da imagem 
		boundCenterBottom(drawable);
		// O populate() dispara os eventos e chama cada createItem(i)
		populate();
	}
	@Override
	public int size() {
		return imagens.size();
	}
	@Override
	protected OverlayItem createItem(int i) {
		return imagens.get(i);
	}
	@Override
	protected boolean onTap(int i) {
		OverlayItem overlayItem = imagens.get(i);
		String texto = overlayItem.getTitle() + " - " + overlayItem.getSnippet();
		Toast.makeText(context, texto,Toast.LENGTH_SHORT).show();
		return(true);
	}
}