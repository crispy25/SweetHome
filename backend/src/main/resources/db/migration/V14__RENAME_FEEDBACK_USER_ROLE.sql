SET search_path = project, pg_catalog;

ALTER TABLE feedback RENAME COLUMN user_role TO user_type;