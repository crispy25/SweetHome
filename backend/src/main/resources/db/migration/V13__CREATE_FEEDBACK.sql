SET search_path = project, pg_catalog;

CREATE TABLE feedback (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    user_role VARCHAR(10) NOT NULL CHECK (user_role IN ('BUYER', 'SELLER')),
    message TEXT,
    created_at TIMESTAMP
);

CREATE TABLE feedback_options (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE feedback_options_map (
    feedback_id INT REFERENCES feedback(id) ON DELETE CASCADE,
    feature_id INT REFERENCES feedback_options(id),
    PRIMARY KEY (feedback_id, feature_id)
);

INSERT INTO feedback_options (name) VALUES
    ('User-friendly UI'),
    ('Fast search'),
    ('Advanced filters'),
    ('Modern design'),
    ('Relevant listings'),
    ('Detailed listings'),
    ('Easy navigation')
