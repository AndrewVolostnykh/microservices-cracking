CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE incidents (
                           id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           reporter_id     UUID NOT NULL,

                           severity        INT NOT NULL,
                           status          INT NOT NULL,

                           latitude        DOUBLE PRECISION NOT NULL,
                           longitude       DOUBLE PRECISION NOT NULL,

                           occurred_at     TIMESTAMP WITH TIME ZONE NOT NULL,

                           created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
                           updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_incidents_reporter_id ON incidents (reporter_id);
CREATE INDEX idx_incidents_occurred_at ON incidents (occurred_at);
CREATE INDEX idx_incidents_location     ON incidents (latitude, longitude);
