SET search_path = project, pg_catalog;

CREATE TABLE favorites (
    user_id UUID NOT NULL,
    property_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,

    PRIMARY KEY (user_id, property_id),

    CONSTRAINT fk_favorite_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_favorite_property FOREIGN KEY (property_id) REFERENCES properties(id) ON DELETE CASCADE
);