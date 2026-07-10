-- =============================================================================
-- SEED DATA: DEFAULT ACCOUNTS AND ADMIN PERMISSIONS
-- =============================================================================
-- Login password for both seeded accounts: Password@123

INSERT IGNORE INTO permissions(name, description)
VALUES
    ('CATEGORY_CREATE', 'Create categories'),
    ('CATEGORY_UPDATE', 'Update categories'),
    ('CATEGORY_DELETE', 'Delete categories'),
    ('BRAND_CREATE', 'Create brands'),
    ('BRAND_UPDATE', 'Update brands'),
    ('BRAND_DELETE', 'Delete brands');

-- ADMIN -> all existing permissions, including category and brand permissions.
INSERT IGNORE INTO role_permissions(role_id, permission_id)
SELECT r.id, p.id
FROM roles r
         CROSS JOIN permissions p
WHERE r.name = 'ADMIN';

INSERT INTO users(username, password, enabled, account_locked, account_expired, deleted)
VALUES
    (
        'user@shopsphere.com',
        '$2a$12$Bv32ez9Kgn8RBn0u5mTFSOg2uLiXWijqRqyLya1c6USWdPoiTzWXy',
        TRUE,
        FALSE,
        FALSE,
        FALSE
    ),
    (
        'admin@shopsphere.com',
        '$2a$12$Bv32ez9Kgn8RBn0u5mTFSOg2uLiXWijqRqyLya1c6USWdPoiTzWXy',
        TRUE,
        FALSE,
        FALSE,
        FALSE
    )
ON DUPLICATE KEY UPDATE
    password = VALUES(password),
    enabled = TRUE,
    account_locked = FALSE,
    account_expired = FALSE,
    deleted = FALSE;

INSERT IGNORE INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'USER'
WHERE u.username = 'user@shopsphere.com';

INSERT IGNORE INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ADMIN'
WHERE u.username = 'admin@shopsphere.com';
