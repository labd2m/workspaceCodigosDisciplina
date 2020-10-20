package vegi.jogo.da.velha;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	
	char tabuleiro [][] = new char[3][3];
    boolean preenchido [][] = new boolean[3][3]; 
    int idBotoes [][] = {{R.id.but_0_0,R.id.but_0_1,R.id.but_0_2},{R.id.but_1_0,R.id.but_1_1,R.id.but_1_2},{R.id.but_2_0,R.id.but_2_1,R.id.but_2_2}};
    char opt = 'X';
    int i,j,vencX,vencO,empt = 0;
    int jogadas = 0;
    boolean terminou = false;
    int cont = 1; // contador de jogadas do android
    int tatica; //contem o numero da tatica empregada pelo android
    int taticaTerceira; //contem o numero da tatica empregada pelo android
    Intent it;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        
        it = getIntent(); // recupera intenção
        
        final TextView jogador = (TextView) findViewById(R.id.textViewJogador);
        final TextView vencedorX = (TextView) findViewById(R.id.textViewPlacarX);
        final TextView vencedorO = (TextView) findViewById(R.id.textViewPlacarO);
        final TextView empates = (TextView) findViewById(R.id.textViewPlacarEmpate);
        
        final TextView jogadorLabel = (TextView) findViewById(R.id.textViewLabelJogador);
        final TextView PlacarLabel = (TextView) findViewById(R.id.textViewLabelPlacar);
        final TextView PlacarXLabel = (TextView) findViewById(R.id.textViewLabelPlacarX);
        final TextView PlacarOLabel = (TextView) findViewById(R.id.textViewLabelPlacarO);
        final TextView PlacarEmpateLabel = (TextView) findViewById(R.id.textViewLabelPlacarEmpate);
        
        //adiciona fonte customizada
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/angelina.TTF");
        
        jogadorLabel.setTypeface(tf, Typeface.BOLD);
        jogadorLabel.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
        
        jogador.setTextSize(TypedValue.COMPLEX_UNIT_PT, 12);
        vencedorX.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        vencedorO.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        empates.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        
        PlacarLabel.setTypeface(tf, Typeface.BOLD);
        PlacarLabel.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
        
        PlacarXLabel.setTypeface(tf, Typeface.BOLD);
        PlacarXLabel.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        
        PlacarOLabel.setTypeface(tf, Typeface.BOLD);
        PlacarOLabel.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        
        PlacarEmpateLabel.setTypeface(tf, Typeface.BOLD);
        PlacarEmpateLabel.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        
        //RelativeLayout.CENTER_HORIZONTAL teste = new RelativeLayout.LayoutParams();  
        //linha.setLayoutParams();
        //final TextView vencedor = (TextView) findViewById(R.id.textViewVencedor);
        
        final Button butRestart = (Button) findViewById(R.id.butRestart);
        
        final ImageButton but0_0 = (ImageButton) findViewById(R.id.but_0_0);
        final ImageButton but0_1 = (ImageButton) findViewById(R.id.but_0_1);
        final ImageButton but0_2 = (ImageButton) findViewById(R.id.but_0_2);
        final ImageButton but1_0 = (ImageButton) findViewById(R.id.but_1_0);
        final ImageButton but1_1 = (ImageButton) findViewById(R.id.but_1_1);
        final ImageButton but1_2 = (ImageButton) findViewById(R.id.but_1_2);
        final ImageButton but2_0 = (ImageButton) findViewById(R.id.but_2_0);
        final ImageButton but2_1 = (ImageButton) findViewById(R.id.but_2_1);
        final ImageButton but2_2 = (ImageButton) findViewById(R.id.but_2_2);
        
        jogador.setText(" " + opt);
        
        if(opt == 'X')
        	jogador.setTextColor(Color.RED);
        else
        	jogador.setTextColor(Color.BLUE);
        
        Log.i("CHEGUEI", "PONTO 1!"); //Log de Informação de Clicks
        
        // Cria jogo
        for (i = 0; i < 3; i++) {
          for (j = 0; j < 3; j++) {
        	Log.i("CHEGUEI", "PONTO 2!"); //Log de Informação de Clicks
        	preenchido[i][j] = false;
            tabuleiro[i][j] = '-';
          }
        }
        
        //NO MODO DIFICIL COMEÇA JOGANDO
        if(it.getStringExtra("tipoJogo").equals("dificil"))       
        	JogaPcDificil();
        
    	//Informa o Listener de um Botão no click ---------------------------------
        butRestart.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v){    		
        		
        		but0_0.setImageResource(R.drawable.vazio);
        		but0_1.setImageResource(R.drawable.vazio);
        		but0_2.setImageResource(R.drawable.vazio);
        		but1_0.setImageResource(R.drawable.vazio);
        		but1_1.setImageResource(R.drawable.vazio);
        		but1_2.setImageResource(R.drawable.vazio);
        		but2_0.setImageResource(R.drawable.vazio);
        		but2_1.setImageResource(R.drawable.vazio);
        		but2_2.setImageResource(R.drawable.vazio);
        		
                //Reinicia Jogo
        		for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
                  	Log.i("CHEGUEI", "PONTO 2!"); //Log de Informação de Clicks
                  	preenchido[i][j] = false;
                      tabuleiro[i][j] = '-';
                    }
                }
        		
        		opt = 'X';
        		jogador.setText(" " + opt);
        		jogador.setTextColor(Color.RED);
        		
        		//vencedor.setText("");
        		jogadas = 0;
        		terminou = false;
        		
      		  ImageView diagonal2 = (ImageView) findViewById(R.id.imageDiagonal2);
      		  diagonal2.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView diagonal1 = (ImageView) findViewById(R.id.imageDiagonal1);
      		  diagonal1.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView colunaMeio = (ImageView) findViewById(R.id.imageColunaMeio);
      		  colunaMeio.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView colunaDireita = (ImageView) findViewById(R.id.imageColunaDireita);
    		  colunaDireita.setVisibility(ImageView.INVISIBLE);
    		  
      		  ImageView colunaEsquerda = (ImageView) findViewById(R.id.imageColunaEsquerda);
    		  colunaEsquerda.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView linha = (ImageView) findViewById(R.id.imageLinha);
      		  linha.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView linhaBaixo = (ImageView) findViewById(R.id.imageLinhaBaixo);
    		  linhaBaixo.setVisibility(ImageView.INVISIBLE);
    		  
    		  ImageView linhaCima = (ImageView) findViewById(R.id.imageLinhaCima);
      		  linhaCima.setVisibility(ImageView.INVISIBLE);
      		  
      		  empates.setText("0");
      		  vencedorO.setText("0");
      		  vencedorX.setText("0");
    		
    		  Log.i("Botão", "Novo Jogo"); //Log de Informação de Clicks
    		  
      		  cont = 1;
      		  
              if(it.getStringExtra("tipoJogo").equals("dificil"))       
              	JogaPcDificil();
      		
        	}
        });
    	
        //Informa o Listener de um Botão no click ---------------------------------
        but0_0.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[0][0] == false && terminou != true){
	        		if(opt == 'X')
	        			but0_0.setImageResource(R.drawable.x);
	        		else
	        			but0_0.setImageResource(R.drawable.o);
	        		
	                preenchido[0][0] = true;
	                tabuleiro[0][0] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                    Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{	            
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();
	                	}
	                }	
        		}
        		
        		Log.i("Botão", "Botão 0_0 foi clicado!"); //Log de Informação de Clicks
        	}
        });

    	//Informa o Listener de um Botão no click ---------------------------------
        but0_1.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[0][1] == false && terminou != true){
	        		if(opt == 'X')
	        			but0_1.setImageResource(R.drawable.x);
	        		else
	        			but0_1.setImageResource(R.drawable.o);
	        		
	                preenchido[0][1] = true;
	                tabuleiro[0][1] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();
	                	}
	                }	     		
        		}  
        		
        		Log.i("Botão", "Botão 0_1 foi clicado!"); //Log de Informação de Clicks
        	}
        });
        
    	//Informa o Listener de um Botão no click ---------------------------------
        but0_2.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[0][2] == false && terminou != true){
	        		if(opt == 'X')
	        			but0_2.setImageResource(R.drawable.x);
	        		else
	        			but0_2.setImageResource(R.drawable.o);
	        		
	                preenchido[0][2] = true;
	                tabuleiro[0][2] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();	
	                	}
	                }	     		
        		}
        		
        		Log.i("Botão", "Botão 0_2 foi clicado!"); //Log de Informação de Clicks
        	}
        });
        
    	//Informa o Listener de um Botão no click ---------------------------------
        but1_0.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[1][0] == false && terminou != true){
	        		if(opt == 'X')
	        			but1_0.setImageResource(R.drawable.x);
	        		else
	        			but1_0.setImageResource(R.drawable.o);
	        		
	                preenchido[1][0] = true;
	                tabuleiro[1][0] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();
	                	}
	                }	     		
        		}
        		
        		Log.i("Botão", "Botão 1_0 foi clicado!"); //Log de Informação de Clicks
        	}
        });
        
    	//Informa o Listener de um Botão no click ---------------------------------
        but1_1.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[1][1] == false && terminou != true){
	        		if(opt == 'X')
	        			but1_1.setImageResource(R.drawable.x);
	        		else
	        			but1_1.setImageResource(R.drawable.o);
	        		
	                preenchido[1][1] = true;
	                tabuleiro[1][1] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);                		
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();	
	                	}
	                }	     		
        		}
        		
        		Log.i("Botão", "Botão 1_1 foi clicado!"); //Log de Informação de Clicks
        	}
        });
        
    	//Informa o Listener de um Botão no click ---------------------------------
        but1_2.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[1][2] == false && terminou != true){
	        		if(opt == 'X')
	        			but1_2.setImageResource(R.drawable.x);
	        		else
	        			but1_2.setImageResource(R.drawable.o);
	        		
	                preenchido[1][2] = true;
	                tabuleiro[1][2] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();	
	                	}
	                }	     		
        		}  
        		
        		Log.i("Botão", "Botão 1_2 foi clicado!"); //Log de Informação de Clicks
        	}
        });
        
    	//Informa o Listener de um Botão no click ---------------------------------
        but2_0.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[2][0] == false && terminou != true){
	        		if(opt == 'X')
	        			but2_0.setImageResource(R.drawable.x);
	        		else
	        			but2_0.setImageResource(R.drawable.o);
	        		
	                preenchido[2][0] = true;
	                tabuleiro[2][0] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();
	                	}
	                }	     		
        		}  
        		
        		Log.i("Botão", "Botão 2_0 foi clicado!"); //Log de Informação de Clicks
        	}
        });
        
    	//Informa o Listener de um Botão no click ---------------------------------
        but2_1.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[2][1] == false && terminou != true){
	        		if(opt == 'X')
	        			but2_1.setImageResource(R.drawable.x);
	        		else
	        			but2_1.setImageResource(R.drawable.o);
	        		
	                preenchido[2][1] = true;
	                tabuleiro[2][1] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                        
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();
	                	}
	                }	     		
        		}
        		
        		Log.i("Botão", "Botão 2_1 foi clicado!"); //Log de Informação de Clicks
        	}
        });
        
    	//Informa o Listener de um Botão no click ---------------------------------
        but2_2.setOnClickListener(new ImageButton.OnClickListener() {
        	public void onClick(View v){    		
        		
        		if(preenchido[2][2] == false && terminou != true){
	        		if(opt == 'X')
	        			but2_2.setImageResource(R.drawable.x);
	        		else
	        			but2_2.setImageResource(R.drawable.o);
	        		
	                preenchido[2][2] = true;
	                tabuleiro[2][2] = opt;
	                
	                jogadas++;
	                
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                }
	                	                
	        		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	                    //vencedor.setText(" " + vitorioso(tabuleiro));
	        			Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	        			
	        			if(vitorioso(tabuleiro) == 'X'){
	        				Restart("O Vencedor foi o Jogador X");
	        				vencX += 1;
	        				vencedorX.setText("" + vencX);
	        				jogador.setTextColor(Color.RED);
	        			}else{
	        				Restart("O Vencedor foi o Jogador O");
	        				vencO += 1;
	        				vencedorO.setText("" + vencO);
	        				jogador.setTextColor(Color.BLUE);
	        			}
	        			
	                }else{
	                	if(jogadas == 9){
	                		//vencedor.setText(" Não houve vencedor!");
	                		Restart("Deu Velha!");
	                		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	                		empt += 1;
	                		empates.setText("" + empt);
	                		
	                		if(opt == 'X')
	                        	jogador.setTextColor(Color.RED);
	                        else
	                        	jogador.setTextColor(Color.BLUE);
	                		
	                	}else{
	                		jogador.setText(" " + opt);
	                		if(it.getStringExtra("tipoJogo").equals("sozinho"))
	                			JogaPcFacil();
	                		else if(it.getStringExtra("tipoJogo").equals("dificil"))
	                			JogaPcDificil();
	                	}
	                }	     		
        		}
        		
        		Log.i("Botão", "Botão 2_2 foi clicado!"); //Log de Informação de Clicks
        	}
        });
                
    }
    
    public char vitorioso(char jogo[][]) {
    	  for (int i = 0; i < 3; i++) {
    	  Log.i("Lógica", "Linha: " + i);  
    		  if (vitoriaLinha(jogo, i) == true) {
    			  RelativeLayout.LayoutParams lin = new RelativeLayout.LayoutParams(300, 220);
    			  terminou = true;
    			  
    	  		  if(i == 0){
    	  			  ImageView linhaCima = (ImageView) findViewById(R.id.imageLinhaCima);
    	  			  linhaCima.setVisibility(ImageView.VISIBLE);
    	  		  }
    	  		  
    	  		  if(i == 1){
    	  			  ImageView linha = (ImageView) findViewById(R.id.imageLinha);
  	  			  	  linha.setVisibility(ImageView.VISIBLE);
    	  		  }
    	  		  
      	  		  if(i == 2){
      	  			  ImageView linhaBaixo = (ImageView) findViewById(R.id.imageLinhaBaixo);
      	  			  linhaBaixo.setVisibility(ImageView.VISIBLE);
    	  		  }
      	  		  //Restart("O Jogador " + jogo[i][0] + " venceu!");
      	  		  return jogo[i][0];
    	    }
    	  }
    	  
    	  for (int i = 0; i < 3; i++) {
    	  Log.i("Lógica", "Coluna: " + i);   
    		  if (vitoriaColuna(jogo, i) == true) {
    			  RelativeLayout.LayoutParams col = new RelativeLayout.LayoutParams(105, 250);
    			  terminou = true;
    			  
    	  		  if(i == 0){
        			  ImageView colunaEsquerda = (ImageView) findViewById(R.id.imageColunaEsquerda);
    	  			  colunaEsquerda.setVisibility(ImageView.VISIBLE);
    	  		  }
    	  		  
    	  		  if(i == 1){
        			  ImageView colunaMeio = (ImageView) findViewById(R.id.imageColunaMeio);
    	  			  colunaMeio.setVisibility(ImageView.VISIBLE);
    	  		  }
    	  		  
      	  		  if(i == 2){
        			  ImageView colunaDireita = (ImageView) findViewById(R.id.imageColunaDireita);
    	  			  colunaDireita.setVisibility(ImageView.VISIBLE);
    	  		  }
    	  		  
      	  		  //Restart("O Jogador " + jogo[0][i] + " venceu!");
    	  		  return jogo[0][i];
    	    }
    	  }
    	  
    	  if (vitoriaDiagonal1(jogo) == true) {
      		  ImageView diagonal1 = (ImageView) findViewById(R.id.imageDiagonal1);
      		  diagonal1.setVisibility(ImageView.VISIBLE);
      		  terminou = true;
      		 
      		  //Restart("O Jogador " + jogo[0][0] + " venceu!");
      		  return jogo[0][0];
    	  }
    	  
    	  if (vitoriaDiagonal2(jogo) == true) {
    		  ImageView diagonal2 = (ImageView) findViewById(R.id.imageDiagonal2);
    		  diagonal2.setVisibility(ImageView.VISIBLE);
    		  terminou = true;
    		  
    		  //Restart("O Jogador " + jogo[2][0] + " venceu!");
    		  return jogo[2][0];
    	  }
    	  
    	  return 'V';
    }  

    private boolean vitoriaDiagonal2(char[][] jogo) {
		if((jogo[0][2] == jogo[1][1] && jogo[1][1] == jogo[2][0]) && jogo[0][2] != '-' )
			return true;
		else
			return false;
	}

	private boolean vitoriaDiagonal1(char[][] jogo) {
		if((jogo[0][0] == jogo[1][1] && jogo[1][1] == jogo[2][2]) && jogo[0][0] != '-' )
			return true;
		else
			return false;
	}

	private boolean vitoriaColuna(char[][] jogo, int i) {
		if((jogo[0][i] == jogo[1][i] && jogo[1][i] == jogo[2][i]) && jogo[0][i] != '-')
			return true;
		else
			return false;
	}

	private boolean vitoriaLinha(char[][] jogo, int i) {
		if((jogo[i][0] == jogo[i][1] && jogo[i][1] == jogo[i][2]) && jogo[i][0] != '-' )
			return true;
		else
			return false;
	}
	
	private void JogaPcFacil(){
        //DESABILITA TODOS OS BOTÕES
		for(int i = 0; i < 3; i++){
        	for(int j = 0; j < 3; j++){
        		ImageButton auxBut = (ImageButton) findViewById(idBotoes[i][j]);
        		auxBut.setClickable(false);
        	}
        }
		
		//PAUSE 2 SEGUNDOS PARA O PC JOGAR
		new Handler().postDelayed(new Runnable(){ 
			public void run() { 
				boolean teste = false;
		        TextView jogador = (TextView) findViewById(R.id.textViewJogador);
		        TextView vencedorX = (TextView) findViewById(R.id.textViewPlacarX);
		        TextView vencedorO = (TextView) findViewById(R.id.textViewPlacarO);
		        TextView empates = (TextView) findViewById(R.id.textViewPlacarEmpate);
				
				while(!teste){
					//SORTEIA A JOGADA DO PC
					int linha = (int) (Math.random() * 3);
					int coluna = (int) (Math.random() * 3);
					
					if(preenchido[linha][coluna] == false){				
						teste = true;
						preenchido[linha][coluna] = true;
						tabuleiro[linha][coluna] = opt;	
						jogadas++;
								
						ImageButton botao = (ImageButton) findViewById(idBotoes[linha][coluna]);
						
						if(opt == 'X')
		        			botao.setImageResource(R.drawable.x);
		        		else
		        			botao.setImageResource(R.drawable.o);
						
		                if (jogadas % 2 == 0) {
		                    opt = 'X';
		                    jogador.setTextColor(Color.RED);
		                } else {
		                    opt = 'O';
		                    jogador.setTextColor(Color.BLUE);
		                }             
					}
				}
				
				if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
		            //vencedor.setText(" " + vitorioso(tabuleiro));
					
					if(vitorioso(tabuleiro) == 'X'){
						Restart("O Vencedor foi o Jogador X");
						vencX += 1;
						vencedorX.setText("" + vencX);
						jogador.setTextColor(Color.RED);
					}else{
						Restart("O Vencedor foi o Jogador O");
						vencO += 1;
						vencedorO.setText("" + vencO);
						jogador.setTextColor(Color.BLUE);
					}
					
		            Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
		        }else{
		        	if(jogadas == 9){
		        		//vencedor.setText(" Não houve vencedor!");
		        		Restart("Deu Velha!");
		        		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
		        		empt += 1;
		        		empates.setText("" + empt);
		                
		        		if(opt == 'X')
		                	jogador.setTextColor(Color.RED);
		                else
		                	jogador.setTextColor(Color.BLUE);
		        		
		        	}else{
		        		jogador.setText(" " + opt);
		        	}
		        }
				
		        //HABILITA TODOS OS BOTÕES
				for(int i = 0; i < 3; i++){
		        	for(int j = 0; j < 3; j++){
		        		ImageButton auxBut = (ImageButton) findViewById(idBotoes[i][j]);
		        		auxBut.setClickable(true);
		        	}
		        }
			} 
		} , 2000);
				
	}
	
	private void JogaPcDificil(){
        //DESABILITA TODOS OS BOTÕES
		for(int i = 0; i < 3; i++){
        	for(int j = 0; j < 3; j++){
        		ImageButton auxBut = (ImageButton) findViewById(idBotoes[i][j]);
        		auxBut.setClickable(false);
        	}
        }
		
		//PAUSE 2 SEGUNDOS PARA O PC JOGAR
		new Handler().postDelayed(new Runnable(){ 
			public void run() { 
		        TextView jogador = (TextView) findViewById(R.id.textViewJogador);
		        ImageButton botao = (ImageButton) findViewById(idBotoes[0][0]);// inicia a variavel
				
				if(cont == 1){
					preenchido[0][2] = true;
					tabuleiro[0][2] = opt;	
					jogadas++;
					cont++;						
					botao = (ImageButton) findViewById(idBotoes[0][2]);
					
					if(opt == 'X')
	        			botao.setImageResource(R.drawable.x);
	        		else
	        			botao.setImageResource(R.drawable.o);
					
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                } 
	                
	                TestaVitoria();
	                return;
				}
				
				if(cont == 2){
					if(preenchido[2][2] == false && preenchido[1][1] == false){
						preenchido[2][2] = true;
						tabuleiro[2][2] = opt;	
						jogadas++;
						cont++;							
						botao = (ImageButton) findViewById(idBotoes[2][2]);
						tatica = 1;
					}else if(preenchido[1][1] == false){
						preenchido[0][0] = true;
						tabuleiro[0][0] = opt;	
						jogadas++;
						cont++;							
						botao = (ImageButton) findViewById(idBotoes[0][0]);
						tatica = 2;						
					}else{
						preenchido[2][0] = true;
						tabuleiro[2][0] = opt;	
						jogadas++;
						cont++;							
						botao = (ImageButton) findViewById(idBotoes[2][0]);
						tatica = 3;
					}
					
					if(opt == 'X')
	        			botao.setImageResource(R.drawable.x);
	        		else
	        			botao.setImageResource(R.drawable.o);
					
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                } 
	                
	                TestaVitoria();
	                return;
				}
				
				if(cont == 3){
					if(tatica == 1){
						if(preenchido[1][2] == false){
							preenchido[1][2] = true;
							tabuleiro[1][2] = opt;	
							jogadas++;
							cont++;							
							botao = (ImageButton) findViewById(idBotoes[1][2]);
						}else if(preenchido[2][0] == false){
							preenchido[2][0] = true;
							tabuleiro[2][0] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 1;
							botao = (ImageButton) findViewById(idBotoes[2][0]);
						}else{
							preenchido[0][0] = true;
							tabuleiro[0][0] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 2;
							botao = (ImageButton) findViewById(idBotoes[0][0]);
						}
					}
					
					if(tatica == 2){
						if(preenchido[0][1] == false){
							preenchido[0][1] = true;
							tabuleiro[0][1] = opt;	
							jogadas++;
							cont++;							
							botao = (ImageButton) findViewById(idBotoes[0][1]);
						}else {
							preenchido[2][0] = true;
							tabuleiro[2][0] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 1;
							botao = (ImageButton) findViewById(idBotoes[2][0]);
						}
					}
					
					if(tatica == 3){
						if(preenchido[0][0] == true){
							preenchido[2][2] = true;
							tabuleiro[2][2] = opt;	
							jogadas++;
							cont++;	
							taticaTerceira = 1;
							botao = (ImageButton) findViewById(idBotoes[2][2]);
						}else if(preenchido[2][2] == true) {
							preenchido[0][0] = true;
							tabuleiro[0][0] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 2;
							botao = (ImageButton) findViewById(idBotoes[0][0]);
						}else if(preenchido[1][0] == true){
							preenchido[1][2] = true;
							tabuleiro[1][2] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 3;
							botao = (ImageButton) findViewById(idBotoes[1][2]);
						}else if(preenchido[1][2] == true){
							preenchido[1][0] = true;
							tabuleiro[1][0] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 4;
							botao = (ImageButton) findViewById(idBotoes[1][0]);
						}else if(preenchido[0][1] == true){
							preenchido[2][1] = true;
							tabuleiro[2][1] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 5;
							botao = (ImageButton) findViewById(idBotoes[2][1]);
						}else if(preenchido[2][1] == true){
							preenchido[0][1] = true;
							tabuleiro[0][1] = opt;	
							jogadas++;
							cont++;
							taticaTerceira = 6;
							botao = (ImageButton) findViewById(idBotoes[0][1]);
						}
					}
					
					if(opt == 'X')
	        			botao.setImageResource(R.drawable.x);
	        		else
	        			botao.setImageResource(R.drawable.o);
					
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                } 
	                
	                TestaVitoria();
	                return;
				}
				
				if(cont == 4){
					if(tatica == 1 && taticaTerceira == 1){
						if(preenchido[1][1] == false){
							preenchido[1][1] = true;
							tabuleiro[1][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][1]);
						}else if(preenchido[2][1] == false){
							preenchido[2][1] = true;
							tabuleiro[2][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][1]);
						}else if(preenchido[0][1] == false){
							preenchido[0][1] = true;
							tabuleiro[0][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][1]);
						}
					}else if(tatica == 1 && taticaTerceira == 2){
						if(preenchido[0][1] == false){
							preenchido[0][1] = true;
							tabuleiro[0][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][1]);
						}else{
							preenchido[1][1] = true;
							tabuleiro[1][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][1]);
						}
					}else if(tatica == 2){
						if(preenchido[1][0] == false){
							preenchido[1][0] = true;
							tabuleiro[1][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][0]);
						}else{
							preenchido[1][1] = true;
							tabuleiro[1][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][1]);
						}
					}else if(tatica == 3 && taticaTerceira == 1){
						if(preenchido[2][1] == false){
							preenchido[2][1] = true;
							tabuleiro[2][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][1]);
						}else{
							preenchido[1][2] = true;
							tabuleiro[1][2] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][2]);
						}
					}else if(tatica == 3 && taticaTerceira == 2){
						if(preenchido[0][1] == false){
							preenchido[0][1] = true;
							tabuleiro[0][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][1]);
						}else{
							preenchido[1][0] = true;
							tabuleiro[1][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][0]);
						}
					}else if(tatica == 3 && taticaTerceira == 3){
						if(preenchido[2][2] == false){
							preenchido[2][2] = true;
							tabuleiro[2][2] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][2]);
						}else{
							preenchido[0][0] = true;
							tabuleiro[0][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][0]);
						}
					}else if(tatica == 3 && taticaTerceira == 4){
						if(preenchido[0][0] == false){
							preenchido[0][0] = true;
							tabuleiro[0][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][0]);
						}else{
							preenchido[2][2] = true;
							tabuleiro[2][2] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][2]);
						}
					}else if(tatica == 3 && taticaTerceira == 5){
						if(preenchido[2][2] == false){
							preenchido[2][2] = true;
							tabuleiro[2][2] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][2]);
						}else{
							preenchido[0][0] = true;
							tabuleiro[0][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][0]);
						}
					}else if(tatica == 3 && taticaTerceira == 6){
						if(preenchido[0][0] == false){
							preenchido[0][0] = true;
							tabuleiro[0][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][0]);
						}else{
							preenchido[2][2] = true;
							tabuleiro[2][2] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][2]);
						}
					}					
					
					if(opt == 'X')
	        			botao.setImageResource(R.drawable.x);
	        		else
	        			botao.setImageResource(R.drawable.o);
					
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                } 
	                
	                TestaVitoria();
	                return;
				}
				
				if(cont == 5){
					if(tatica == 1 && taticaTerceira == 1){
						if(preenchido[1][0] == false){
							preenchido[1][0] = true;
							tabuleiro[1][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][0]);
						}else{
							preenchido[0][0] = true;
							tabuleiro[0][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][0]);
						}
					}else if(tatica == 3 && taticaTerceira == 3){
						if(preenchido[0][1] == false){
							preenchido[0][1] = true;
							tabuleiro[0][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][1]);
						}else{
							preenchido[2][1] = true;
							tabuleiro[2][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][1]);
						}
					}else if(tatica == 3 && taticaTerceira == 4){
						if(preenchido[2][1] == false){
							preenchido[2][1] = true;
							tabuleiro[2][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][1]);
						}else{
							preenchido[0][1] = true;
							tabuleiro[0][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[0][1]);
						}
					}else if(tatica == 3 && taticaTerceira == 5){
						if(preenchido[1][0] == false){
							preenchido[1][0] = true;
							tabuleiro[1][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][0]);
						}else{
							preenchido[1][2] = true;
							tabuleiro[1][2] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][2]);
						}
					}else if(tatica == 3 && taticaTerceira == 6){
						if(preenchido[2][1] == false){
							preenchido[2][1] = true;
							tabuleiro[2][1] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[2][1]);
						}else{
							preenchido[1][0] = true;
							tabuleiro[1][0] = opt;	
							jogadas++;
							cont++;
							botao = (ImageButton) findViewById(idBotoes[1][0]);
						}
					}
					
					if(opt == 'X')
	        			botao.setImageResource(R.drawable.x);
	        		else
	        			botao.setImageResource(R.drawable.o);
					
	                if (jogadas % 2 == 0) {
	                    opt = 'X';
	                    jogador.setTextColor(Color.RED);
	                } else {
	                    opt = 'O';
	                    jogador.setTextColor(Color.BLUE);
	                } 
	                TestaVitoria();
	                return;
				}	
			} 
		} , 2000);
				
	}//fim metodo
	
	public void TestaVitoria(){
        TextView jogador = (TextView) findViewById(R.id.textViewJogador);
        TextView vencedorX = (TextView) findViewById(R.id.textViewPlacarX);
        TextView vencedorO = (TextView) findViewById(R.id.textViewPlacarO);
        TextView empates = (TextView) findViewById(R.id.textViewPlacarEmpate);
        
		if (vitorioso(tabuleiro) == 'X' || vitorioso(tabuleiro) == 'O') {
	        //vencedor.setText(" " + vitorioso(tabuleiro));
			
			if(vitorioso(tabuleiro) == 'X'){
				Restart("O Vencedor foi o Jogador X");
				vencX += 1;
				vencedorX.setText("" + vencX);
				jogador.setTextColor(Color.RED);
			}else{
				Restart("O Vencedor foi o Jogador O");
				vencO += 1;
				vencedorO.setText("" + vencO);
				jogador.setTextColor(Color.BLUE);
			}
			
	        Toast.makeText(getBaseContext(), "O jogador " + vitorioso(tabuleiro) + " foi o vencedor!", Toast.LENGTH_SHORT).show();
	    }else{
	    	if(jogadas == 9){
	    		//vencedor.setText(" Não houve vencedor!");
	    		Restart("Deu Velha!");
	    		Toast.makeText(getBaseContext(), "Não houve Vencedor!", Toast.LENGTH_SHORT).show();
	    		empt += 1;
	    		empates.setText("" + empt);
	            
	    		if(opt == 'X')
	            	jogador.setTextColor(Color.RED);
	            else
	            	jogador.setTextColor(Color.BLUE);
	    		
	    	}else{
	    		jogador.setText(" " + opt);
	    	}
	    }
		
	    //HABILITA TODOS OS BOTÕES
		for(int i = 0; i < 3; i++){
	    	for(int j = 0; j < 3; j++){
	    		ImageButton auxBut = (ImageButton) findViewById(idBotoes[i][j]);
	    		auxBut.setClickable(true);
	    	}
	    }
	}//fim metodo
	
	public void MenuPrincipal(View v){
		finish();
	}
	
	public void Restart(String vencedor){
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle(vencedor);
		alerta.setMessage("Deseja realizar uma nova partida?");
		
		// Método executado se escolher Sim
		alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
		        
				TextView jogador = (TextView) findViewById(R.id.textViewJogador);
				ImageButton but0_0 = (ImageButton) findViewById(R.id.but_0_0);
		        ImageButton but0_1 = (ImageButton) findViewById(R.id.but_0_1);
		        ImageButton but0_2 = (ImageButton) findViewById(R.id.but_0_2);
		        ImageButton but1_0 = (ImageButton) findViewById(R.id.but_1_0);
		        ImageButton but1_1 = (ImageButton) findViewById(R.id.but_1_1);
		        ImageButton but1_2 = (ImageButton) findViewById(R.id.but_1_2);
		        ImageButton but2_0 = (ImageButton) findViewById(R.id.but_2_0);
		        ImageButton but2_1 = (ImageButton) findViewById(R.id.but_2_1);
		        ImageButton but2_2 = (ImageButton) findViewById(R.id.but_2_2);
				
				but0_0.setImageResource(R.drawable.vazio);
        		but0_1.setImageResource(R.drawable.vazio);
        		but0_2.setImageResource(R.drawable.vazio);
        		but1_0.setImageResource(R.drawable.vazio);
        		but1_1.setImageResource(R.drawable.vazio);
        		but1_2.setImageResource(R.drawable.vazio);
        		but2_0.setImageResource(R.drawable.vazio);
        		but2_1.setImageResource(R.drawable.vazio);
        		but2_2.setImageResource(R.drawable.vazio);
        		
                //Reinicia Jogo
        		for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
                  	Log.i("CHEGUEI", "PONTO 2!"); //Log de Informação de Clicks
                  	preenchido[i][j] = false;
                      tabuleiro[i][j] = '-';
                    }
                }
        		
        		opt = 'X';
        		jogador.setText(" " + opt);
        		jogador.setTextColor(Color.RED);
        		
        		//vencedor.setText("");
        		jogadas = 0;
        		terminou = false;
        		
      		  ImageView diagonal2 = (ImageView) findViewById(R.id.imageDiagonal2);
      		  diagonal2.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView diagonal1 = (ImageView) findViewById(R.id.imageDiagonal1);
      		  diagonal1.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView colunaMeio = (ImageView) findViewById(R.id.imageColunaMeio);
      		  colunaMeio.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView colunaDireita = (ImageView) findViewById(R.id.imageColunaDireita);
    		  colunaDireita.setVisibility(ImageView.INVISIBLE);
    		  
      		  ImageView colunaEsquerda = (ImageView) findViewById(R.id.imageColunaEsquerda);
    		  colunaEsquerda.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView linha = (ImageView) findViewById(R.id.imageLinha);
      		  linha.setVisibility(ImageView.INVISIBLE);
      		  
      		  ImageView linhaBaixo = (ImageView) findViewById(R.id.imageLinhaBaixo);
    		  linhaBaixo.setVisibility(ImageView.INVISIBLE);
    		  
    		  ImageView linhaCima = (ImageView) findViewById(R.id.imageLinhaCima);
      		  linhaCima.setVisibility(ImageView.INVISIBLE);
      		  
      		  cont = 1;
      		  it = getIntent();
      		  
              if(it.getStringExtra("tipoJogo").equals("dificil"))       
              	JogaPcDificil();
      		
			}
		});
		
		// Método executado se escolher Não
		alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				finish();
			}
		});
		
		// Exibe o alerta de confirmação
		alerta.show();
	}
    
}
