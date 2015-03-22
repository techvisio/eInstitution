var enquiryModule = angular.module('enquiryModule', ['masterdataModule']);

enquiryModule.controller('enquiryController', ['$scope','enquiryService','masterdataService',function($scope,enquiryService,masterdataService) {

	// Data variables.
	$scope.form={};
	$scope.data={};
	$scope.searchCriteria={};
	$scope.dueEnquiries=[];
	$scope.data.enquiry={};
	$scope.data.task=[];
	
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
	 
	 $scope.getmaster = function(entity){
		 return masterdataService.get(entity);
	 }
	 
	 $scope.addEnquiry=function(){
		 enquiryService.addEnquiry($scope.enquiry);
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
		 proceedToAdmission : proceedToAdmission
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
	
	function getEnquiryByCriteria(){
		
	}
	
	function addEnquiry(enquiry){
		
	}
	
	function proceedToAdmission(enquiry){
		
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