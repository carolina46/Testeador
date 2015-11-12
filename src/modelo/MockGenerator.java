package modelo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import anotaciones.Mock;
import anotaciones.MockStringAttribute;
import anotaciones.MockTodayAttribute;

public class MockGenerator {
	
	public static<T> List<T> createMockInstances(Class<T> aClass, int cant) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{ 
		List<T> list= new ArrayList<T>();

		if(aClass.isAnnotationPresent(Mock.class)){
			Random rand = new Random();
			
			for(int i=0; i<cant; i++){
				
				//CREO UNA INSTANCIA VACIA
				T aux = aClass.newInstance();
						
				//COMPLETO LOS ATRIBUTOS DE LA INSTANCIA
				Field[] fields = aClass.getFields();
				for(Field f: fields){
					//SETTER DEL ATRIBUTO
					String auxField = f.getName();
					auxField = "set"+Character.toUpperCase(auxField.charAt(0)) + auxField.substring(1);
					
					if(f.isAnnotationPresent(MockStringAttribute.class)){
						//POSIBLES VALORES PARA EL ATRIBUTO
						String[] values = f.getAnnotation(MockStringAttribute.class).value();
						Method method = aux.getClass().getMethod(auxField, String.class);
						method.invoke(aux, values[rand.nextInt(values.length)]);
					}
					else{
						if(f.isAnnotationPresent(MockTodayAttribute.class)){
							Method method = aux.getClass().getMethod(auxField, Date.class);
							method.invoke(aux, new Date());
						}	
					}
				}
				list.add(aux);
			}
		 }
		return list;
	}
}
