package andrew_volostnykh.security_analytics.adapters.elastic;

import andrew_volostnykh.security_analytics.adapters.elastic.incidents.analytics.IncidentsAnalyticDocument;
import andrew_volostnykh.security_analytics.adapters.elastic.incidents.analytics.IncidentsAnalyticsElasticRepository;
import andrew_volostnykh.security_analytics.application.incident.ports.outbound.UpdateElasticOutPort;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@RequiredArgsConstructor
public class ElasticIncidentUpdater implements UpdateElasticOutPort {

	private final ElasticsearchClient esClient;
	private final Queue<String> pending = new ConcurrentLinkedQueue<>();
	private final IncidentsAnalyticsElasticRepository repository;

	@Override
	public void update(
		String incidentId,
		Long falseReportsCount
	) {
		IncidentsAnalyticDocument doc = repository.findById(incidentId)
			.orElse(
				new IncidentsAnalyticDocument(
					incidentId,
					0L,
					0,
					Instant.now()
				)
			);

		doc.setFalseReportsCount(falseReportsCount);
		doc.setLastUpdateAt(Instant.now());

		repository.save(doc);
	}
}
