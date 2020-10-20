package pacote.ferramentas;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
 
public class UploadFile extends Activity {
    
	private static String KEY_SUCCESS = "success";
	int serverResponseCode = 0;
       
    String upLoadServerUri = "http://200.235.131.170:8008/cidadaovicosa/android_upload.php";
    
    /**********  File Path *************/
    final String uploadFilePath = Environment.getExternalStorageDirectory().getPath();
    final String uploadFileName = "temp.png";
    HttpURLConnection conn;
    DataOutputStream dos;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
    }  
    
    public ArrayList<String> reduzirImagem(String sourceFileUri, String ID, double proporcao, int qualidade){
    	ArrayList<String> lista = new ArrayList<String>();
    	String file = null;
    	
    	if(sourceFileUri == null){
    		Log.d("REDUÇÃO DA FOTO", "NÃO EXISTE FOTO");
        	return null;
    	}else{
    	
	    	try{
			    	Bitmap imagem = BitmapFactory.decodeFile(sourceFileUri);
			    	Bitmap resized = Bitmap.createScaledBitmap(imagem,(int)(imagem.getWidth()*proporcao), (int)(imagem.getHeight()*proporcao), true);
			    	
			        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
			                Environment.DIRECTORY_PICTURES), "CidadaoVicosa");
			
			        if (! mediaStorageDir.exists()){
			          if (! mediaStorageDir.mkdirs()){
			              Log.d("PASTA", "FALHA AO CRIAR PASTA");
			              return null;
			          }
			        }
			      
			        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US).format(new Date());
			
			        File mediaFile;
			      	file = "IMG"+ ID + "_" + timeStamp + ".jpg";
			        mediaFile = new File(mediaStorageDir.getPath() + File.separator + file);
			        Uri outputFileUri = Uri.fromFile(mediaFile); 
			        
			        lista.add(file);
			        lista.add(outputFileUri.getPath());
			        
			        Log.i("INFO FOTO", "NOME: " + file + " CAMINHO: " + outputFileUri);
	
			        FileOutputStream out = new FileOutputStream(mediaFile);
			        resized.compress(Bitmap.CompressFormat.JPEG, qualidade, out);
			        out.flush();
			        out.close();
			        
			        Log.d("REDUÇÃO DA FOTO", "FOTO REDUZIDA COM SUCESSO");
			        return lista;
		    	
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		Log.d("REDUÇÃO DA FOTO", "FALHA AO REDUZIR A FOTO");
	    		return null;
	    	}
    	}
    	
    }
    
    public int uploadFile(String sourceFileUri) {
          
    	  String fileName = sourceFileUri;
 
          HttpURLConnection conn = null;
          DataOutputStream dos = null;  
          String lineEnd = "\r\n";
          String twoHyphens = "--";
          String boundary = "*****";
          int bytesRead, bytesAvailable, bufferSize;
          byte[] buffer;
          int maxBufferSize = 1 * 1024 * 1024; 
          File sourceFile = new File(sourceFileUri); 
          
          if (!sourceFile.isFile()) {
        	  
	           Log.e("uploadFile", "Source File not exist :" +uploadFilePath + "" + uploadFileName);
	           
	           return 0;
           
          }
          else
          {
	           try { 
	        	   // open a URL connection to the Servlet
	               FileInputStream fileInputStream = new FileInputStream(sourceFile);
	               URL url = new URL(upLoadServerUri);
	               
	               // Open a HTTP  connection to  the URL
	               conn = (HttpURLConnection) url.openConnection(); 
	               conn.setDoInput(true); // Allow Inputs
	               conn.setDoOutput(true); // Allow Outputs
	               conn.setUseCaches(false); // Don't use a Cached Copy
	               conn.setRequestMethod("POST");
	               conn.setRequestProperty("Connection", "Keep-Alive");
	               conn.setRequestProperty("ENCTYPE", "multipart/form-data");
	               conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	               conn.setRequestProperty("uploaded_file", fileName); 
	               
	               dos = new DataOutputStream(conn.getOutputStream());
	     
	               dos.writeBytes(twoHyphens + boundary + lineEnd); 
	               dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
	            		   		+ fileName + "\"" + lineEnd);
	               
	               dos.writeBytes(lineEnd);
	     
	               // create a buffer of  maximum size
	               bytesAvailable = fileInputStream.available(); 
	     
	               bufferSize = Math.min(bytesAvailable, maxBufferSize);
	               buffer = new byte[bufferSize];
	     
	               // read file and write it into form...
	               bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
	                 
	               while (bytesRead > 0) {
	            	   
	                 dos.write(buffer, 0, bufferSize);
	                 bytesAvailable = fileInputStream.available();
	                 bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                 bytesRead = fileInputStream.read(buffer, 0, bufferSize);   
	                 
	                }
	     
	               // send multipart form data necesssary after file data...
	               dos.writeBytes(lineEnd);
	               dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	     
	               // Responses from the server (code and message)
	               serverResponseCode = conn.getResponseCode();
	               String serverResponseMessage = conn.getResponseMessage();
	                
	               Log.i("UPLOAD", "HTTP Response is : " 
	            		   + serverResponseMessage + ": " + serverResponseCode);
	               
	               if(serverResponseCode == 200){
	            	   
	                   runOnUiThread(new Runnable() {
	                        public void run() {
	                        	Log.i("UPLOAD", "Upload Completo!");
	                        }
	                    });                
	               }    
	               
	               //close the streams //
	               fileInputStream.close();
	               dos.flush();
	               dos.close();
	                
	          } catch (MalformedURLException ex) {
	        	  
	              ex.printStackTrace();
	              
	              runOnUiThread(new Runnable() {
	                  public void run() {
	                	  Log.e("Upload File", "Url mal construida");
	                  }
	              });
	              
	              Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
	          } catch (Exception e) {
	        	  
	              e.printStackTrace();
	              
	              runOnUiThread(new Runnable() {
	                  public void run() {
	                	  Log.e("Upload File", "Got Exception : see logcat ");
	                  }
	              });
	              Log.e("Upload file to server Exception", "Exception : " 
	            		                           + e.getMessage(), e);  
	          }
	          return serverResponseCode; 
	          
           } // End else block 
    }
    
    
    public int uploadFile(String sourceFileUri, String nomeArquivo, String tipo, int timeConnection, int timeRead) {
        
  	  	String fileName = sourceFileUri;

        conn = null;
        dos = null;  
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024; 
        File sourceFile = new File(sourceFileUri); 
       
        if (!sourceFile.isFile()) {
      	  
	           Log.e("uploadFile", "Source File not exist :" +uploadFilePath + "" + uploadFileName);
	           
	           return 0;
         
        }
        else
        {
	           try { 
	        	   // open a URL connection to the Servlet
	               FileInputStream fileInputStream = new FileInputStream(sourceFile);	    	             
	                 
	               URL url = new URL(upLoadServerUri);
	                 
	               System.setProperty("http.keepAlive", "false");
	               // Open a HTTP  connection to  the URL
	               conn = (HttpURLConnection) url.openConnection(); 
	               
	               //SET TIMEOUT
	               conn.setConnectTimeout(timeConnection);
	               conn.setReadTimeout(timeRead);        
	               //-----------
	               
	               conn.setDoInput(true); // Allow Inputs
	               conn.setDoOutput(true); // Allow Outputs
	               conn.setUseCaches(false); // Don't use a Cached Copy
	               conn.setRequestMethod("POST");
	               //conn.setRequestProperty("Connection", "Keep-Alive");   ///<<-----
	               conn.setRequestProperty("ENCTYPE", "multipart/form-data");
	               conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	               conn.setRequestProperty("uploaded_file", fileName); 
	               
	               conn.connect();
	               	               
	               dos = new DataOutputStream(conn.getOutputStream());
	               	     
	               dos.writeBytes(twoHyphens + boundary + lineEnd); 
	               dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
	            		   		+ fileName + "\"" + lineEnd);
	               
	               dos.writeBytes(lineEnd);
	     
	               // create a buffer of  maximum size
	               bytesAvailable = fileInputStream.available(); 
	     
	               bufferSize = Math.min(bytesAvailable, maxBufferSize);
	               buffer = new byte[bufferSize];
	     
	               // read file and write it into form...
	               bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
	                 
	               while (bytesRead > 0) {
	            	   
	                 dos.write(buffer, 0, bufferSize);
	                 bytesAvailable = fileInputStream.available();
	                 bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                 bytesRead = fileInputStream.read(buffer, 0, bufferSize);   
	                 
	                }
	     
	               // send multipart form data necesssary after file data...
	               dos.writeBytes(lineEnd);
	               dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	     
	               // Responses from the server (code and message)
	               serverResponseCode = conn.getResponseCode();
	               String serverResponseMessage = conn.getResponseMessage();
	                
	               Log.i("UPLOAD", "HTTP Response is : " 
	            		   + serverResponseMessage + ": " + serverResponseCode);
	               
	               if(serverResponseCode == 200){
	            	   
	                   runOnUiThread(new Runnable() {
	                        public void run() {
	                        	Log.i("UPLOAD", "Upload Completo!");
	                        }
	                    });                
	               }    
	               
	               //close the streams //
	               fileInputStream.close();
	               dos.flush();
	               dos.close();
	               conn.disconnect();
	                
	          } catch (MalformedURLException ex) {
	        	  
	              ex.printStackTrace();
	              
	              runOnUiThread(new Runnable() {
	                  public void run() {
	                	  Log.e("UPLOAD", "Url mal construida");
	                  }
	              });
	              
	              Log.e("UPLOAD", "error: " + ex.getMessage(), ex);  
	          } catch (Exception e) {        	  
	              e.printStackTrace();
              
	              runOnUiThread(new Runnable() {
	                  public void run() {
	                	  Log.e("UPLOAD", "Got Exception : see logcat ");
	                  }
	              });
	              Log.e("UPLOAD", "Exception : " + e.getMessage(), e);  
	              
	              //FAZER UMA CHAMADA DA DELEÇÃO NO SERVER pois pode ter dado timeout na função antes do handler cancelar a AsyncTask
		  			UserFunctions userFunction = new UserFunctions(); 
					
					JSONObject json = userFunction.AtualizarColaboracao(nomeArquivo,tipo, timeConnection, timeRead);
		    	
					try {
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){				
							Log.i("DELETAR MULTIMIDIA", tipo + " deletado com sucesso! (método uploadFile)");
						}
					}catch (Exception e1) {
						e1.printStackTrace();				
						Log.i("DELETAR MULTIMIDIA", "Serviço indisponível ao tentar apagar " + tipo + "! (método uploadFile)");
					}
	          }
	           
	          return serverResponseCode; 
	          
         } // End else block 
  }
    
}