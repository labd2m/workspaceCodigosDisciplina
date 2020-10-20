package com.example.lucas.serializablecompareexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SegundaTela extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        //recupera pokemon via navegação de tela
        Intent it = getIntent();
        Pokemon pokemonS = (Pokemon) it.getSerializableExtra("pkmn");

        Log.d("POKEMON","toString()\nPkmnList: " + MainActivity.pokemons.get(1).toString() + "\nPkmnS: " + pokemonS.toString());

        if(MainActivity.pokemons.get(1).equals(pokemonS))
            Log.d("POKEMON","equals() - SIM");
        else
            Log.d("POKEMON","equals() - NÃO");

        if(MainActivity.pokemons.contains(pokemonS))
            Log.d("POKEMON","contains() - SIM");
        else
            Log.d("POKEMON","contains() - NÃO");

    }
}
