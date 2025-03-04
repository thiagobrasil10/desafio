CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    phone VARCHAR(20) NOT NULL CHECK (phone ~* '^\+\d{1,3}\s\d{1,4}\s\d{4,5}-\d{4}$'),
    birth_date DATE NOT NULL,
    user_type VARCHAR(10) NOT NULL CHECK (user_type IN ('ADMIN', 'EDITOR', 'VIEWER'))
);