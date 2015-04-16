var hostelModule = angular.module('hostelModule', []);

hostelModule.controller('hostelController', ['$scope','hostelService',function($scope,hostelService) {

	$scope.hostelAvailability={};
	$scope.currentReservation={};
	$scope.hostelReservation={};
	$scope.isNew=false;
	
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

	$scope.syncReservationStatus = function(){

		if($scope.currentReservation && $scope.currentReservation.fileNo){

			$scope.student.hostel=true;
		}
		else{

			$scope.student.hostel=false;
		}
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
				}
				
			})
		}

	}

	$scope.saveHostel = function(){
		
		if($scope.currentReservation.typeCode){
			 $scope.updateReservation();
		 }
		 else
		 {
			 $scope.reserveRoom();
		 }
	}

	$scope.reserveRoom = function(){

		var fileNo=$scope.student.fileNo;
		$scope.hostelReservation.fileNo=fileNo;

		hostelService.reserveRoom($scope.hostelReservation)
		.then(function(response){
			console.log('Hostel Reservation callback');
			console.log(response.data.responseBody);
			$scope.currentReservation=response.data.responseBody;
			$scope.isNew=true;
			$scope.syncReservationStatus();
		})
	}


	$scope.updateReservation = function(){

		var fileNo=$scope.student.fileNo;

		$scope.hostelReservation.fileNo=fileNo;
		if($scope.hostelReservation.typeCode != null)
		{            	 

			hostelService.updateReservation($scope.hostelReservation)
			.then(function(response){
				console.log('update hostel Reservation callback');
				console.log(response.data.responseBody);
				$scope.currentReservation=response.data.responseBody;
				$scope.isNew=false;
				$scope.syncReservationStatus();
			})
		}
	}


	$scope.cancelReservation = function(){

		var fileNo=$scope.student.fileNo;

		if(fileNo){
			hostelService.cancelReservation(fileNo)
			.then(function(response){

				$scope.currentReservation = {};
				$scope.syncReservationStatus();
			})
		}}


} ]);

hostelModule.service('hostelService', function($http, $q) {

	// Return public API.
	return ({
		getHostelAvailability : getHostelAvailability,
		reserveRoom : reserveRoom,
		getReservedHostel : getReservedHostel,
		cancelReservation : cancelReservation,
		updateReservation : updateReservation
	});

	function getHostelAvailability() {

		console.log('getHostelAvailability called in service');
		var request = $http({
			method : "get",
			url : "hostel/hostelAvailability",
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
			url : "hostel/hostelReservation/" + fileNo,
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
			url : "hostel/hostelReservation",
			params : "",
			data : hostelReservation

		});

		return (request.then(handleSuccess, handleError));

	}

	function updateReservation(hostelReservation) {

		console.log('update hostel reservation called in service');
		var request = $http({
			method : "put",
			url : "hostel/hostelReservation",
			params : "",
			data : hostelReservation

		});

		return (request.then(handleSuccess, handleError));

	}


	function cancelReservation(fileNo) {
		var request = $http({
			method : "delete",
			url : "hostel/hostelReservation/" + fileNo,
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