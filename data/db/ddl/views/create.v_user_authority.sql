DROP VIEW IF EXISTS v_user_authority;
CREATE VIEW
 v_user_authority
AS
 SELECT
  CONCAT(u.userid, '-', a.authorityid) id,
  u.userid,
  a.authorityname
 FROM
  m_user u, m_user_role ur, m_role_authority ra, m_role r, m_authority a
 WHERE
  u.userid = ur.userid AND
  ur.roleid = r.roleid AND
  ur.roleid = ra.roleid AND
  ra.authorityid = a.authorityid;
