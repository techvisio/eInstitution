var hostelModule = angular.module('hostelModule', []);

hostelModule.controller('hostelController', ['$scope','hostelService',function($scope,hostelService) {

			$scope.hostelAvailability = {};
			
			$scope.hostelReservation={};
            
			$scope.getHostelAvailability = function() {

				hostelService.getHostelAvailability().then(function(response) {
					console.log('getHostelAvailability call back : ');
					console.log(response);
					if(response.data != null)
						{
						$scope.hostelAvailability=response.data;
						}
					else
						{
						console.log('Error getting hostel inventory:'+data.error);
						alert('Error getting hostel inventory:'+data.error);
						}
				})

			}
			
			
			$scope.getReservedHostel = function(){
				var fileNo=$scope.student.fileNo;
				console.log('Getting current hostel reservation for student : '+fileNo);
				if(fileNo){				
					
				hostelService.getReservedHostel(fileNo)
		              .then(function(response){
		              console.log('Getting reserved hosetl in controller : ');
			          console.log(response);
			 if (response !=null && response.data != null && response.data.responseBody != null) {
				 $scope.currentReservation = response.data.responseBody;
			 } else {
				 console.log(response.data.error);
				 alert(response.data.error);
			 }
		 })
			}
	
			}
			
			$scope.cancelReservation = function(){
				
				transportService.cancelReservation(fileNo)
				.then(function(response){
			
                  })
               }
			
			
			$scope.reserveRoom = function(){
				
				hostelService.reserveRoom($scope.hostelReservation)
				.then(function(data){
					console.log('Hostel Reservation callback');
					console.log(data.responseBody);
					$scope.hostelReservation=data.responseBody;
					
				})
			}
			

		} ]);

hostelModule.service('hostelService', function($http, $q) {

	// Return public API.
	return ({
		getHostelAvailability : getHostelAvailability,
		reserveRoom : reserveRoom,
		getReservedHostel : getReservedHostel
		
	});

	function getHostelAvailability() {

		console.log('getHostelAvailability called in service');
		var request = $http({
			method : "get",
			url : "Hostel/HostelAvailability",
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
			url : "Hostel/HostelReservation/" + fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function reserveRoom(hostelReservation) {

		 console.log('Hostel reservation called in service');
		 var request = $http({
			 method : "post",
			 url : "Hostel/HostelReservation",
			 params : "",
			 data : hostelReservation

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