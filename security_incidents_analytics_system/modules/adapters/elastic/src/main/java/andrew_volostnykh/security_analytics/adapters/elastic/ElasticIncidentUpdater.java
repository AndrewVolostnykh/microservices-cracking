package andrew_volostnykh.security_analytics.adapters.elastic;

import andrew_volostnykh.security_analytics.adapters.elastic.incidents.IncidentDocument;
import andrew_volostnykh.security_analytics.adapters.elastic.incidents.IncidentElasticRepository;
import andrew_volostnykh.security_analytics.adapters.elastic.incidents.analytics.IncidentsAnalyticDocument;
import andrew_volostnykh.security_analytics.adapters.elastic.incidents.analytics.IncidentsAnalyticsElasticRepository;
import andrew_volostnykh.security_analytics.application.incident.ports.outbound.UpdateElasticOutPort;
import andrew_volostnykh.security_analytics.domain.incident.event.IncidentReportedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ElasticIncidentUpdater implements UpdateElasticOutPort {

	private final IncidentElasticRepository repository;

	@Override
	public void update(
		IncidentReportedEvent event
	) {
		IncidentDocument incidentDocument = new IncidentDocument(
			event.getId().value(),
			event.reporterId().value(),
			event.severity().getId(),
			event.status().getId(),
			new GeoPoint(event.location().latitude(), event.location().longitude()),
			event.occurredAt().value(),
			Instant.now()
		);

		repository.save(incidentDocument);
	}
}
