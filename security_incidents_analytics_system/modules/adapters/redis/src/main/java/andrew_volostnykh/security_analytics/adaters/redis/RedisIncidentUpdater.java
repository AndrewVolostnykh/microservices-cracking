package andrew_volostnykh.security_analytics.adaters.redis;

import andrew_volostnykh.security_analytics.application.incident.ports.outbound.UpdateRedisOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisIncidentUpdater
	implements UpdateRedisOutPort {

	private final StringRedisTemplate redis;

	@Override
	public Long incrementFalseReports(String incidentId) {
		return redis.opsForValue()
			.increment(
				IncidentFalseReportsKey.build(incidentId)
			);
	}
}