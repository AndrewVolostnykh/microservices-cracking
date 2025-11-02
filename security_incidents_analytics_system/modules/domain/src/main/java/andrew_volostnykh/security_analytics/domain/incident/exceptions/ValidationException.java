package andrew_volostnykh.security_analytics.domain.incident.exceptions;

// TODO: add global exception handler
public class ValidationException
	extends RuntimeException {

	private String message;

	public ValidationException(String message) {
		super(message);
		this.message = message;
	}

}
