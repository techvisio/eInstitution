var managementModule = angular.module('managementModule', ['ui.bootstrap']);

managementModule.controller('managementController',['$scope','managementService', function($scope,managementService) {
	$scope.unapprovedRecords=[];
	$scope.currentFetchLimit=20;
	$scope.form.fileNo="";
	$scope.managementData={};
	
	$scope.getDetails = function(){
		$scope.form.fileNo=prompt("Provide file no","");
		$scope.getAdmissionDetail();
	};
	
	$scope.getUnapprovedRecords=function(){
		managementService.getUnapprovedList($scope.currentFetchLimit)
		.then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.unapprovedRecords = response.data.responseBody;
					 } else {
						 console.log(response.data.error);
					 }
				 })
	};
	
	$scope.getAdmissionDetail=function(){
		managementService.getAdmissionDetail($scope.form.fileNo)
		.then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.managementData = response.data.responseBody;
					 } else {
						 console.log(response.data.error);
					 }
				 })
	};
}]);

managementModule.service("managementService", function($http, $q) {

	 // Return public API.
	 return ({
		 getUnapprovedList : getUnapprovedList,
		 getAdmissionDetail: getAdmissionDetail
	 });

	 function getUnapprovedList(size) {

		 console.log('Get unapproved list service');
		 var request = $http({
			 method : "get",
			 url : "admission/uapprovedList/"+size,
			 params : ""

		 });

		 return (request.then(handleSuccess, handleError));

	 }
	 
	 function getAdmissionDetail(fileNo)
	 {

		 console.log('Get admission detail service');
		 var request = $http({
			 method : "get",
			 url : "management/admission/approval/"+fileNo,
			 params : ""

		 });

		 return (request.then(handleSuccess, handleError));

	 }

		 
	 function handleError(response) {
		 console.log('Error occured while calling service');
		 console.log(response);
		 if (!angular.isObject(response.data) || !response.data.message) {

			 return ($q.reject("An unknown error occurred."));

		 }

		 return ($q.reject(response.data.message));

	 }

	 function handleSuccess(response) {
		 console.log('handle success');
		 console.log(response);
		 return (response);

	 }

	 

});