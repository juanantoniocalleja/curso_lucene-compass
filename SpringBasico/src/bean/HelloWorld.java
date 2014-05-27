package bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component(value="holaMundo")
public class HelloWorld implements IHolaMundo {
	@Autowired
	private Envoltorio envoltorio;
	
	@Override
	public void saludar() {
		System.out.println("Hello " + envoltorio);
	}

	public Envoltorio getEnvoltorio() {
		return envoltorio;
	}

	public void setEnvoltorio(Envoltorio envoltorio) {
		this.envoltorio = envoltorio;
	}
	
	
}
