selectDictDataList
===
select * from sys_dict_data
@if(!isEmpty(dictType)){
  and dict_type = #{dictType}
@}
@if(!isEmpty(dictLabel)){
  and dict_label like #{'%' + dictLabel + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by dict_sort

selectDictDataByType
===
select * from sys_dict_data where dict_type = #{dictType} and status = '0' order by dict_sort

insert
===
insert into sys_dict_data(dict_code,dict_sort,dict_label,dict_value,dict_type,css_class,list_class,is_default,status,create_by,create_time,remark)
values(#{dictCode},#{dictSort},#{dictLabel},#{dictValue},#{dictType},#{cssClass},#{listClass},#{isDefault},#{status},#{createBy},now(),#{remark})

update
===
update sys_dict_data set dict_sort=#{dictSort},dict_label=#{dictLabel},dict_value=#{dictValue},dict_type=#{dictType},css_class=#{cssClass},list_class=#{listClass},is_default=#{isDefault},status=#{status},update_by=#{updateBy},update_time=now(),remark=#{remark}
where dict_code = #{dictCode}

deleteById
===
delete from sys_dict_data where dict_code = #{id}

selectById
===
select * from sys_dict_data where dict_code = #{id}
