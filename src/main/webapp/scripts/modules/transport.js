var transportModule = angular.module('transportModule', []);

transportModule.controller('transportController', ['$scope','transportService',function($scope,transportService) {

			$scope.availableTransport = {};

			$scope.transportReservation = {};
			
			$scope.getAvailableTransport = function() {

				transportService.getAvailableTransport().then(function(data) {
					console.log('Data received from service : ');
					console.log(data);
					if(data != null)
						{
						$scope.availableTransport=data;
						}
					else
						{
						console.log('Error getting transport inventory:'+data.error);
						alert('Error getting transport inventory:'+data.error);
						}
				})

			}

//			$scope.getReserveTransport = function(){
//				
//				transportService.getReservedTransport(fileNo)
//				 .then(function(response)
//				
//			}
			
                 $scope.reserveTransport = function(){
				
                	 transportService.reserveTransport($scope.transportReservation)
				     .then(function(data){
					console.log('Transport Reservation callback');
					console.log(data.responseBody);
					$scope.transportReservation=data.responseBody;
					
				})
			}
			

		} ]);

transportModule.service('transportService', function($http, $q) {

	// Return public API.
	return ({
		getAvailableTransport : getAvailableTransport,
		reserveTransport : reserveTransport,
		getReservedTransport : getReservedTransport
	});

	function getAvailableTransport() {

		var request = $http({
			method : "get",
			url : "transport/AvailableTransport",
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function getReservedTransport(fileNo) {

		var request = $http({
			method : "get",
			url : "transport/Reservation" + fileNo,
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
			 url : "transport/Reservation",
			 params : "",
			 data : transportReservation

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
		return (response.data);

	}

});