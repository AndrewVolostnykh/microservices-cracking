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
	public void incrementFalseReports(String incidentId) {
		// TODO: refactor to keyBuilder
		redis.opsForValue()
			.increment(
				"incident:" + incidentId + ":falseReports"
			);
	}
}