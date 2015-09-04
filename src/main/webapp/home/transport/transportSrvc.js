transportModule.service('transportService', function($http, $q) {

	// Return public API.
	return ({
		getAvailableTransport : getAvailableTransport,
		reserveTransport : reserveTransport,
		cancelReservation : cancelReservation,
		getReservedTransport : getReservedTransport,
//		getTransportlAllocationAdmissionDetail : getTransportlAllocationAdmissionDetail,
//		addTransportAllocationAdmissionDetail : addTransportAllocationAdmissionDetail,
//		updateTransportAllocationAdmissionDetail : updateTransportAllocationAdmissionDetail,
//		getTransportAllocationDtlForStudent : getTransportAllocationDtlForStudent,
//		getTransportAllocationDtlForVehicle : getTransportAllocationDtlForVehicle
	});

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

//	function getTransportlAllocationAdmissionDetail(fileNo){
//		console.log('getTransportlAllocationAdmissionDetail called in service')
//		var request = $http({
//			 method : "get",
//			 url : "service/transport/transportAllocationAdmission/"+fileNo,
//			 params : {
//				 action : "get"
//			 }
//		 });
//
//		 return (request.then(handleSuccess, handleError));
//	}
//
//	function addTransportAllocationAdmissionDetail(transportAllocationAdmissionDtl){
//		console.log('addTransportlAllocationAdmissionDetail called in service');
//		 var request = $http({
//			 method : "post",
//			 url : "service/transport/transportAllocationAdmission/",
//			 params : "",
//			 data: transportAllocationAdmissionDtl
//
//		 });
//		 
//		 return (request.then(handleSuccess, handleError));
//		
//	}
//
//	function updateTransportAllocationAdmissionDetail(transportAllocationAdmissionDtl) {
//
//		console.log('updateTransportlAllocationAdmissionDetail reservation called in service');
//		var request = $http({
//			method : "put",
//			url : "service/transport/transportAllocationAdmission",
//			params : "",
//			data : transportAllocationAdmissionDtl
//
//		});
//
//		return (request.then(handleSuccess, handleError));
//	}
//	
//	function getTransportAllocationDtlForStudent(fileNo){
//		console.log('getTransportAllocationDtlForStudent called in service');
//		var request = $http({
//			method : "get",
//			url : "service/transport/studentTransportAllocationDtl/"+fileNo,
//			params : {
//				action : "get"
//			}
//		});
//
//		return (request.then(handleSuccess, handleError));
//
//	}

//	function getTransportAllocationDtlForVehicle(vehicleId){
//		console.log('getTransportAllocationDtlForVehicle called in service');
//		var request = $http({
//			method : "get",
//			url : "service/transport/transportAllocationDetailForVehicle/"+vehicleId,
//			params : {
//				action : "get"
//			}
//		});
//
//		return (request.then(handleSuccess, handleError));
//
//	}

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