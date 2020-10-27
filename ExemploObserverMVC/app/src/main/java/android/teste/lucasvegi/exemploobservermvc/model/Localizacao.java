package android.teste.lucasvegi.exemploobservermvc.model;

import android.teste.lucasvegi.exemploobservermvc.util.observer.Subject;

public class Localizacao extends Subject {
    private double latitude;
    private double longitude;
    private double altura;
    private double precisao;
    private double velocidade;
    private double distancia; //em relação a um ponto fixo

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
        notifyObservers();
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
        notifyObservers();
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
        notifyObservers();
    }

    public double getPrecisao() {
        return precisao;
    }

    public void setPrecisao(double precisao) {
        this.precisao = precisao;
        notifyObservers();
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
        notifyObservers();
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
        notifyObservers();
    }


}
