package com.studio.mapas.lucas.playervideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class SelecaoVideo extends Activity {

    public static Map<String,Integer> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_selecao_video);

        //cria o dicionário com todos os vídeos do projeto
        configuraVideos();
    }

    public static void configuraVideos(){
        //PREENCHER o dicionário com todos os vídeos do projeto.
        //Cada video tem um título.
        //ESSES TÍTULOS DEVERÃO SER IDENTICOS ÀS TAGs DO ARQUIVO "content_selecao_video.xml"
        if(videos == null) {
            videos = new HashMap<String, Integer>();
            //um put para cada video
            videos.put("massagem", R.raw.video_massagem_3gp);
            videos.put("desfibrilador", R.raw.video_massagem);
        }
    }

    public void VerVideo(View v){
        //Executado ao escolher o procedimento que deseja ver descrição e video
        //cada video é diferenciado por uma TAG. Olhar arquivo "content_selecao_video.xml"
        String tag = v.getTag().toString();

        Intent it = new Intent(this, DescricaoVideo.class);

        //envia Titulo do vídeo e arquivo a ser exibido para próxima tela: "DescricaoVideo.java"
        //todos os arquivos se encontram na pasta /res/draw
        //pasta raw não existe por padrão, portanto deve ser criada
        it.putExtra("titulo",tag);
        it.putExtra("video",videos.get(tag));

        startActivity(it);
    }

}
