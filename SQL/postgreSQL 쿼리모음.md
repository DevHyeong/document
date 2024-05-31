# postgreSQL 쿼리 모음

### 다중 merge (MyBatis)
```sql
<insert id="save" parameterType="java.util.List">
    WITH UPSERT AS (
        UPDATE OBS_DATA AS A
        SET OBS_VALUE = TMP.obsValue
        FROM (
        VALUES
            <foreach collection="list" item="item" separator=",">
                (
                    #{item.obsPostId},
                    #{item.item},
                    #{item.observedAt},
                    #{item.obsValue}
                )
            </foreach>
        ) AS TMP (obsPostId, item, observedAt, obsValue)
        WHERE A.OBS_POST_ID = TMP.obsPostId
        AND A.OBS_ITEM = TMP.item
        AND A.OBS_DATE = TMP.observedAt
        RETURNING A.OBS_POST_ID, A.OBS_ITEM, A.OBS_DATE
    )
    INSERT INTO OBS_DATA
    (
     OBS_POST_ID,
     OBS_ITEM,
     OBS_DATE,
     OBS_VALUE
    )
    SELECT obsPostId, item, observedAt, obsValue
    FROM
    (
        VALUES
            <foreach collection="list" item="item" separator=",">
                (
                #{item.obsPostId},
                #{item.item},
                #{item.observedAt},
                #{item.obsValue}
                )
            </foreach>
    ) AS B(obsPostId, item, observedAt, obsValue)
    WHERE NOT EXISTS(
        SELECT * FROM UPSERT
        WHERE UPSERT.OBS_POST_ID = B.obsPostId
        AND UPSERT.OBS_ITEM = B.ITEM
        AND UPSERT.OBS_DATE = B.observedAt
    )
</insert>
```
