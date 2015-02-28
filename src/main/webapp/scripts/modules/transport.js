var transportModule = angular.module('transportModule', []);

transportModule.controller('transportController', ['$scope','transportService',function($scope,transportService) {

			$scope.availableTransport = {};

			$scope.transportReservation = {};
			
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
				
			})
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
					
				})
			}

                 
                 $scope.cancelReservation = function(){
     				
     				var fileNo=$scope.student.fileNo;

     				if(fileNo){
     					transportService.cancelReservation(fileNo)
     				.then(function(response){
                           
     					$scope.currentReservation = {};
     				})
                    }}

		} ]);

transportModule.service('transportService', function($http, $q) {

	// Return public API.
	return ({
		getAvailableTransport : getAvailableTransport,
		reserveTransport : reserveTransport,
		cancelReservation : cancelReservation,
		getReservedTransport : getReservedTransport,
		updateReservation : updateReservation
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