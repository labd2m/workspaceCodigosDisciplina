package br.livro.android.cap16;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.maps.GeoPoint;

/**
 * Ajuda a construir um GeoPoint
 * 
 * @author ricardo
 * 
 */
public class Coordenada extends GeoPoint {
	// valores em graus * 1E6 (microdegrees)
	public Coordenada(int latitudeE6, int longitudeE6) {
		super(latitudeE6, longitudeE6);
	}

	// Converte para "graus * 1E6"
	public Coordenada(double latitude, double longitude) {
		this((int) (latitude * 1E6), (int) (longitude * 1E6));
	}

	// Cria baseado no objeto 'Location' diretamente recebido do GPS
	public Coordenada(Location location) {
		this(location.getLatitude(), location.getLongitude());
	}

	// Cria baseado em um endereço
	public Coordenada(Address endereco) {
		this(endereco.getLatitude(), endereco.getLongitude());
	}

	// Cria baseado em um endereço
	public Coordenada(Endereco endereco) {
		this(endereco.getLatitude(), endereco.getLongitude());
	}

	@Override
	public int getLatitudeE6() {
		return super.getLatitudeE6();
	}

	@Override
	public int getLongitudeE6() {
		return super.getLongitudeE6();
	}

	public double getLatitude() {
		return super.getLatitudeE6() / 1E6;
	}

	public double getLongitude() {
		return super.getLongitudeE6() / 1E6;
	}

	public static void get(Context context, double latitude, double longitude) {
		Geocoder gc = new Geocoder(context, Locale.US);
		List<Address> addresses = null;
		try {
			addresses = gc.getFromLocation(latitude, longitude, 10);
			for (Address address : addresses) {
				System.out.println(address.getFeatureName());
			}

			addresses = gc.getFromLocationName(
					"Rua Carlos Benato, 5 , Curtiiba", 10);
			for (Address address : addresses) {
				System.out.println(address.getFeatureName());
			}
		} catch (IOException e) {
			System.out.println("Deu erro o geo coder - " + e.getMessage());
		}
	}

	public static List<Address> getA(Context context, double latitude,
			double longitude) {
		Geocoder gc = new Geocoder(context, Locale.US);
		List<Address> addresses = null;
		try {
			addresses = gc.getFromLocation(latitude, longitude, 10);
		} catch (IOException e) {
			System.out.println("Deu erro o geo coder - " + e.getMessage());
		}
		return addresses;
	}

	public static List<Address> getA(Context context, String rua) {
		Geocoder gc = new Geocoder(context, Locale.US);
		List<Address> addresses = null;
		try {
			addresses = gc.getFromLocationName(rua, 10);
			return addresses;
		} catch (IOException e) {
			System.out.println("Deu erro o geo coder - " + e.getMessage());
		}
		return addresses;
	}

	public List<Address> getEndereco(Context context) {
		return getA(context, getLatitude(), getLongitude());
	}

	public static Coordenada getFromRua(Context context, String rua) {
		List<Address> endereco = Coordenada.getA(context, rua);
		Address eOrigem = endereco.get(0);
		Coordenada c = new Coordenada(eOrigem);
		return c;
	}
}
