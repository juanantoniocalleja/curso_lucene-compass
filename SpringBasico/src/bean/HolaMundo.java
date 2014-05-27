package bean;

public class HolaMundo implements IHolaMundo {
	private Envoltorio envoltorio;
	
	@Override
	public void saludar() {
		System.out.println("Hola " + envoltorio);
	}
	
	public Envoltorio getEnvoltorio() {
		return envoltorio;
	}

	public void setEnvoltorio(Envoltorio envoltorio) {
		this.envoltorio = envoltorio;
	}
		
}
