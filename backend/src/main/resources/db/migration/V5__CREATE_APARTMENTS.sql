SET search_path = project, pg_catalog;

CREATE TABLE apartments (
    id UUID PRIMARY KEY,

    layout VARCHAR(20) NOT NULL CHECK (layout IN ('DETACHED', 'SEMI_DETACHED', 'NON_DETACHED', 'CIRCULAR')),

    comfort INT NOT NULL CHECK (comfort BETWEEN 1 AND 4),
    floor INT NOT NULL,

    CONSTRAINT fk_apartment_property FOREIGN KEY (id) REFERENCES properties(id) ON DELETE CASCADE
);