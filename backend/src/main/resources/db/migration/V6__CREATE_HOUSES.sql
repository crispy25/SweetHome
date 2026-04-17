SET search_path = project, pg_catalog;

CREATE TABLE houses (
    id UUID PRIMARY KEY,

    house_type VARCHAR(20) NOT NULL CHECK (
    house_type IN ('DETACHED', 'DUPLEX', 'TRIPLEX', 'TERRACED', 'OTHER')),

    floors INT NOT NULL CHECK (floors > 0),
    land_area DECIMAL(8, 2) NOT NULL CHECK (land_area > 0.0),

    CONSTRAINT fk_house_property
    FOREIGN KEY (id) REFERENCES properties(id) ON DELETE CASCADE
);