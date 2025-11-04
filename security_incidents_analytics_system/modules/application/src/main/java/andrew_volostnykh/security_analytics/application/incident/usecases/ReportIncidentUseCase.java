package andrew_volostnykh.security_analytics.application.incident.usecases;

import andrew_volostnykh.security_analytics.application.incident.commands.ReportIncidentCommand;
import andrew_volostnykh.security_analytics.application.incident.ports.inbound.ReportIncidentInPort;
import andrew_volostnykh.security_analytics.application.incident.ports.outbound.IncidentEventPublisherOutPort;
import andrew_volostnykh.security_analytics.application.incident.ports.outbound.IncidentPersistenceWriteOutPort;
import andrew_volostnykh.security_analytics.domain.incident.Incident;
import andrew_volostnykh.security_analytics.domain.incident.vo.Severity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportIncidentUseCase
	implements ReportIncidentInPort {

	private final IncidentPersistenceWriteOutPort writePort;
	private final IncidentEventPublisherOutPort publisherPort;

	public Incident getById(String id) {
		return
			writePort.findById(id)
				.orElse(null);
	}

	public void reportIncident(ReportIncidentCommand command) {
		Incident incident = Incident.report(
			command.reporterId(),
			Severity.getById(command.severity()),
			command.latitude(),
			command.longitude(),
			command.description(),
			command.occurredAt()
		);

		writePort.save(incident);

		incident.getDomainEvents()
			.forEach(
				publisherPort::publish
		);
	}
}
