selectConfigList
===
select * from sys_config
@if(!isEmpty(configName)){
  and config_name like #{'%' + configName + '%'}
@}
@if(!isEmpty(configKey)){
  and config_key like #{'%' + configKey + '%'}
@}
@if(!isEmpty(configType)){
  and config_type = #{configType}
@}
order by create_time desc

checkConfigKeyUnique
===
select * from sys_config where config_key = #{configKey}

selectConfigByKey
===
select * from sys_config where config_key = #{configKey}

insert
===
insert into sys_config(config_id,config_name,config_key,config_value,config_type,create_by,create_time,remark)
values(#{configId},#{configName},#{configKey},#{configValue},#{configType},#{createBy},now(),#{remark})

update
===
update sys_config set config_name=#{configName},config_key=#{configKey},config_value=#{configValue},config_type=#{configType},update_by=#{updateBy},update_time=now(),remark=#{remark}
where config_id = #{configId}

deleteById
===
delete from sys_config where config_id = #{id}

selectById
===
select * from sys_config where config_id = #{id}
