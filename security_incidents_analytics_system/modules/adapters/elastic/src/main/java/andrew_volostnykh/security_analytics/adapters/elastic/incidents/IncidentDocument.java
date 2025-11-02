package andrew_volostnykh.security_analytics.adapters.elastic.incidents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.time.Instant;

@Document(indexName = "incidents")
@Getter
@Setter
public class IncidentDocument {

	@Id
	private String id;

	private String reporterId;

	@Field(type = FieldType.Integer)
	private int severity;

	@Field(type = FieldType.Integer)
	private int status;

	@Field(type = FieldType.Auto)
	private GeoPoint location;

	@Field(type = FieldType.Date)
	private Instant occurredAt;

	@Field(type = FieldType.Date)
	private Instant createdAt;

	// getters/setters/constructor
}
