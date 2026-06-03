selectLogininforList
===
select * from sys_logininfor
@if(!isEmpty(userName)){
  and user_name like #{'%' + userName + '%'}
@}
@if(!isEmpty(ipaddr)){
  and ipaddr like #{'%' + ipaddr + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by login_time desc

selectLogininforList$count
===
select count(1) from sys_logininfor
@if(!isEmpty(userName)){
  and user_name like #{'%' + userName + '%'}
@}
@if(!isEmpty(ipaddr)){
  and ipaddr like #{'%' + ipaddr + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}

cleanLogininfor
===
truncate table sys_logininfor

insert
===
insert into sys_logininfor(info_id,user_name,ipaddr,login_location,browser,os,status,msg,login_time)
values(#{infoId},#{userName},#{ipaddr},#{loginLocation},#{browser},#{os},#{status},#{msg},now())

selectById
===
select * from sys_logininfor where info_id = #{id}

deleteById
===
delete from sys_logininfor where info_id = #{id}
