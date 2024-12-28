-- Active: 1735216136286@@127.0.0.1@3306@cloud_hospital
-- 删除重复的医生记录，保留ID最大的记录
DELETE e1 FROM empinfo e1
INNER JOIN empinfo e2
WHERE e1.username = e2.username 
AND e1.id < e2.id
AND e1.username LIKE '%医生%';

-- 重置自增ID
ALTER TABLE empinfo AUTO_INCREMENT = 92; 