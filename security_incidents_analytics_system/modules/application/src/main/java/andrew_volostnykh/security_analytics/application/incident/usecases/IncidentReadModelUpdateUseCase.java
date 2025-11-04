package andrew_volostnykh.security_analytics.application.incident.usecases;

import andrew_volostnykh.security_analytics.application.incident.ports.inbound.IncidentReadModelUpdateInPort;
import andrew_volostnykh.security_analytics.application.incident.ports.outbound.UpdateElasticOutPort;
import andrew_volostnykh.security_analytics.application.incident.ports.outbound.UpdateRedisOutPort;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentReadModelUpdateUseCase
	implements IncidentReadModelUpdateInPort {

	private final UpdateRedisOutPort redisPort;
	private final UpdateElasticOutPort elasticPort;

	// TODO: replace with false report event
	@Override
	public void handle(IncidentReportedEvent event) {

		Long falseReportsCount = redisPort.incrementFalseReports(event.incidentId());

		elasticPort.update(event.incidentId(), falseReportsCount);
	}
}
