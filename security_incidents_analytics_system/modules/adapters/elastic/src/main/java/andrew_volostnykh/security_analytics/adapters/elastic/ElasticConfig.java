package andrew_volostnykh.security_analytics.adapters.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
	basePackages = "andrew_volostnykh.security_analytics.adapters.elastic"
)
public class ElasticConfig {

	@Bean
	public ElasticsearchClient elasticClient(RestClientBuilder builder) {
		RestClient restClient = builder.build();
		ElasticsearchTransport transport = new RestClientTransport(
			restClient, new JacksonJsonpMapper()
		);
		return new ElasticsearchClient(transport);
	}

}
