package andrew_volostnykh.security_analytics.domain.incident.exceptions;

public class ProcessingException extends RuntimeException {

	public ProcessingException(String message, Throwable cause) {
		super(message);
	}
}
