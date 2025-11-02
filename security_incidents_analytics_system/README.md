## ✅ **Задача: Побудувати систему пошуку і аналітики інцидентів безпеки**

### **Контекст**

У тебе є сервіс (наприклад, у твоєму Reloven / Danger Map), який логує "інциденти". Це можуть бути:

* події кібербезпеки (login, brute force, suspicious activity)
* або, ближче до твоєї теми, реальні **небезпечні події в місті**

Ти повинен:

1. **Зібрати події**
2. **Зберігати їх в Elastic**
3. **Зробити пошук (фул-текст, гео, фільтри)**
4. **Реалізувати агрегації та аналітику**
5. **Побудувати дашборд**
6. **Оптимізувати індекси**
7. **Додати пайплайни, alias, lifecycle**

---

## 📌 **Вимоги**

### ### 1) **Структура події**

Приклад документа, який ти індексуєш:

```json
{
  "incident_id": "UUID",
  "timestamp": "2025-01-01T13:55:21Z",
  "location": {
    "lat": 50.4501,
    "lon": 30.5234
  },
  "category": "robbery", 
  "severity": "high",
  "description": "Two armed suspects robbed a pharmacy.",
  "metadata": {
    "source": "mobile",
    "reporter_id": "user-123",
    "tags": ["weapon", "masked"]
  }
}
```

Створити **custom mapping**:

* geo_point
* keyword vs text поля
* date
* nested тегів

---

### 2) **Основні операції**

Реалізуй REST-ендпоінти:

* ✅ add incident
* ✅ batch import (bulk API)
* ✅ search (full-text + filters)
* ✅ search by radius (geo_distance)
* ✅ time range query (last X hours)
* ✅ autocomplete по категорії/місту (ES suggesters)

---

### 3) **Запити, які треба підтримати**

| Функція          | Опис                               |
| ---------------- | ---------------------------------- |
| Full-text search | по `description` + fuzzy           |
| Filters          | category, severity                 |
| Geo              | incidents within 2km from point    |
| Time             | incidents за останні 24h           |
| Analytics        | count per category                 |
| Heatmap          | агрегація інцидентів по гео-сітці  |
| Top keywords     | terms aggregation по metadata.tags |
| Trend chart      | date histogram (за годинами)       |

Особливо важливо: **aggs + filters + sort + pagination**.

---

### 4) **Агрегації, які зробиш**

* **Top categories**
* **Incidents per hour**
* **Heatmap grid (geotile_grid aggregation)**
* **Top tags**
* **Severity distribution**

---

### 5) **Performance & Scaling**

Реалізувати:

* alias для rollover індексів (`incidents-*`)
* ILM policy (30 days -> warm -> cold)
* refresh interval tuning
* routing по місту/району (опц.)

---

### 6) **Security & Observability**

* index template versioning
* structured logs → ingest pipeline (normalize text, parse tags)
* error handling & ES retries
* dashboards in Kibana (or Grafana)

---

## 🎯 **Фінальний результат**

Ти маєш отримати міні-продукт:

| Компонент                                | Done? |
| ---------------------------------------- | ----- |
| REST API                                 | ✅     |
| ES schema + ILM                          | ✅     |
| Search UI (можна Swagger/Insomnia/React) | ✅     |
| Kibana dashboard                         | ✅     |
| Load test (10M docs)                     | ✅     |

---

## 💡 **Що ти вивчиш**

| Концепт ES         | Навички                     |
| ------------------ | --------------------------- |
| Inverted index     | Full-text search            |
| Mappings           | Тюнінг індексації           |
| Bulk API           | Массові дані                |
| Geo search         | Розширені типи              |
| Suggesters         | Автокомпліт                 |
| Aggregations       | Аналітика / BI              |
| ILM/Rollover/Alias | Production архітектура      |
| Ingest pipelines   | ETL в ES                    |
| Scaling            | Sharding, routing, replicas |

---

## 🚀 Optional Advanced

* Logstash ingest from Kafka
* ML anomaly detection (Kibana ML)
* geo_shape для периметрів
* alerts & watchers

---

Хочеш — можу:

📦 Дати **готовий skeleton проекту** (Spring / Node.js)
📑 Дати чек-лист запитів
🧠 Дати "правильні" індекс-налаштування як в продакшені
🧪 Дати сценарій навантажувального тесту (`k6`)

Сказати яку мову та стек обрати для реалізації?

Супер, давай розширимо задачу, щоб ти тренанув **Elastic + CQRS + Redis** як у серйозних high-load проєктах.

Ми залишаємо попередню “систему інцидентів безпеки”, але додаємо **мінімальний, реалістичний, бойовий флоу**:

---

## ✅ **Додатковий функціонал: Реакції користувачів на інциденти**

Користувач може на інцидент:

* ✅ поставити “like” (підтверджую, це правда)
* ✅ “report false” (можливо фейк)
* ✅ додати коментар

### Команди (write side)

* `ConfirmIncident(incidentId, userId)`
* `ReportIncidentAsFalse(incidentId, userId)`
* `AddComment(incidentId, userId, text)`

### Події (event sourcing flavor)

* `IncidentConfirmedV1`
* `IncidentReportedFalseV1`
* `IncidentCommentAddedV1`

---

## 🧠 **CQRS: Мінімальний дизайн**

### **Write Model**

* Postgres (або EventStore / Kafka)
* Івенти зберігаємо (щоб потренувати event log)
* Команди валідять логіку (не більше одного голосу на юзера і т.д.)

### **Read Model**

* ElasticSearch: зберігає інцидент + counters + latest comments

### **Redis роль**

1. ✅ **Read Optimisation**: кеш топ-інцидентів + список останніх
2. ✅ **Rate limiting**: 1 реакція на 5 секунд, 5 коментарів / 1 хв.
3. ✅ **Debounce**/throttle оновлення ES (batched apply)
4. ✅ **User recent feed** (Redis Sorted Set)

---

## 🏗 **Архітектура**

```
[Client]
   |
   | Commands (REST/gRPC)
   v
[Command Service]
   |
   | Publish events
   v
[Event Bus / log] --- optional Kafka, or just DB table "events"
   |
   | Subscriptions
   v
[Read Model Updater]
   |--> Redis (cache counts, latest comments)
   |--> Elastic (sync model periodically / on trigger)
```

---

## 🧩 **Які задачі зробити**

### ✅ **Write**

* Команди з валідацією
* Події тригерять апдейти read-model

### ✅ **Read**

* Endpoint: `GET /incidents/:id` (спочатку Redis, fallback ES)
* Endpoint: `GET /incidents/top` (Redis Sorted Set)
* Endpoint: `GET /incidents/recent` (Redis List)

### ✅ **Sync logic**

* Подія прийшла → поклали в Redis counters
* Раз у N секунд → flush в ES bulk update

### ✅ **Синхронізація через тригер**

Відразу в ES оновлюємо:

* тільки критичні (утвердив / фейк)
* коментарі — асинхронно батчами

---

## 📦 **Мінімум таблиць / індексів**

### PostgreSQL / Event Store

`events`:

| field      | type      |
| ---------- | --------- |
| id         | uuid      |
| type       | text      |
| payload    | jsonb     |
| created_at | timestamp |

### Redis

| Key                          | Type    | Purpose                |
| ---------------------------- | ------- | ---------------------- |
| `incident:{id}:likes`        | integer | лічильник підтверджень |
| `incident:{id}:falseReports` | integer | лічильник скарг        |
| `incident:{id}:comments`     | list    | останні 50             |
| `top_incidents`              | zset    | рейтинг інцидентів     |
| `recent_incidents`           | list    | фід нових              |

---

## 🧠 **Що ти відпрацюєш**

| Технологія             | Практика                     |
| ---------------------- | ---------------------------- |
| CQRS                   | команди/події, окремі моделі |
| Redis                  | кеш, rate limits, ZSET feed  |
| Elastic                | async read model + агрегації |
| Event sourcing         | зберігання event log         |
| Bulk updates           | продакшн патерн              |
| Feed system            | топ і останні інциденти      |
| Anti-spam              | redis throttling             |
| Eventually consistency | реальні кейси                |

---

## 🧪 **Тест кейси**

| Сценарій                     | Очікування                        |
| ---------------------------- | --------------------------------- |
| 100 лайків за 1 секунду      | Redis absorbs, ES sync later      |
| Юзер спамить коментарі       | Redis rate limit                  |
| Запит `/incident/:id`        | Redis → ES fallback               |
| Краш сервісу                 | ES sync продовжився після restart |
| UI бачить коментар не одразу | eventual consistency              |

---

Далі:

✅ Додати outbox патерн
✅ Додати retry + DLQ для Kafka
✅ Додати idempotency key в Redis
✅ Додати bulk sync in ES
✅ Вивести через Swagger UI / Grafana dashboard