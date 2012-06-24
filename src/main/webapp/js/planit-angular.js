angular.module('planitmod', ['testmodule'])
	.config(function($routeProvider) {
		$routeProvider
			.when('/people', {controller:PeopleListCtrl, templateUrl:'people.html'})
			.when('/people/:personId', {controller:MusicianListCtrl, templateUrl:'people.html'})
			
			.when('/people/edit/:personId', {controller:MusicianEditCtrl, templateUrl:'person_detail.html'})
			.when('/people/create', {controller:MusicianCreateCtrl, templateUrl:'detail.html'})
			
			.when('/songs', {controller:SongListCtrl, templateUrl:'songs.html'})
			.when('/welcome', {templateUrl:'choose.html'})
			
			.otherwise({redirectTo:'/welcome'});
	
	});

angular.module('testmodule', [])
.config(function($routeProvider) {
	$routeProvider
		.when('/events', {templateUrl:'events.html'})
		

});

function PeopleListCtrl($scope)
{
	$scope.musicians = [
	 {id:'0',name:'andrew',description:'guitar player', 
		 	instruments:[{name:'guitar'},
					 	  {name:'vocals'},
					  	  {name:'drums'}]},
	 {id:'1',name:'isaac',description:'bass player', 
		 	instruments:[{name:'bass'}]},
	 {id:'2',name:'doug',description:'drummer', 
		 	instruments:[{name:'drums'},
					 	  {name:'percussion'}]}
	];
	
	$scope.soundTechs = [
   	 {id:'0',name:'andrew',description:'guitar player'},
   	 {id:'1',name:'isaac',description:'bass player'}
   	];
	
	$scope.audioVisuals = [
   	 {id:'0',name:'andrew',description:'guitar player'},
   	 {id:'1',name:'isaac',description:'bass player'}
   	];
	
	$scope.speakers = [
	 {id:'0',name:'andrew',description:'guitar player'},
	 {id:'1',name:'isaac',description:'bass player'}
	];
}



function MusicianListCtrl($scope)
{
	$scope.musicians = [
	 {id:'0',name:'andrew',description:'guitar player'},
	 {id:'1',name:'isaac',description:'bass player'}
	];
}

function SongListCtrl($scope)
{
	$scope.musicians = [
	 {id:'0',name:'andrew',description:'guitar player'},
	 {id:'1',name:'isaac',description:'bass player'}
	];
}

function MusicianEditCtrl($scope, $location, $routeParams)
{
	$scope.instruments = [
	  {id:'0',name:'Guitar'},
	  {id:'1',name:'Electric Guitar'},
	  {id:'2',name:'Bass'},
	  {id:'3',name:'Drims'},
	  {id:'4',name:'Piano'},
	  {id:'5',name:'Keyboard'},
	  {id:'6',name:'Percussion'},
	  {id:'7',name:'Violin'},
	  {id:'8',name:'Flute'},
	  {id:'9',name:'Saxophone'},
	  {id:'10',name:'Strings'},
	  {id:'11',name:'Woodwind'},
	  {id:'12',name:'Vocal 1'},
	  {id:'13',name:'Vocal 2'},
	  {id:'14',name:'Vocal 3'},
	  {id:'15',name:'Vocal 4'}
	];
}

function MusicianCreateCtrl($scope, $location)
{
	
}