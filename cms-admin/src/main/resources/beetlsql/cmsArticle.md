selectArticleList
===
select a.*, c.category_name
from cms_article a
left join cms_category c on c.category_id = a.category_id
@if(!isEmpty(title)){
  and a.title like #{'%' + title + '%'}
@}
@if(!isEmpty(categoryId)){
  and a.category_id = #{categoryId}
@}
@if(!isEmpty(status)){
  and a.status = #{status}
@}
@if(!isEmpty(author)){
  and a.author like #{'%' + author + '%'}
@}
order by a.is_top desc, a.publish_time desc

selectArticleList$count
===
select count(1)
from cms_article a
left join cms_category c on c.category_id = a.category_id
@if(!isEmpty(title)){
  and a.title like #{'%' + title + '%'}
@}
@if(!isEmpty(categoryId)){
  and a.category_id = #{categoryId}
@}
@if(!isEmpty(status)){
  and a.status = #{status}
@}
@if(!isEmpty(author)){
  and a.author like #{'%' + author + '%'}
@}

insert
===
insert into cms_article(article_id,category_id,title,summary,content,cover_image,author,source,source_url,seo_title,seo_keywords,seo_description,tags,status,is_top,is_recommend,view_count,publish_time,static_path,create_by,create_time)
values(#{articleId},#{categoryId},#{title},#{summary},#{content},#{coverImage},#{author},#{source},#{sourceUrl},#{seoTitle},#{seoKeywords},#{seoDescription},#{tags},#{status},#{isTop},#{isRecommend},#{viewCount},#{publishTime},#{staticPath},#{createBy},now())

update
===
update cms_article set category_id=#{categoryId},title=#{title},summary=#{summary},content=#{content},cover_image=#{coverImage},author=#{author},source=#{source},source_url=#{sourceUrl},seo_title=#{seoTitle},seo_keywords=#{seoKeywords},seo_description=#{seoDescription},tags=#{tags},status=#{status},is_top=#{isTop},is_recommend=#{isRecommend},publish_time=#{publishTime},static_path=#{staticPath},update_by=#{updateBy},update_time=now()
where article_id = #{articleId}

updateViewCount
===
update cms_article set view_count = view_count + 1 where article_id = #{articleId}

deleteById
===
delete from cms_article where article_id = #{id}

selectById
===
select a.*, c.category_name from cms_article a left join cms_category c on c.category_id = a.category_id where a.article_id = #{id}
