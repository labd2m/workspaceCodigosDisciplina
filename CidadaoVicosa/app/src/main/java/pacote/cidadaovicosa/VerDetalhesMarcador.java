package pacote.cidadaovicosa;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VerDetalhesMarcador extends ListActivity {
	private String URLimagem;
	private String URLvideo;
	
	private String notaMedia;
	private String dtOcorrencia;
	private String hrOcorrencia;
	private String categoria;
	private String subcategoria;
	private String dtCriacao;
	private String hrCriacao;
	
	private ProgressDialog dialog;
	private Handler handler = new Handler();
	private String titulo;
	public Intent itMultimidia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent it = getIntent();
		
		if(it != null){
			URLimagem = it.getStringExtra("foto");
			URLvideo = it.getStringExtra("video");
			titulo = it.getStringExtra("titulo");
			setTitle(titulo); // muda o título da activity
			
			
			//novidade
			dtOcorrencia = it.getStringExtra("dtOcorrencia");
			hrOcorrencia = it.getStringExtra("hrOcorrencia");
			dtCriacao = it.getStringExtra("dtCriacao");
			hrCriacao = it.getStringExtra("hrCriacao");
			categoria = it.getStringExtra("categoria");
			subcategoria = it.getStringExtra("subcategoria");
			notaMedia = it.getStringExtra("notaMedia");
			
			//pega nome das categorias e subcategorias
			categoria = nomeCategoria(categoria);
			subcategoria = nomeSubcategoria(subcategoria);
			
			//Monta o menu - novidade
			String menu[];
			if(!URLimagem.equals("") && !URLvideo.equals("")){
				menu = new String[] {"Ver Foto","Assistir Vídeo","Detalhes da Colaboração"};
			}else if(!URLimagem.equals("")){
				menu = new String[] {"Ver Foto","Detalhes da Colaboração"};
			}else if(!URLvideo.equals("")){
				menu = new String[] {"Assistir Vídeo","Detalhes da Colaboração"};
			}else{
				menu = new String[] {"Detalhes da Colaboração"};
			}
			
			ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menu);
			setListAdapter(adaptador);
			
			Toast.makeText(VerDetalhesMarcador.this, " Título: "+ titulo +"\n Data ocorrência: " + dtOcorrencia + "\n Hora ocorrência: " + hrOcorrencia + "\n Data criação: " + dtCriacao +"\n Hora criação: " + hrCriacao +"\n Categoria: " + categoria + "\n Subcategoria: " + subcategoria + "\n Nota: " + notaMedia, Toast.LENGTH_LONG).show();
			
		}else{
			finish();
		}
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String op = l.getItemAtPosition(position).toString();
		
		if(op.equals("Ver Foto")){
			itMultimidia = new Intent(VerDetalhesMarcador.this,VerImagem.class);
			itMultimidia.putExtra("foto", URLimagem);
			itMultimidia.putExtra("video", "");	
			itMultimidia.putExtra("titulo", titulo);
			
			itMultimidia.putExtra("dtOcorrencia", dtOcorrencia);
			itMultimidia.putExtra("hrOcorrencia", hrOcorrencia);
			itMultimidia.putExtra("dtCriacao", dtCriacao);
			itMultimidia.putExtra("hrCriacao", hrCriacao);
			itMultimidia.putExtra("categoria", categoria);
			itMultimidia.putExtra("subcategoria", subcategoria);
			itMultimidia.putExtra("notaMedia", notaMedia);
			
			startActivity(itMultimidia);
			
		}else if(op.equals("Assistir Vídeo")){
			itMultimidia = new Intent(VerDetalhesMarcador.this,VerVideo.class);
			itMultimidia.putExtra("foto", "");
			itMultimidia.putExtra("video", URLvideo);	
			itMultimidia.putExtra("titulo", titulo);
			
			itMultimidia.putExtra("dtOcorrencia", dtOcorrencia);
			itMultimidia.putExtra("hrOcorrencia", hrOcorrencia);
			itMultimidia.putExtra("dtCriacao", dtCriacao);
			itMultimidia.putExtra("hrCriacao", hrCriacao);
			itMultimidia.putExtra("categoria", categoria);
			itMultimidia.putExtra("subcategoria", subcategoria);
			itMultimidia.putExtra("notaMedia", notaMedia);
			
			startActivity(itMultimidia);
		}else if(op.equals("Detalhes da Colaboração")){
			/*itMultimidia = new Intent(VerDetalhesMarcador.this,VerVideo.class);
			itMultimidia.putExtra("foto", "");
			itMultimidia.putExtra("video", URLvideo);	
			itMultimidia.putExtra("titulo", titulo);
			startActivity(itMultimidia);*/
		}
		
		
		/*l.getChildCount();
		switch (position) {
		case 0:
			itMultimidia = new Intent(VerDetalhesMarcador.this,VerImagem.class);
			itMultimidia.putExtra("foto", URLimagem);
			itMultimidia.putExtra("video", "");	
			itMultimidia.putExtra("titulo", titulo);
			startActivity(itMultimidia);
			break;
		case 1:
			itMultimidia = new Intent(VerDetalhesMarcador.this,VerVideo.class);
			itMultimidia.putExtra("foto", "");
			itMultimidia.putExtra("video", URLvideo);	
			itMultimidia.putExtra("titulo", titulo);
			startActivity(itMultimidia);
			break;

		default:
			break;
		}*/
	}
	
	public String nomeCategoria(String cat){
		int categoria = Integer.parseInt(cat);
		String nome = "";
		
		switch (categoria) {
		case 1:
			nome = "Desaparecidos";
			break;		
		case 2:
			nome = "Entretenimento";
			break;
		case 3:
			nome = "Infraestrutura";
			break;		
		case 4:	
			nome = "Meio Ambiente";
			break;	
		case 5:	
			nome = "Saúde";
			break;
		case 6:	
			nome = "Segurança";
			break;	
		case 7:
			nome = "Serviço ou Produto";
			break;	
		case 8:
			nome = "Outras Categorias";
			break;
		default:
			break;
		}
		
		return nome;	
	}
	
	public String nomeSubcategoria(String sub){
		int subcategoria = Integer.parseInt(sub);
		String nome = "";
		
		switch (subcategoria) {
		case 1:
			nome = "Animal desaparecido";
			break;		
		case 2:
			nome = "Pessoa desaparecida";
			break;
		case 3:
			nome = "Outro";
			break;		
		case 4:	
			nome = "Balada";
			break;	
		case 5:	
			nome = "Calourada";
			break;
		case 6:	
			nome = "Casamento";
			break;	
		case 7:
			nome = "Cinema";
			break;	
		case 8:
			nome = "Clube";
			break;
		case 9:
			nome = "Empresarial";
			break;		
		case 10:
			nome = "Exposição";
			break;
		case 11:
			nome = "Formatura";
			break;		
		case 12:	
			nome = "Religiosa";
			break;	
		case 13:	
			nome = "Show";
			break;
		case 14:	
			nome = "Teatro";
			break;	
		case 15:
			nome = "Temática";
			break;	
		case 16:
			nome = "Outro";
			break;
		case 17:
			nome = "Calçada";
			break;		
		case 18:
			nome = "Coleta de lixo";
			break;
		case 19:
			nome = "Distribuição de água";
			break;		
		case 20:	
			nome = "Faixa Pedestre";
			break;	
		case 21:	
			nome = "Iluminação urbana";
			break;
		case 22:	
			nome = "Internet";
			break;	
		case 23:
			nome = "Logradouro";
			break;	
		case 24:
			nome = "Rua/Avenida";
			break;
		case 25:
			nome = "Saneamento Básico";
			break;		
		case 27:
			nome = "Semáforo";  //que estranho pular 1 aqui
			break;
		case 28:
			nome = "Terreno Baldio";
			break;		
		case 29:	
			nome = "Outro";
			break;	
		case 30:	
			nome = "Desmatamento";
			break;
		case 31:	
			nome = "Fauna";
			break;	
		case 32:
			nome = "Flora";
			break;	
		case 33:
			nome = "Incêndio ou Queimadas";
			break;
		case 34:	
			nome = "Poluição";
			break;	
		case 35:	
			nome = "Tráfico de Animal";
			break;
		case 36:	
			nome = "Tráfico de Vegetal";
			break;	
		case 37:
			nome = "Outro";
			break;	
		case 38:
			nome = "Epidemia";
			break;
		case 39:	
			nome = "Foco de Doenças";
			break;	
		case 40:	
			nome = "Foco de Dengue";
			break;
		case 41:	
			nome = "Hospital";
			break;	
		case 42:
			nome = "Plano de Saúde";
			break;	
		case 43:
			nome = "Pronto Socorro";
			break;
		case 44:	
			nome = "Outro";
			break;	
		case 45:	
			nome = "Assalto";
			break;
		case 46:	
			nome = "Denúncia";
			break;	
		case 47:
			nome = "Furto";
			break;	
		case 48:
			nome = "Policiamento";
			break;
		case 49:	
			nome = "Ponto de Uso de Drogas";
			break;	
		case 50:	
			nome = "Roubo";
			break;
		case 51:	
			nome = "Tráfico Ilegal";
			break;	
		case 52:
			nome = "Violência Doméstica";
			break;	
		case 53:
			nome = "Outro";
			break;
		case 54:	
			nome = "Academia";
			break;	
		case 55:	
			nome = "Açougue";
			break;
		case 56:	
			nome = "Banco";
			break;	
		case 57:
			nome = "Bar";
			break;	
		case 58:
			nome = "Correio";
			break;
		case 59:	
			nome = "Empresa";
			break;	
		case 60:	
			nome = "Farmácia";
			break;
		case 61:	
			nome = "Lojas";
			break;	
		case 62:
			nome = "Mercado";
			break;	
		case 63:
			nome = "Papelaria";
			break;
		case 64:	
			nome = "Pizzaria";
			break;	
		case 65:	
			nome = "Restaurante";
			break;
		case 66:	
			nome = "Salão de Beleza";
			break;	
		case 67:
			nome = "Supermercado";
			break;	
		case 68:
			nome = "Outro";
			break;
		case 69:
			nome = "Outro Tipo";
			break;
		default:
			break;
		}
		
		return nome;	
	}

}
