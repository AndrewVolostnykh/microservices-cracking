package andrew_volostnykh.security_analytics.application.incident.usecases;

import andrew_volostnykh.security_analytics.application.incident.ports.inbound.IncidentReadModelUpdateInPort;
import andrew_volostnykh.security_analytics.application.incident.ports.outbound.UpdateElasticOutPort;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentReadModelUpdateUseCase
	implements IncidentReadModelUpdateInPort {

	private final UpdateElasticOutPort elasticPort;

	@Override
	public void handle(IncidentReportedEvent event) {

		elasticPort.update(event);
	}
}
