admissionModule.service("admissionService", function($http, $q) {

			 // Return public API.
			 return ({
				 getStudentByCriteria : getStudentByCriteria,
				 saveStudent : saveStudent,
				 getStudent : getStudent,
				 getNewAdmission : getNewAdmission,
				 processWorkflow : processWorkflow,
	             updateStudentBasics : updateStudentBasics,
				 getStudentBasics : getStudentBasics,
				 getStudentAcademicDtl : getStudentAcademicDtl,
				 updateStudentAcademicDtl : updateStudentAcademicDtl,
				 getStudentAddress : getStudentAddress,
				 updateStudentAddress : updateStudentAddress,
				 getStudentDiscountDtl : getStudentDiscountDtl,
				 updateStudentDiscountDtl : updateStudentDiscountDtl,
				 getBranchPref : getBranchPref,
				 updateBranchPref : updateBranchPref,
				 getCounsellingDtl : getCounsellingDtl,
				 updateCounsellingDtl : updateCounsellingDtl,
				 getConsultantDtl : getConsultantDtl,
				 updateConsultantDtl : updateConsultantDtl,
				 getScholarshipDtl : getScholarshipDtl,
				 updateScholarshipDtl : updateScholarshipDtl,
				 getdocuments : getdocuments,
				 updateDocuments : updateDocuments, 
				 getLatestAdmission : getLatestAdmission,
				 getStudentByCriteria : getStudentByCriteria,
				 submitToManagement:submitToManagement,
				 saveAmenity:saveAmenity
			 });

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
			 
			 function saveStudent(student) {

				 console.log('add student called in service');
				 var request = $http({
					 method : "post",
					 url : "admission/student/",
					 params : "",
					 data : student

				 });

				 return (request.then(handleSuccess, handleError));
			 }

			 function getStudent(fileNo) {

				 var request = $http({
					 method : "get",
					 url : "admission/student/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function getNewAdmission() {

				 var request = $http({
					 method : "get",
					 url : "admission/student/new/",
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function processWorkflow(student,stepId){
				 var request = $http({
					 method : "put",
					 url : "admission/processWorkFlow/"+stepId,
					 params : "",
					 data : student
				 });
				 return (request.then(handleSuccess, handleError));
			 }
			 
			 function getStudentBasics(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/studentbasic/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function updateStudentBasics(studentBasics, fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/studentbasic/"+fileNo,
					 params : "",
					 data : studentBasics
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function getStudentAcademicDtl(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/academic/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function updateStudentAcademicDtl(academicDtl, fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/academic/"+fileNo,
					 params : "",
					 data : academicDtl
				 });
				 return (request.then(handleSuccess, handleError));
			 }			 

			 function getStudentAddress(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/address/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));				 
			 }

			 function updateStudentAddress(addressDtl,fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/address/"+fileNo,
					 params : "",
					 data : addressDtl
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function getStudentDiscountDtl(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/discount/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function updateStudentDiscountDtl(DiscountDtl, fileNo){

				 var request = $http({
					 method : "put",
					 url : "admission/student/discount/"+fileNo,
					 params : "",
					 data : DiscountDtl
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function getBranchPref(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/branchpref/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function updateBranchPref (branchPreference, fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/branchpref/"+fileNo,
					 params : "",
					 data : branchPreference
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function getCounsellingDtl(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/counselling/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));				 
			 }

			 function updateCounsellingDtl(counsellingDtl, fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/counselling/"+fileNo,
					 params : "",
					 data : counsellingDtl
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function getScholarshipDtl(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/scholarship/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function updateScholarshipDtl(scholarship, fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/scholarship/"+fileNo,
					 params : "",
					 data : scholarship
				 });
				 return (request.then(handleSuccess, handleError));
			 }

			 function getConsultantDtl(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/consultant/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }
			 
			 function updateConsultantDtl(consultantDetail, fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/consultant/"+fileNo,
					 params : "",
					 data : consultantDetail
				 });
				 return (request.then(handleSuccess, handleError));
			 }
			 
			 function getdocuments(fileNo){
				 var request = $http({
					 method : "get",
					 url : "admission/student/document/"+fileNo,
					 params : {
						 action : "get"
					 }
				 });
				 return (request.then(handleSuccess, handleError));
			 }
			 
			 function updateDocuments(studentDocument, fileNo){
				 var request = $http({
					 method : "put",
					 url : "admission/student/document/"+fileNo,
					 params : "",
					 data : studentDocument
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

			 function saveAmenity(feeStaging) {

				 console.log('save amenity called in service');
				 var request = $http({
					 method : "post",
					 url : "fee/saveAmenity/",
					 params : "",
					 data : feeStaging

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
