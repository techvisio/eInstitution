var hostelModule = angular.module('hostelModule', []);

hostelModule.controller('hostelController', ['$scope','hostelService','masterdataService',function($scope,hostelService,masterdataService) {

	$scope.form={};
	$scope.form.content='dashboard';
	$scope.hostelAvailability={};
	$scope.currentReservation={};
	$scope.hostelReservation={};
	$scope.isNew=false;
	$scope.hostelAllocationAdmissionDtl={};
	$scope.getHostelAvailability = function() {

		$scope.init=function(){

			console.log('getting masterdata for admission module in init block');

			masterdataService.getAdmissionMasterData()
			.then(function(data) {
				console.log(data);
				if (data != null) {
					$scope.serverModelData = data;
				} else {
					console.log('error');
				}
			})

		}


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
		}};

		$scope.getHostelAllocationAdmissionDetail = function(){

			console.log('getHostelAllocationAdmissionDetail called in controller');
			var fileNo = prompt("enter file no" + " ") 
			hostelService.getHostelAllocationAdmissionDetail(fileNo)
			.then(function(response) {
				console.log('hostelAllocationAdmissionDtl Data received from service : ');
				console.log(response);
				if (response != null && response.data != null && response.data.responseBody != null) {
					$scope.hostelAllocationAdmissionDtl = response.data.responseBody;

				} else {
					console.log(response.data.error);
					alert(response.data.error);
				}
			})
		};

		$scope.addHostelAllocationAdmissionDetail = function(){
			console.log('addHostelAllocationAdmissionDetail called in controller');
			hostelService.addHostelAllocationAdmissionDetail($scope.hostelAllocationAdmissionDtl)
			.then(function(response) {
				console.log('addHostelAllocationAdmissionDetail Data received from service : ');
				console.log(response);
				if (response != null && response.data != null && response.data.responseBody != null) {
					$scope.hostelAllocationAdmissionDtl = response.data.responseBody;
					alert("Your Records Saved Successfully")
				} else {
					console.log(response.data.error);
					alert(response.data.error);
				}
			})
		};

		$scope.updateHostelAllocationAdmissionDetail = function(){
			console.log('updateHostelAllocationAdmissionDetail called in controller');
			hostelService.updateHostelAllocationAdmissionDetail($scope.hostelAllocationAdmissionDtl)
			.then(function(response) {
				console.log('updateHostelAllocationAdmissionDetail Data received from service : ');
				console.log(response);
				if (response != null && response.data != null && response.data.responseBody != null) {
					$scope.hostelAllocationAdmissionDtl = response.data.responseBody;
					alert("Your Records Saved Successfully")
				} else {
					console.log(response.data.error);
					alert(response.data.error);
				}
			})
		};

		$scope.getRoomAllocationDtlByRoomNo = function(){

			console.log('getRoomAllocationDtlByRoomNo called in controller');
			var roomNo = prompt("enter room no" + " ") 
			hostelService.getRoomAllocationDtlByRoomNo(roomNo)
			.then(function(response) {
				console.log(' getRoomAllocationDtlByRoomNo Data received from service in controller : ');
				console.log(response);
				if (response != null && response.data != null && response.data.responseBody != null) {
					$scope.RoomAllocationDetailForRoom = response.data.responseBody;

				} else {
					console.log(response.data.error);
					alert(response.data.error);
				}
			})
		};

		
		$scope.getRoomAllocationDtlForStudent = function(){

			console.log('getRoomAllocationDtlForStudent called in controller');
			var fileNo = prompt("enter file no" + " ") 
			hostelService.getRoomAllocationDtlForStudent(fileNo)
			.then(function(response) {
				console.log(' getRoomAllocationDtlForStudent Data received from service in controller : ');
				console.log(response);
				if (response != null && response.data != null && response.data.responseBody != null) {
					$scope.roomAllocationForStudent = response.data.responseBody;

				} else {
					console.log(response.data.error);
					alert(response.data.error);
				}
			})
		};

		

} ]);

hostelModule.service('hostelService', function($http, $q) {

	// Return public API.
	return ({
		getHostelAvailability : getHostelAvailability,
		reserveRoom : reserveRoom,
		getReservedHostel : getReservedHostel,
		cancelReservation : cancelReservation,
		updateReservation : updateReservation,
		getHostelAllocationAdmissionDetail : getHostelAllocationAdmissionDetail,
		addHostelAllocationAdmissionDetail : addHostelAllocationAdmissionDetail,
		updateHostelAllocationAdmissionDetail:updateHostelAllocationAdmissionDetail,
		getRoomAllocationDtlByRoomNo : getRoomAllocationDtlByRoomNo,
		getRoomAllocationDtlForStudent : getRoomAllocationDtlForStudent
		

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

	function updateHostelAllocationAdmissionDetail(hostelAllocationAdmissionDtl) {

		console.log('update hostel reservation called in service');
		var request = $http({
			method : "put",
			url : "hostel/hostelAllocationAdmission",
			params : "",
			data : hostelAllocationAdmissionDtl

		});

		return (request.then(handleSuccess, handleError));
	}


	function getRoomAllocationDtlForStudent(fileNo){
		console.log('get room allocation detail for student called from service')
		var request = $http({
			method : "get",
			url : "hostel/studenRoomAllocationDtl/"+fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));
	}

	function getRoomAllocationDtlByRoomNo(roomNo){
		console.log('get room allocation detail by room no called from service')
		var request = $http({
			method : "get",
			url : "hostel/RoomAllocationDtlForRoomNo/"+roomNo,
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