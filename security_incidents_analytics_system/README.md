### Security Incidents Analytics System

- Навчальний проєкт для практичної реалізації патернів мікросервісів і архітектурних.
- Поглиблена практика в Elastic Search, Redis, Kafka
- Реалізація відмовостійкості
- Використання практик тестування chaos monkey, stress testing, mutation testing

> [Постановка задачі](./TASK_IDEA.md)

### Infra:
Redis, Elastic, Postgres, Kafka
k8s

### Patterns:
Event-Driven CQRS, Eventually consistency, outbox,
Feed system, antispam

### Arch:
DDD, Hexagonal

## 🏗 **Архітектура**

DDD, Hexagonal

```
[Client]
   |
   | Commands (REST/gRPC)
   v
[Command Service]
   |
   | Publish events
   v
[Event Bus / log] --- kafka
   |
   | Subscriptions
   v
[Read Model Updater]
   |--> Redis (rate limits, latest comments)
   |--> Elastic (sync model periodically / on trigger)
```

---

### Testing
- mutation testing
- chaos monkey

### TODO:

- Додати outbox патерн
- Додати retry + DLQ для Kafka
- Додати idempotency key в Redis
- Додати bulk sync in ES
- Вивести через Swagger UI / Grafana dashboard