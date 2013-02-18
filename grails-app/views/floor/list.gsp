
<div class="page-header">
	<h1>Album List</h1>
</div>
<alert level="{{message.level}}" text="{{message.text}}"/>
<table class="table table-bordered table-striped">
    <thead>
        <tr>
		
			<th>Nr</th>
		
			<th>Svg</th>
		
        </tr>
    </thead>
    <tbody>
        <tr data-ng-repeat="item in list" data-ng-click="show(item)">
		
			<td>{{item.nr}}</td>
		
			<td>{{item.svg}}</td>
		
        </tr>
    </tbody>
</table>
<div class="pagination pagination-centered" data-pagination data-total="total"></div>