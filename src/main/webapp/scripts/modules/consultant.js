var consultantModule = angular.module('consultantModule', []);

consultantModule.controller('consultantController', ['$scope','consultantService','masterdataService',function($scope,consultantService,masterdataService) {

	// Data variables.
	$scope.form={};
	$scope.searchCriteria={};
	$scope.dueEnquiries=[];
	$scope.consultant={};
	$scope.searchRes=[];
	
	// Variables for show and hiding.
	$scope.processing=false;
	$scope.showCriteria=false;
	$scope.form.isNew=true;
	$scope.form.isEdit=false;
	$scope.dashboard=true;
	$scope.addNew=false;
	$scope.searchResult=false;
	
/*	$scope.getEnquiryByCriteria = function(){
		
	}*/
	
/*	$scope.getDueEnquiry=function(){
		
	}*/
	
/*	 $scope.LoadMoreData = function() {

		 $scope.currentFetchLimit += 5;

		 $scope.getDueEnquiry();

	 };*/
	 
	 $scope.search = function(searchCriteria){
		 consultantService.getConsultantByCriteria(searchCriteria)
		 .then(function(response) {
			 console.log('Data searched in controller : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.searchRes = response.data.responseBody;
				
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }

		 })
		 searchResult = true;
	 }
	 
	 $scope.saveConsultant=function(){
		 if($scope.consultant.consultantId){
			updateConsultant();
		 }else{
			addConsultant();
		 }
	 }
	 
	 $scope.addConsultant = function(){
		 consultantService.addConsultant($scope.consultant)
		 .then(function(response) {
		 console.log('Adding consultant : ');
		 console.log(response);
		 if (response != null && response.data != null && response.data.responseBody != null) {
			 $scope.consultant = response.data.responseBody;
			
		 } else {
			 console.log(response.data.error);
			 alert(response.data.error);
		 }

	 })
	 }
	
	 
	 $scope.updateConsultant=function(){
		 consultantService.updateConsultant($scope.consultant);
	 }
	 
	 $scope.resetForm = function(){
		 consultant = {};
	 }
	 
	 $scope.showDetail = function(index){
		 var consoltantId = $scope.searchRes[index].consultantId;
		 consultantService.getConsultant(consoltantId)
		 .then(function(response) {
			 console.log('Data received by consultantID in controller : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.consultant = response.data.responseBody;
				
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }

		 })
		 searchResult = false;
		 addNew = true;
		 form.isEdit=true;
	 }
	 
} ]);

consultantModule.service('consultantService', function($http, $q) {

	// Return public API.
	return ({
		 addConsultant : addConsultant,
		 getConsultantByCriteria : getConsultantByCriteria,
		 updateConsultant : updateConsultant,
		 getConsultant : getConsultant
	 });
	
	function addConsultant(consultant){
		 console.log('add consultant enquiries');
		 var request = $http({
			 method : "post",
			 url : "consultant/consultantMaster",
			 params : "",
			 data: consultant

		 });

		 return (request.then(handleSuccess, handleError));

	}
	
	function getConsultant(consultantId){
		 console.log('get due enquiries');
		 var request = $http({
			 method : "get",
			 url : "consultant/consultantMaster/"+consultantId,
			 params : "",
			 data: ""

		 });

		 return (request.then(handleSuccess, handleError));

	}
	
	function getConsultantByCriteria(searchCriteria){
		 console.log('search enquiries');
		 var request = $http({
			 method : "get",
			 url : "consultant",
			 params : "",
			 data: searchCriteria

		 });

		 return (request.then(handleSuccess, handleError));
	}
	
	function addConsultant(consultant){
		 console.log('add new enquiry');
		 var request = $http({
			 method : "post",
			 url : "consultant/consultantMaster",
			 params : "",
			 data: consultant

		 });
	}
	
	function updateConsultant(consultant){
		 console.log('add new enquiry');
		 var request = $http({
			 method : "put",
			 url : "consultant",
			 params : "",
			 data: consultant

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