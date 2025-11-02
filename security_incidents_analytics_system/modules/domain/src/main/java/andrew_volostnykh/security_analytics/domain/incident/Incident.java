package andrew_volostnykh.security_analytics.domain.incident;

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
	private final List<Object> domainEvents;

	private Incident(
		IncidentId id,
		ReporterId reporterId,
		Severity severity
	) {
		this.id = id;
		this.reporterId = reporterId;
		this.severity = severity;
		this.status = IncidentStatus.REPORTED;
		this.domainEvents = new ArrayList<>();

		domainEvents.add(new IncidentReportedEvent(id, reporterId, severity));
	}

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
				occurredAt,
				new ArrayList<>()
			);
	}

	public static Incident report(
		ReporterId reporterId,
		Severity severity
	) {
		return new Incident(IncidentId.newId(), reporterId, severity);
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

	public List<Object> pullDomainEvents() {
		var copy = new ArrayList<>(domainEvents);
		domainEvents.clear();
		return copy;
	}
}
