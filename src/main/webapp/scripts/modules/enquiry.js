var enquiryModule = angular.module('enquiryModule', []);

enquiryModule.controller('enquiryController', ['$scope','enquiryService','masterdataService',function($scope,enquiryService,masterdataService) {

	// Data variables.
	$scope.form={};
	$scope.data={};
	$scope.searchCriteria={};
	$scope.dueEnquiries=[];
	$scope.data.enquiry={};
	$scope.data.task=[];
	$scope.enqCriteria={};
	$scope.serverModelData={};
	
	// Variables for show and hiding.
	$scope.processing=false;
	$scope.showCriteria=false;
	$scope.addReminder=false;
	$scope.form.isNew=true;
	$scope.form.isEdit=false;
	$scope.dashboard=true;
	
	$scope.getEnquiryByCriteria = function(){
		
	}
	
	$scope.getDueEnquiry=function(){
		
	}
	
	 $scope.LoadMoreData = function() {

		 $scope.currentFetchLimit += 5;

		 $scope.getDueEnquiry();

	 };
	 
	 $scope.init=function(){

		 console.log('getting masterdata for admission module in init block');

		 $scope.serverModelData = masterdataService.getAdmissionMasterData();
//		 .then(function(data) {
//			 console.log(data);
//			 if (data != null) {
//				 $scope.serverModelData = data;
//			 } else {
//				 console.log('error');
//			 }
//		 })

	 }
	 
	 $scope.addEnquiry=function(){
		 enquiryService.addEnquiry(scope.data);
	 }
	 
	 $scope.proceedToAdmission=function(){
		 enquiryService.proceedToAdmission($scope.enquiry);
	 }

} ]);

enquiryModule.service('enquiryService', function($http, $q) {

	// Return public API.
	return ({
		 getDueEnquiry : getDueEnquiry,
		 getEnquiryByCriteria : getEnquiryByCriteria,
		 addEnquiry : addEnquiry,
		 proceedToAdmission : proceedToAdmission,
		 getEnquiry : getEnquiry
	 });
	
	function getDueEnquiry() {

		 console.log('get due enquiries');
		 var request = $http({
			 method : "get",
			 url : "inquiry",
			 params : "",
			 data: ""

		 });

		 return (request.then(handleSuccess, handleError));

	 }
	
	function getEnquiry(enquiryId){
		 console.log('get due enquiries');
		 var request = $http({
			 method : "get",
			 url : "inquiry/"+enquiryId,
			 params : "",
			 data: ""

		 });

		 return (request.then(handleSuccess, handleError));

	}
	
	function getEnquiryByCriteria(EnqCriteria){
		 console.log('search enquiries');
		 var request = $http({
			 method : "get",
			 url : "inquiry",
			 params : "",
			 data: EnqCriteria

		 });

		 return (request.then(handleSuccess, handleError));
	}
	
	function addEnquiry(enquiry){
		 console.log('add new enquiry');
		 var request = $http({
			 method : "post",
			 url : "inquiry",
			 params : "",
			 data: enquiry

		 });
	}
	
	function proceedToAdmission(enquiry){
		 console.log('get due enquiries');
		 var request = $http({
			 method : "put",
			 url : "inquiry",
			 params : "",
			 data: enquiry

		 });
	}


	function handleError(response) {
		console.log('handle error');
		console.log(response);
		// The API response from the server should be returned in a
		// nomralized format. However, if the request was not handled by the
		// server (or what not handles properly - ex. server error), then we
		// may have to normalize it on our end, as best we can.
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