enquiryModule.service('enquiryService', function($http, $q) {

	// Return public API.
	return ({
		 getDueEnquiry : getDueEnquiry,
		 getEnquiryBySearchCriteria : getEnquiryBySearchCriteria,
		 addEnquiry : addEnquiry,
		 getEnquiry : getEnquiry,
		 updateEnquiry : updateEnquiry,
		 getDueEnquiry : getDueEnquiry,
		 closeEnquiry : closeEnquiry,
		 proceedToAdmission : proceedToAdmission
	 });
	
	function getDueEnquiry() {

		 console.log('get due enquiries');
		 var request = $http({
			 method : "get",
			 url : "enquiry/enquiryByTaskDate/",
			 params : {
				 action : "get"
			 }
		 });

		 return (request.then(handleSuccess, handleError));

	 }
	
	function getEnquiry(enquiryId) {

		 var request = $http({
			 method : "get",
			 url : "enquiry/"+enquiryId,
			 params : {
				 action : "get"
			 }
		 });

		 return (request.then(handleSuccess, handleError));

	 }
	
	 function getEnquiryBySearchCriteria(searchCriteria){

		 console.log('Getting enquiry by search criteria in service');
		 var request = $http({
			 method : "post",
			 url : "enquiry/search/",
			 params : "",
			 data : searchCriteria

		 });

		 return (request.then(handleSuccess, handleError));
	 }

	 
	 function addEnquiry(enquiryAndTaskBean){
		 console.log('add new enquiry');
		 var request = $http({
			 method : "post",
			 url : "enquiry",
			 params : "",
			 data: enquiryAndTaskBean

		 });
		 
		 return (request.then(handleSuccess, handleError));
	}
	
	 function updateEnquiry(enquiryAndTaskBean, enquiryId){
		 
		 console.log('update student called in service');
		 var request = $http({
			 method : "put",
			 url : "enquiry/"+enquiryId,
			 params : "",
			 data : enquiryAndTaskBean

		 });

		 return (request.then(handleSuccess, handleError));
	 }
	 	 
	function proceedToAdmission(enquiryAndTaskBean){
		 console.log('proceed to admission called ins service');
		 var request = $http({
			 method : "post",
			 url : "enquiry/proceedToAdmission/",
			 params : "",
			 data: enquiryAndTaskBean

		 });	
		 
		 return (request.then(handleSuccess, handleError));
	}

	
	 function closeEnquiry(enquiryAndTaskBean){
		 
		 console.log('close enquiry called in service');
		 var request = $http({
			 method : "put",
			 url : "enquiry/toggleEnquiryStatus/",
			 params : "",
			 data : enquiryAndTaskBean

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
