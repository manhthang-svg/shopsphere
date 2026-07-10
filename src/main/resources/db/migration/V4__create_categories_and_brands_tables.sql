-- =============================================================================
-- CATEGORIES
-- =============================================================================
CREATE TABLE categories (
                            id BIGINT NOT NULL AUTO_INCREMENT,

                            name VARCHAR(100) NOT NULL,
                            description VARCHAR(255) NULL,
                            status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                            parent_category_id BIGINT NULL,

                            created_by_id BIGINT NULL,
                            updated_by_id BIGINT NULL,

                            created_at DATETIME(6)
                                NOT NULL
                                DEFAULT CURRENT_TIMESTAMP(6),

                            updated_at DATETIME(6)
                                NOT NULL
                                DEFAULT CURRENT_TIMESTAMP(6)
                                ON UPDATE CURRENT_TIMESTAMP(6),

                            deleted BOOLEAN NOT NULL DEFAULT FALSE,

                            version BIGINT NOT NULL DEFAULT 0,

                            PRIMARY KEY (id),

                            CONSTRAINT fk_categories_created_by
                                FOREIGN KEY (created_by_id)
                                    REFERENCES users(id),

                            CONSTRAINT fk_categories_updated_by
                                FOREIGN KEY (updated_by_id)
                                    REFERENCES users(id),

                            CONSTRAINT fk_categories_parent_category
                                FOREIGN KEY (parent_category_id)
                                    REFERENCES categories(id)
);

CREATE INDEX idx_categories_created_by
    ON categories(created_by_id);

CREATE INDEX idx_categories_updated_by
    ON categories(updated_by_id);

CREATE INDEX idx_categories_name
    ON categories(name);

CREATE INDEX idx_categories_parent_category
    ON categories(parent_category_id);

-- =============================================================================
-- BRANDS
-- =============================================================================
CREATE TABLE brands (
                        id BIGINT NOT NULL AUTO_INCREMENT,

                        name VARCHAR(100) NOT NULL,
                        logo VARCHAR(500) NULL,
                        description VARCHAR(255) NULL,
                        status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                        created_by_id BIGINT NULL,
                        updated_by_id BIGINT NULL,

                        created_at DATETIME(6)
                            NOT NULL
                            DEFAULT CURRENT_TIMESTAMP(6),

                        updated_at DATETIME(6)
                            NOT NULL
                            DEFAULT CURRENT_TIMESTAMP(6)
                            ON UPDATE CURRENT_TIMESTAMP(6),

                        deleted BOOLEAN NOT NULL DEFAULT FALSE,

                        version BIGINT NOT NULL DEFAULT 0,

                        PRIMARY KEY (id),

                        CONSTRAINT fk_brands_created_by
                            FOREIGN KEY (created_by_id)
                                REFERENCES users(id),

                        CONSTRAINT fk_brands_updated_by
                            FOREIGN KEY (updated_by_id)
                                REFERENCES users(id)
);

CREATE INDEX idx_brands_created_by
    ON brands(created_by_id);

CREATE INDEX idx_brands_updated_by
    ON brands(updated_by_id);

CREATE INDEX idx_brands_name
    ON brands(name);
