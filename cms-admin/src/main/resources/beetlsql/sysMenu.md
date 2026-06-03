selectMenuList
===
select * from sys_menu
@if(!isEmpty(menuName)){
  and menu_name like #{'%' + menuName + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by parent_id, order_num

selectMenuTreeAll
===
select * from sys_menu where menu_type in ('M','C') and status = '0' order by parent_id, order_num

selectMenusByUserId
===
select distinct m.* from sys_menu m
left join sys_role_menu rm on m.menu_id = rm.menu_id
left join sys_user_role ur on rm.role_id = ur.role_id
where ur.user_id = #{userId} and m.status = '0'
order by m.parent_id, m.order_num

selectMenuPermsByUserId
===
select distinct m.perms from sys_menu m
left join sys_role_menu rm on m.menu_id = rm.menu_id
left join sys_user_role ur on rm.role_id = ur.role_id
where ur.user_id = #{userId} and m.status = '0' and m.perms != '' and m.perms is not null

selectMenuListByRoleId
===
select m.menu_id from sys_menu m
left join sys_role_menu rm on m.menu_id = rm.menu_id
where rm.role_id = #{roleId}

hasChildByMenuId
===
select count(1) from sys_menu where parent_id = #{menuId}

checkMenuNameUnique
===
select count(1) from sys_menu where menu_name = #{menuName} and parent_id = #{parentId}

insert
===
insert into sys_menu(menu_id,menu_name,parent_id,order_num,path,component,query,route_name,is_frame,is_cache,menu_type,visible,status,perms,icon,create_by,create_time,remark)
values(#{menuId},#{menuName},#{parentId},#{orderNum},#{path},#{component},#{query},#{routeName},#{isFrame},#{isCache},#{menuType},#{visible},#{status},#{perms},#{icon},#{createBy},now(),#{remark})

update
===
update sys_menu set menu_name=#{menuName},parent_id=#{parentId},order_num=#{orderNum},path=#{path},component=#{component},query=#{query},route_name=#{routeName},is_frame=#{isFrame},is_cache=#{isCache},menu_type=#{menuType},visible=#{visible},status=#{status},perms=#{perms},icon=#{icon},update_by=#{updateBy},update_time=now(),remark=#{remark}
where menu_id = #{menuId}

deleteById
===
delete from sys_menu where menu_id = #{id}

selectById
===
select * from sys_menu where menu_id = #{id}
