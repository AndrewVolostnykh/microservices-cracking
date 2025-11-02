package andrew_volostnykh.security_analytics.adapters.elastic.incidents;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface IncidentElasticRepository
	extends ElasticsearchRepository<IncidentDocument, String> {

	List<IncidentDocument> findBySeverity(int severity);

	List<IncidentDocument> findByLocationWithin(
		GeoPoint point,
		String distance
	);
}
