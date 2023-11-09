
- Potsgres 9.5버전 이하에서 여러개의 row를 merge하는 query
```sql
with upsert as 
(
  update ${table_name} as A
    set ${column_name1} = tmp_name.${custom_colum_name1},
    ${column_name2} = tmp_name.${custom_colum_name2}
  from (
  values 
  (1, 'update123123', false, to_timestamp('2023-10-01 12:00', 'yyyy-MM-dd HH24:MI'), now()),
  (5, 'te123st', false, to_timestamp('2023-10-01 12:00', 'yyyy-MM-dd HH24:MI'), now())
  ) as tmp_name (${custom_colum_name1}, ${custom_colum_name2}, ${custom_colum_name3}, ${custom_colum_name4}, ${custom_colum_name5}) 
  where A.${column_name1} = tmp_values.${custom_colum_name1}
  returning A.${column_name1}
)
insert into ${table_name}
(
  ${colum_name1},
  ${colum_name2},
  ${colum_name3},
  ${colum_name4},
  ${colum_name5}
)
select ${custom_colum_name1}, ${custom_colum_name2}, ${custom_colum_name3}, ${custom_colum_name4}, ${custom_colum_name5}
from 
( 
values
(1, 'update123123', false, to_timestamp('2023-10-01 12:00', 'yyyy-MM-dd HH24:MI'), now()),
(5, 'te123st', false, to_timestamp('2023-10-01 12:00', 'yyyy-MM-dd HH24:MI'), now())
) as B(${custom_colum_name1}, ${custom_colum_name2}, ${custom_colum_name3}, ${custom_colum_name4}, ${custom_colum_name5})
where not exists (select * from upsert where B.${custom_colum_name1} = upsert.${column_name1})


```
