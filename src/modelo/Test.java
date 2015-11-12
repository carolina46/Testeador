package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		try {
			List<Notification> list = MockGenerator.createMockInstances(modelo.Notification.class, 5);
			for(Notification n: list){
				System.out.println(n);
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
