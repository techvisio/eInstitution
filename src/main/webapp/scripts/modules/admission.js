var admissionModule = angular.module('admissionModule', []);
admissionModule.run(['$anchorScroll', function($anchorScroll) {
	$anchorScroll.yOffset = 50;   // always scroll by 50 extra pixels
}]);

admissionModule.controller('headerCtrl', [ '$anchorScroll', '$location',
                                           '$scope', function($anchorScroll, $location, $scope) {
	$scope.gotoAnchor = function(x) {
		var newHash = x;
		if ($location.hash() !== newHash) {
			// set the $location.hash to `newHash` and
			// $anchorScroll will automatically scroll to it
			$location.hash(x);
		} else {
			// call $anchorScroll() explicitly,
			// since $location.hash hasn't changed
			$anchorScroll();
		}
	};
} ]);


admissionModule
.controller(
		'admissionController',
		[
		 '$scope',
		 'admissionService',
		 'masterdataService',
		 function($scope, admissionService,masterdataService) {

			 $scope.processing=false;
			 $scope.isEdit=false;
			 $scope.serverModelData = {};

			 $scope.student = {};
			 $scope.student.addressDtl = [];
			 $scope.student.academicDtl = [{
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
			 }];
			 $scope.student.discountDtl = [{
				 "fileNo" : null,
				 "feeHeadId" : null,
				 "amount" : 0.0,
				 "percent" : 0.0
			 }];

			 $scope.student.counsellingDtl = [{

				 "fileNo" : null, 
				 "counsellingId" : null,
				 "rollNo" : null,
				 "rank" : null,
				 "categoryRank" : null,
				 "percentile" : 0.0
			 }];

			 $scope.showSub = false;

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
				 if($scope.sameAsAbove == true){
					 console.log('same as above');
					 var cAddress=$scope.getStudentAddress('C');
					 angular.copy($scope.getStudentAddress('P'),cAddress)
					 cAddress.addressType='C';
				 }
			 }

			 $scope.saveStudent = function(){
				 if($scope.isEdit){
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
						 $scope.isEdit=true;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.processing=false;  
				 })


			 }

			 $scope.updateStudent = function() {
				 console.log('update student called');
				 console.log($scope.student);
				 $scope.processing=true;
				 admissionService.updateStudent($scope.student)
				 .then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.populateMissingData(response.data.responseBody);
						 $scope.student = response.data.responseBody;
						 $scope.isEdit=true;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.processing=false;  
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
						 $scope.isEdit=true;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.processing=false;
				 })

			 }



			 $scope.resetForm = function(){

				 $scope.processing=false;
				 $scope.isEdit=false;
				 $scope.student = {};
				 $scope.student.addressDtl = [];
				 $scope.student.academicDtl = [{
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
				 }];
				 $scope.student.discountDtl = [{
					 "fileNo" : null,
					 "feeHeadId" : null,
					 "amount" : 0.0,
					 "percent" : 0.0
				 }];

				 $scope.student.counsellingDtl = [{

					 "fileNo" : null, 
					 "counsellingId" : null,
					 "rollNo" : null,
					 "rank" : null,
					 "categoryRank" : null,
					 "percentile" : 0.0
				 }];

			 }

			 $scope.populateMissingData = function(data){

				 if(angular.isUndefined(data.academicDtl) || data.academicDtl==null || data.academicDtl.length<0)
				 {
					 $scope.student.academicDtl = [];
					 $scope.student.academicDtl.push(angular.copy($scope.dummyAcademicDtl))
				 }

				 if(angular.isUndefined(data.discountDtl) || data.discountDtl==null || data.discountDtl.length<0)
				 {
					 $scope.student.discountDtl = [];
					 $scope.student.discountDtl.push(angular.copy($scope.dummyDiscountDtl));
				 }


				 if(angular.isUndefined(data.counsellingDtl) || data.counsellingDtl==null || data.counsellingDtl.length<0)
				 {
					 $scope.student.counsellinDtl = [];
					 $scope.student.counsellinDtl.push(angular.copy($scope.dummyCounsellingDtl))
				 }

			 }

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

