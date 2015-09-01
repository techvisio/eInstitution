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
