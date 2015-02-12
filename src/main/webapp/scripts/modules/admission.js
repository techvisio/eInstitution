var admissionModule = angular.module('admissionModule', ['ui.bootstrap']);

admissionModule
.controller(
		'admissionController',
		[
		 '$scope',
		 'admissionService',
		 'masterdataService',
		 '$modal',
		  function($scope, admissionService,masterdataService,$modal) {
			 
			 $scope.subModules=["personal","address","academic","discount","other"];
		 	$scope.selection=$scope.subModules[0];	
		 	$scope.form = {};
		 	$scope.form.sameAsAbove=false;
			 $scope.form.processing=false;
			 $scope.form.isEdit=false;
			 $scope.serverModelData = {};

			 $scope.student = {};
			 $scope.student.addressDtl = [];
			 
			 $scope.student.academicDtl = [];
			 $scope.student.academicDtl.push(angular.copy($scope.dummyQualification));
			 
			 
			 $scope.student.discountDtl = [];
			 $scope.student.discountDtl.push(angular.copy($scope.dummyDiscountDtl));

			 $scope.student.counsellingDtl = [];
			 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl));

			 $scope.form.showSub = false;

			 $scope.dummyAddress = {
					 "houseNo" : null,
					 "locality" : null,
					 "landmark" : null,
					 "district" : null,
					 "city" : null,
					 "pincode" : null,
					 "fileNo" : null,
					 "addressType" : null,
					 "state" : null
			 };
			 $scope.dummyQualification = {
					 "university" : null,
					 "collegeName" : null,
					 "passingYear" : null,
					 "percentage" : 0.0,
					 "rollNo" : null,
					 "fileNo" : null,
					 "qualificationId" : null,
					 "qualificationSubDtl" : [ {
						 "subjectId" : null,
						 "qualificationId" : null,
						 "fileNo" : null,
						 "marksObtained" : 0.0,
						 "maxMarks" : 0.0
					 } ]
			 };
			 $scope.dummyQualificationSubDtl = {
					 "subjectId" : null,
					 "qualificationId" : null,
					 "fileNo" : null,
					 "marksObtained" : 0.0,
					 "maxMarks" : 0.0
			 };

			 $scope.dummyDiscountDtl = {
					 "fileNo" : null,
					 "feeHeadId" : null,
					 "amount" : 0.0,
					 "percent" : 0.0
			 };

			 $scope.dummyCounsellingDtl = {
					 "fileNo" : null, 
					 "counsellingId" : null,
					 "rollNo" : null,
					 "rank" : null,
					 "categoryRank" : null,
					 "percentile" : 0.0
			 };


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

			 }


			 $scope.click = function(arg) {
				 
				 console.log("print on submit click");
				 console.log($scope.student);
			 }

			 $scope.addDiscountDtl = function(){

				 var discountDtl = angular.copy($scope.dummyDiscountDtl);
				 $scope.student.discountDtl.push(discountDtl);
			 };       

			 $scope.removeDiscountDtl = function(index){

				 $scope.student.discountDtl.splice(index, 1);
			 };


			 $scope.addCounsellingDtl = function(){

				 var counsellingDtl = angular.copy($scope.dummyCounsellingDtl);
				 $scope.student.counsellingDtl.push(counsellingDtl);
			 };       

			 $scope.removeCounsellingDtl = function(index){

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

			 $scope.getStudentAddress = function(addressType) {
				 var address = null;
				 for (var i = 0; i <= $scope.student.addressDtl.length - 1; i++) {
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

			 $scope.copyPermanentAddress=function(){
				 console.log('same as above');
				 console.log($scope.form.sameAsAbove);
				 if($scope.form.sameAsAbove == true){
					 console.log('same as above');
					 var cAddress=$scope.getStudentAddress('C');
					 angular.copy($scope.getStudentAddress('P'),cAddress)
					 cAddress.addressType='C';
				 }
			 }

			 $scope.saveStudent = function(){
				 console.log($scope.form.isEdit);
				 if($scope.form.isEdit){
					 $scope.updateStudent();
				 }
				 else
				 {
					 $scope.addStudent();
				 }
			 }

			 $scope.addStudent = function() {
				 console.log('add student called');
				 console.log($scope.student);
				 $scope.processing=true;
				 admissionService.addStudent($scope.student)
				 .then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.populateMissingData(response.data.responseBody);
						 $scope.student = response.data.responseBody;
						 $scope.form.isEdit=true;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.form.processing=false;  
				 })


			 }

			 $scope.updateStudent = function() {
				 console.log('update student called');
				 console.log($scope.student);
				 $scope.form.processing=true;
				 admissionService.updateStudent($scope.student)
				 .then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.populateMissingData(response.data.responseBody);
						 $scope.student = response.data.responseBody;
						 $scope.form.isEdit=true;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.form.processing=false;  
				 })


			 }

			 $scope.getStudent = function() {
				 var fileNo=prompt("Enter File No", "");
				 $scope.processing=true;

				 admissionService.getStudent(fileNo)
				 .then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if (response !=null && response.data != null && response.data.responseBody != null) {
						 $scope.student = response.data.responseBody;
						 $scope.populateMissingData($scope.student);
						 $scope.form.isEdit=true;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.processing=false;
				 })

			 }

			 				
				 $scope.next=function(){
				 var selectionIndex=$scope.subModules.indexOf($scope.selection);
				 if(selectionIndex != $scope.subModules.length-1){
				 selectionIndex=selectionIndex+1;
				 $scope.selection=$scope.subModules[selectionIndex];
				 $scope.form.direction=1;
				 }
				 }
				 
				 $scope.prev=function(){
					 var selectionIndex=$scope.subModules.indexOf($scope.selection);
					 if(selectionIndex != 0){
					 selectionIndex=selectionIndex-1;
					 $scope.selection=$scope.subModules[selectionIndex];
					 $scope.form.direction=0;
					 }
					 }

			 $scope.resetForm = function(){

				 $scope.form.processing=false;
				 $scope.form.isEdit=false;
				 $scope.student = {};
				 $scope.student.addressDtl = [];
				 
				 $scope.student.academicDtl = [];
				 $scope.student.academicDtl.push(angular.copy($scope.dummyQualification));
				 
				 $scope.student.discountDtl = [];
				 $scope.student.discountDtl.push(angular.copy($scope.dummyDiscountDtl));

				 $scope.student.counsellingDtl = [];
				 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl));

			 }

			 $scope.populateMissingData = function(data){

				 if(angular.isUndefined(data.academicDtl) || data.academicDtl==null || data.academicDtl.length == 0)
				 {
					 $scope.student.academicDtl = [];
					 $scope.student.academicDtl.push(angular.copy($scope.dummyQualification))
				 }

				 if(angular.isUndefined(data.discountDtl) || data.discountDtl==null || data.discountDtl.length == 0)
				 {
					 $scope.student.discountDtl = [];
					 $scope.student.discountDtl.push(angular.copy($scope.dummyDiscountDtl));
				 }


				 if(angular.isUndefined(data.counsellingDtl) || data.counsellingDtl==null || data.counsellingDtl.length == 0)
				 {
					 $scope.student.counsellingDtl = [];
					 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl))
				 }

			 }

			 $scope.showTransportModal=function (size) {

				    var modalInstance = $modal.open({
				      templateUrl: 'views/transport.html',
				      controller: 'transportController',
				      size: size,
				      resolve: {
				        items: function () {
				          return $scope.items;
				        }
				      }
				    });

				    modalInstance.result.then(function (selectedItem) {
				      $scope.selected = selectedItem;
				    }, function () {
				      $log.info('Modal dismissed at: ' + new Date());
				    });
				  };
				  
				  
					 $scope.showHostelModal=function (size) {

						    var modalInstance = $modal.open({
						      templateUrl: 'views/hostel.html',
						      controller: 'hostelController',
						      size: size,
						      resolve: {
						        items: function () {
						          return $scope.items;
						        }
						      }
						    });

						    modalInstance.result.then(function (selectedItem) {
						      $scope.selected = selectedItem;
						    }, function () {
						      $log.info('Modal dismissed at: ' + new Date());
						    });
						  };
				  
		 } ])

		 admissionModule.service("admissionService", function($http, $q) {

			 // Return public API.
			 return ({
				 addStudent : addStudent,
				 getStudent : getStudent,
				 updateStudent : updateStudent
			 });

			 function addStudent(student) {

				 console.log('add student called in service');
				 var request = $http({
					 method : "post",
					 url : "admission",
					 params : "",
					 data : student

				 });

				 return (request.then(handleSuccess, handleError));

			 }

			 function updateStudent(student) {

				 console.log('update student called in service');
				 var request = $http({
					 method : "put",
					 url : "admission",
					 params : "",
					 data : student

				 });

				 return (request.then(handleSuccess, handleError));

			 }


			 function getStudent(fileNo) {

				 var request = $http({
					 method : "get",
					 url : "admission/"+fileNo,
					 params : {
						 action : "get"
					 }
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

