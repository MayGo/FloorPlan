
<div class="page-header">
	<h1>Edit Floor</h1>
</div>
<alert level="{{message.level}}" text="{{message.text}}"/>
<form name="form" data-ng-submit="update(item)" class="form-horizontal">
	<input type="hidden" data-ng-model="item.id">
	<input type="hidden" data-ng-model="item.version">

	<div class="control-group" data-ng-class="{error: errors.nr}">
		<label class="control-label" for="nr">Nr</label>
		<div class="controls">
			<input type="text" id="nr" name="nr" required data-ng-model="item.nr">
			<span class="help-inline" data-ng-show="errors.nr">{{errors.nr}}</span>
		</div>
	</div>
	
	<div class="control-group" data-ng-class="{error: errors.rooms}">
		<label class="control-label" for="rooms">Rooms</label>
		<div class="controls">
			<input type="text" id="rooms" name="rooms" required data-ng-model="item.rooms">
			<span class="help-inline" data-ng-show="errors.rooms">{{errors.rooms}}</span>
		</div>
	</div>
	
	<div class="control-group" data-ng-class="{error: errors.svg}">
		<label class="control-label" for="svg">Svg</label>
		<div class="controls">
			<input type="text" id="svg" name="svg" required data-ng-model="item.svg">
			<span class="help-inline" data-ng-show="errors.svg">{{errors.svg}}</span>
		</div>
	</div>
	
	<div class="form-actions">
		<button type="submit" class="btn btn-primary" data-ng-disabled="form.$invalid"><i class="icon-ok"></i> Update</button>
		<button type="button" class="btn btn-danger" data-ng-click="delete(item)"><i class="icon-trash"></i> Delete</button>
	</div>
</form>
