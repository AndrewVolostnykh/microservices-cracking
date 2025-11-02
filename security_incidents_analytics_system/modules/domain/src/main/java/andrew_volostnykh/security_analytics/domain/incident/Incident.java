package andrew_volostnykh.security_analytics.domain.incident;

import andrew_volostnykh.security_analytics.domain.incident.event.Event;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentClosedEvent;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentUpdatedEvent;
import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentId;
import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentStatus;
import andrew_volostnykh.security_analytics.domain.incident.vo.Location;
import andrew_volostnykh.security_analytics.domain.incident.vo.OccurredAt;
import andrew_volostnykh.security_analytics.domain.incident.vo.ReporterId;
import andrew_volostnykh.security_analytics.domain.incident.vo.Severity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Incident {

	private final IncidentId id;
	private final ReporterId reporterId;
	private Severity severity;
	private IncidentStatus status;
	private Location location;
	private OccurredAt occurredAt;
	private final List<Event> domainEvents = new ArrayList<>();

	public static Incident rehydrate(
		IncidentId id,
		ReporterId reporterId,
		Severity severity,
		IncidentStatus status,
		Location location,
		OccurredAt occurredAt
	) {
		return
			new Incident(
				id,
				reporterId,
				severity,
				status,
				location,
				occurredAt
			);
	}

	public static Incident report(
		String reporterId,
		Severity severity,
		double latitude,
		double longitude,
		Instant occurredAt
	) {
		Incident incident = new Incident(
			IncidentId.newId(),
			new ReporterId(reporterId),
			severity,
			IncidentStatus.REPORTED,
			new Location(latitude, longitude),
			new OccurredAt(occurredAt)
		);

		incident.domainEvents.add(new IncidentReportedEvent(
			incident.getId().value(),
			reporterId,
			severity.getId(),
			latitude,
			longitude,
			occurredAt
		));

		return incident;
	}

	public void updateSeverity(Severity newSeverity) {
		if (status == IncidentStatus.CLOSED) {
			throw new IllegalStateException("Cannot update closed incident");
		}

		this.severity = newSeverity;
		this.status = IncidentStatus.UPDATED;

		domainEvents.add(new IncidentUpdatedEvent(id, newSeverity));
	}

	public void close() {
		if (status == IncidentStatus.CLOSED) {
			throw new IllegalStateException("Incident already closed");
		}

		this.status = IncidentStatus.CLOSED;
		domainEvents.add(new IncidentClosedEvent(id));
	}

	public List<Event> pullDomainEvents() {
		List<Event> copy = new ArrayList<>(domainEvents);
		domainEvents.clear();
		return copy;
	}
}
