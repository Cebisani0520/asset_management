-- Start transaction
BEGIN;

INSERT INTO department (name, description, created_at, updated_at)
VALUES
    ('CIO', 'Chief Information Officer', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('CRO', 'Chief Risk Officer', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('RIO', 'Risk Information Officer', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- If everything is successful, commit the transaction
COMMIT;
