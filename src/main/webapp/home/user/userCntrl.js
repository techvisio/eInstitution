var userModule = angular.module('userModule', []);

userModule
.controller(
		'userController',
		[
		 '$scope',
		 '$state',
		 '$rootScope',
		 'userService',
		 'injectedData',
		 function($scope, $state, $rootScope,userService,injectedData) {
			 $scope.form={};
			 $scope.user={};
			 $scope.user.roles=[];
			 
			 $scope.allUserRoles=[];
			 if(injectedData.data){
				 $scope.user = injectedData.data.responseBody;
			 }			 
			 $scope.authenticateUser=function(){
				 userService.authenticateUser($scope.form);
			 }


			 $scope.redirectViewUser=function(currentUserId){
				 $state.go('getUser',{userId:currentUserId});
			 }

			 
			 $scope.addAndRemoveRoleFromUser = function(object){
				 
				 if($scope.user.roles.indexOf(object)<0){
					 $scope.user.roles.push(object);					 
				 }
				 else{
					 $scope.user.roles.splice(object);					 
				 }
			 }

			 $scope.getAuthenticatedUser=function(){
				 userService.getAuthenticatedUser()
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

			 $scope.addUser=function(){
				 userService.addUser($scope.user)
				 .then(
						 function(response) {
							 console
							 .log('user Data received from service in controller : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.user=response.data.responseBody;
								 $scope.redirectViewUser($scope.user.userId)
							 }

						 })
			 }

			 $scope.getUserRole=function(){
				 userService.getUserRole($scope.user.userId)
				 .then(
						 function(response) {
							 console
							 .log('userRole Data received from service in controller : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.allUserRoles=response.data.responseBody;
							 }
						 })
			 }


		 } ]);
