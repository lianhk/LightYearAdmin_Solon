selectBannerList
===
select * from cms_banner
@if(!isEmpty(title)){
  and title like #{'%' + title + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by sort_num

selectBannerList$count
===
select count(1) from cms_banner
@if(!isEmpty(title)){
  and title like #{'%' + title + '%'}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}

insert
===
insert into cms_banner(banner_id,title,subtitle,image_url,link_url,bg_color,sort_num,status,position,create_by,create_time)
values(#{bannerId},#{title},#{subtitle},#{imageUrl},#{linkUrl},#{bgColor},#{sortNum},#{status},#{position},#{createBy},now())

update
===
update cms_banner set title=#{title},subtitle=#{subtitle},image_url=#{imageUrl},link_url=#{linkUrl},bg_color=#{bgColor},sort_num=#{sortNum},status=#{status},position=#{position},update_by=#{updateBy},update_time=now()
where banner_id = #{bannerId}

deleteById
===
delete from cms_banner where banner_id = #{id}

selectById
===
select * from cms_banner where banner_id = #{id}
