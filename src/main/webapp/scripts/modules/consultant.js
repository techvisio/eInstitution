var consultantModule = angular.module('consultantModule', []);

consultantModule.controller('consultantController', ['$scope','consultantService','masterdataService',function($scope,consultantService,masterdataService) {

	// Data variables.
	$scope.form={};
	$scope.consultantAdmissionDetail={};
	$scope.consultantAdmissionDetail.consultantDetails=[];
	$scope.consultantAdmissionDetail.consultantDetails.push(angular.copy($scope.dummyConsultantDetails));
	
	$scope.searchCriteria={};
	$scope.dueEnquiries=[];
	$scope.consultant={};
	$scope.consultantList=[];
	$scope.searchRes=[];
	$scope.form.content='dashboard';
	// Variables for show and hiding.
	$scope.processing=false;
	$scope.showCriteria=false;
	$scope.form.isNew=true;
	$scope.form.isEdit=false;
	$scope.dashboard=true;
	$scope.addNew=false;
	$scope.searchResult=false;
	
	
	$scope.dummyConsultantDetails = {"consultant":{},"consultantPaymentCriterias" : [ {} ], "consultantPaymentDetail" : [ {} ]};
	
	$scope.dummyConsultantPaymentCriterias = {};
	
	$scope.dummyConsultantPaymentDetail = {};
	
	
	 $scope.addConsultantPaymentCriteria = function(object) {
		 var consultantPaymentCriteria = angular
		 .copy($scope.dummyConsultantPaymentCriterias);
		 consultantPaymentCriteria.consultantId = object.consultant.consultantId;
		 object.consultantPaymentCriterias
		 .push(consultantPaymentCriteria);
	 };

	 $scope.removeConsultantPaymentCriteria = function(object, index) {
		 console.log(index);
		 object.consultantPaymentCriterias.splice(index, 1);
	 };

	 $scope.addConsultantPaymentDtl = function(object) {
		 var consultantPaymentDtl = angular
		 .copy($scope.dummyConsultantPaymentDetail);
		 consultantPaymentDtl.consultantId = object.consultant.consultantId;
		 object.consultantPaymentDetail
		 .push(consultantPaymentDtl);
	 };

	 $scope.removeConsultantPaymentDtl = function(object, index) {
		 console.log(index);
		 object.consultantPaymentDetail.splice(index, 1);
	 };
	
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
			$scope.updateConsultant();
		 }else{
			 $scope.addConsultant();
		 }
	 }
	 
	 $scope.init=function(){

		 console.log('getting masterdata for admission module in init block');

		 masterdataService.getAdmissionMasterData()
		 .then(function(data) {
			 console.log(data);
			 if (data != null) {
				 $scope.serverModelData = data;
			 } else {
				 console.log('error');
			 }
		 })
	 };

	 $scope.addConsultant = function() {
		 console.log('add consultant called');
		 consultantService.addConsultant($scope.consultant)
		 .then(function(response) {
			 console.log('Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.consultant = response.data.responseBody;
				 
				 alert("Your Records Saved Successfully")
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })
	 };
	
	 $scope.updateConsultant=function(){
		 consultantService.updateConsultant($scope.consultant);
	 }
	 
	 $scope.getConsultantAdmissionDetail = function(){
	
		 console.log('getConsultantAdmissionDetail called in controller');
          var fileNo = prompt("Enter File No"+" ")	 
		 consultantService.getConsultantAdmissionDetail(fileNo)
		 .then(function(response) {
			 console.log('consultantAdmission Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.consultantAdmissionDetail = response.data.responseBody;
				 
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })
	 };

	 $scope.addConsultantAdmissionDetail = function(){
		 console.log('addConsultantAdmissionDetail called in controller');
		 consultantService.addConsultantAdmissionDetail($scope.consultantAdmissionDetail)
		 .then(function(response) {
			 console.log('addConsultantAdmissionDetail Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.consultantAdmissionDetail = response.data.responseBody;
				 alert("Your Records Saved Successfully")
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })
	 };
	
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
		 addConsultant  : addConsultant,
		 getConsultantByCriteria : getConsultantByCriteria,
		 updateConsultant : updateConsultant,
		 getConsultant : getConsultant,
		 getConsultantAdmissionDetail : getConsultantAdmissionDetail,
		 addConsultantAdmissionDetail : addConsultantAdmissionDetail
	 });
	
	function addConsultant(consultant){
		 console.log('add new enquiry');
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
			 method : "post",
			 url : "consultant/search/",
			 params : "",
			 data: searchCriteria

		 });

		 return (request.then(handleSuccess, handleError));
	}
	
	function addConsultant(consultant){
		 console.log('add new enquiry');
		 var request = $http({
			 method : "post",
			 url : "consultant/consultantMaster/",
			 params : "",
			 data: consultant

		 });
		 
		 return (request.then(handleSuccess, handleError));
	}
	
	function updateConsultant(consultant){
		 console.log('update consultant called in service');
		 var request = $http({
			 method : "put",
			 url : "consultant/consultantMaster/",
			 params : "",
			 data: consultant

		 });
		 
		 return (request.then(handleSuccess, handleError));
	    }
	
	function getConsultantAdmissionDetail(fileNo){
		console.log('getConsultantAdmissionDetail called in service')
		var request = $http({
			 method : "get",
			 url : "consultant/consultantAdmission/"+fileNo,
			 params : {
				 action : "get"
			 }
		 });

		 return (request.then(handleSuccess, handleError));
	}

	function addConsultantAdmissionDetail(consultantAdmissionDetail){
		console.log('addConsultantAdmissionDetail called in service');
		 var request = $http({
			 method : "post",
			 url : "consultant/consultantAdmission/",
			 params : "",
			 data: consultantAdmissionDetail

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