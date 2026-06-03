selectNoticeList
===
select * from sys_notice
@if(!isEmpty(noticeTitle)){
  and notice_title like #{'%' + noticeTitle + '%'}
@}
@if(!isEmpty(noticeType)){
  and notice_type = #{noticeType}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by create_time desc

selectNoticeList$count
===
select count(1) from sys_notice
@if(!isEmpty(noticeTitle)){
  and notice_title like #{'%' + noticeTitle + '%'}
@}
@if(!isEmpty(noticeType)){
  and notice_type = #{noticeType}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}

insert
===
insert into sys_notice(notice_id,notice_title,notice_type,notice_content,status,create_by,create_time,remark)
values(#{noticeId},#{noticeTitle},#{noticeType},#{noticeContent},#{status},#{createBy},now(),#{remark})

update
===
update sys_notice set notice_title=#{noticeTitle},notice_type=#{noticeType},notice_content=#{noticeContent},status=#{status},update_by=#{updateBy},update_time=now(),remark=#{remark}
where notice_id = #{noticeId}

deleteById
===
delete from sys_notice where notice_id = #{id}

selectById
===
select * from sys_notice where notice_id = #{id}
