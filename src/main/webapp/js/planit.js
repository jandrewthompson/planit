
function Musician(name)
{
	var self = this;
	self.name = ko.observable(name);
	self.description = ko.observable();
	
}

function MusicianViewModel()
{
	$.ajaxSetup({
		  contentType: "application/json; charset=utf-8"
		});
	
	var self = this;
	
	self.musicians = ko.observableArray();
	self.newMusician = ko.observable();
	self.selectedMusician = ko.observable(); 
	self.viewHome = ko.observable("");
	self.viewMusicians = ko.observable("");
	self.cnt = 0;

	self.addMusician = function(musician)
	{
		$.post("/planit/rest/musician/add",JSON.stringify(ko.toJS(acct),null,2), function()
		{
			window.location.href="#/";
			
		}, "json");

	};
	
	self.removeMusician = function(musician)
	{
	//	window.location.href="#/remove/"+acct.id;
		var del = confirm("Are you sure you want to delete "+ musician.name +"?");
		if(del == true)
			self.musicians.remove(musician);
		//window.location.href="#/";

	};
	
	Sammy(function() {
		
		
		this.get("#/musicians", function() {
			//this.partial('files/account.html');
			$.get("rest/musicians", '', 
					function(data) {
						self.musicians(data.musicians);
					}
			);
		});
		
		this.get("#/resources", function() {
			
			$.get("rest/musicians", '', 
					function(data) {
						self.musicians(data.musicians);
					}
			);
			
			//alert(self.musicians());
			
			self.viewHome("");
			self.viewMusicians("true");
		});
		
		this.get("#/", function() {
			//this.partial('files/account.html');
			$.get("rest/musicians", '', 
					function(data)	{
						self.musicians(data.musicians);
					}
			);
			//$("#main").append("<strong>you're home</strong>");
			//alert("testing....");
			//self.newAccountData("");
			self.viewHome("true");
			self.viewMusicians("");
			
		});
		
		this.get("#/new", function() {
			//self.newAccountData({name: "NEW ACCOUNT" + self.cnt++});
		});
		
		
		
	}).run();
};


$(function() {
	
	ko.applyBindings(new MusicianViewModel());
	
	$("#msg").ajaxError(function(event,request,settings) 
	{
		if(request.status == 403)
		$(this).append("<strong>Error "+ request.status +" requesting URI: " + settings.url);
	});
	
	setInterval(function() {
		
		$.ajaxSetup({
		  contentType: "text/plain; charset=utf-8"
		});
		
		$.get("session", '', 
				function(data) {
					$("#msg").append(data);
				}
		);
	}, 
	3000);
	
});
