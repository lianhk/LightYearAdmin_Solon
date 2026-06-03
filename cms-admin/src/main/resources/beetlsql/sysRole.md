selectRoleList
===
select r.* from sys_role r
where r.del_flag = '0'
@if(!isEmpty(roleName)){
  and r.role_name like #{'%' + roleName + '%'}
@}
@if(!isEmpty(roleKey)){
  and r.role_key like #{'%' + roleKey + '%'}
@}
@if(!isEmpty(status)){
  and r.status = #{status}
@}
order by r.role_sort

selectRoleList$count
===
select count(1) from sys_role r
where r.del_flag = '0'
@if(!isEmpty(roleName)){
  and r.role_name like #{'%' + roleName + '%'}
@}
@if(!isEmpty(roleKey)){
  and r.role_key like #{'%' + roleKey + '%'}
@}
@if(!isEmpty(status)){
  and r.status = #{status}
@}

selectRolesByUserId
===
select r.* from sys_role r
left join sys_user_role ur on r.role_id = ur.role_id
where ur.user_id = #{userId} and r.del_flag = '0'

checkRoleNameUnique
===
select count(1) from sys_role where role_name = #{roleName} and del_flag = '0'

checkRoleKeyUnique
===
select count(1) from sys_role where role_key = #{roleKey} and del_flag = '0'

deleteRoleMenuByRoleId
===
delete from sys_role_menu where role_id = #{roleId}

insertRoleMenu
===
insert into sys_role_menu(role_id, menu_id) values(#{roleId}, #{menuId})

insert
===
insert into sys_role(role_id,role_name,role_key,role_sort,data_scope,status,del_flag,create_by,create_time,remark)
values(#{roleId},#{roleName},#{roleKey},#{roleSort},#{dataScope},#{status},'0',#{createBy},now(),#{remark})

update
===
update sys_role set role_name=#{roleName},role_key=#{roleKey},role_sort=#{roleSort},data_scope=#{dataScope},status=#{status},update_by=#{updateBy},update_time=now(),remark=#{remark}
where role_id = #{roleId}

deleteById
===
update sys_role set del_flag = '1' where role_id = #{id}

selectById
===
select * from sys_role where role_id = #{id}
