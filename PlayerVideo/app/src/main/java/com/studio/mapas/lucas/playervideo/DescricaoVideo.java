package com.studio.mapas.lucas.playervideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DescricaoVideo extends Activity {
    public String titulo;
    public int video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_video);

        //recupera informações sobre o procedimento escolhido na tela anterior: "SelecaoVideo.java"
        Intent it = getIntent();
        titulo = it.getStringExtra("titulo");
        video = it.getIntExtra("video", 0);

        TextView txtTituloVideo = (TextView) findViewById(R.id.txtTituloVideo);
        TextView txtDescricaoVideo = (TextView) findViewById(R.id.txtDescricaoVideo);

        //atualiza a tela com as informações recuperadas da tela anterior
        txtTituloVideo.setText(titulo);
        txtDescricaoVideo.setText(buscaDescricao(titulo));
    }

    public String buscaDescricao(String titulo){
        //Recupera descrição presente no arquivo "string.xml"
        //Nesse arquivo teremos uma constante descrevendo cada procedimento
        switch (titulo){
            case "massagem":
                return this.getString(R.string.descricao_massagem);
            case "desfibrilador":
                return this.getString(R.string.descricao_desfibrilador);
            default:
                return "inexistente";
        }
    }

    public void Assistir(View v){
        //Chamado quando o usuário deseja ver o vídeo
        Intent it = new Intent(this, PlayerVideo.class);
        it.putExtra("titulo",titulo);
        it.putExtra("video",video);

        startActivity(it);
    }
}
