package andrew_volostnykh.security_analytics.adapters.elastic.incidents.analytics;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentsAnalyticsElasticRepository
	extends ElasticsearchRepository<IncidentsAnalyticDocument, String> {

}
