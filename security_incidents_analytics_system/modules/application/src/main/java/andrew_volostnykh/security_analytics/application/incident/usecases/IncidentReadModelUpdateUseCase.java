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

	@Override
	public void handle(IncidentReportedEvent event) {

		redisPort.incrementFalseReports(event.incidentId());

		elasticPort.scheduleUpdate(event.incidentId());
	}
}
