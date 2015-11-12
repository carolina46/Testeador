package modelo;

import java.util.Date;

import anotaciones.Mock;
import anotaciones.MockStringAttribute;
import anotaciones.MockTodayAttribute;
import java.util.Date;


@Mock
public class Notificacion {
	
	@MockStringAttribute({"Juan", "Pedro", "Juana", "Manuela"})
	public String child;
	
	@MockStringAttribute ({"Establo-Terapia","Pista", "Hogar"})
	public String context;
	
	@MockStringAttribute ({"Predeterminada", "Emociones", "Alimentos", "Actividades y Paseos"})
	public String category;
	
	@MockTodayAttribute
	public Date sent;
	
	@MockStringAttribute ({"Alegre", "Entusiasmado", "Molesto"})
	public String pictogram;
	
	
	
	
	public String getChild() {return child;}

	public void setChild(String child) {this.child = child;}

	public String getContext() {return context;}

	public void setContext(String context) {this.context = context;}

	public String getCategory() {return category;}

	public void setCategory(String category) {this.category = category;}

	public Date getSent() {return sent;}

	public void setSent(Date sent) {this.sent = sent;}

	public String getPictogram() {return pictogram;}

	public void setPictogram(String pictogram) {this.pictogram = pictogram;}


}
