
<div class="page-header">
	<h1>Show Album</h1>
</div>
<alert level="{{message.level}}" text="{{message.text}}"/>
<dl class="dl-horizontal">
	
	<dt>Nr</dt>
	<dd data-ng-bind="item.nr"></dd>
	
	<dt>Rooms</dt>
	<dd data-ng-bind="item.rooms"></dd>
	
	<dt>Svg</dt>
	<dd data-ng-bind="item.svg"></dd>
	
</dl>
<div class="form-actions">
	<a class="btn" data-ng-href="#/edit/{{item.id}}"><i class="icon-edit"></i> Edit</a>
	<a class="btn" href="${createLink(controller:'map',action: 'editor', params:[floorId:1])}">Edit Svg</a>
	<button type="button" class="btn btn-danger" data-ng-click="delete(item)"><i class="icon-trash"></i> Delete</button>
</div>
