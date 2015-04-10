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


			 $scope.form = {};
			 $scope.form.sameAsAbove=false;
			 $scope.form.processing=false;
			 $scope.form.isEdit=true;
			 $scope.form.isNew=true;
			 $scope.dashboard=true;
			 $scope.showCriteria=false;
			 $scope.tab = 1;
			 $scope.serverModelData = {};

			 $scope.student = {};
			 $scope.latestAdmission=[];
			 $scope.searchCriteria={};
			 $scope.currentFetchLimit=2;

			 $scope.student.addressDtl = [];

			 $scope.student.consultantDetail=[];
			 $scope.student.consultantDetail.push(angular.copy($scope.dummyConsultantDetail));
			 
			 $scope.student.academicDtl = [];
			 $scope.student.academicDtl.push(angular.copy($scope.dummyQualification));


			 $scope.student.discountDtl = [];
			 $scope.student.discountDtl.push(angular.copy($scope.dummyDiscountDtl));

			 $scope.student.counsellingDtl = [];
			 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl));

			 $scope.form.showSub = false;
			 $scope.searchResultList=[];
			$scope.filteredSearch=[];

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
					 "percent" : 0.0,
					 "discountType" : null   
			 };

			 $scope.dummyCounsellingDtl = {
					 "fileNo" : null, 
					 "counsellingId" : null,
					 "rollNo" : null,
					 "rank" : null,
					 "categoryRank" : null,
					 "percentile" : 0.0
			 };


			 $scope.dummyConsultantDetail={
					 
					 "consultantId" : null
			 };
			 
			 $scope.admissionMode={"C":"Counselling",
					 "W":"Walk-In",
					 "R":"Referral"};
			 $scope.itemsPerPage = 5
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

			 $scope.LoadMoreData = function() {

				 $scope.currentFetchLimit += 5;

				 $scope.getLatestAdmission();

			 };
			 

			 $scope.lessData = function() {

				 $scope.currentFetchLimit -= 5;

				 $scope.getLatestAdmission();

			 };

			 $scope.selectTab = function (setTab){
				 $scope.tab = setTab;
			 };
			 $scope.isSelected = function(checkTab) {
				 return $scope.tab === checkTab;
			 };

			 $scope.backtoDashboard = function(){
				 if($scope.form.isNew || $scope.form.isEdit ){
					 var response=confirm("Current data will be lost..\nContinue?");
					 if(!response){
						 return;
					 }
				 }

				 $scope.dashboard=true;
				 $scope.resetSearchCriteria();
				 
			 }
			 $scope.checkAmout=function(index,type){
				 if(type=='amount'){
					 if($scope.student.discountDtl[index].amount>0){
						 $scope.student.discountDtl[index].percent=0;
					 }
				 }
				 else
				 {
					 if($scope.student.discountDtl[index].percent>0){
						 $scope.student.discountDtl[index].amount=0;
					 }
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

			 }


			 $scope.click = function(arg) {

				 console.log("print on submit click");
				 console.log($scope.student);
			 }

			 $scope.addConsultantDetail = function(){

				 var consultantDetail = angular.copy($scope.dummyConsultantDetail);
				 $scope.student.consultantDetail.push(consultantDetail);
			 };       

			 $scope.removeConsultantDetail = function(index){

				 $scope.student.consultantDetail.splice(index, 1);
			 };

			 
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


			 $scope.resetSearchCriteria = function(){

				 $scope.searchCriteria={};
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
				 if(!$scope.admissionForm.$valid){
					 alert('Form is invalid');
					 return;
				 }
				 console.log($scope.form.isEdit);
				 if(!$scope.form.isNew){
					 $scope.updateStudent();
				 }
				 else
				 {
					 $scope.addStudent();
				 }


			 }

			 $scope.submitToManagement = function(){
				 
				 console.log('submitToManagement called in controller')
				 console.log($scope.student);
				 $scope.processing=true;
				 admissionService.submitToManagement($scope.student)
				 .then(function(response) {
					 console.log('submitToManagement Data received from service : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						 $scope.student = response.data.responseBody;
						 $scope.populateMissingData($scope.student);
						 $scope.getLatestAdmission();
						 $scope.form.isNew=false;
						 $scope.form.isEdit=false;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }

					 $scope.processing=false;  
					 
				 })

				 
				 
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
						 $scope.student = response.data.responseBody;
						 $scope.populateMissingData($scope.student);
						 $scope.getLatestAdmission();
						 $scope.form.isNew=false;
						 $scope.form.isEdit=false;
						 alert("Your Records Saved Successfully")
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
						 $scope.student = response.data.responseBody;
						 $scope.populateMissingData($scope.student);
						 $scope.getLatestAdmission();
						 $scope.form.isNew=false;
						 $scope.form.isEdit=false;
					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.processing=false;  
				 })


			 }

			 $scope.getStudent = function(fileNo) {
				 $scope.processing=true;
				 admissionService.getStudent(fileNo)
				 .then(function(response) {
					 console.log('Data received from service : ');
					 console.log(response);
					 if (response !=null && response.data != null && response.data.responseBody != null) {
						 $scope.student = response.data.responseBody;
						 $scope.populateMissingData($scope.student);
						 $scope.getLatestAdmission();
						 $scope.form.isNew=false;
						 $scope.form.isEdit=false;
						 $scope.dashboard=false;

					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
					 $scope.processing=false;
				 })

			 }


			 $scope.getLatestAdmission = function(){

				 admissionService.getLatestAdmission($scope.currentFetchLimit)
				 .then(function(response) {
					 console.log('Latest admission data received in controller : ');
					 console.log(response);
					 if (response !=null && response.data != null && response.data.responseBody != null) {
						 $scope.latestAdmission = response.data.responseBody;

					 } else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
				 })

			 }

			 $scope.showDetail =  function(index){

				 var fileNo=$scope.latestAdmission[index].fileNo;
				 if(fileNo){
				 $scope.getStudent(fileNo);
				 }
				 else
					 {
					 alert("No valid fileNo provided");
					 }
			 }
			 
			 $scope.showStudentDetail =  function(index){

				 var fileNo=$scope.searchResultList[index].fileNo;
				 if(fileNo){
				 $scope.getStudent(fileNo);
				 }
				 else
					 {
					 alert("No valid fileNo provided");
					 }
			 }

			 $scope.getStudentByCriteria = function() {
				 console.log('get student by search criteria in controller');
				 console.log($scope.searchCriteria);
				 $scope.processing=true;
				 admissionService.getStudentByCriteria($scope.searchCriteria)
				 .then(function(response) {
					 console.log('Data received from service in controller : ');
					 console.log(response);
					 if (response != null && response.data != null && response.data.responseBody != null) {
						$scope.searchResultList=response.data.responseBody;
						$scope.currentPage=1;
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

				 console.log("calling reset.....")
				 $scope.form.processing=false;
				 $scope.form.isNew=true;
				 $scope.form.isEdit=true;
				 $scope.student = {};
				 $scope.student.addressDtl = [];

				 $scope.student.academicDtl = [];
				 $scope.student.academicDtl.push(angular.copy($scope.dummyQualification));

				 $scope.student.discountDtl = [];
				 $scope.student.discountDtl.push(angular.copy($scope.dummyDiscountDtl));

				 $scope.student.counsellingDtl = [];
				 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl));
				 
				 $scope.student.consultantDetail=[];
				 $scope.student.consultantDetail.push(angular.copy($scope.dummyConsultantDetail));

			 }

			 $scope.resetAdmissionMode = function(){
				 
				 $scope.student.counsellingDtl = [];
				 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl));
				 
				 $scope.student.referredBy = null;
				 
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

				 if(angular.isUndefined(data.consultantDetail) || data.consultantDetail==null || data.consultantDetail.length == 0)
				 {
					 $scope.student.consultantDetail=[];
					 $scope.student.consultantDetail.push(angular.copy($scope.dummyConsultantDetail));
				 }

				 if(angular.isUndefined(data.counsellingDtl) || data.counsellingDtl==null || data.counsellingDtl.length == 0)
				 {
					 $scope.student.counsellingDtl = [];
					 $scope.student.counsellingDtl.push(angular.copy($scope.dummyCounsellingDtl))
				 }

				 for(var i=0; i<$scope.student.academicDtl.length; i++){

					 if(angular.isUndefined($scope.student.academicDtl[i].qualificationSubDtl) ||$scope.student.academicDtl[i].qualificationSubDtl==null || $scope.student.academicDtl[i].qualificationSubDtl.length==0)
					 {

						 $scope.student.academicDtl[i].qualificationSubDtl=[];
						 $scope.student.academicDtl[i].qualificationSubDtl.push(angular.copy($scope.dummyQualificationSubDtl))
					 }
				 } 

			 }

			 $scope.showTransportModal=function (size) {

				 if(!$scope.form.isEdit && !scope.form.isNew){
					 return;
				 }
				 var fileNo=$scope.student.fileNo;
				 if($scope.form.isNew){
					 alert("Please save record before reserving transport");
					 return;
				 }

				 var modalInstance = $modal.open({
					 templateUrl: 'views/transport.html',
					 controller: 'transportController',
					 scope:$scope,
					 size: size,
					 resolve: {
						 items: function () {
							 return $scope.items;
						 }
					 }
				 });

				 modalInstance.result.then(function (selectedItem) {
					 $scope.selected = selectedItem;
				 });
			 };


			 $scope.showHostelModal=function (size) {

				 if(!$scope.form.isEdit && !scope.form.isNew){
					 return;
				 }
				 var fileNo=$scope.student.fileNo;

				 if($scope.form.isNew){
					 alert("Please save record before reserving hostel");
					 return;
				 }

				 var modalInstance = $modal.open({
					 templateUrl: 'views/hostel.html',
					 controller: 'hostelController',
					 scope:$scope,
					 size: size,
					 resolve: {
						 items: function () {
							 return $scope.items;
						 }
					 }
				 });

				 modalInstance.result.then(function (selectedItem) {
					 $scope.selected = selectedItem;
				 });
			 };
			 
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

				  $scope.open = function($event) {
				    $event.preventDefault();
				    $event.stopPropagation();

				    $scope.opened = true;
				  };

				  $scope.dateOptions = {
				    formatYear: 'yy',
				    startingDay: 1
				  };

		 } ])

		 admissionModule.service("admissionService", function($http, $q) {

			 // Return public API.
			 return ({
				 addStudent : addStudent,
				 getStudent : getStudent,
				 updateStudent : updateStudent,
				 getLatestAdmission : getLatestAdmission,
				 getStudentByCriteria : getStudentByCriteria,
				 submitToManagement:submitToManagement
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


			 function getLatestAdmission(limit){

				 console.log("Latest Admission called from service")
				 var request = $http({
					 method : "get",
					 url : "admission/LatestAdmissionInfo/"+limit,
					 params : {
						 action : "get"
					 }
				 });

				 return (request.then(handleSuccess, handleError));
			 }

			 function getStudentByCriteria(searchCriteria){

				 console.log('Getting student by search criteria in service');
				 var request = $http({
					 method : "post",
					 url : "admission/search/",
					 params : "",
					 data : searchCriteria

				 });

				 return (request.then(handleSuccess, handleError));
			 }

			 function submitToManagement(student){

				 console.log('submit to management called in service');
				 var request = $http({
					 method : "post",
					 url : "admission/submitToManagement/",
					 params : "",
					 data : student

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

