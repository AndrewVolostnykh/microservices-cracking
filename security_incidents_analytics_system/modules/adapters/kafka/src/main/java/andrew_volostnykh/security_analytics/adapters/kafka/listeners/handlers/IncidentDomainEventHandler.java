package andrew_volostnykh.security_analytics.adapters.kafka.listeners.handlers;

import andrew_volostnykh.security_analytics.domain.incident.event.IncidentEvent;

interface IncidentDomainEventHandler<T extends IncidentEvent> {

	Class<T> supports();

	void handle(T event);
}
