package bean;
import org.springframework.stereotype.Component;


public class Envoltorio {
	private String nombre;
	
	public Envoltorio(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Envoltorio: " + nombre;
	}
}
