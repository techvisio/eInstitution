var enquiryModule = angular.module('enquiryModule', ['ngGrid']);

enquiryModule.controller('enquiryController', ['$scope','enquiryService','masterdataService',function($scope,enquiryService,masterdataService) {

	// Data variables.
	$scope.form={};
	$scope.data={};
	$scope.data.admissionEnquiry={};
	$scope.data.tasks=[];
	$scope.searchCriteria={};
	$scope.dueEnquiries=[];
	$scope.searchEnquiryList=[];
	$scope.filteredSearch=[];
	
	$scope.serverModelData={};
	
	// Variables for show and hiding.
	$scope.processing=false;
	$scope.showCriteria=false;
	$scope.addReminder=false;
	$scope.form.isNew=true;
	$scope.form.isEdit=true;
	$scope.dashboard=true;

	 $scope.itemsPerPage = 3
	  $scope.currentPage = 0;
	 $scope.totalItems = 0;

	  $scope.pageCount = function () {
	    return Math.ceil($scope.searchEnquiryList.length / $scope.itemsPerPage);
	  };
	  
	 $scope.dummyTask = {
			 "taskDate" : null,
			 "remark" : null,
			
	 };
	 
	 
	 $scope.admissionMode={"A":"Consultant",
			 "W":"Walk-In",
			 "R":"Referral"};
	 
	 $scope.resetForm = function(){
		 console.log("calling reset.....")
		 $scope.data = {};
		 $scope.data.admissionEnquiry={};
		 $scope.data.tasks=[];
		 $scope.data.tasks.push(angular.copy($scope.dummyTask));
	 }

	 $scope.backtoDashboard = function(){
		 $scope.dashboard=true;
	 }

	$scope.isTaskClosedOrInAdmission=function(){
		return $scope.data.admissionEnquiry.applicationStatus=='CLOSED'||$scope.data.admissionEnquiry.applicationStatus=='MOVED_TO_ADMISSION';
	}
	
	 $scope.closeTask = function(task) {
		
		 if(!task.remark){
			
			 alert("you must provide remark")
            return;
		 }
		 var cnfirm=confirm("closing a task can not be undone. \n would you like to continue?");
		 
		 if(cnfirm){
			 
			 task.status='C';
			 
		 }
        
	  
	 }
	$scope.getEnquiryBySearchCriteria = function() {
		 console.log('get enquiry by search criteria in controller');
		 $scope.currentPage = 0;
		 enquiryService.getEnquiryBySearchCriteria($scope.searchCriteria)
		 .then(function(response) {
			 console.log('Data received from service in controller : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.searchEnquiryList = response.data.responseBody;
				 if($scope.searchEnquiryList.length>0){
					 $scope.showCriteria=false;
					 //$scope.dashboard = false;
					 $scope.currentPage=1;
					 $scope.totalItems = $scope.searchEnquiryList.length;
				 }
				 else
					 {
					 var createNew=confirm('No records availble for given criteria.\n Would you like to create One?');
					 if(createNew){
						 $scope.resetForm();
						 $scope.data.admissionEnquiry.courseId=angular.copy($scope.searchCriteria.courseId);
						 $scope.data.admissionEnquiry.branchId=angular.copy($scope.searchCriteria.branchId);
						 $scope.data.admissionEnquiry.name=angular.copy($scope.searchCriteria.name);
						 $scope.data.admissionEnquiry.emailId=angular.copy($scope.searchCriteria.emailId);
						 $scope.data.admissionEnquiry.contactNo=angular.copy($scope.searchCriteria.mobileNo);
						 $scope.dashboard = false;
					 }
					 }
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }

		 })

	 }
	 $scope.showDetail =  function(index){

		 var enquiryId=$scope.dueEnquiries[index].enquiryId;
		 if(enquiryId){
		 $scope.getEnquiry(enquiryId);
		 $scope.dashboard=false;
		 }
		 else
			 {
			 alert("No valid enquiryId provided");
			 }
	 }


	$scope.getDueEnquiry = function(){

		console.log('get enquiry by taskId in controller');
		 enquiryService.getDueEnquiry()
		 .then(function(response) {
			 console.log('Data received by taskId in controller : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.dueEnquiries = response.data.responseBody;
				
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }

		 })
	}
		
		
	 $scope.getEnquiry = function(enquiryId) {
		 enquiryService.getEnquiry(enquiryId)
		 .then(function(response) {
			 console.log('Data received from service : ');
			 console.log(response);
			 if (response !=null && response.data != null && response.data.responseBody != null) {
				$scope.data = response.data.responseBody;
				$scope.dashboard=false;
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })

	 }

	
	 $scope.LoadMoreData = function() {

		 $scope.currentFetchLimit += 5;

		 $scope.getDueEnquiry();

	 };
	 
	 $scope.init=function(){	
		 
	console.log('getting masterdata for Enquiry module in init block');

	 masterdataService.getAdmissionMasterData()
	 .then(function(data) {
		 console.log(data);
		 if (data != null) {
			 $scope.serverModelData = data;
		 } else {
			 console.log('error');
		 }
	 })}
	 
$scope.AddTask = function(){
		 $scope.data.tasks.push(angular.copy($scope.dummyTask));
	 }

$scope.saveEnquiry = function(){
	
	 if(!$scope.data.admissionEnquiry.enquiryId){
		
		 $scope.addEnquiry();
		
	 }
	 else
	 {
		 $scope.updateEnquiry();
	 }

	 $scope.getDueEnquiry();
}

	 $scope.addEnquiry = function() {
		 console.log('add enquiry called');
		 enquiryService.addEnquiry($scope.data)
		 .then(function(response) {
			 console.log('Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.data = response.data.responseBody;
				 $scope.form.isNew=false;
				 $scope.form.isEdit=false;
				 alert("Your Records Saved Successfully")
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })
	 }

	 $scope.updateEnquiry = function() {
		 console.log('update enquiry called');
		 enquiryService.updateEnquiry($scope.data)
		 .then(function(response) {
			 console.log('udpate Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.data = response.data.responseBody;
				 $scope.form.isNew=false;
				 $scope.form.isEdit=false;
				 alert("Your Records has been updated Successfully")
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })
	 }
	 
	 $scope.proceedToAdmission=function(){
		 enquiryService.proceedToAdmission($scope.data)
		 .then(function(response) {
			 console.log('proceed to admission Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.data = response.data.responseBody;
				 alert("Successfully Moved to Admission\n Registration No. "+$scope.data.admissionEnquiry.registrationNo);
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })

	 }

	 
	 $scope.toggleEnquiryStatus=function(){
		 enquiryService.closeEnquiry($scope.data)
		 .then(function(response) {
			 console.log('close enquiry Data received from service : ');
			 console.log(response);
			 if (response != null && response.data != null && response.data.responseBody != null) {
				 $scope.data = response.data.responseBody;
				 alert("Enquiry Status updated")
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })

	 }

	 $scope.numPages = function () {
		    return Math.ceil($scope.searchEnquiryList.length / $scope.itemsPerPage);
		  };

		  $scope.$watch('currentPage + itemsPerPage', function() {
		    var begin = (($scope.currentPage - 1) * $scope.itemsPerPage)
		    , end = begin + $scope.itemsPerPage;

		    $scope.filteredSearch = $scope.searchEnquiryList.slice(begin, end);
		  });
		  
		  $scope.gridOptions = {
			      multiSelect:false,
			        data: 'filteredSearch',
			        rowTemplate: '<div ng-dblclick="getEnquiry(row.config.selectedItems[0].enquiryId)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
			        columnDefs: [{ field: "name", width: 180,displayName :"Name"},
			                    { field: "fatherName", width: 180,displayName :"Father Name" },
			                    { field: "course.course", width: 140,displayName :"Course" },
			                    { field: "branch.branchName", width: 180,displayName :"Branch" },
			                    {field:"applicationStatus",width:200,displayName :"Status"}]
			    };
} ]);
