-- =============================================================================
-- ADDRESS
-- Stores delivery addresses owned by users.
-- =============================================================================
CREATE TABLE address (
                         id BIGINT NOT NULL AUTO_INCREMENT,

                         recipient_name VARCHAR(100) NOT NULL,
                         phone VARCHAR(15) NOT NULL,
                         province VARCHAR(50) NOT NULL,
                         district VARCHAR(50) NOT NULL,
                         ward VARCHAR(50) NOT NULL,
                         detail_address TEXT NOT NULL,
                         is_default BOOLEAN NOT NULL DEFAULT FALSE,
                         user_id BIGINT NOT NULL,

    -- Audit fields
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

                         CONSTRAINT fk_address_user
                             FOREIGN KEY (user_id)
                                 REFERENCES users(id)
                                 ON DELETE CASCADE,

                         CONSTRAINT fk_address_created_by
                             FOREIGN KEY (created_by_id)
                                 REFERENCES users(id),

                         CONSTRAINT fk_address_updated_by
                             FOREIGN KEY (updated_by_id)
                                 REFERENCES users(id)
);

CREATE INDEX idx_address_user
    ON address(user_id);

CREATE INDEX idx_address_created_by
    ON address(created_by_id);

CREATE INDEX idx_address_updated_by
    ON address(updated_by_id);
