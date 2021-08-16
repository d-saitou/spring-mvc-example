DROP VIEW IF EXISTS v_user_authority;
CREATE VIEW
 v_user_authority
AS
 SELECT
  u.user_id,
  a.authority_name
 FROM
  m_user u, m_user_role ur, m_role_authority ra, m_role r, m_authority a
 WHERE
  u.user_id = ur.user_id AND
  ur.role_id = r.role_id AND
  ur.role_id = ra.role_id AND
  ra.authority_id = a.authority_id;
