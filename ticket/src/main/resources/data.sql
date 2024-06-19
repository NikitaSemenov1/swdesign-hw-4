-- to make data static across different boots the values are explicit

DELETE FROM tickets;
DELETE FROM stations;

INSERT INTO stations(id, name) VALUES('82a32d32-cce7-4d31-8c8e-669e845ea29b', 'station A');
INSERT INTO stations(id, name) VALUES('0ee6522e-f2a8-42e8-ae63-837e0a16ec30', 'station B');

INSERT INTO
    tickets(id, status, user_id, from_station_id, to_station_id)
    VALUES('71e11470-ce53-4e65-ac83-612f101e1ea2', -- id
           'SUCCESS',  -- status
           '3dd2e99d-cb11-46f0-9655-72602d8c7d14', -- user_id
           '82a32d32-cce7-4d31-8c8e-669e845ea29b', -- from_station_id
           '0ee6522e-f2a8-42e8-ae63-837e0a16ec30' -- to_station_id
    );