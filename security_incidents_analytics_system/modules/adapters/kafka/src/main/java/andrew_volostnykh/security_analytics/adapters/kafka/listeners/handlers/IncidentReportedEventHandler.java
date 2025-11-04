package andrew_volostnykh.security_analytics.adapters.kafka.listeners.handlers;

import andrew_volostnykh.security_analytics.application.incident.ports.inbound.IncidentReadModelUpdateInPort;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class IncidentReportedEventHandler
	implements IncidentDomainEventHandler<IncidentReportedEvent> {

	private final IncidentReadModelUpdateInPort readModelUpdateInPort;

	@Override
	public Class<IncidentReportedEvent> supports() {
		return IncidentReportedEvent.class;
	}

	@Override
	public void handle(IncidentReportedEvent event) {
		readModelUpdateInPort.handle(
			event
		);
	}
}
