package modelo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Testeador {
	 
	
	public static void main(String[] args) {
		
		String notificaciones=generarJson().toString();
		while(!enviarNotificaciones(notificaciones)){
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
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
			out.write(json);
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
		JSONArray jArray = new JSONArray();
		try {
			list = MockGenerator.createMockInstances(modelo.Notification.class, 40);
			JSONObject json;
			
			for (Notification notification : list){
				json = new JSONObject();
				json.put("child",	 notification.getChild());
				json.put("context",	 notification.getContext());
				json.put("category", notification.getCategory());
				json.put("pictogram",notification.getPictogram());
				json.put("sent",     notification.getSent().getTime());
				jArray.add(json);
			}
			
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}

		return jArray;
	}
}
