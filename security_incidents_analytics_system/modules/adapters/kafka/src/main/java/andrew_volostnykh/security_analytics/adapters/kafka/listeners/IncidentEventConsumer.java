package andrew_volostnykh.security_analytics.adapters.kafka.listeners;

import andrew_volostnykh.security_analytics.adapters.kafka.listeners.handlers.IncidentDomainEventHandlerDispatcher;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentEvent;
import andrew_volostnykh.security_analytics.domain.incident.exceptions.ProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentEventConsumer {

	private final ObjectMapper objectMapper;
	private final IncidentDomainEventHandlerDispatcher eventHandlerDispatcher;

	@KafkaListener(topics = "incidents.events", groupId = "incident-read-model")
	public void onMessage(String message) {
		try {
			eventHandlerDispatcher.dispatch(
				objectMapper.readValue(message, IncidentEvent.class)
			);
		} catch (Exception ex) {
			throw new ProcessingException("Failed to process event", ex);
		}
	}
}