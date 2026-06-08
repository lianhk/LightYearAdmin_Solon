selectCategoryList
===
select c.*, p.category_name as parent_name
from cms_category c
left join cms_category p on p.category_id = c.parent_id
@if(!isEmpty(categoryName)){
  and c.category_name like #{'%' + categoryName + '%'}
@}
@if(!isEmpty(categoryCode)){
  and c.category_code like #{'%' + categoryCode + '%'}
@}
@if(!isEmpty(status)){
  and c.status = #{status}
@}
order by c.parent_id asc, c.sort_num asc

checkNameUnique
===
select * from cms_category
where category_name = #{categoryName}
@if(parentId != null){
  and parent_id = #{parentId}
@}
@if(parentId == null){
  and parent_id = 0
@}

insert
===
insert into cms_category(category_id,parent_id,category_name,category_code,template_id,article_template_id,seo_title,seo_keywords,seo_description,url_path,image,target_blank,sort_num,status,create_by,create_time)
values(#{categoryId},#{parentId},#{categoryName},#{categoryCode},#{templateId},#{articleTemplateId},#{seoTitle},#{seoKeywords},#{seoDescription},#{urlPath},#{image},#{targetBlank},#{sortNum},#{status},#{createBy},now())

update
===
update cms_category set parent_id=#{parentId},category_name=#{categoryName},category_code=#{categoryCode},template_id=#{templateId},article_template_id=#{articleTemplateId},seo_title=#{seoTitle},seo_keywords=#{seoKeywords},seo_description=#{seoDescription},url_path=#{urlPath},image=#{image},target_blank=#{targetBlank},sort_num=#{sortNum},status=#{status},update_by=#{updateBy},update_time=now()
where category_id = #{categoryId}

deleteById
===
delete from cms_category where category_id = #{id}

selectById
===
select * from cms_category where category_id = #{id}
