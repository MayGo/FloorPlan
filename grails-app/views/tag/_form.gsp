<%@ page import="floorplan.Tag" %>



<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="tag.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${tagInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'shops', 'error')} ">
	<label for="shops">
		<g:message code="tag.shops.label" default="Shops" />
		
	</label>
	<g:select name="shops" from="${floorplan.Shop.list()}" multiple="multiple" optionKey="id" size="5" value="${tagInstance?.shops*.id}" class="many-to-many"/>
</div>

