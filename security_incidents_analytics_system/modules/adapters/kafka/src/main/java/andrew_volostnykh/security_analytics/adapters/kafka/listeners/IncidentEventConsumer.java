package andrew_volostnykh.security_analytics.adapters.kafka.listeners;

import andrew_volostnykh.security_analytics.application.incident.ports.inbound.IncidentReadModelUpdateInPort;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentEventConsumer {

	private final ObjectMapper objectMapper;
	private final IncidentReadModelUpdateInPort readModelUpdateInPort;

	@KafkaListener(topics = "incidents.events", groupId = "incident-read-model")
	public void onMessage(String message) {
		try {
			IncidentReportedEvent event =
				objectMapper.readValue(message, IncidentReportedEvent.class);

			readModelUpdateInPort.handle(event);

		} catch (Exception ex) {
			throw new RuntimeException("Failed to process event", ex);
		}
	}
}