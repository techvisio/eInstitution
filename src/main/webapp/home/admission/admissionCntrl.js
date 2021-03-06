var admissionModule = angular.module('admissionModule', [ 'ui.bootstrap',
                                                          'ngGrid' ]);

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
		 'transportService',
		 'hostelService',
		 function($scope, admissionService, masterdataService,
				 $modal, $state, $rootScope, injectedData,transportService,hostelService) {

			 $scope.form = {};
			 $scope.form.isNew = true;
			 $scope.student = {};
			 $scope.currentWorkflow ={};
			 $scope.availableWorkflows=[];
			 $scope.student.studentBasics = {};
			 $scope.student.documents={};
			 $scope.form.sameAsAbove = false;
			 $scope.form.processing = false;
			 $scope.showCriteria = true;
			 $scope.serverModelData = {};

			 $scope.availableTransport = {};
			 $scope.transportReservation = {};
			 $scope.currentTransportReservation={};

			 $scope.hostelAvailability = {};
			 $scope.hostelReservation = {};
			 $scope.currentHostelReservation={};

			 $scope.formTabs = {
					 "DOCUMENT" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_DOC_R",
							 "create" : "ROLE_DOC_C",
							 "update" : "ROLE_DOC_U"
						 },
						 "displayName" : "OFFICIAL -> DOCUMENT"
					 },
					 "PERSONALINFO" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_PER_R",
							 "create" : "ROLE_PER_C",
							 "update" : "ROLE_PER_U"
						 },
						 "displayName" : "PERSONAL -> PERSONAL INFO"
					 },
					 "ADDRESS" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_ADD_R",
							 "create" : "ROLE_ADD_C",
							 "update" : "ROLE_ADD_U"
						 },
						 "displayName" : "PERSONAL -> ADDRESS"
					 },
					 "ACADEMIC" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_ACD_R",
							 "create" : "ROLE_ACD_C",
							 "update" : "ROLE_ACD_U"
						 },
						 "displayName" : "PERSONAL -> ACADEMIC"
					 },
					 "COUNSELLING" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_COUN_R",
							 "create" : "ROLE_COUN_C",
							 "update" : "ROLE_COUN_U"
						 },
						 "displayName" : "PERSONAL -> COUNSELLING"
					 },
					 "OFFICEUSE" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_OFF_R",
							 "create" : "ROLE_OFF_C",
							 "update" : "ROLE_OFF_U"
						 },
						 "displayName" : "OFFICIAL -> OFFICE USE"
					 },
					 "DISCOUNT" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_DIS_R",
							 "create" : "ROLE_DIS_C",
							 "update" : "ROLE_DIS_U"
						 },
						 "displayName" : "OFFICIAL -> DISCOUNT"
					 },
					 "SCHOLARSHIP" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_SCH_R",
							 "create" : "ROLE_SCH_C",
							 "update" : "ROLE_SCH_U"
						 },
						 "displayName" : "OFFICIAL -> SCHOLARSHIP"
					 },
					 "CONSULTANT" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_CON_R",
							 "create" : "ROLE_CON_C",
							 "update" : "ROLE_CON_U"
						 },
						 "displayName" : "OFFICIAL -> CONSULTANT"
					 },
					 "REFERRAL" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_REF_R",
							 "create" : "ROLE_REF_C",
							 "update" : "ROLE_REF_U"
						 },
						 "displayName" : "OFFICIAL -> REFERRAL"
					 },
					 "TRANSPORT" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_TRA_R",
							 "create" : "ROLE_TRA_C",
							 "update" : "ROLE_TRA_U"
						 },
						 "displayName" : "OFFICIAL -> TRANSPORT"
					 },
					 "HOSTEL" : {
						 "isEdit" : false,
						 "activities" : [],
						 "privilege" : {
							 "view" : "ROLE_HOS_R",
							 "create" : "ROLE_HOS_C",
							 "update" : "ROLE_HOS_U"
						 },
						 "displayName" : "OFFICIAL -> HOSTEL"
					 },
					 // "APPLICABLEFEE" : {
					 // "isEdit" : false,
					 // "activities" : [],
					 // "privilege"
					 // :{"view":"ROLE_AFE_R","create":"ROLE_AFE_C","update":"ROLE_AFE_U"},
					 // "displayName" : "OFFICIAL -> APPLICABLEFEE"
					 // }

			 };

			 $scope.dummyAddress = {};
			 $scope.dummyQualification = {
					 "qualificationSubDtl" : [ {} ]
			 };
			 $scope.dummyQualificationSubDtl = {};

			 $scope.dummyDiscountDtl = {};

			 $scope.dummyCounsellingDtl = {};

			 $scope.dummyConsultantDetail = {};

			 $scope.searchCriteria = {};

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

			 $scope.searchAdmissionList = [];
			 $scope.filteredSearch = [];

			 $scope.itemsPerPage = 3
			 $scope.currentPage = 0;
			 $scope.totalItems = 0;

			 $scope.pageCount = function() {
				 return Math
				 .ceil($scope.searchAdmissionList.length
						 / $scope.itemsPerPage);
			 };

			 $scope.admissionMode = {
					 "C" : "Counselling",
					 "W" : "Walk-In",
					 "R" : "Referral",
					 "A" : "Consultant"
			 };

			 $scope.directViewAdmission = function(currentFileNo) {
				 $state.go('admission', {
					 fileNo : currentFileNo
				 });
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

				 masterdataService
				 .getAdmissionMasterData()
				 .then(
						 function(data) {
							 console.log(data);
							 if (data) {
								 $scope.serverModelData = data.responseBody;
							 } else {
								 console.log('error');
							 }
						 })

			 };

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

			 $scope.getAddress = function(addressType) {
				 var address = null;
				 var addressSize=0;
				 if(!$scope.student.addressDtl){
					 $scope.student.addressDtl=[];
				 }
				 addressSize =$scope.student.addressDtl.length;

				 for (var i = 0; i <= addressSize - 1; i++) {
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
					 var cAddressId=cAddress.addressId;
					 angular.copy($scope.getAddress('P'),
							 cAddress)
							 cAddress.addressType = 'C';
					 cAddress.addressId=cAddressId;
				 }
			 }

			 $scope.processWorkflow = function() {

				 var stepId = $scope.currentWorkflow.stepId;
				 admissionService
				 .processWorkflow($scope.student, stepId)
				 .then(
						 function(response) {
							 console
							 .log('Data received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student = response.data.responseBody.student;
								 $scope.availableWorkflows = response.data.responseBody.workflows;
								 if( $scope.availableWorkflows[0]){
									 $scope.currentWorkflow=$scope.availableWorkflows[0];
								 }
								 $scope
								 .directViewAdmission($scope.student.fileNo);
								 $scope
								 .populateMissingData($scope.student);
								 $scope.form.isNew = false;
								 alert("Your Records Saved Successfully")
							 }
						 })
			 };

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
								 $scope.form.isNew = false;
								 alert("Your Records Saved Successfully")
								 $scope
								 .directViewAdmission($scope.student.fileNo);
							 }
							 $scope.processing = false;
						 })
			 };


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
								 $scope.student = response.data.responseBody.student;
								 $scope.availableWorkflows = response.data.responseBody.workflows;
								 $scope
								 .directViewAdmission(fileNo);
								 $scope
								 .populateMissingData($scope.student);
								 $scope.form.isNew = false;
							 }
							 $scope.processing = false;
						 })
			 };

			 $scope.getNewAdmission = function() {
				 admissionService
				 .getNewAdmission()
				 .then(
						 function(response) {
							 console
							 .log('Data received from service : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.availableWorkflows = response.data.responseBody;
								 if( $scope.availableWorkflows[0]){
									 $scope.currentWorkflow=$scope.availableWorkflows[0];
								 }
							 }

						 })
			 };

			 
			 $scope.getSaveButtonText = function() {
				 if($scope.availableWorkflows.length>1){
					 return "Update Status";
				 }
				if ($scope.currentWorkflow) {
					 return $scope.currentWorkflow.step;
				 }
			 }

			 $scope.getStudentBasics = function() {
				 admissionService
				 .getStudentBasics($scope.student.fileNo)
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
			 };

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
										 alert("Your Records Updated Successfully")
									 }
								 })
			 };

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
										 $scope.populateMissingData(response.data.responseBody.academicDtl);
									 }
								 })
			 };

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
										 alert("Academic Info Updated Successfully");
										
									 }
								 })
			 };

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
										 $scope.populateMissingData(response.data.responseBody.addressDtl);
									 }
								 })
			 };

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
										 alert("Address Updated Successfully")
									 }
								 })
			 };

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
										 $scope.populateMissingData(response.data.responseBody.DiscountDtl);
									 }
								 })
			 };

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
										 alert("Discount Info Updated Successfully")
									 }
								 })
			 };

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
			 };

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
			 };

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
										 $scope.populateMissingData(response.data.responseBody.counsellingDtl);
									 }
								 })
			 };

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
										 alert("Counselling Info Updated Successfully")
									 }
								 })
			 };

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
								 $scope.populateMissingData(response.data.responseBody.consultantDetail);
							 }
						 })
			 };

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
										 alert("Consultant Info Updated Successfully")
									 }
								 })
			 };

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
										 $scope.populateMissingData(response.data.responseBody.scholarship);
									 }
								 })
			 };

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
										 alert("Scholarship Info Updated Successfully")
									 }
								 })
			 };

			 $scope.getstudentDocument = function() {
				 admissionService
				 .getStudentDocument($scope.student.fileNo)
				 .then(
						 function(response) {
							 console
							 .log('Student document Data received in controller : ');
							 console.log(response);
							 if (response != null
									 && response.data != null
									 && response.data.responseBody != null) {
								 $scope.student.documents = response.data.responseBody;
							 }
						 })
			 };

			 $scope.updateDocuments = function() {
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
										 alert("Document Updated Successfully")
									 }
								 })
			 };

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
										 $scope.searchAdmissionList = response.data.responseBody;
										 if ($scope.searchAdmissionList.length > 0) {
											 $scope.showCriteria = false;
											 // $scope.dashboard
											 // = false;
											 $scope.currentPage = 1;
											 $scope.totalItems = $scope.searchAdmissionList.length;
										 }
									 }

									 $scope.processing = false;
								 })
			 };

			 $scope.numPages = function() {
				 return Math
				 .ceil($scope.searchAdmissionList.length
						 / $scope.itemsPerPage);
			 };

			 $scope
			 .$watch(
					 'currentPage + itemsPerPage',
					 function() {
						 var begin = (($scope.currentPage - 1) * $scope.itemsPerPage), end = begin
						 + $scope.itemsPerPage;

						 $scope.filteredSearch = $scope.searchAdmissionList
						 .slice(begin, end);
					 });
			 

			 $scope.gridOptions = {
					 multiSelect : false,
					 data : 'filteredSearch',
					 rowTemplate : '<div ng-dblclick="getStudent(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
					 columnDefs : [ {
						 field : "registrationNo",
						 width : 200,
						 displayName : "Registration No"
					 }, {
						 field : "firstName",
						 width : 180,
						 displayName : "Name"
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
					 } ]
			 };

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

				 if (angular.isUndefined(data.counsellingDtl)
						 || data.counsellingDtl == null
						 || data.counsellingDtl.length == 0) {
					 $scope.student.counsellingDtl = [];
					 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl))}

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
			 };

			 $scope.getRelatedData = function(type) {

				 if (!$scope.form.isNew) {
					 switch (type) {
					 case "DOCUMENT": {
						 $scope
						 .getstudentDocument($scope.student.fileNo);
						 break;
					 }

					 case "PERSONALINFO": {
						 $scope
						 .getStudentBasics($scope.student.fileNo);
						 break;
					 }

					 case "ACADEMIC": {
						 $scope
						 .getStudentAcademicDtl($scope.student.fileNo);
						 break;
					 }

					 case "ADDRESS": {
						 $scope
						 .getStudentAddress($scope.student.fileNo);
						 break;
					 }
					 case "COUNSELLING": {
						 $scope
						 .getCounsellingDtl($scope.student.fileNo);
						 break;
					 }
					 case "OFFICEUSE": {
						 $scope
						 .getStudentBasics($scope.student.fileNo);
						 break;
					 }
					 case "DISCOUNT": {
						 $scope
						 .getStudentDiscountDtl($scope.student.fileNo);
						 break;
					 }
					 case "SCHOLARSHIP": {
						 $scope
						 .getScholarshipDtl($scope.student.fileNo);
						 break;
					 }
					 case "CONSULTANT": {
						 $scope
						 .getConsultantDtl($scope.student.fileNo);
						 break;
					 }
					 case "REFERRAL": {
						 $scope
						 .getStudentBasics($scope.student.fileNo);
						 break;
					 }
					 case "TRANSPORT": {
						 $scope.getReservedTransport($scope.student.fileNo);
						 break;
					 }
					 case "HOSTEL": {
						 $scope.getReservedHostel($scope.student.fileNo);
						 break;
					 }
					 default: {
						 $scope
						 .getStudent($scope.student.fileNo);
					 }
					 }
				 }
			 };

			 $scope.makeAllReadOnly = function() {

				 for ( var form in $scope.formTabs) {
					 var tab = $scope.formTabs[form];
					 if (tab) {

						 $('#' + form + ' *').attr('readonly',
								 true);
						 $('#' + form + ' input[type="radio"]')
						 .attr('disabled', true);
						 tab.isEdit = false;
					 }
				 }
			 }

			 if (injectedData.data) {
				 $scope.form.isNew = false;
				 $scope.student = injectedData.data.responseBody.student;
				 $scope.availableWorkflows = [];
				 $scope.availableWorkflows = injectedData.data.responseBody.workflows;
				 if( $scope.availableWorkflows[0]){
					 $scope.currentWorkflow=$scope.availableWorkflows[0];
				 }
				 $scope.makeAllReadOnly();
			 }

			 if ($scope.form.isNew) {
				 $scope.getNewAdmission();
			 }

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
				 if (!$scope.form.isNew
						 && $scope.isEditing(form)) {
					 return true;
				 }

			 };

			 $scope.isTabViewable = function(form) {

				 var userPrivilege = $rootScope.user.privilege;
				 if (userPrivilege) {
					 var tab = $scope.formTabs[form];
					 var tabPrivilege = tab.privilege.view;
					 if (userPrivilege.indexOf(tabPrivilege) != -1) {
						 return true;
					 }
				 }
			 }

			 $scope.isTabEditable = function(form) {
				 var userPrivilege = $rootScope.user.privilege;
				 if (userPrivilege) {
					 var tab = $scope.formTabs[form];
					 var tabPrivilege = tab.privilege.update;
					 if (userPrivilege.indexOf(tabPrivilege) != -1) {
						 return true;
					 }
				 }
			 }

			 $scope.isEditing = function(form) {
				 var tab = $scope.formTabs[form];
				 var isEdited = tab.isEdit;

				 return isEdited;
			 }

			 $scope.isAlltabsReadOnly = function() {
				 for ( var key in $scope.formTabs) {
					 if (key["isEdit"]) {
						 return false;
					 }
				 }
				 return true;
			 }


			 // angular.element(document).ready(function () {
			 //
			 // if(!$scope.form.isNew){
			 // $scope.makeAllReadOnly();
			 // }
			 // });


			 //date picker work

			 $scope.today = function() {
				 $scope.dt = new Date();
			 };
			 $scope.today();

			 $scope.clear = function () {
				 $scope.dt = null;
			 };

			 // Disable weekend selection
			 $scope.disabled = function(date, mode) {
				 return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
			 };

			 $scope.toggleMin = function() {
				 $scope.minDate = $scope.minDate ? null : new Date();
			 };
			 $scope.toggleMin();

			 $scope.open = function($event) {
				 $scope.status.opened = true;
			 };

			 $scope.dateOptions = {
					 formatYear: 'yy',
					 startingDay: 1
			 };

			 $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
			 $scope.format = $scope.formats[0];

			 $scope.status = {
					 opened: false
			 };

			 var tomorrow = new Date();
			 tomorrow.setDate(tomorrow.getDate() + 1);
			 var afterTomorrow = new Date();
			 afterTomorrow.setDate(tomorrow.getDate() + 2);
			 $scope.events =
				 [
				  {
					  date: tomorrow,
					  status: 'full'
				  },
				  {
					  date: afterTomorrow,
					  status: 'partially'
				  }
				  ];

			 $scope.getDayClass = function(date, mode) {
				 if (mode === 'day') {
					 var dayToCheck = new Date(date).setHours(0,0,0,0);

					 for (var i=0;i<$scope.events.length;i++){
						 var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

						 if (dayToCheck === currentDay) {
							 return $scope.events[i].status;
						 }
					 }
				 }

				 return '';
			 };

			 //transport work

			 $scope.getAvailableTransport = function() {

				 transportService.getAvailableTransport().then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if(response.data != null)
					 {
						 $scope.availableTransport=response.data;
					 }
				 })

			 };

			 $scope.getReservedTransport = function(){
				 var fileNo=$scope.student.fileNo;
				 if(!fileNo){
					 return;
				 }
				 console.log('Getting current transport reservation for student : '+fileNo);
				 if(fileNo){				
					 transportService.getReservedTransport(fileNo)
					 .then(function(response){
						 console.log('Data received from getReservedTransport controller : ');
						 console.log(response);
						 if (response !=null && response.data != null && response.data.responseBody != null) {
							 $scope.currentTransportReservation = response.data.responseBody;
						 }
					 })
				 }
			 };


			 $scope.reserveTransport = function(){

				 var fileNo=$scope.student.fileNo;

				 $scope.transportReservation.fileNo=fileNo;

				 transportService.reserveTransport($scope.transportReservation, fileNo)
				 .then(function(response){
					 console.log('Transport Reservation callback');
					 console.log(response.data.responseBody);
					 $scope.currentTransportReservation=response.data;
					 alert("Transport Reserved Successfully")
				 })
			 };

			 //hostel work

			 $scope.getHostelAvailability=function(){

				 hostelService.getHostelAvailability().then(function(response) {
					 console.log('getHostelAvailability call back : ');
					 console.log(response);
					 if(response.data != null)
					 {
						 $scope.hostelAvailability=response.data;
					 }
				 })
			 };

			 $scope.getReservedHostel = function(){
				 var fileNo=$scope.student.fileNo;
				 console.log('Getting current hostel reservation for student : '+fileNo);
				 if(fileNo){				

					 hostelService.getReservedHostel(fileNo)
					 .then(function(response){
						 console.log('Getting reserved hosetl in controller : ');
						 console.log(response);
						 if (response !=null && response.data != null && response.data.responseBody != null) {
							 $scope.currentHostelReservation = response.data.responseBody;
						 } 					 })
				 }
			 };

			 $scope.reserveRoom = function(){

				 var fileNo=$scope.student.fileNo;
				 $scope.hostelReservation.fileNo=fileNo;

				 hostelService.reserveRoom($scope.hostelReservation, fileNo)
				 .then(function(response){
					 console.log('Hostel Reservation callback');
					 console.log(response.data.responseBody);
					 $scope.currentHostelReservation=response.data.responseBody;
					 alert("Hostel Reserved Successfully")
				 })
			 };

		 } ]);
