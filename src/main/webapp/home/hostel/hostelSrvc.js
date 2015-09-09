hostelModule.service('hostelService', function($http, $q) {

	// Return public API.
	return ({
		getHostelAvailability : getHostelAvailability,
		reserveRoom : reserveRoom,
		getReservedHostel : getReservedHostel,
		cancelReservation : cancelReservation,
		getHostelAllocationAdmissionDetail : getHostelAllocationAdmissionDetail,
		addHostelAllocationAdmissionDetail : addHostelAllocationAdmissionDetail
	});

	function getHostelAvailability() {

		console.log('getHostelAvailability called in service');
		var request = $http({
			method : "get",
			url : "service/hostel/hostelAvailability/",
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function getReservedHostel(fileNo) {

		console.log('getReservedHostel called in service');
		var request = $http({
			method : "get",
			url : "service/hostel/hostelReservation/" + fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function reserveRoom(hostelReservation, fileNo) {

		console.log('Hostel reservation called in service');
		var request = $http({
			method : "post",
			url : "service/hostel/hostelReservation/" + fileNo,
			params : "",
			data : hostelReservation

		});

		return (request.then(handleSuccess, handleError));

	}

	function cancelReservation(fileNo) {
		var request = $http({
			method : "delete",
			url : "service/hostel/hostelReservation/" + fileNo,
			params : {
				action : "delete"
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function getHostelAllocationAdmissionDetail(fileNo){
		console.log('getHostelAllocationAdmissionDetail called in service')
		var request = $http({
			method : "get",
			url : "hostel/hostelAllocationAdmission/"+fileNo,
			params : {
				action : "get"
			}
		});
		return (request.then(handleSuccess, handleError));
	}

	function addHostelAllocationAdmissionDetail(hostelAllocationAdmissionDtl){
		console.log('addHostelAllocationAdmissionDetail called in service');
		var request = $http({
			method : "post",
			url : "hostel/hostelAllocationAdmission/",
			params : "",
			data: hostelAllocationAdmissionDtl

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