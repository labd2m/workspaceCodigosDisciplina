package lucas.vegi.radaresgps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Radares extends MapActivity {
    //teste----------------------------
	private MapController mapControll;
    private GeoPoint geoPoint=null;
    private MapView mapview;
    private MyItemizedOverlay userPicOverlay;
    private MyItemizedOverlay nearPicOverlay;
    private Drawable userPic,atmPic;
    private OverlayItem nearatms[];
    public static Context context;
    //teste-------------------------------
	
	private final String nomeArquivo = "radares.txt";
	private String listaRadar = "";
	private double latitude = 0;
	private double longitude = 0;
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
	
		//Cria o MapView
		mapview = new MapView(this, "02MUv09elecvWqWeZsqlkn3S_IYAqzBuAnqRNDA");
		mapview.setClickable(true);  //permite navegar
		mapview.setBuiltInZoomControls(true);  //controle de zoom
        
		//context = getApplicationContext();
		//lerArquivo();	
		//Log.v("Arquivo Texto", "Conteúdo: " + listaRadar );
       // showMap();
		
		
		MapController mc = mapview.getController();
        
		lerArquivo();	
		Log.v("Arquivo Texto", "Conteúdo: " + listaRadar );
		
		String radares[] = listaRadar.split(":");		
		Log.v("Arquivo Texto", "Tamanho: " + radares.length );
		
		for(int i = 0; i < radares.length; i++){
			String coordenadas[] = radares[i].split(",");
			latitude = Double.parseDouble(coordenadas[1]) * 1E6;
			longitude = Double.parseDouble(coordenadas[0])* 1E6;
			mapview.getOverlays().add(new BolaOverlay(new GeoPoint((int)latitude, (int) longitude),Color.RED));
		}
		
		
		//Coordenadas PADRÃO
		latitude = -15.860006 * 1E6;
		longitude = -47.818825 * 1E6;
		
		//Muda título da Activity
		setTitle("Radares");
		
		//Faz Zoom (1 a 21)
		mc.setZoom(4);
		
		//Cria ponto com a localização
		GeoPoint point = new GeoPoint((int)latitude, (int) longitude);	
		
		//Centraliza mapa no ponto escolhido
		mc.setCenter(point);
		
		//Ativa o modo satélite
		mapview.setSatellite(true);
		mapview.setStreetView(true);
		mapview.setTraffic(true);
		
		//Exibe o MapView na tela
		setContentView(mapview);		
	}
	
    public void showMap() {
        // TODO Auto-generated method stub

        try {
            geoPoint = new GeoPoint((int)(latitude * 1E6),(int)(longitude * 1E6));          
            //mapview = (MapView)findViewById(R.id.mapview);
            mapControll= mapview.getController();
            mapview.setBuiltInZoomControls(true);
            mapview.setStreetView(true);
            mapControll.setZoom(16);
            mapControll.animateTo(geoPoint);

            userPic = this.getResources().getDrawable(R.drawable.radar);
            userPicOverlay = new MyItemizedOverlay(userPic);
            OverlayItem overlayItem = new OverlayItem(geoPoint, "I'm Here!!!", null);
            userPicOverlay.addOverlay(overlayItem);
            mapview.getOverlays().add(userPicOverlay);

            atmPic = this.getResources().getDrawable(R.drawable.radar);
            nearPicOverlay = new MyItemizedOverlay(atmPic);
            
            //teste
    		String radares[] = listaRadar.split(":");		
    		Log.v("Arquivo Texto", "Tamanho: " + radares.length );
    		
    		for(int i = 0; i < radares.length; i++){
    			String coordenadas[] = radares[i].split(",");
    			latitude = Double.parseDouble(coordenadas[1]) * 1E6;
    			longitude = Double.parseDouble(coordenadas[0])* 1E6;
    			//mapview.getOverlays().add(new BolaOverlay(new GeoPoint((int)latitude, (int) longitude),Color.RED));
    			  nearatms[i] = new OverlayItem( new GeoPoint( (int)((latitude) * 1E6),(int)((longitude) * 1E6)),"Name",null );//just check the brackets i just made change here so....
                  nearPicOverlay.addOverlay(nearatms[i]);
    		}
            //          
            
            /*for (int i = 0; i < define your length here; i++) {
                nearatms[i] = new OverlayItem( new GeoPoint( (int)((latitude) * 1E6),(int)((longitude) * 1E6)),"Name", null);//just check the brackets i just made change here so....
                nearPicOverlay.addOverlay(nearatms[i]);
            }*/
    		
    		
            mapview.getOverlays().add(nearPicOverlay);
            //Added symbols will be displayed when map is redrawn so force redraw now
            mapview.postInvalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    private void lerArquivo() {
		try {
			File f = getFileStreamPath(nomeArquivo);
			Log.i("Arquivo Texto", "Abrindo arquivo: " + f.getAbsolutePath());

			if(f.exists()){
				FileInputStream in = openFileInput(nomeArquivo);
				int tamanho = in.available();
				byte bytes[] = new byte[tamanho];
				in.read(bytes);
				listaRadar = new String(bytes);
			}else{
				Log.i("Arquivo Texto", "Arquivo não existe ou excluído");
			}
		} catch (Exception e) {
			Log.e("Arquivo Texto", "Arquivo não encontrado: " + e.getMessage(), e);
		} 
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
