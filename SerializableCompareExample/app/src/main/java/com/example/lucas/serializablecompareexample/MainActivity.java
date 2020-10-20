package com.example.lucas.serializablecompareexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static List<Pokemon> pokemons;

    //ExemploSensorGiroscopioObjeto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemons = new ArrayList<Pokemon>();

        //Tipos de teste
        Tipo t1 = new Tipo();
        t1.setIdTipo(1);
        t1.setNome("Grass");

        Tipo t2 = new Tipo();
        t2.setIdTipo(2);
        t2.setNome("Poison");

        Tipo t3 = new Tipo();
        t3.setIdTipo(3);
        t3.setNome("Fire");

        Tipo t4 = new Tipo();
        t4.setIdTipo(4);
        t4.setNome("Water");

        //POKEMON 1 - BULBASAUR
        List<Tipo> list1 = new ArrayList<Tipo>();
        list1.add(t1);
        list1.add(t2);
        Pokemon p1 = new Pokemon(1,"Bulbasaur","C",1,1,list1);
        pokemons.add(p1);

        //POKEMON 4 - CHARMANDER
        List<Tipo> list2 = new ArrayList<Tipo>();
        list2.add(t3);
        Pokemon p2 = new Pokemon(4,"Charmander","C",4,4,list2);
        pokemons.add(p2);

        //POKEMON 6 - SQUIRTLE
        List<Tipo> list3 = new ArrayList<Tipo>();
        list3.add(t4);
        Pokemon p3 = new Pokemon(7,"Squirtle","C",7,7,list3);
        pokemons.add(p3);

        //navegação para a segunda tela
        Intent it = new Intent(this,SegundaTela.class);
        it.putExtra("pkmn",p2); //envia charmander para a segunda tela
        startActivity(it);

    }
}
