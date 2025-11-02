package andrew_volostnykh.security_analytics.adapters.postgres.converters;

import andrew_volostnykh.security_analytics.domain.incident.vo.IncidentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IncidentStatusEnumConverter implements AttributeConverter<IncidentStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(IncidentStatus attribute) {
		return attribute == null ? null : attribute.getId();
	}

	@Override
	public IncidentStatus convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : IncidentStatus.getById(dbData);
	}
}
