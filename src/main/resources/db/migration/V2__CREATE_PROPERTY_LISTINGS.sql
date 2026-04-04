SET search_path = project, pg_catalog;

CREATE TYPE apartment_layout AS ENUM (
    'Detached',
    'Semi-detached',
    'Non-detached',
    'Circular'
);

CREATE TYPE house_type AS ENUM (
    'Detached',
    'Duplex',
    'Triplex',
    'Terraced',
    'Other'
);

CREATE TABLE apartment_listing (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    title VARCHAR(255) NOT NULL,
    description TEXT,
    address VARCHAR(255) NOT NULL,
    rooms INT NOT NULL CHECK (rooms > 0),
    bathrooms INT NOT NULL CHECK (bathrooms >= 0),
    usable_area DECIMAL(6, 2) NOT NULL CHECK (usable_area > 0.0),
    construction_year INT NOT NULL CHECK (construction_year BETWEEN 1500 AND 2100),
    price DECIMAL(9, 2) NOT NULL CHECK (price > 0.0),
    seller_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    layout project.apartment_layout NOT NULL,
    comfort INT NOT NULL CHECK (comfort BETWEEN 1 AND 4),
    floor INT NOT NULL,

    CONSTRAINT fk_apartment_seller FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE house_listing (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    title VARCHAR(255) NOT NULL,
    description TEXT,
    address VARCHAR(255) NOT NULL,
    rooms INT NOT NULL CHECK (rooms > 0),
    bathrooms INT NOT NULL CHECK (bathrooms >= 0),
    usable_area DECIMAL(6, 2) NOT NULL CHECK (usable_area > 0.0),
    construction_year INT NOT NULL CHECK (construction_year BETWEEN 1500 AND 2100),
    price DECIMAL(9, 2) NOT NULL CHECK (price > 0.0),
    seller_id UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    house_type project.house_type NOT NULL,
    floors INT NOT NULL CHECK (floors > 0),
    land_area DECIMAL(8, 2) NOT NULL CHECK (land_area > 0.0),

    CONSTRAINT fk_house_seller FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE property_photos (
    property_id UUID NOT NULL,
    photo_url TEXT NOT NULL,

    PRIMARY KEY (property_id, photo_url)
);
