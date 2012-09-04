(function () {


function PeopleListCtrl($scope, Musicians)
{
	
	

	Musicians.get( function(data){
			$scope.musicians = data.musicians;
	});
	
	$scope.deleteMusician = function(musicianToRemove) {
	
		var index = $scope.musicians.indexOf(musicianToRemove);
		
		$scope.musicians.splice(index,1);
	
		Musicians.remove({"id":musicianToRemove.id}); 
	
	};

	
};

//PeopleListCtrl.$inject = ['$scope', '$http'];


function SongListCtrl($scope, $http , Musicians, Songs)
{
	
	Musicians.get( function(d) {
		 $scope.musicians = d.musicians;
	});
	
	Songs.get( function(d) {
		 $scope.songs = d.songs;
	});
	

};

function MusicianEditCtrl($scope, $location, Musicians, Instruments, $routeParams)
{
	//alert($http);
	
	$scope.editHeading = "Edit User";
	
	if($routeParams.personId)
	{
		Musicians.get({"id":$routeParams.personId}, function(data){
			$scope.musician = data;
		});
	} else {
		$scope.editHeading = "New User";
		$scope.musician = {name:'',description:'',instruments:[]};
	}
	
	Instruments.get( function(data) {
		$scope.instruments = data.instruments;
	});
	
	$scope.hasInstrument = function(instrumentToCheck) {
		for(mi in $scope.musician.instruments)
		{
			if($scope.musician.instruments[mi].id == instrumentToCheck.id)
				return true;
		}
		return false;
	};
	
	$scope.change = function(instrumentToChange, checked)
	{
		//alert(checked); 
		if(checked)
		{
			$scope.musician.instruments.push(instrumentToChange);
		} else {
			
			var index = -1;
			
			//Can't us indexOf because $$haskey attribute assigned by ng interferes
			for(i in $scope.musician.instruments)
			{
				if($scope.musician.instruments[i].id == instrumentToChange.id)
				{
					index = i;
				}
			}

			$scope.musician.instruments.splice(index,1);
		}

	};
	
	$scope.save = function() {
	

		Musicians.save($scope.musician, function(data) {
			alert("saved successful");
			$location.path("/people");
		});
	};
	
	$scope.cancel = function() {
		$location.replace().path("/people/");
	};
};

function SongEditCtrl($scope, $location, $http, $routeParams)
{
	//alert($http);
	
	$scope.editHeading = "Edit Song";
	
	if($routeParams.songId)
	{
		$scope.song = $http.get('/planit/rest/songs/'+$routeParams.songId).success(function(data){
			$scope.song = data;
		});
	} else {
		$scope.editHeading = "New Song";
		$scope.song = {name:'',composer:'',arrangements:[]};
	}

	
	$scope.save = function() {
		//alert($http);
		
		//$scope.song.arrangements.push({name:'jt',description:'my arrangement'});
		
		$http.post('/planit/rest/songs/save',$scope.song).success(function(data) {
			alert("saved successful");
			$location.path("/songs");
		}).error(function(data, status, headers, config) {
			alert(status);
		});
	};
	
	$scope.cancel = function() {
		$location.replace().path("/songs/");
	};
};

function ArrangementEditCtrl($scope, $location, $http, $routeParams)
{
	//alert($http);
	
	$scope.editHeading = "Edit Arrangement";
	
	
};

angular.module('planitmod', ['planitservices'])
.config(function($routeProvider) {
	$routeProvider

		.when('/people/create', {controller:MusicianEditCtrl, templateUrl:'person_detail.html'})
		.when('/people', {controller:PeopleListCtrl, templateUrl:'people.html'})
		//.when('/people/:personId', {controller:MusicianListCtrl, templateUrl:'people.html'})
		
		.when('/people/edit/:personId', {controller:MusicianEditCtrl, templateUrl:'person_detail.html'})
		
		.when('/songs/create', {controller:SongEditCtrl, templateUrl:'song_detail.html'})
		.when('/songs/edit/:songId', {controller:SongEditCtrl, templateUrl:'song_detail.html'})
		.when('/songs', {controller:SongListCtrl, templateUrl:'songs.html'})
		
		.when('/services', {controller:SongListCtrl, templateUrl:'services.html'})
		
		.when('/welcome', {templateUrl:'choose.html'})
		
		.otherwise({redirectTo:'/welcome'});

});


angular.module('planitservices', ['ngResource']).
	factory('Musicians', function($resource) {
		var Musicians = $resource('/planit/rest/musicians/:id',
				{remove: {method:'DELETE'}}
		);
	
		return Musicians; 
	}).
	factory('Songs', function($resource) {
		var Songs = $resource('/planit/rest/songs');
	
		return Songs;
	}).
	factory('Instruments', function($resource) {
		var Instruments = $resource('/planit/rest/instruments');
	
		return Instruments;
	});


})();
