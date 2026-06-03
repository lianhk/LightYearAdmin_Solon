selectPostList
===
select * from sys_post
@if(!isEmpty(postCode)){
  and post_code like #{'%' + postCode + '%'}
@}
@if(!isEmpty(postName)){
  and post_name like #{'%' + postName + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by post_sort

selectPostList$count
===
select count(1) from sys_post
@if(!isEmpty(postCode)){
  and post_code like #{'%' + postCode + '%'}
@}
@if(!isEmpty(postName)){
  and post_name like #{'%' + postName + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}

selectPostsByUserId
===
select p.* from sys_post p
left join sys_user_post up on p.post_id = up.post_id
where up.user_id = #{userId}

checkPostNameUnique
===
select count(1) from sys_post where post_name = #{postName}

checkPostCodeUnique
===
select count(1) from sys_post where post_code = #{postCode}

insert
===
insert into sys_post(post_id,post_code,post_name,post_sort,status,create_by,create_time,remark)
values(#{postId},#{postCode},#{postName},#{postSort},#{status},#{createBy},now(),#{remark})

update
===
update sys_post set post_code=#{postCode},post_name=#{postName},post_sort=#{postSort},status=#{status},update_by=#{updateBy},update_time=now(),remark=#{remark}
where post_id = #{postId}

deleteById
===
delete from sys_post where post_id = #{id}

selectById
===
select * from sys_post where post_id = #{id}
