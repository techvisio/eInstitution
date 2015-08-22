feeModule.service('feeService', function($http, $q){

	// Return public API.
	return ({
		getUnapprovedList : getUnapprovedList,
		getAdmissionDetailManagement : getAdmissionDetailManagement,
		updateAdmissionDetailManagement : updateAdmissionDetailManagement,
		getFeeTransactionAndBasicInfoDetail : getFeeTransactionAndBasicInfoDetail,
		depositeFee : depositeFee,
		getPendingFee : getPendingFee,
	});

	function getUnapprovedList(size) {
		console.log('Get unapproved list service');
		var request = $http({
			method : "get",
			url : "service/management/uapprovedList/"+size,
			params : ""

		});
		return (request.then(handleSuccess, handleError));
	}

	function getAdmissionDetailManagement(fileNo) {

		console.log('getting Admission detail for management in service');
		var request = $http({
			method : "get",
			url : "service/management/admission/approval/"+fileNo,
			params : ""

		});
		return (request.then(handleSuccess, handleError));
	}

	function updateAdmissionDetailManagement(student) {
		console.log('update Admission Detail for Management called in service');
		var request = $http({
			method : "put",
			url : "service/management/updateManagementChanges/",
			params : "",
			data : student
		});
		return (request.then(handleSuccess, handleError));
	}

	function getFeeTransactionAndBasicInfoDetail(fileNo) {
		console.log('fee Transaction called in service');
		var request = $http({
			method : "get",
			url : "service/fee/feeTransaction/" + fileNo,
			params : {
				action : "get"
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function depositeFee(newTransaction) {
		console.log('fee deposite called in service');
		var request = $http({
			method : "post",
			url : "service/fee/feeTransactionCredit/",
			params : "",
			data : newTransaction
		});
		return (request.then(handleSuccess, handleError));
	}

	function getPendingFee(limit) {

		console.log('pending fee called in service');
		var request = $http({
			method : "get",
			url : "service/fee/pandingFee/" + limit,
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
		return ($q.reject(response.data.message));
	}

	function handleSuccess(response) {
		console.log('handle success');
		console.log(response);
		return (response);
	}

});