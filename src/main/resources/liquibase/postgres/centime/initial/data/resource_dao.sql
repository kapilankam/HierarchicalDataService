CREATE
OR REPLACE FUNCTION resource_dao$get_child_idsByParentID(p_ParentId INT) RETURNS TABLE(id INT, parent_id INT, name VARCHAR , color VARCHAR )
        AS '
        WITH RECURSIVE subordinates AS (
            SELECT
                id,
                parent_id,
                name,
                color
            FROM
                resource
            WHERE
                parent_id = p_ParentId
            UNION ALL
            SELECT
                e.id,
                e.parent_id,
                e.name,
                e.color
            FROM
                resource e
            INNER JOIN subordinates s ON s.id = e.parent_id
        )
        SELECT id, parent_id, name, color FROM subordinates;
        ' LANGUAGE sql;