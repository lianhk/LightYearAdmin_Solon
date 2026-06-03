selectOperLogList
===
select * from sys_oper_log
@if(!isEmpty(title)){
  and title like #{'%' + title + '%'}
@}
@if(!isEmpty(operName)){
  and oper_name like #{'%' + operName + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by oper_time desc

cleanOperLog
===
truncate table sys_oper_log

insert
===
insert into sys_oper_log(oper_id,title,business_type,method,request_method,oper_name,oper_url,oper_ip,oper_param,json_result,status,error_msg,oper_time,cost_time)
values(#{operId},#{title},#{businessType},#{method},#{requestMethod},#{operName},#{operUrl},#{operIp},#{operParam},#{jsonResult},#{status},#{errorMsg},now(),#{costTime})

selectById
===
select * from sys_oper_log where oper_id = #{id}

deleteById
===
delete from sys_oper_log where oper_id = #{id}
