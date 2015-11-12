package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import anotaciones.Mock;
import anotaciones.MockStringAttribute;
import anotaciones.MockTodayAttribute;

public class MockGenerator {
	
	public static<T> List<T> createMockInstances(Class<T> elClass, int cant) throws InstantiationException, IllegalAccessException{ 
		List<T> list= new ArrayList<T>();
		
		if(elClass.isAnnotationPresent(Mock.class)){
			Random rand = new Random();
			
			
			for(int i=0; i<cant; i++){
				
				//CREO UNA INSTANCIA VACIA
				T aux=  elClass.newInstance();
						
				//COMPLETO LOS ATRIBUTOS DE LA INSTANCIA
				Field[] fields= elClass.getFields();
				for(Field f: fields){
					
					//SETER DEL ATRIBUTO
					String auxField = f.getName();
					auxField="set"+Character.toUpperCase(auxField.charAt(0)) + auxField.substring(1); 
					
					if(f.isAnnotationPresent(MockStringAttribute.class)){
						//POSIBLES VALORES PARA EL ATRIBUTO
						String[] values=f.getAnnotation(MockStringAttribute.class).value();
					    
						
						auxField.invoke(aux, values[rand.nextInt(values.length-1)]);
					}
					else{
						if(f.isAnnotationPresent(MockTodayAttribute.class)){
							auxField.invoke(aux, new Date());
							
						}
						
					}
				}
				list.add(aux);
			}
		 }
		
		
		
		return list;
		
		
		
		

	
	
	}
	

}
