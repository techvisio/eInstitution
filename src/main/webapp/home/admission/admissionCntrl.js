var admissionModule = angular.module('admissionModule', [ 'ui.bootstrap' ]);

admissionModule
.controller(
		'admissionController',
		[
		 '$scope',
		 'admissionService',
		 'masterdataService',
		 '$modal',
		 '$state',  
		 '$rootScope',
		 'injectedData',
		 function($scope, admissionService, masterdataService,
				 $modal, $state,$rootScope,injectedData) {

			 $scope.form = {};
			 $scope.form.isNew = true;
			 $scope.student = {};
			 $scope.student.studentBasics={};
			 if(injectedData.data){
				 $scope.form.isNew=false;
				 $scope.student = injectedData.data;
			 }
			 $scope.form.sameAsAbove = false;
			 $scope.form.processing = false;
			 $scope.form.isEdit = true;
			
			 $scope.dashboard = true;
			 $scope.showCriteria = false;
			 $scope.tab = 1;
			 $scope.serverModelData = {};

			 $scope.formTabs = {
					 "DOCUMENT" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "OFFICIAL -> DOCUMENT"
					 },
					 "PERSONALINFO" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "PERSONAL -> PERSONAL INFO"
					 },
					 "ADDRESS" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "PERSONAL -> ADDRESS"
					 },
					 "ACADEMIC" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "PERSONAL -> ACADEMIC"
					 },
					 "COUNSELLING" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "PERSONAL -> COUNSELLING"
					 },
					 "OFFICEUSE" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "OFFICIAL -> OFFICE USE"
					 },
					 "DISCOUNT" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "OFFICIAL -> DISCOUNT"
					 },
					 "SCHOLARSHIP" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "OFFICIAL -> SCHOLARSHIP"
					 },
					 "CONSULTANT" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "OFFICIAL -> CONSULTANT"
					 },
					 "REFERRAL" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege":{},
						 "privilege" :{"view":"VIEW_PERSONAL","add":"ADD_PERSONAL","update":"UPDATE_PERSONAL"},
						 "displayName" : "OFFICIAL -> REFERRAL"
					 }

			 };

			 $scope.dummyAddress = {};
			 $scope.dummyQualification = {
					 "qualificationSubDtl" : [ {} ]
			 };
			 $scope.dummyQualificationSubDtl = {};

			 $scope.dummyDiscountDtl = {};

			 $scope.dummyCounsellingDtl = {};

			 $scope.dummyConsultantDetail = {};

			 
			 $scope.latestAdmission = [];
			 $scope.searchCriteria = {};
			 $scope.currentFetchLimit = 2;

			 $scope.student.addressDtl = [];

			 $scope.student.consultantDetail = [];
			 $scope.student.scholarship = {};

			 $scope.student.academicDtl = [];
			 $scope.student.academicDtl.push(angular
					 .copy($scope.dummyQualification));

			 $scope.student.discountDtl = [];
			 $scope.student.discountDtl.push(angular
					 .copy($scope.dummyDiscountDtl));

			 $scope.student.counsellingDtl = [];
			 $scope.student.counsellingDtl.push(angular
					 .copy($scope.dummyCounsellingDtl));

			 $scope.form.showSub = false;
			 $scope.searchResultList = [];
			 $scope.filteredSearch = [];


			 $scope.admissionMode = {
					 "C" : "Counselling",
					 "W" : "Walk-In",
					 "R" : "Referral",
					 "A" : "Consultant"
			 };

			 $scope.itemsPerPage = 3;
			 $scope.currentPage = 0;
			 $scope.totalItems = 0;

			 $scope.pageCount = function() {
				 return Math.ceil($scope.searchResultList.length
						 / $scope.itemsPerPage);
			 };

			 $scope.numPages = function() {
				 return Math.ceil($scope.searchResultList.length
						 / $scope.numPerPage);
			 };

			 $scope
			 .$watch(
					 'currentPage + numPerPage',
					 function() {
						 var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end = begin
						 + $scope.itemsPerPage;

						 $scope.filteredSearch = $scope.searchResultList
						 .slice(begin, end);
					 });

			 $scope.newAdmission = function() {
				 $state.go('newadmission');
			 }

			 $scope.admissionSearch = function() {
				 $state.go('admissionSearch');
			 }
			 
			 $scope.directViewAdmission=function(currentFileNo){
				 $state.go('admission',{fileNo:currentFileNo});
			 }

			 $scope.gridOptions = {
					 multiSelect : false,
					 data : 'filteredSearch',
					 rowTemplate : '<div ng-dblclick="getStudent(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
					 columnDefs : [ {
						 field : "firstName",
						 width : 100,
						 displayName : "FirstName"
					 }, {
						 field : "lastName",
						 width : 100,
						 displayName : "LastName"
					 }, {
						 field : "fatherName",
						 width : 180,
						 displayName : "Father Name"
					 }, {
						 field : "course.course",
						 width : 140,
						 displayName : "Course"
					 }, {
						 field : "branch.branchName",
						 width : 180,
						 displayName : "Branch"
					 }, {
						 field : "applicationStatus",
						 width : 200,
						 displayName : "Status"
					 } ]
			 };

			 $scope.LoadMoreData = function() {

				 $scope.currentFetchLimit += 5;

				 $scope.getLatestAdmission();

			 };

			 $scope.lessData = function() {

				 $scope.currentFetchLimit -= 5;

				 $scope.getLatestAdmission();

			 };

			 $scope.selectTab = function(setTab) {
				 $scope.tab = setTab;
			 };
			 $scope.isSelected = function(checkTab) {
				 return $scope.tab === checkTab;
			 };

			 $scope.backtoDashboard = function() {
				 if ($scope.form.isNew || $scope.form.isEdit) {
					 var response = confirm("Current data will be lost..\nContinue?");
					 if (!response) {
						 return;
					 }
				 }

				 $scope.dashboard = true;
				 $scope.resetSearchCriteria();

			 }
			 $scope.checkAmout = function(index, type) {
				 if (type == 'amount') {
					 if ($scope.student.discountDtl[index].amount > 0) {
						 $scope.student.discountDtl[index].percent = 0;
					 }
				 } else {
					 if ($scope.student.discountDtl[index].percent > 0) {
						 $scope.student.discountDtl[index].amount = 0;
					 }
				 }

			 }

			 $scope.init = function() {

				 console
				 .log('getting masterdata for admission module in init block');

				 masterdataService.getAdmissionMasterData()
				 .then(function(data) {
					 console.log(data);
					 if (data) {
						 $scope.serverModelData = data.responseBody;
					 } else {
						 console.log('error');
					 }
				 })

			 }

			 $scope.click = function(arg) {

				 console.log("print on submit click");
				 console.log($scope.student);
			 }

			 $scope.addConsultantDetail = function() {

				 var consultantDetail = angular
				 .copy($scope.dummyConsultantDetail);
				 $scope.student.consultantDetail
				 .push(consultantDetail);
			 };

			 $scope.removeConsultantDetail = function(index) {

				 $scope.student.consultantDetail
				 .splice(index, 1);
			 };

			 $scope.addDiscountDtl = function() {

				 var discountDtl = angular
				 .copy($scope.dummyDiscountDtl);
				 $scope.student.discountDtl.push(discountDtl);
			 };

			 $scope.removeDiscountDtl = function(index) {

				 $scope.student.discountDtl.splice(index, 1);
			 };

			 $scope.addCounsellingDtl = function() {

				 var counsellingDtl = angular
				 .copy($scope.dummyCounsellingDtl);
				 $scope.student.counsellingDtl
				 .push(counsellingDtl);
			 };

			 $scope.removeCounsellingDtl = function(index) {

				 $scope.student.counsellingDtl.splice(index, 1);
			 };

			 $scope.addQualification = function(index) {
				 var copedDtl = angular
				 .copy($scope.dummyQualification);
				 $scope.student.academicDtl.push(copedDtl);
			 };

			 $scope.removeQualification = function(index) {
				 $scope.student.academicDtl.splice(index, 1);
			 };

			 $scope.resetSearchCriteria = function() {
				 $scope.searchCriteria = {};
			 }

			 $scope.addSubject = function(object) {
				 var qualificationSub = angular
				 .copy($scope.dummyQualificationSubDtl);
				 qualificationSub.qualificationId = object.qualificationId;
				 object.qualificationSubDtl
				 .push(qualificationSub);
			 };

			 $scope.removeSubject = function(object, index) {
				 console.log(index);
				 object.qualificationSubDtl.splice(index, 1);
			 };

			 $scope.showSubject = function() {
				 $scope.showSub = true;
			 };

			 $scope.getAddress = function(addressType) {
				 var address = null;
				 for (var i = 0; i <= ($scope.student.addressDtl.length||0) - 1; i++) {
					 var add = $scope.student.addressDtl[i];
					 if (add.addressType == addressType) {
						 address = add;
					 }
				 }
				 ;
				 if (address == null) {
					 address = angular.copy($scope.dummyAddress);
					 address.addressType = addressType;
					 $scope.student.addressDtl.push(address);
				 }

				 return address;
			 }

			 $scope.copyPermanentAddress = function() {
				 console.log('same as above');
				 console.log($scope.form.sameAsAbove);
				 if ($scope.form.sameAsAbove == true) {
					 console.log('same as above');
					 var cAddress = $scope.getAddress('C');
					 angular.copy($scope.getAddress('P'),
							 cAddress)
							 cAddress.addressType = 'C';
				 }
			 }

//			 $scope.saveStudent = function() {
//			 if (!$scope.admissionForm.$valid) {
//			 alert('Form is invalid');
//			 return;
//			 }
//			 console.log($scope.form.isEdit);
//			 if (!$scope.form.isNew) {
//			 $scope.updateStudent();
//			 } else {
//			 $scope.addStudent();
//			 }
//			 }

			 $scope.submitToManagement = function() {

				 console
				 .log('submitToManagement called in controller')
				 console.log($scope.student);
				 $scope.processing = true;
				 admissionService
				 .submitToManagement($scope.student)
				 .then(
						 function(response) {
							 console
							 .log('submitToManagement Data received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student = response.data.responseBody;
								 $scope
								 .populateMissingData($scope.student);
								 $scope
								 .getLatestAdmission();
								 $scope.form.isNew = false;
								 $scope.form.isEdit = false;
							 } else {
								 console
								 .log(response.data.error);
								 alert(response.data.error);
							 }

							 $scope.processing = false;
						 })
			 }

			 $scope.saveStudent = function() {
				 console.log('add student called');
				 console.log($scope.student);
				 $scope.processing = true;
				 admissionService
				 .saveStudent($scope.student)
				 .then(
						 function(response) {
							 console
							 .log('Data received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student = response.data.responseBody;
								 $scope.form.isNew=false;
								 alert("Your Records Saved Successfully")
								 $scope.directViewAdmission($scope.student.fileNo);
							 } 
							 $scope.processing = false;
						 })
			 }

			 $scope.updateStudent = function() {
				 console.log('update student called');
				 console.log($scope.student);
				 $scope.processing = true;
				 admissionService
				 .updateStudent($scope.student)
				 .then(
						 function(response) {
							 console
							 .log('Data received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student = response.data.responseBody;
								 $scope
								 .populateMissingData($scope.student);
								 $scope
								 .getLatestAdmission();
								 $scope.form.isNew = false;
								 $scope.form.isEdit = false;
							 }
							 $scope.processing = false;
						 })
			 }

			 $scope.getStudent = function(fileNo) {
				 $scope.processing = true;
				 admissionService
				 .getStudent(fileNo)
				 .then(
						 function(response) {
							 console
							 .log('Data received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student = response.data.responseBody;
								 $scope
								 .populateMissingData($scope.student);
								 $scope
								 .getLatestAdmission();
								 $scope.form.isNew = false;
								 $scope.form.isEdit = false;
								 $scope.dashboard = false;

							 }
							 $scope.processing = false;
						 })
			 }
			 $scope.getStudentBasics = function() {
				 admissionService
				 .getStudentBasics(
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('student basic detail received from service : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.studentBasics = response.data.responseBody;

									 }
								 })
			 }

			 $scope.updateStudentBasics = function() {
				 admissionService
				 .updateStudentBasics(
						 $scope.student.studentBasics,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateStudentBasics called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.studentBasics = response.data.responseBody;

									 }
								 })
			 }

			 $scope.getStudentAcademicDtl = function() {

				 admissionService
				 .getStudentAcademicDtl(
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('academic detail received from service : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.academicDtl = response.data.responseBody;

									 }
								 })
			 }

			 $scope.updateStudentAcademicDtl = function() {
				 admissionService
				 .updateStudentAcademicDtl(
						 $scope.student.academicDtl,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateStudentAcademicDtl called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.academicDtl = response.data.responseBody;

									 }
								 })
			 }

			 $scope.getStudentAddress = function() {
				 admissionService
				 .getStudentAddress(
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('address detail received from service : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.addressDtl = response.data.responseBody;

									 }
								 })
			 }

			 $scope.updateStudentAddress = function() {
				 admissionService
				 .updateStudentAddress(
						 $scope.student.addressDtl,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateStudentAddress called i controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.addressDtl = response.data.responseBody;
										 alert("Your Records Saved Successfully")
									 }
								 })
			 }

			 $scope.getStudentDiscountDtl = function() {
				 admissionService
				 .getStudentDiscountDtl(
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('student discount detail received from service : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.discountDtl = response.data.responseBody;
									 }
								 })
			 }

			 $scope.updateStudentDiscountDtl = function() {
				 admissionService
				 .updateStudentDiscountDtl(
						 $scope.student.discountDtl,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateStudentDiscountDtl called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.discountDtl = response.data.responseBody;
									 }
								 })
			 }

			 $scope.getBranchPref = function() {
				 admissionService
				 .getBranchPref($scope.student.fileNo)
				 .then(
						 function(response) {
							 console
							 .log('branchpref detail received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student.branchPreference = response.data.responseBody;

							 }
						 })
			 }

			 $scope.updateBranchPref = function() {
				 admissionService
				 .updateBranchPref(
						 $scope.student.branchPreference,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateBranchPref called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.branchPreference = response.data.responseBody;

									 }
								 })
			 }

			 $scope.getCounsellingDtl = function() {
				 admissionService
				 .getCounsellingDtl(
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('counselling detail received from service : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.counsellingDtl = response.data.responseBody;

									 }
								 })
			 }

			 $scope.updateCounsellingDtl = function() {
				 admissionService
				 .updateCounsellingDtl(
						 $scope.student.counsellingDtl,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateCounsellingDtl called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.counsellingDtl = response.data.responseBody;

									 }
								 })
			 }

			 $scope.getConsultantDtl = function() {
				 admissionService
				 .getConsultantDtl($scope.student.fileNo)
				 .then(
						 function(response) {
							 console
							 .log('consulant detail received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student.consultantDetail = response.data.responseBody;

							 }
						 })
			 }

			 $scope.updateConsultantDtl = function() {
				 admissionService
				 .updateConsultantDtl(
						 $scope.student.counsellingDtl,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateConsultantDtl called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.counsellingDtl = response.data.responseBody;

									 }
								 })
			 }

			 $scope.getScholarshipDtl = function() {
				 admissionService
				 .getScholarshipDtl(
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('scholarship detail received from service : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.scholarship = response.data.responseBody;

									 }
								 })
			 }

			 $scope.updateScholarshipDtl = function() {
				 admissionService
				 .updateScholarshipDtl(
						 $scope.student.scholarship,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateScholarshipDtl called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.scholarship = response.data.responseBody;

									 }
								 })
			 }


			 $scope.getdocuments = function(){

				 admissionService
				 .getdocuments(
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('document detail received from service : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.documents = response.data.responseBody;
									 }
								 })
			 }

			 $scope.updateDocuments = function(){
				 admissionService
				 .updateDocuments(
						 $scope.student.documents,
						 $scope.student.fileNo)
						 .then(
								 function(response) {
									 console
									 .log('updateDocuments called in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.student.documents = response.data.responseBody;

									 }
								 })								
			 }

			 $scope.getLatestAdmission = function() {

				 admissionService
				 .getLatestAdmission(
						 $scope.currentFetchLimit)
						 .then(
								 function(response) {
									 console
									 .log('Latest admission data received in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.latestAdmission = response.data.responseBody;

									 }
								 })
			 }

			 $scope.showDetail = function(index) {

				 var fileNo = $scope.latestAdmission[index].fileNo;
				 if (fileNo) {
					 $scope.getStudent(fileNo);
				 } else {
					 alert("No valid fileNo provided");
				 }
			 }

			 $scope.showStudentDetail = function(index) {

				 var fileNo = $scope.searchResultList[index].fileNo;
				 if (fileNo) {
					 $scope.getStudent(fileNo);
				 } else {
					 alert("No valid fileNo provided");
				 }
			 }

			 $scope.getStudentByCriteria = function() {
				 console
				 .log('get student by search criteria in controller');
				 console.log($scope.searchCriteria);
				 $scope.processing = true;
				 $scope.currentPage = 0;
				 admissionService
				 .getStudentByCriteria(
						 $scope.searchCriteria)
						 .then(
								 function(response) {
									 console
									 .log('Data received from service in controller : ');
									 console.log(response);
									 if (response != null
											 && response.data != null
											 && response.data.responseBody != null) {
										 $scope.searchResultList = response.data.responseBody;
										 $scope.showCriteria = false;
										 $scope.currentPage = 1;
										 $scope.totalItems = $scope.searchResultList.length;
									 } else {
										 console
										 .log(response.data.error);
										 alert(response.data.error);
									 }

									 $scope.processing = false;
								 })
			 }

			 $scope.next = function() {
				 var selectionIndex = $scope.subModules
				 .indexOf($scope.selection);
				 if (selectionIndex != $scope.subModules.length - 1) {
					 selectionIndex = selectionIndex + 1;
					 $scope.selection = $scope.subModules[selectionIndex];
					 $scope.form.direction = 1;
				 }
			 }

			 $scope.prev = function() {
				 var selectionIndex = $scope.subModules
				 .indexOf($scope.selection);
				 if (selectionIndex != 0) {
					 selectionIndex = selectionIndex - 1;
					 $scope.selection = $scope.subModules[selectionIndex];
					 $scope.form.direction = 0;
				 }
			 }

			 $scope.resetForm = function() {

				 console.log("calling reset.....")
				 $scope.form.processing = false;
				 a
				 $scope.form.isNew = true;
				 $scope.form.isEdit = true;
				 $scope.student = {};
				 $scope.student.addressDtl = [];

				 $scope.student.academicDtl = [];
				 $scope.student.academicDtl.push(angular
						 .copy($scope.dummyQualification));

				 $scope.student.discountDtl = [];
				 $scope.student.discountDtl.push(angular
						 .copy($scope.dummyDiscountDtl));

				 $scope.student.counsellingDtl = [];
				 $scope.student.counsellingDtl.push(angular
						 .copy($scope.dummyCounsellingDtl));

				 $scope.student.consultantDetail = [];

			 }

			 $scope.saveAmenity = function(amenityStaging) {

				 console.log('saveAmenity called in controller');

				 var fileNo = $scope.student.fileNo;
				 amenityStaging.fileNo = fileNo;

				 admissionService
				 .saveAmenity(amenityStaging)
				 .then(
						 function(response) {
							 console
							 .log('saveAmenity Data received from service in controller : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {

								 $scope
								 .getStudent(fileNo);

							 } else {
								 console
								 .log(response.data.error);
								 alert(response.data.error);
							 }
						 })
			 }

			 $scope.resetAdmissionMode = function() {

				 $scope.student.counsellingDtl = [];
				 $scope.student.counsellingDtl.push(angular
						 .copy($scope.dummyCounsellingDtl));

				 $scope.student.referredBy = null;

			 }
			 $scope.populateMissingData = function(data) {

				 if (angular.isUndefined(data.academicDtl)
						 || data.academicDtl == null
						 || data.academicDtl.length == 0) {
					 $scope.student.academicDtl = [];
					 $scope.student.academicDtl.push(angular
							 .copy($scope.dummyQualification))
				 }

				 if (angular.isUndefined(data.discountDtl)
						 || data.discountDtl == null
						 || data.discountDtl.length == 0) {
					 $scope.student.discountDtl = [];
					 $scope.student.discountDtl.push(angular
							 .copy($scope.dummyDiscountDtl));
				 }

//				 if (angular.isUndefined(data.consultantDetail)
//				 || data.consultantDetail == null
//				 || data.consultantDetail.length == 0) {
//				 $scope.student.consultantDetail = [];
//				 $scope.student.consultantDetail
//				 .push(angular
//				 .copy($scope.dummyConsultantDetail));
//				 }

				 if (angular.isUndefined(data.counsellingDtl)
						 || data.counsellingDtl == null
						 || data.counsellingDtl.length == 0) {
					 $scope.student.counsellingDtl = [];
					 $scope.student.counsellingDtl.push(angular
							 .copy($scope.dummyCounsellingDtl))
				 }

				 for (var i = 0; i < $scope.student.academicDtl.length; i++) {

					 if (angular
							 .isUndefined($scope.student.academicDtl[i].qualificationSubDtl)
							 || $scope.student.academicDtl[i].qualificationSubDtl == null
							 || $scope.student.academicDtl[i].qualificationSubDtl.length == 0) {

						 $scope.student.academicDtl[i].qualificationSubDtl = [];
						 $scope.student.academicDtl[i].qualificationSubDtl
						 .push(angular
								 .copy($scope.dummyQualificationSubDtl))
					 }
				 }
			 }

			 $scope.showTransportModal = function(size) {

				 if (!$scope.form.isEdit && !scope.form.isNew) {
					 return;
				 }
				 var fileNo = $scope.student.fileNo;
				 if ($scope.form.isNew) {
					 alert("Please save record before reserving transport");
					 return;
				 }

				 var modalInstance = $modal.open({
					 templateUrl : 'transportReservation.html',
					 controller : 'transportController',
					 scope : $scope,
					 size : size,
					 resolve : {
						 items : function() {
							 return $scope.items;
						 }
					 }
				 });

				 modalInstance.result
				 .then(function(selectedItem) {
					 $scope.selected = selectedItem;
				 });
			 };

			 $scope.showHostelModal = function(size) {

				 if (!$scope.form.isEdit && !scope.form.isNew) {
					 return;
				 }
				 var fileNo = $scope.student.fileNo;

				 if ($scope.form.isNew) {
					 alert("Please save record before reserving hostel");
					 return;
				 }

				 var modalInstance = $modal.open({
					 templateUrl : 'hostelReservation.html',
					 controller : 'hostelController',
					 scope : $scope,
					 size : size,
					 resolve : {
						 items : function() {
							 return $scope.items;
						 }
					 }
				 });

				 modalInstance.result
				 .then(function(selectedItem) {
					 $scope.selected = selectedItem;
				 });
			 };

			 $scope.saveAmenityPopup = function(size) {

				 var modalInstance = $modal.open({
					 templateUrl : 'amenity.html',
					 controller : 'feeController',
					 scope : $scope,
					 size : size,
				 });
			 };

			 $scope.getRelatedData = function(type) {

				 if (!$scope.form.isNew) {
					 switch (type) {
					 case "DOCUMENT": {
						 $scope.getDocuments($scope.student.fileNo);
						 break;
					 }

					 case "PERSONALINFO": {
						 $scope.getStudent($scope.student.fileNo);
						 break;
					 }

					 case "ACADEMIC": {
						 $scope.getStudentAcademicDtl($scope.student.fileNo);
						 break;
					 }

					 case "ADDRESS": {
						 $scope.getStudentAddress($scope.student.fileNo);
						 break;
					 }
					 case "COUNSELLING": {
						 $scope.getCounsellingDtl($scope.student.fileNo);
						 break;
					 }
					 case "OFFICEUSE": {
						 $scope.getStudent($scope.student.fileNo);
						 break;
					 }
					 case "DISCOUNT": {
						 $scope.getStudentDiscountDtl($scope.student.fileNo);
						 break;
					 }
					 case "SCHOLARSHIP": {
						 $scope.getScholarshipDtl($scope.student.fileNo);
						 break;
					 }
					 case "CONSULTANT": {
						 $scope.getConsultantDtl($scope.student.fileNo);
						 break;
					 }
					 case "REFERRAL": {
						 $scope.getStudent($scope.student.fileNo);
						 break;
					 }
					
					 default: 
					 { 
						 $scope.getStudent($scope.student.fileNo);
					 }
					 }
				 }
			 };

			 $scope.toggleReadOnly = function(form) {
				 var tab = $scope.formTabs[form];
				 if (tab) {
					 if (tab.isEdit) {
						 $('#' + form + ' *').attr('readonly',
								 true);
						 $('#' + form + ' input[type="radio"]')
						 .attr('disabled', true);
						 tab.isEdit = !tab.isEdit;
					 } else {
						 $('#' + form + ' *').attr('readonly',
								 false);
						 $('#' + form + ' input[type="radio"]')
						 .attr('disabled', false);
						 tab.isEdit = !tab.isEdit;
					 }
				 }
			 };

			 $scope.isTabEnabled = function(form) {
				 if($scope.form.isNew){
					 return $scope.isPrivileged(form);
				 }
				 else
				 {
					 return $scope.isPrivileged(form) && $scope.isAlltabsReadOnly() ;
				 }

			 };

			 $scope.isPrivileged=function(form){
				 var userPrivilege=$rootScope.user.privilege;
				 if(userPrivilege){
					 var tab = $scope.formTabs[form];
					 var tabPrivilege=tab.privilege;
					 if(userPrivilege.tabPrivilege != -1)
					 {
						 return true;
					 }
				 }
			 }

			 $scope.isAlltabsReadOnly=function(){
				 for (var key in $scope.formTabs) {
					 if(key["isEdit"]){
						 return false;
					 }
				 }
				 return true;
			 }

			 $scope.showTab = function(){

			 }
		 } ]);
