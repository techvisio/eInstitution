transportModule.service('transportService', function($http, $q) {

	// Return public API.
	return ({
		getAvailableTransport : getAvailableTransport,
		reserveTransport : reserveTransport,
		cancelReservation : cancelReservation,
		getReservedTransport : getReservedTransport,
		getAllocatedTransport : getAllocatedTransport,
		AllocateTransport : AllocateTransport,
		cancelAllocation : cancelAllocation,
		getStudentByCriteria : getStudentByCriteria,
		getStudentBasicInfo : getStudentBasicInfo,
		getAvailableVehicles : getAvailableVehicles 
	});

	
	function getAvailableVehicles() {

		console.log('get available vehicle called in service');
		var request = $http({
			method : "get",
			url : "service/transport/availableVehicles/",
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function getAvailableTransport() {

		console.log('getAvailableTransport called in service');
		var request = $http({
			method : "get",
			url : "service/transport/availableTransport",
			params : {
				action : "get"
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function getReservedTransport(fileNo) {

		console.log('getReservedTransport called in service');
		var request = $http({
			method : "get",
			url : "service/transport/reservation/"+fileNo,
			params : {
				action : "get"
			}
		});
		return (request.then(handleSuccess, handleError));
	}


	function reserveTransport(transportReservation, fileNo) {

		console.log('Transport reservation called in service');
		var request = $http({
			method : "post",
			url : "service/transport/reservation/" + fileNo,
			params : "",
			data : transportReservation

		});
		return (request.then(handleSuccess, handleError));
	}

	function cancelReservation(fileNo) {
		var request = $http({
			method : "delete",
			url : "service/transport/reservation/" + fileNo,
			params : {
				action : "delete"
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function getAllocatedTransport(fileNo) {

		console.log('getAllocatedTransport called in service');
		var request = $http({
			method : "get",
			url : "service/transport/transportAllocation/" + fileNo,
			params : {
				action : "get"
			}
		});
		
		return (request.then(handleSuccess, handleError));

	}

	function AllocateTransport(transportAllocation, fileNo) {

		console.log('Transport Allocation called in service');
		var request = $http({
			method : "post",
			url : "service/transport/transportAllocation/" + fileNo,
			params : "",
			data : transportAllocation

		});

		return (request.then(handleSuccess, handleError));

	}

	function cancelAllocation(fileNo) {
		var request = $http({
			method : "delete",
			url : "service/transport/transportAllocation/" + fileNo,
			params : {
				action : "delete"
			}
		});
		return (request.then(handleSuccess, handleError));
	}
	
	function getStudentByCriteria(searchCriteria){

		console.log('Getting student by search criteria in service');
		var request = $http({
			method : "post",
			url : "service/transport/searchStudent/",
			params : "",
			data : searchCriteria

		});
		return (request.then(handleSuccess, handleError));
	}
	
	function getStudentBasicInfo(fileNo) {

		console.log('getStudentBasicInfo called in service');
		var request = $http({
			method : "get",
			url : "service/transport/basicInfo/" + fileNo,
			params : {
				action : "get"
			}
		});
		return (request.then(handleSuccess, handleError));
	}


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
		return (response);

	}

});