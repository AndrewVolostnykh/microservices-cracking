package andrew_volostnykh.security_analytics.adapters.kafka.listeners.handlers;

import andrew_volostnykh.security_analytics.domain.incident.event.IncidentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class IncidentDomainEventHandlerDispatcher {

	private final List<IncidentDomainEventHandler<IncidentEvent>> handlers;

	public void dispatch(IncidentEvent event) {
		handlers.stream()
			.filter(h -> h.supports().equals(event.getClass()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(
				"No handler for event type: " + event.getClass()))
			.handle(event);
	}
}
