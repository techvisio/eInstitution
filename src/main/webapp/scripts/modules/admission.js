var admissionModule = angular.module('admissionModule', []);

admissionModule.run([ '$anchorScroll', function($anchorScroll) {
  $anchorScroll.yOffset = 200; // always scroll by 50 extra pixels
} ]);

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

       $scope.serverModelData = {};

       $scope.student = {};
       $scope.student.addressDtl = [];
       $scope.student.academicDtl = [];
       $scope.student.discountDtl = [];

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
         console.log('test');
         console.log($scope.student);
         console
         .log($scope.getStudentAddress('P').addressType);
       }

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

       $scope.addStudent = function() {
         console.log('add student called');
         console.log($scope.student);
         admissionService.addStudent($scope.student)
         .then(function(data) {
           console.log(data);
           if (data != null) {
             $scope.student = data;
           } else {
             console.log('error');
           }
         })
       }

     } ])

     admissionModule.service("admissionService", function($http, $q) {

       // Return public API.
       return ({
         addStudent : addStudent,
         getFriends : getFriends,
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

       // I get all of the friends in the remote collection.
       function getFriends() {

         var request = $http({
           method : "get",
           url : "api/index.cfm",
           params : {
             action : "get"
           }
         });

         return (request.then(handleSuccess, handleError));

       }

       // I remove the friend with the given ID from the remote collection.
       function updateStudent(id) {

         var request = $http({
           method : "delete",
           url : "api/index.cfm",
           params : {
             action : "delete"
           },
           data : {
             id : id
           }
         });

         return (request.then(handleSuccess, handleError));

       }

       // ---
       // PRIVATE METHODS.
       // ---

       // I transform the error response, unwrapping the application dta from
       // the API response payload.
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
         return (response.data);

       }

     });