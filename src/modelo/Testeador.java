package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Testeador {
	 
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Notification> list;
		try {
			list = MockGenerator.createMockInstances(modelo.Notification.class, 5);

			JSONArray jsons = new JSONArray();
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
			
			System.out.println(jsons);
			
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
