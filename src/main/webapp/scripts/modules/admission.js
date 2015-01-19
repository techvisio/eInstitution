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

admissionModule.directive('dynamic', function($compile) {
  return {
    restrict : 'A',
    replace : true,
    link : function(scope, ele, attrs) {
      scope.$watch(attrs.dynamic, function(html) {
        ele.html(html);
        $compile(ele.contents())(scope);
      });
    }
  };
});

admissionModule
.controller(
    'admissionController',
    [
     '$scope',
     'admissionService',
     function($scope, admissionService) {

       $scope.serverModelData = {
           user : "Adam",
           personalDetailAttributes : [ {
             "id" : "fileNo",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "File No."
           }, {
             "id" : "enrollNo",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Enrollment No."
           }, {
             "id" : "uniEnrollNo",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "University Enrollment No."
           }, {
             "id" : "firstName",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "First Name"
           }, {
             "id" : "lastName",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Last Name"
           }, {
             "id" : "fatherName",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Father Name"
           }, {
             "id" : "motherName",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Mother Name"
           }, {
             "id" : "gender",
             "type" : "radio",
             "masterDataCode" : "",
             "validValue" : [ "Male", "Female" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Gender"
           }, {
             "id" : "dob",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Date of Birth"
           }, {
             "id" : "bloodGroup",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Blood Group"
           }, {
             "id" : "fatherOccupation",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Father Occupation"
           }, {
             "id" : "fixedlineNo",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Fixed Line No."
           }, {
             "id" : "selfMobileNo",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Student Mobile No."
           }, {
             "id" : "parentMobileNo",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Parent Mobile No."
           }, {
             "id" : "gaurdianMobileNo",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Gaurdian Mobile No."
           }, {
             "id" : "emailId",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Student EmailId"
           }, {
             "id" : "gaurdianEmailId",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Gaurdian EmailId"
           }, {
             "id" : "hostel",
             "type" : "check",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Hostel Required ?"
           }, {
             "id" : "transportation",
             "type" : "check",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Transport Required ?"
           }, {
             "id" : "academicYear",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Academic Year"
           }, {
             "id" : "semester",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Semester"
           }, {
             "id" : "managementApproval",
             "type" : "check",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Management Approved"
           }, {
             "id" : "feePaid",
             "type" : "check",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Fees Paid"
           }, {
             "id" : "categoryId",
             "type" : "select",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Category"
           }, {
             "id" : "courseId",
             "type" : "select",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Course"
           }, {
             "id" : "branchId",
             "type" : "select",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Branch"
           }, {
             "id" : "createdBy",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Created By"
           }, {
             "id" : "createdOn",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Created On"
           }, {
             "id" : "updatedBy",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Updated By"
           }, {
             "id" : "updatedOn",
             "type" : "text",
             "masterDataCode" : "",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Updated On"
           }, {
             "id" : "domicileState",
             "type" : "select",
             "masterDataCode" : "state",
             "validValue" : [ "" ],
             "mandatoryInd" : false,
             "visible" : false,
             "title" : "Domicile State"
           } ],
           dropdownMasterData : {
             state : [ {
               "id" : "1",
               "value" : "UP"
             }, {
               "id" : "2",
               "value" : "MP"
             } ],
             qualification : [ {
               "id" : "1",
               "value" : "High School"
             }, {
               "id" : "2",
               "value" : "Intermediate"
             } ]
           }
       };

       $scope.student = {};
       $scope.student.addressDtl = [ {
         "houseNo" : null,
         "locality" : null,
         "landmark" : null,
         "district" : null,
         "city" : null,
         "pincode" : null,
         "fileNo" : null,
         "addressType" : "C",
         "state" : null
       } ];
       $scope.student.academicDtl = [ {
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
       } ];
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

       // This is code for generating dynamiccHTML for
       // Personal details Tab
       // since field will be dynamic in this case
       // #1 Start
       $scope.html = '<table class="table borderless">';
       for (var i = 0; i <= $scope.serverModelData.personalDetailAttributes.length - 1; i++) {
         var attribute = $scope.serverModelData.personalDetailAttributes[i];
         var attrHTML = '';
         if (i % 2 == 0) {
           $scope.html = $scope.html + '<tr>';
         }
         $scope.html = $scope.html + '<td>';
         $scope.html = $scope.html + attribute.title
         + '</td><td>';
         if (attribute.type == 'text') {
           attrHTML = '<input type="Text" ng-model="student.'
             + attribute.id + '">';
         }
         // else if (attribute.type == 'select') {
         // attrHTML='<select
         // ng-model="student.'+attribute.id+'"> <option
         // ng-repeat="object in
         // serverModelData.dropdownMasterData.'+attribute.masterDataCode+'"
         // value="{{object.id}}">{{object.value}}</option></select>';
         // }
         else if (attribute.type == 'radio') {
           attrHTML = attrHTML + '<table><tr>';
           for (var j = 0; j <= attribute.validValue.length - 1; j++) {
             attrHTML = attrHTML + '<td>';
             attrHTML = attrHTML
             + '<input type="radio" ng-model="student.'
             + attribute.id + '" value="'
             + attribute.validValue[j]
             + '" name="' + attribute.id
             + '">'
             + attribute.validValue[j];
             attrHTML = attrHTML + '</td>';
           }
           ;
           attrHTML = attrHTML + '</tr></table>';

         } else if (attribute.type == 'date') {
           attrHTML = '<input type="date" ng-model="student.'
             + attribute.id + '">';
         }
         ;
         $scope.html = $scope.html + attrHTML;
         $scope.html = $scope.html + '</td>';
         if (i % 2 == 1) {
           $scope.html = $scope.html + '</tr>';
         }
       }
       $scope.html = $scope.html + '</table>';
       // #1 End

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

//     -------------------------------------------------- //
//     -------------------------------------------------- //

//     I act a repository for the remote friend collection.
     admissionModule.service("admissionService", function($http, $q) {

       // Return public API.
       return ({
         addStudent : addStudent,
         getFriends : getFriends,
         updateStudent : updateStudent
       });

       // ---
       // PUBLIC METHODS.
       // ---

       // I add a friend with the given name to the remote collection.
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