CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS stations (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    status VARCHAR(10) NOT NULL,
    user_id UUID NOT NULL,
    from_station_id UUID NOT NULL,
    to_station_id UUID NOT NULL,
    created TIMESTAMP DEFAULT NOW() NOT NULL,
    CONSTRAINT fk_from_station FOREIGN KEY (from_station_id) REFERENCES stations(id) ON DELETE CASCADE,
    CONSTRAINT fk_to_station FOREIGN KEY (to_station_id) REFERENCES stations(id) ON DELETE CASCADE
);