var transportModule = angular.module('transportModule', []);

transportModule.controller('transportController', ['$scope','transportService',function($scope,transportService) {

	$scope.transportAllocationAdmissionDtl={};
	$scope.availableTransport = {};
	$scope.transportReservation = {};
	$scope.currentReservation={};
	$scope.form={};
	$scope.form.content='dashboard';
	
	$scope.getAvailableTransport = function() {

		transportService.getAvailableTransport().then(function(response) {
			console.log('Data received from service : ');
			console.log(response);
			if(response.data != null)
			{
				$scope.availableTransport=response.data;
			}
			else
			{
				console.log('Error getting transport inventory:'+data.error);
				alert('Error getting transport inventory:'+data.error);
			}
		})

	}

	$scope.getReservedTransport = function(){
		var fileNo=$scope.student.fileNo;
		if(!fileNo){
			return;
		}
		console.log('Getting current transport reservation for student : '+fileNo);
		if(fileNo){				
			transportService.getReservedTransport(fileNo)
			.then(function(response){
				console.log('Data received from getReservedTransport controller : ');
				console.log(response);
				if (response !=null && response.data != null && response.data.responseBody != null) {
					$scope.currentReservation = response.data.responseBody;
				} else {
					console.log(response.data.error);
				}
			})
		}
	}

	$scope.saveTransport=function(){

		if($scope.currentReservation.routeCode)
		{
			$scope.updateReservation();
		}
		else{
			$scope.reserveTransport();
		}
	}
	$scope.updateReservation = function(){

		var fileNo=$scope.student.fileNo;

		$scope.transportReservation.fileNo=fileNo;
		if($scope.transportReservation.routeCode != null)
		{            	 

			transportService.updateReservation($scope.transportReservation)
			.then(function(response){
				console.log('update Transport Reservation callback');
				console.log(response.data.responseBody);
				$scope.currentReservation=response.data.responseBody;
				$scope.syncReservationStatus();
			})
		}
	}

	$scope.syncReservationStatus = function(){

		if($scope.currentReservation && $scope.currentReservation.fileNo){

			$scope.student.transportation=true;
		}
		else{

			$scope.student.transportation=false;
		}

	}


	$scope.reserveTransport = function(){

		var fileNo=$scope.student.fileNo;

		$scope.transportReservation.fileNo=fileNo;

		transportService.reserveTransport($scope.transportReservation)
		.then(function(response){
			console.log('Transport Reservation callback');
			console.log(response.data.responseBody);

			$scope.currentReservation=response.data.responseBody;
			$scope.syncReservationStatus();
		})
	}


	$scope.cancelReservation = function(){

		var fileNo=$scope.student.fileNo;

		if(fileNo){
			transportService.cancelReservation(fileNo)
			.then(function(response){

				$scope.currentReservation = {};
				$scope.syncReservationStatus();
			})
		}};
		
		$scope.getTransportlAllocationAdmissionDetail = function(){
			
			 console.log('getTransportlAllocationAdmissionDetail called in controller');
			 var fileNo = prompt("enter file no" + " ")
			 transportService.getTransportlAllocationAdmissionDetail(fileNo)
			 .then(function(response) {
				 console.log('getTransportlAllocationAdmissionDetail Data received from service : ');
				 console.log(response);
				 if (response != null && response.data != null && response.data.responseBody != null) {
					 $scope.transportAllocationAdmissionDtl = response.data.responseBody;
					 
				 } else {
					 console.log(response.data.error);
					 alert(response.data.error);
				 }
			 })
		 };

		 $scope.addTransportAllocationAdmissionDetail = function(){
			 console.log('addTransportAllocationAdmissionDetail called in controller');
			 transportService.addTransportAllocationAdmissionDetail($scope.transportAllocationAdmissionDtl)
			 .then(function(response) {
				 console.log('addTransportAllocationAdmissionDetail Data received from service : ');
				 console.log(response);
				 if (response != null && response.data != null && response.data.responseBody != null) {
					 $scope.transportAllocationAdmissionDtl = response.data.responseBody;
					 alert("Your Records Saved Successfully")
				 } else {
					 console.log(response.data.error);
					 alert(response.data.error);
				 }
			 })
		 };
		
		 $scope.updateTransportAllocationAdmissionDetail = function(){
			 console.log('updateTransportAllocationAdmissionDetail called in controller');
			 transportService.updateTransportAllocationAdmissionDetail($scope.transportAllocationAdmissionDtl)
			 .then(function(response) {
				 console.log('updateTransportAllocationAdmissionDetail Data received from service : ');
				 console.log(response);
				 if (response != null && response.data != null && response.data.responseBody != null) {
					 $scope.transportAllocationAdmissionDtl = response.data.responseBody;
					 alert("Your Records Saved Successfully")
				 } else {
					 console.log(response.data.error);
					 alert(response.data.error);
				 }
			 })
		 };


		 
		 $scope.getTransportAllocationDtlForStudent = function(){

				console.log('getTransportAllocationDtlForStudent called in controller');
				var fileNo = prompt("enter file no" + " ") 
				transportService.getTransportAllocationDtlForStudent(fileNo)
				.then(function(response) {
					console.log(' getTransportAllocationDtlForStudent Data received from service in controller : ');
					console.log(response);
					if (response != null && response.data != null && response.data.responseBody != null) {
						$scope.TransportAllocationForStudent = response.data.responseBody;

					} else {
						console.log(response.data.error);
						alert(response.data.error);
					}
				})
			};


//			 $scope.getTransportAllocationDtlForVehicle = function(){
//
//					console.log('getTransportAllocationDtlForVehicle called in controller');
//					var vehicleId = prompt("enter vehicle id" + " ") 
//					transportService.getTransportAllocationDtlForVehicle(vehicleId)
//					.then(function(response) {
//						console.log(' getTransportAllocationDtlForVehicle Data received from service in controller : ');
//						console.log(response);
//						if (response != null && response.data != null && response.data.responseBody != null) {
//							$scope.TransportAllocationDtlForVehicle = response.data.responseBody;
//
//						} else {
//							console.log(response.data.error);
//							alert(response.data.error);
//						}
//					})
//				};

		 
} ]);

transportModule.service('transportService', function($http, $q) {

	// Return public API.
	return ({
		getAvailableTransport : getAvailableTransport,
		reserveTransport : reserveTransport,
		cancelReservation : cancelReservation,
		getReservedTransport : getReservedTransport,
		updateReservation : updateReservation,
		getTransportlAllocationAdmissionDetail : getTransportlAllocationAdmissionDetail,
		addTransportAllocationAdmissionDetail : addTransportAllocationAdmissionDetail,
		updateTransportAllocationAdmissionDetail : updateTransportAllocationAdmissionDetail,
		getTransportAllocationDtlForStudent : getTransportAllocationDtlForStudent,
//		getTransportAllocationDtlForVehicle : getTransportAllocationDtlForVehicle
	});

	function getAvailableTransport() {

		console.log('getAvailableTransport called in service');
		var request = $http({
			method : "get",
			url : "transport/availableTransport",
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
			url : "transport/reservation/"+fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}


	function reserveTransport(transportReservation) {

		console.log('Transport reservation called in service');
		var request = $http({
			method : "post",
			url : "transport/reservation",
			params : "",
			data : transportReservation

		});

		return (request.then(handleSuccess, handleError));

	}

	function updateReservation(transportReservation) {

		console.log('update transport reservation called in service');
		var request = $http({
			method : "put",
			url : "transport/reservation",
			params : "",
			data : transportReservation

		});

		return (request.then(handleSuccess, handleError));

	}


	function cancelReservation(fileNo) {
		var request = $http({
			method : "delete",
			url : "transport/reservation/" + fileNo,
			params : {
				action : "delete"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function getTransportlAllocationAdmissionDetail(fileNo){
		console.log('getTransportlAllocationAdmissionDetail called in service')
		var request = $http({
			 method : "get",
			 url : "transport/transportAllocationAdmission/"+fileNo,
			 params : {
				 action : "get"
			 }
		 });

		 return (request.then(handleSuccess, handleError));
	}

	function addTransportAllocationAdmissionDetail(transportAllocationAdmissionDtl){
		console.log('addTransportlAllocationAdmissionDetail called in service');
		 var request = $http({
			 method : "post",
			 url : "transport/transportAllocationAdmission/",
			 params : "",
			 data: transportAllocationAdmissionDtl

		 });
		 
		 return (request.then(handleSuccess, handleError));
		
	}

	function updateTransportAllocationAdmissionDetail(transportAllocationAdmissionDtl) {

		console.log('updateTransportlAllocationAdmissionDetail reservation called in service');
		var request = $http({
			method : "put",
			url : "transport/transportAllocationAdmission",
			params : "",
			data : transportAllocationAdmissionDtl

		});

		return (request.then(handleSuccess, handleError));
	}
	
	function getTransportAllocationDtlForStudent(fileNo){
		console.log('getTransportAllocationDtlForStudent called in service');
		var request = $http({
			method : "get",
			url : "transport/studentTransportAllocationDtl/"+fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

//	function getTransportAllocationDtlForVehicle(vehicleId){
//		console.log('getTransportAllocationDtlForVehicle called in service');
//		var request = $http({
//			method : "get",
//			url : "transport/transportAllocationDetailForVehicle/"+vehicleId,
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