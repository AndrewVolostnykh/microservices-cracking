package andrew_volostnykh.security_analytics.adapters.elastic.incidents.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Document(indexName = IncidentsAnalyticDocument.INCIDENTS_ANALYTICS_INDEX)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncidentsAnalyticDocument {

	public static final String INCIDENTS_ANALYTICS_INDEX = "incidents-analytics";

	@Id
	private String id;

	@Field(type = FieldType.Integer)
	private Long falseReportsCount;

	@Field(type = FieldType.Integer)
	private int likesCount;

	@Field(type = FieldType.Date)
	private Instant lastUpdateAt;
}
