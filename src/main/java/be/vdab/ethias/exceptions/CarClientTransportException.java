package be.vdab.ethias.exceptions;

public class CarClientTransportException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CarClientTransportException() {
		super();
	}
	
	public CarClientTransportException(String message) {
		super(message);
	}
	
}
