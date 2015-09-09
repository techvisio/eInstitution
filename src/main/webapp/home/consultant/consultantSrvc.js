

consultantModule.service('consultantService', function($http, $q) {

	// Return public API.
	return ({
		addConsultant  : addConsultant,
		getConsultantByCriteria : getConsultantByCriteria,
		getStudentByCriteria : getStudentByCriteria,
		updateConsultant : updateConsultant,
		getConsultant : getConsultant,
		getConsultantAdmissionDetail : getConsultantAdmissionDetail,
		addConsultantAdmissionDetail : addConsultantAdmissionDetail,
		addAdmConsultant : addAdmConsultant 
	});

	function getConsultant(consultantId){
		console.log('get due enquiries');
		
		var request = $http({
			method : "get",
			url : "service/consultant/consultantMaster/"+consultantId,
			params : {
				action : "get"
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function getConsultantByCriteria(searchCriteria){
		console.log('search enquiries');
		var request = $http({
			method : "post",
			url : "service/consultant/search/",
			params : "",
			data: searchCriteria

		});
		return (request.then(handleSuccess, handleError));
	}

	function addConsultant(consultant){
		console.log('add new consultant');
		var request = $http({
			method : "post",
			url : "service/consultant/consultantMaster/",
			params : "",
			data: consultant

		});
		return (request.then(handleSuccess, handleError));
	}

	function updateConsultant(consultant){
		console.log('update consultant called in service');
		var request = $http({
			method : "put",
			url : "service/consultant/consultantMaster/",
			params : "",
			data: consultant

		});
		return (request.then(handleSuccess, handleError));
	}

	function addAdmConsultant(AdmissnConsltntDtl){
		console.log('update Admission consultant called in service');
		var request = $http({
			method : "put",
			url : "service/consultant/admservice/consultant/",
			params : "",
			data: AdmissnConsltntDtl

		});
		return (request.then(handleSuccess, handleError));
	}
	
	function getConsultantAdmissionDetail(fileNo){
		console.log('getConsultantAdmissionDetail called in service')
		var request = $http({
			method : "get",
			url : "service/consultant/consultantAdmission/"+fileNo,
			params : {
				action : "get"
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function addConsultantAdmissionDetail(consultantAdmissionDetail){
		console.log('addConsultantAdmissionDetail called in service');
		var request = $http({
			method : "post",
			url : "service/consultant/consultantAdmission/",
			params : "",
			data: consultantAdmissionDetail

		});
		return (request.then(handleSuccess, handleError));
	}

	function getStudentByCriteria(searchCriteria){

		console.log('Getting student by search criteria in service');
		var request = $http({
			method : "post",
			url : "service/consultant/searchStudent/",
			params : "",
			data : searchCriteria

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