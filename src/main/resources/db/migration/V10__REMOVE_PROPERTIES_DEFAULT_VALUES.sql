SET search_path = project, pg_catalog;

ALTER TABLE properties ALTER COLUMN id DROP DEFAULT;
ALTER TABLE properties ALTER COLUMN created_at DROP DEFAULT;
ALTER TABLE properties ALTER COLUMN last_updated DROP DEFAULT;