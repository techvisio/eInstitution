var enquiryModule = angular.module('enquiryModule', []);

enquiryModule.controller('enquiryController', ['$scope','enquiryService','masterdataService',function($scope,enquiryService,masterdataService) {

	// Data variables.
	
	$scope.admissionInquiry={};
	$scope.form={};
	$scope.data={};
	$scope.searchCriteria={};
	$scope.dueEnquiries=[];
	$scope.data.enquiry={};
	$scope.data.task=[];
	$scope.serverModelData={};
	
	// Variables for show and hiding.
	$scope.processing=false;
	$scope.showCriteria=false;
	$scope.addReminder=false;
	$scope.form.isNew=true;
	$scope.form.isEdit=false;
	$scope.dashboard=true;
	
	$scope.getEnquiryBySearchCriteria = function() {
		 console.log('get enquiry by search criteria in controller');
		 console.log($scope.searchCriteria);
		 enquiryService.getEnquiryBySearchCriteria($scope.searchCriteria)
		 .then(function(response) {
			 console.log('Data received from service in controller : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.data.enquiry = response.data.responseBody;
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }

		 })


	 }

	 $scope.getEnquiry = function() {
		
		 var enquiryId=prompt("Enter File No", "");
		 
		 enquiryService.getEnquiry(enquiryId)
		 .then(function(response) {
			 console.log('Data received from service : ');
			 console.log(response);
			 if (response !=null && response.data != null && response.data.responseBody != null) {
				$scope.data.enquiry = response.data.responseBody;

			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })

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
	 
	 $scope.addEnquiry = function() {
		 console.log('add student called');
		 console.log($scope.student);
		 enquiryService.addEnquiry($scope.admissionInquiry)
		 .then(function(response) {
			 console.log('Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.admissionInquiry = response.data.responseBody;
				 alert("Your Records Saved Successfully")
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }

		 })


	 }
 
	 $scope.proceedToAdmission=function(){
		 enquiryService.proceedToAdmission($scope.enquiry);
	 }

} ]);

enquiryModule.service('enquiryService', function($http, $q) {

	// Return public API.
	return ({
		 getDueEnquiry : getDueEnquiry,
		 getEnquiryBySearchCriteria : getEnquiryBySearchCriteria,
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
	
	function getEnquiry(enquiryId) {

		 var request = $http({
			 method : "get",
			 url : "inquiry/"+enquiryId,
			 params : {
				 action : "get"
			 }
		 });

		 return (request.then(handleSuccess, handleError));

	 }
	
	 function getEnquiryBySearchCriteria(searchCriteria){

		 console.log('Getting enquiry by search criteria in service');
		 var request = $http({
			 method : "post",
			 url : "inquiry/search/",
			 params : "",
			 data : searchCriteria

		 });

		 return (request.then(handleSuccess, handleError));
	 }

	 
	 function addEnquiry(admissionInquiry){
		 console.log('add new enquiry');
		 var request = $http({
			 method : "post",
			 url : "inquiry",
			 params : "",
			 data: admissionInquiry

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