angular.module('planitmod', [])
	.config(function($routeProvider) {
		$routeProvider
			.when('/musicians', {controller:MusicianListCtrl, templateUrl:'musicians.html'})
			.when('/edit/musicianId', {controller:MusicianEditCtrl, templateUrl:'musician_detail.html'})
			.when('/new', {controller:MusicianCreateCtrl, templateUrl:'detail.html'})
			.otherwise({redirectTo:'/musicians'});
	
	});

function MusicianListCtrl($scope)
{
	$scope.musicians = [
	 {id:'0',name:'andrew',description:'guitar player'},
	 {id:'1',name:'isaac',description:'bass player'}
	];
}

function MusicianEditCtrl($scope, $location, $routeParams)
{
	
}

function MusicianCreateCtrl($scope, $location)
{
	
}