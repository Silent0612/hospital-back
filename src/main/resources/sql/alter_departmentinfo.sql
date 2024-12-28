-- Active: 1735216136286@@127.0.0.1@3306@cloud_hospital
ALTER TABLE departmentinfo ADD COLUMN description VARCHAR(255) COMMENT '科室描述';

-- 添加state字段
ALTER TABLE departmentinfo ADD COLUMN state INT DEFAULT 1 COMMENT '状态 1-启用 0-禁用';

-- 删除institution_id字段（因为实体类中没有这个字段）
ALTER TABLE departmentinfo DROP COLUMN institution_id;

-- 更新现有数据的描述信息
UPDATE departmentinfo SET description = 
CASE departmentname
    WHEN '骨科' THEN '专门研究骨骼、关节、肌肉等运动系统疾病的诊断和治疗'
    WHEN '眼科' THEN '专门研究眼部疾病的诊断和治疗'
    WHEN '内科' THEN '主要研究人体内部器官疾病的诊断和治疗'
    WHEN '妇科' THEN '专门研究女性生殖系统疾病的诊断和治疗'
    WHEN '神经科' THEN '专门研究神经系统疾病的诊断和治疗'
    WHEN '精神科' THEN '专门研究心理和精神疾病的诊断和治疗'
    ELSE '暂无描述'
END;