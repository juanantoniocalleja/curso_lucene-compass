package bean;

public abstract class HolaMundoFactory {
	public static HolaMundo createHolaMundo() {
		return new HolaMundo();
	}
}
