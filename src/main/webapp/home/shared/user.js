var userModule = angular.module('userModule', []);

userModule
.controller(
		'userController',
		[
		 '$scope',
		 '$state',
		 '$rootScope',
		 'userService',
		 function($scope, $state, $rootScope,userService) {
			 $scope.form={};
			 
			 $scope.authenticateUser=function(){
				 userService.authenticateUser($scope.form);
			 }
			 
			 $scope.getUser=function(){
				 userService.getUser()
				 .then(
						 function(response) {
							 console
							 .log('Data received from service in controller : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $rootScope.user=response.data.responseBody
								 }

						 })
	 

			 }
			 
		 } ]);

userModule.service('userService', function($http, $q) {

	 // Return public API.
	 return ({
		 authenticateUser : authenticateUser,
		 getUser : getUser
	 });
	
	 function authenticateUser(form) {
		  $http({
		  method  : 'POST',
		  url     : 'j_spring_security_check',
		  data    : $.param(form),  // pass in data as strings
		  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
		 })
		  .success(function(data) {
		    console.log(data);

		    if (!data.success) {
		      // if not successful, bind errors to error variables
		      $scope.errorName = data.errors.name;
		      $scope.errorSuperhero = data.errors.superheroAlias;
		    } else {
		      // if successful, bind success message to message
		      $scope.message = data.message;
		    }
		  });
		};
		
	 function getUser(){

		 console.log('Getting user in service');
			 var request = $http({
				 method : "get",
				 url : "service/user/loggedinuser/",
				 params : {
					 action : "get"
				 }
			 });
			 return (request.then(handleSuccess, handleError));
		 }
	 function handleError(response) {
		 console.log('Error occured while calling service');
		 console.log(response);
		 if (!angular.isObject(response.data) || !response.data.message) {

			 return ($q.reject("An unknown error occurred."));

		 }
		 // Otherwise, use expected error message.
		 return ($q.reject(response.data.message));
	 }

	 // I transform the successful response, unwrapping the application data
	 // from the API response payload.
	 function handleSuccess(response) {
		 console.log('handle success');
		 console.log(response);
		 return (response);

	 }

});