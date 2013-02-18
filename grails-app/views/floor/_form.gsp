<%@ page import="floorplan.Floor" %>



<div class="fieldcontain ${hasErrors(bean: floorInstance, field: 'nr', 'error')} required">
	<label for="nr">
		<g:message code="floor.nr.label" default="Nr" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="nr" required="" value="${floorInstance.nr}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: floorInstance, field: 'rooms', 'error')} ">
	<label for="rooms">
		<g:message code="floor.rooms.label" default="Rooms" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${floorInstance?.rooms?}" var="r">
    <li><g:link controller="room" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="room" action="create" params="['floor.id': floorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'room.label', default: 'Room')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: floorInstance, field: 'svg', 'error')} ">
	<label for="svg">
		<g:message code="floor.svg.label" default="Svg" />
		
	</label>
	<g:textField name="svg" value="${floorInstance?.svg}"/>
</div>

