selectDeptList
===
select * from sys_dept
where del_flag = '0'
@if(!isEmpty(deptName)){
  and dept_name like #{'%' + deptName + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by parent_id, order_num

checkDeptNameUnique
===
select count(1) from sys_dept where dept_name = #{deptName} and parent_id = #{parentId} and del_flag = '0'

hasChildByDeptId
===
select count(1) from sys_dept where parent_id = #{deptId} and del_flag = '0'

checkDeptExistUser
===
select count(1) from sys_user where dept_id = #{deptId} and del_flag = '0'

insert
===
insert into sys_dept(dept_id,parent_id,ancestors,dept_name,order_num,leader,phone,email,status,del_flag,create_by,create_time)
values(#{deptId},#{parentId},#{ancestors},#{deptName},#{orderNum},#{leader},#{phone},#{email},#{status},'0',#{createBy},now())

update
===
update sys_dept set parent_id=#{parentId},ancestors=#{ancestors},dept_name=#{deptName},order_num=#{orderNum},leader=#{leader},phone=#{phone},email=#{email},status=#{status},update_by=#{updateBy},update_time=now()
where dept_id = #{deptId}

deleteById
===
update sys_dept set del_flag = '1' where dept_id = #{id}

selectById
===
select * from sys_dept where dept_id = #{id}
