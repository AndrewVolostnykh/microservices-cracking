package andrew_volostnykh.security_analytics.adapters.postgres.converters;

import andrew_volostnykh.security_analytics.domain.incident.vo.Severity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SeverityEnumConverter implements AttributeConverter<Severity, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Severity attribute) {
		return attribute == null ? null : attribute.getId();
	}

	@Override
	public Severity convertToEntityAttribute(Integer dbData) {
		return dbData == null ? null : Severity.getById(dbData);
	}
}
