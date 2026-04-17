SET search_path = project, pg_catalog;

CREATE TABLE properties (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    title VARCHAR(128) NOT NULL,
    description TEXT,
    address VARCHAR(255) NOT NULL,

    rooms INT NOT NULL CHECK (rooms > 0),
    bathrooms INT NOT NULL CHECK (bathrooms >= 0),
    usable_area DECIMAL(6, 2) NOT NULL CHECK (usable_area > 0.0),
    construction_year INT NOT NULL CHECK (construction_year BETWEEN 1500 AND 2100),
    price DECIMAL(9, 2) NOT NULL CHECK (price > 0.0),

    seller_id UUID NOT NULL,
    property_type VARCHAR(20) NOT NULL CHECK (property_type IN ('APARTMENT', 'HOUSE')),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_property_seller FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE
);