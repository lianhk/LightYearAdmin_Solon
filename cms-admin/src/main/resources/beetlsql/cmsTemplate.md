selectTemplateList
===
select * from cms_template
@if(!isEmpty(templateName)){
  and template_name like #{'%' + templateName + '%'}
@}
@if(!isEmpty(templateCode)){
  and template_code like #{'%' + templateCode + '%'}
@}
@if(!isEmpty(templateType)){
  and template_type = #{templateType}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}
order by create_time desc

selectTemplateList$count
===
select count(1) from cms_template
@if(!isEmpty(templateName)){
  and template_name like #{'%' + templateName + '%'}
@}
@if(!isEmpty(templateCode)){
  and template_code like #{'%' + templateCode + '%'}
@}
@if(!isEmpty(templateType)){
  and template_type = #{templateType}
@}
@if(!isEmpty(status)){
  and status = #{status}
@}

selectByCode
===
select * from cms_template where template_code = #{templateCode}

insert
===
insert into cms_template(template_id,template_name,template_code,template_type,file_path,content,description,status,create_by,create_time)
values(#{templateId},#{templateName},#{templateCode},#{templateType},#{filePath},#{content},#{description},#{status},#{createBy},now())

update
===
update cms_template set template_name=#{templateName},template_code=#{templateCode},template_type=#{templateType},file_path=#{filePath},content=#{content},description=#{description},status=#{status},update_by=#{updateBy},update_time=now()
where template_id = #{templateId}

deleteById
===
delete from cms_template where template_id = #{id}

selectById
===
select * from cms_template where template_id = #{id}
