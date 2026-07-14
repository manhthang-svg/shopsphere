-- =============================================================================
-- SYSTEM ROLES
-- Keep only ADMIN, SELLER and CUSTOMER while preserving existing assignments.
-- =============================================================================

INSERT IGNORE INTO roles(name)
VALUES
    ('ADMIN'),
    ('SELLER'),
    ('CUSTOMER');

-- Migrate the legacy role assignments and permissions before removing them.
INSERT IGNORE INTO user_roles(user_id, role_id)
SELECT ur.user_id, target_role.id
FROM user_roles ur
         JOIN roles legacy_role ON legacy_role.id = ur.role_id
         JOIN roles target_role ON target_role.name = 'SELLER'
WHERE legacy_role.name = 'MANAGER';

INSERT IGNORE INTO user_roles(user_id, role_id)
SELECT ur.user_id, target_role.id
FROM user_roles ur
         JOIN roles legacy_role ON legacy_role.id = ur.role_id
         JOIN roles target_role ON target_role.name = 'CUSTOMER'
WHERE legacy_role.name = 'USER';

INSERT IGNORE INTO role_permissions(role_id, permission_id)
SELECT target_role.id, rp.permission_id
FROM role_permissions rp
         JOIN roles legacy_role ON legacy_role.id = rp.role_id
         JOIN roles target_role ON target_role.name = 'SELLER'
WHERE legacy_role.name = 'MANAGER';

INSERT IGNORE INTO role_permissions(role_id, permission_id)
SELECT target_role.id, rp.permission_id
FROM role_permissions rp
         JOIN roles legacy_role ON legacy_role.id = rp.role_id
         JOIN roles target_role ON target_role.name = 'CUSTOMER'
WHERE legacy_role.name = 'USER';

DELETE ur
FROM user_roles ur
         JOIN roles r ON r.id = ur.role_id
WHERE r.name NOT IN ('ADMIN', 'SELLER', 'CUSTOMER');

DELETE rp
FROM role_permissions rp
         JOIN roles r ON r.id = rp.role_id
WHERE r.name NOT IN ('ADMIN', 'SELLER', 'CUSTOMER');

DELETE FROM roles
WHERE name NOT IN ('ADMIN', 'SELLER', 'CUSTOMER');

-- =============================================================================
-- SHOPS
-- A unique user_id enforces one shop per user at database level.
-- =============================================================================

CREATE TABLE shops (
                       id BIGINT NOT NULL AUTO_INCREMENT,

                       name VARCHAR(255) NOT NULL,
                       description VARCHAR(255) NULL,
                       logo_url VARCHAR(255) NULL,
                       status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                       user_id BIGINT NOT NULL,

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
                       CONSTRAINT uk_shops_user UNIQUE (user_id),

                       CONSTRAINT fk_shops_user
                           FOREIGN KEY (user_id)
                               REFERENCES users(id)
                               ON DELETE CASCADE,

                       CONSTRAINT fk_shops_created_by
                           FOREIGN KEY (created_by_id)
                               REFERENCES users(id),

                       CONSTRAINT fk_shops_updated_by
                           FOREIGN KEY (updated_by_id)
                               REFERENCES users(id)
);

CREATE INDEX idx_shops_created_by
    ON shops(created_by_id);

CREATE INDEX idx_shops_updated_by
    ON shops(updated_by_id);

-- =============================================================================
-- SHOP PERMISSIONS
-- =============================================================================

INSERT IGNORE INTO permissions(name, description)
VALUES
    ('SHOP_CREATE', 'Create shops'),
    ('SHOP_UPDATE', 'Update shops'),
    ('SHOP_APPROVE', 'Approve shops'),
    ('SHOP_REJECT', 'Reject shops'),
    ('SHOP_VIEW', 'View shops');

-- Preserve the existing convention that ADMIN owns every system permission.
INSERT IGNORE INTO role_permissions(role_id, permission_id)
SELECT r.id, p.id
FROM roles r
         CROSS JOIN permissions p
WHERE r.name = 'ADMIN';
