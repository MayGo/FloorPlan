
<div class="page-header">
	<h1>Edit Category</h1>
</div>
<alert level="{{message.level}}" text="{{message.text}}"/>
<form name="form" data-ng-submit="update(item)" class="form-horizontal">
	<input type="hidden" data-ng-model="item.id">
	<input type="hidden" data-ng-model="item.version">

	<div class="control-group" data-ng-class="{error: errors.name}">
		<label class="control-label" for="name">Name</label>
		<div class="controls">
			<input type="text" id="name" name="name" required data-ng-model="item.name">
			<span class="help-inline" data-ng-show="errors.name">{{errors.name}}</span>
		</div>
	</div>
	
	<div class="control-group" data-ng-class="{error: errors.shops}">
		<label class="control-label" for="shops">Shops</label>
		<div class="controls">
			<input type="text" id="shops" name="shops" required data-ng-model="item.shops">
			<span class="help-inline" data-ng-show="errors.shops">{{errors.shops}}</span>
		</div>
	</div>
	
	<div class="form-actions">
		<button type="submit" class="btn btn-primary" data-ng-disabled="form.$invalid"><i class="icon-ok"></i> Update</button>
		<button type="button" class="btn btn-danger" data-ng-click="delete(item)"><i class="icon-trash"></i> Delete</button>
	</div>
</form>
