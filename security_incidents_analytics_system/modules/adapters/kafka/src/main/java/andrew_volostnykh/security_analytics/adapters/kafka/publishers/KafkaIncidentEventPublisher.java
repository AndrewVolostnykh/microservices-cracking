package andrew_volostnykh.security_analytics.adapters.kafka.publishers;

import andrew_volostnykh.security_analytics.application.incident.ports.outbound.IncidentEventPublisherOutPort;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentEvent;
import andrew_volostnykh.security_analytics.domain.incident.exceptions.ProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaIncidentEventPublisher
	implements IncidentEventPublisherOutPort {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	@Override
	public void publish(IncidentEvent event) {
		try {
			String payload = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(
				"incidents.events",
				event.getId().value(),
				payload
			);

			// TODO: replace with local exception
			// TODO: dlq
		} catch (Exception ex) {
			throw new ProcessingException("Failed to publish Kafka event", ex);
		}
	}
}
