
var managementModule = angular.module('managementModule', ['ui.bootstrap']);

managementModule.controller('managementController',['$scope','managementService', function($scope,managementService) {
	
	$scope.unapprovedRecords=[];
	$scope.currentFetchLimit=20;
	$scope.admissionDetailManagement={};
	
	$scope.feeTransactionAdmissionBean = {};
	
//	$scope.getAdmissionDetailsManagement = function(){
//		var fileNo = $scope.feeTransactionAdmissionBean.basicInfo.fileNo;
//		managementService.getPreviousBalace(fileNo).then(function(response) {
//			 console.log('get privious balance data received from service : ');
//			 console.log(response);
//			 if (response != null && response.data != null && response.data.responseBody != null) {
//				 $scope.feeTransactionAdmissionBean = response.data.responseBody;
//			 } else {
//				 console.log(response.data.error);
//			 }
//		 })
//};

//	$scope.form.fileNo=$scope.feeTransactionAdmissionBean.studentBasicInfo.fileNo;
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
	
	$scope.getAdmissionDetailManagement=function(){
		var fileNo=prompt("Enter File No", "");
		
		managementService.getAdmissionDetailManagement(fileNo)
		.then(function(response) {
					 console.log('Admission detail for management received from service : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.admissionDetailManagement = response.data.responseBody;
					 } else {
						 console.log(response.data.error);
					 }
				 })
	};


	$scope.updateAdmissionDetailManagement=function(){
		managementService.updateAdmissionDetailManagement($scope.admissionDetailManagement)
		.then(function(response) {
					 console.log('update Admission detail for management received from service in controller : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.updatedDetail = response.data.responseBody;
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
		 getAdmissionDetailManagement : getAdmissionDetailManagement,
		 updateAdmissionDetailManagement : updateAdmissionDetailManagement
	 });

	 function getUnapprovedList(size) {

		 console.log('Get unapproved list service');
		 var request = $http({
			 method : "get",
			 url : "management/uapprovedList/"+size,
			 params : ""

		 });

		 return (request.then(handleSuccess, handleError));

	 }
	 
	 
//	 function getAdmissionDetailsManagement(fileNo) {
//
//		 console.log('getting previous balance in service');
//		 var request = $http({
//			 method : "get",
//			 url : "fee/feeTransaction/"+fileNo,
//			 params : ""
//
//		 });
//
//		 
//		 return (request.then(handleSuccess, handleError));
//
//	 }

	 function getAdmissionDetailManagement(fileNo) {
	 
	 		 console.log('getting Admission detail for management in service');
	 		 var request = $http({
	 			 method : "get",
	 			 url : "management/admission/approval/"+fileNo,
	 			 params : ""
	 
	 		 });
	 		 
		 return (request.then(handleSuccess, handleError));
	 
	 	 }
	 
	 
	 function updateAdmissionDetailManagement(student) {

		 console.log('update Admission Detail for Management called in service');
		 var request = $http({
			 method : "put",
			 url : "management/updateManagementChanges/",
			 params : "",
			 data : student

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
