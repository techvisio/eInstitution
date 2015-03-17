var managementModule = angular.module('managementModule', ['ui.bootstrap']);

managementModule.controller('managementController',['$scope','managementService', function($scope,managementService) {
	
	$scope.unapprovedRecords=[];
	$scope.currentFetchLimit=20;
	$scope.feeTransactionAdmissionBean = {};
	
	$scope.getPreviousBalace = function(){
		var fileNo = $scope.feeTransactionAdmissionBean.basicInfo.fileNo;
		managementService.getPreviousBalace(fileNo).then(function(response) {
			 console.log('get privious balance data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.feeTransactionAdmissionBean = response.data.responseBody;
			 } else {
				 console.log(response.data.error);
			 }
		 })
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
	
}]);

managementModule.service("managementService", function($http, $q) {

	 // Return public API.
	 return ({
		 getUnapprovedList : getUnapprovedList,
		 getPreviousBalace : getPreviousBalace
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

	 
	 function getPreviousBalace(fileNo) {

		 console.log('getting previous balance in service');
		 var request = $http({
			 method : "get",
			 url : "fee/feeTransaction/"+fileNo,
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