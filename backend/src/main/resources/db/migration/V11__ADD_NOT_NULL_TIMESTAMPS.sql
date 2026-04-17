SET search_path = project, pg_catalog;

ALTER TABLE properties ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE properties ALTER COLUMN last_updated SET NOT NULL;