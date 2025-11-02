package andrew_volostnykh.security_analytics.adapters.kafka.publishers;

import andrew_volostnykh.security_analytics.application.incident.ports.outbound.IncidentEventPublisherOutPort;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;
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
	public void publish(IncidentReportedEvent event) {
		try {
			String payload = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(
				"incidents.events",
				event.incidentId(),
				payload
			);

			// TODO: replace with local exception
		} catch (Exception ex) {
			throw new RuntimeException("Failed to publish Kafka event", ex);
		}
	}
}
