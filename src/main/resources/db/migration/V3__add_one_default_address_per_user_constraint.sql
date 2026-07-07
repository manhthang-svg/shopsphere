-- =============================================================================
-- ADDRESS DEFAULT CONSTRAINT
-- Ensures each user can have at most one active default address.
-- =============================================================================

ALTER TABLE address
    ADD COLUMN default_address_user_id BIGINT
        GENERATED ALWAYS AS (
            CASE
                WHEN is_default = TRUE AND deleted = FALSE THEN user_id
                ELSE NULL
            END
        ) VIRTUAL;

CREATE UNIQUE INDEX uk_address_one_default_per_user
    ON address(default_address_user_id);
