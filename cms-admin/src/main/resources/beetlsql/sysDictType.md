selectDictTypeList
===
select * from sys_dict_type
@if(!isEmpty(dictName)){
  and dict_name like #{'%' + dictName + '%'}
@}
@if(!isEmpty(dictType)){
  and dict_type like #{'%' + dictType + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by create_time desc

checkDictTypeUnique
===
select count(1) from sys_dict_type where dict_type = #{dictType}

insert
===
insert into sys_dict_type(dict_id,dict_name,dict_type,status,create_by,create_time,remark)
values(#{dictId},#{dictName},#{dictType},#{status},#{createBy},now(),#{remark})

update
===
update sys_dict_type set dict_name=#{dictName},dict_type=#{dictType},status=#{status},update_by=#{updateBy},update_time=now(),remark=#{remark}
where dict_id = #{dictId}

deleteById
===
delete from sys_dict_type where dict_id = #{id}

selectById
===
select * from sys_dict_type where dict_id = #{id}
