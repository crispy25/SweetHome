SET search_path = project, pg_catalog;

ALTER TABLE apartment_listing
ALTER COLUMN layout TYPE VARCHAR(20)
USING layout::text;

ALTER TABLE house_listing
ALTER COLUMN house_type TYPE VARCHAR(20)
USING house_type::text;

ALTER TABLE apartment_listing
ADD CONSTRAINT chk_apartment_layout
CHECK (layout IN ('DETACHED', 'SEMI_DETACHED', 'NON_DETACHED', 'CIRCULAR'));

ALTER TABLE house_listing
ADD CONSTRAINT chk_house_type
CHECK (house_type IN ('DETACHED', 'DUPLEX', 'TRIPLEX', 'TERRACED', 'OTHER'));

DROP TYPE apartment_layout;
DROP TYPE house_type;