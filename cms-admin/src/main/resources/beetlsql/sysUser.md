selectUserByUserName
===
select * from sys_user where user_name = #{userName} and del_flag = '0'

selectUserList
===
select u.*, d.dept_name from sys_user u
left join sys_dept d on u.dept_id = d.dept_id
where u.del_flag = '0'
@if(!isEmpty(userName)){
  and u.user_name like #{'%' + userName + '%'}
@}
@if(!isEmpty(nickName)){
  and u.nick_name like #{'%' + nickName + '%'}
@}
@if(!isEmpty(phoneNumber)){
  and u.phone_number like #{'%' + phoneNumber + '%'}
@}
@if(!isEmpty(status)){
  and u.status = #{status}
@}
@if(!isEmpty(deptId)){
  and (u.dept_id = #{deptId} or u.dept_id in (select dept_id from sys_dept where find_in_set(#{deptId}, ancestors)))
@}
order by u.create_time desc

checkUserNameUnique
===
select count(1) from sys_user where user_name = #{userName} and del_flag = '0'

checkPhoneUnique
===
select count(1) from sys_user where phone_number = #{phoneNumber} and del_flag = '0'

checkEmailUnique
===
select count(1) from sys_user where email = #{email} and del_flag = '0'

deleteUserRoleByUserId
===
delete from sys_user_role where user_id = #{userId}

insertUserRole
===
insert into sys_user_role(user_id, role_id) values(#{userId}, #{roleId})

selectById
===
select u.*, d.dept_name from sys_user u
left join sys_dept d on u.dept_id = d.dept_id
where u.user_id = #{id} and u.del_flag = '0'

insert
===
insert into sys_user(user_id,dept_id,user_name,nick_name,email,phone_number,sex,avatar,password,status,del_flag,create_by,create_time,remark)
values(#{userId},#{deptId},#{userName},#{nickName},#{email},#{phoneNumber},#{sex},#{avatar},#{password},#{status},'0',#{createBy},now(),#{remark})

update
===
update sys_user set dept_id=#{deptId},nick_name=#{nickName},email=#{email},phone_number=#{phoneNumber},sex=#{sex},status=#{status},update_by=#{updateBy},update_time=now(),remark=#{remark}
@if(!isEmpty(password)){
  ,password=#{password}
@}
where user_id = #{userId}

deleteById
===
update sys_user set del_flag = '1' where user_id = #{id}
