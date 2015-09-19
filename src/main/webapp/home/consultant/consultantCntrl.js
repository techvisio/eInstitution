var consultantModule = angular.module('consultantModule', []);

consultantModule.controller('consultantController', ['$scope','consultantService','masterdataService','$state',
                                                     '$rootScope',
                                                     'injectedData','$modal','masterdataService',function($scope,consultantService,masterdataService,$state,
                                                    		 $rootScope,
                                                    		 injectedData,$modal,masterdataService) {

	// Data variables.
	$scope.form={};
	$scope.AdmissnConsltntDtl={};
	$scope.consultantAdmissionDetail={};
	$scope.consultantAdmissionDetail.consultantDetails=[];
	$scope.consultantAdmissionDetail.consultantDetails.push(angular.copy($scope.dummyConsultantDetails));
	if(injectedData.data){
		 $scope.consultantAdmissionDetail = injectedData.data.responseBody;
		 }
	$scope.addNew=true;
	$scope.searchCriteria={};
	$scope.dueEnquiries=[];
	$scope.consultant={};
	if(injectedData.data){
		 $scope.addNew=false;
		 $scope.consultant = injectedData.data.responseBody;
		 
		 }
	$scope.consultantList=[];
	$scope.searchRes=[];
	$scope.searchResultList=[];
	$scope.filteredSearch=[];
	$scope.form.content='dashboard';

	// Variables for show and hiding.
	$scope.processing=false;
	$scope.showCriteria=true;
	$scope.form.isNew=true;
	$scope.form.isEdit=false;
	$scope.dashboard=true;
	
	$scope.getConslt=false;
	$scope.searchResult=false;
	$scope.searchType="STUDENT";

	$scope.dummyConsultantDetails = {"consultant":{},"consultantPaymentCriterias" : [ {} ], "consultantPaymentDetail" : [ {} ]};

	$scope.dummyConsultantPaymentCriterias = {};

	$scope.dummyConsultantPaymentDetail = {};


	$scope.redirectToConsultantScreen=function(currentFileNo){
		$state.go('consultant',{fileNo:currentFileNo});
	}


	$scope.redirectToConsultantM=function(currentConsultantId){
		$state.go('consultantM',{consultantId:currentConsultantId});
	}
	
	 $scope.init=function(){	
		 
			console.log('getting masterdata for Enquiry module in init block');

			 masterdataService.getAdmissionMasterDataEnquiry()
			 .then(function(data) {
				 console.log(data);
				 if (data != null) {
					 $scope.serverModelData = data.responseBody;
				 } else {
					 console.log('error');
				 }
			 })}

	
	$scope.itemsPerPage = 3;
	$scope.currentPage = 0;
	$scope.totalItems = 0;

	$scope.pageCount = function () {
		return Math.ceil($scope.searchResultList.length / $scope.itemsPerPage);
	};

	$scope.numPages = function () {
		return Math.ceil($scope.searchResultList.length / $scope.numPerPage);
	};

	$scope.$watch('currentPage + numPerPage', function() {
		var begin = (($scope.currentPage - 1) * $scope.itemsPerPage)
		, end = begin + $scope.itemsPerPage;

		$scope.filteredSearch = $scope.searchResultList.slice(begin, end);
	});

	$scope.gridOptions = {
			multiSelect:false,
			data: 'filteredSearch',
			rowTemplate: '<div ng-dblclick="redirectToConsultantScreen(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
			columnDefs: [{ field: "firstName", width: 100,displayName :"FirstName"},
			             { field: "lastName", width: 100,displayName :"LastName"},
			             { field: "fatherName", width: 180,displayName :"Father Name" },
			             { field: "course.course", width: 140,displayName :"Course" },
			             { field: "branch.branchName", width: 180,displayName :"Branch" },
			             {field:"applicationStatus",width:200,displayName :"Status"}]
	};

	$scope.consultantGridOptions = {
			multiSelect:false,
			data: 'searchRes',
			rowTemplate: '<div ng-dblclick="redirectToConsultantM(row.config.selectedItems[0].consultantId)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
			columnDefs: [{ field: "name", width: 100,displayName :"Consultant Name"},
			             { field: "primaryContactNo", width: 180,displayName :"Primary Contact No" },
			             { field: "secondaryContactNo", width: 140,displayName :"Secondary Contact No" },
			             { field: "emailId", width: 180,displayName :"Email Id" },
			             {field:"address",width:200,displayName :"Address"}]
	};
	
	$scope.getStudentByCriteria = function() {
		console.log('get student by search criteria in controller');
		console.log($scope.searchCriteria);
		$scope.processing=true;
		$scope.currentPage=0;
		consultantService.getStudentByCriteria($scope.searchCriteria)
		.then(function(response) {
			console.log('get student Data received from service in controller : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.searchResultList=response.data.responseBody;
//				$scope.consultantSearch=false;
//				$scope.studentSearch=false;
				$scope.showCriteria=false;
				$scope.currentPage=1;
				$scope.totalItems = $scope.searchResultList.length;
			} 
			$scope.processing=false;
		})
	}


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

	$scope.addConsultantDetail = function() {

		 var consultantDetail = angular
		 .copy($scope.dummyConsultantDetails);
		 $scope.consultantAdmissionDetail.consultantDetails.push(consultantDetail);
	 };

	 $scope.consultantPopup = function (size) {

			var modalInstance = $modal.open({
				templateUrl: 'home/modals/consultantPopup.html',
				scope:$scope,
				size: size,
			});
		};

	
	$scope.getConsultantByCriteria = function(){
		consultantService.getConsultantByCriteria($scope.searchCriteria)
		.then(function(response) {
			console.log('Data searched in controller : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.searchRes = response.data.responseBody;
				$scope.showCriteria=false;
				
			} 
		})
		searchResult = true;
	}

	$scope.getConsultant = function(){
		consultantService.getConsultant($scope.consultant.consultantId)
		.then(function(response) {
			console.log('consultant master Data received in controller : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.consultant = response.data.responseBody;
				$scope.addNew=false;
			} 
		})

	}

//	$scope.saveConsultant=function(){
//	if($scope.consultant.consultantId){
//	$scope.updateConsultant();
//	}else{
//	$scope.addConsultant();
//	}
//	}

	$scope.init=function(){

		console.log('getting masterdata for consultant module in init block');

		masterdataService.getConsultantMasterData()
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
		
		if(!$scope.consultant.name && !$scope.consultant.primaryContactNo){
	
			alert("Must provide consultant's name")
			return;
		}
		if(!$scope.consultant.primaryContactNo){
			
			alert("Must provide consultant's primary No")
			return;
		}
	
		if(!$scope.consultant.address){
			
			alert("Must provide  address")
			return;
		}
		if(!$scope.consultant.consultancyName){
			
			alert("Must provide consultant's consultancy name")
			return;
		}
		if(!$scope.consultant.emailId){
			
			alert("Must provide consultant's email Id")
			return;
		}
		if(!$scope.consultant.secondaryContactNo){
			
			alert("Must provide consultant's secondary No")
			return;
		}
		
		else{
		consultantService.addConsultant($scope.consultant)
		.then(function(response) {
			console.log('Data received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.consultant = response.data.responseBody;
				alert("Your Records Saved Successfully")
				$scope.addNew=false;
				$scope.redirectToConsultantM($scope.consultant.consultantId);
			} 
		})
		}
	};

	$scope.updateConsultant=function(){
		consultantService.updateConsultant($scope.consultant);
	}

	$scope.getConsultantAdmissionDetail = function(){

		console.log('getConsultantAdmissionDetail called in controller');
		var fileNo = $scope.consultantAdmissionDetail.basicInfo.fileNo;
		consultantService.getConsultantAdmissionDetail(fileNo)
		.then(function(response) {
			console.log('consultantAdmission Data received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {

				$scope.consultantAdmissionDetail = response.data.responseBody;
//				$scope.form.content='consultantAdmission';
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
				$scope.consultantAdmisssulkionDetail = response.data.responseBody;
				alert("Your Records Saved Successfully")
			} 
		})
	};

	$scope.resetForm = function(){
		consultant = {};
	}

	$scope.showDetail = function(index){
		var consultantId = $scope.searchRes[index].consultantId;
		consultantService.getConsultant(consultantId)
		.then(function(response) {
			console.log('Data received by consultantID in controller : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.consultant = response.data.responseBody;
			} 
		})
		searchResult = false;
		addNew = true;
		form.isEdit=true;
	}

	
	$scope.addAdmConsultant = function(){
		console.log('addAdmConsultant called in controller');
		$scope.AdmissnConsltntDtl=$scope.consultantAdmissionDetail.consultantDetails[0];
		consultantService.addAdmConsultant($scope.AdmissnConsltntDtl)
		.then(function(response) {
			console.log('addAdmConsultant Data received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.AdmissnConsltntDtl = response.data.responseBody;
				alert("Your Records Saved Successfully")
			} 
		})
	};

} ]);