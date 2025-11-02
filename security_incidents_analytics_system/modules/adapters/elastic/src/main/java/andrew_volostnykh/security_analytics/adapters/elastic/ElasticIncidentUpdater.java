package andrew_volostnykh.security_analytics.adapters.elastic;

import andrew_volostnykh.security_analytics.application.incident.ports.outbound.UpdateElasticOutPort;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@RequiredArgsConstructor
public class ElasticIncidentUpdater implements UpdateElasticOutPort {

	private final ElasticsearchClient esClient;
	private final Queue<String> pending = new ConcurrentLinkedQueue<>();

	@Override
	public void scheduleUpdate(String incidentId) {
		pending.add(incidentId);
	}

	@Scheduled(fixedRate = 2000)
	public void flush() {
		if (pending.isEmpty()) {
			return;
		}

		BulkRequest.Builder bulk = new BulkRequest.Builder();
		String id;

		while ((id = pending.poll()) != null) {
			String finalId = id;
			bulk.operations(op -> op
				.update(u -> u
					.index("incidents")
					.id(finalId)
					.script(Script.of(s -> s
						.source("ctx._source.false_reports_count = (ctx._source.false_reports_count != null ? ctx._source.false_reports_count : 0) + 1")
					))
					.upsert(Map.of("false_reports_count", 1))
				)
			);
		}

		try {
			esClient.bulk(bulk.build());
		} catch (Exception e) {
			throw new RuntimeException("Failed ES bulk update", e);
		}
	}
}
