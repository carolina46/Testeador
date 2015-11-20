package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Testeador {
	 
	
	public static void main(String[] args) {
		
		String notificaciones=generarJson().toString();
		while(!enviarNotificaciones(notificaciones)){
			try {
				TimeUnit.SECONDS.sleep(10);
				System.out.println("oooo");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("todo ok");
	}
	
	
	private static boolean enviarNotificaciones(String json){
	
		try {
			String url = "http://localhost:8000/get";
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");
			
			conn.setDoOutput(true);//HABILITAMOS EL ENVIO
			conn.setRequestMethod("POST");
			
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());//PARA PODER ESCRIBIR DATOS A ENVIAR
			out.write(URLEncoder.encode(json, "UTF-8")); 
	        out.close();
			
	        
	        
	        //RESPUESTA SERVIDOR
	        int status = conn.getResponseCode();
	        return status == HttpURLConnection.HTTP_OK;
	         
			
		} catch (IOException e) {

		}
		return false;
		
	}












	@SuppressWarnings("unchecked")
	public static JSONArray generarJson(){
		List<Notification> list;
		JSONArray jsons=null;
		try {
			list = MockGenerator.createMockInstances(modelo.Notification.class, 40);

			jsons = new JSONArray();
			JSONObject json;
			
			for (Notification notification : list){
				json = new JSONObject();

				json.put("child",	 notification.getChild());
				json.put("context",	 notification.getContext());
				json.put("category", notification.getCategory());
				json.put("pictogram",notification.getPictogram());
				json.put("sent",     notification.getSent().getTime());
				
				jsons.add(json);
			}
			return jsons;
			
			
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}

		return jsons;
		
		
		
		
	}
}
