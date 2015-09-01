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

//	$scope.saveTransport=function(){
//
//		if($scope.currentReservation.routeCode)
//		{
//			$scope.updateReservation();
//		}
//		else{
//			$scope.reserveTransport();
//		}
//	}

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
		
//		$scope.getTransportlAllocationAdmissionDetail = function(){
//			
//			 console.log('getTransportlAllocationAdmissionDetail called in controller');
//			 var fileNo = prompt("enter file no" + " ")
//			 transportService.getTransportlAllocationAdmissionDetail(fileNo)
//			 .then(function(response) {
//				 console.log('getTransportlAllocationAdmissionDetail Data received from service : ');
//				 console.log(response);
//				 if (response != null && response.data != null && response.data.responseBody != null) {
//					 $scope.transportAllocationAdmissionDtl = response.data.responseBody;
//					 
//				 } else {
//					 console.log(response.data.error);
//					 alert(response.data.error);
//				 }
//			 })
//		 };

//		 $scope.addTransportAllocationAdmissionDetail = function(){
//			 console.log('addTransportAllocationAdmissionDetail called in controller');
//			 transportService.addTransportAllocationAdmissionDetail($scope.transportAllocationAdmissionDtl)
//			 .then(function(response) {
//				 console.log('addTransportAllocationAdmissionDetail Data received from service : ');
//				 console.log(response);
//				 if (response != null && response.data != null && response.data.responseBody != null) {
//					 $scope.transportAllocationAdmissionDtl = response.data.responseBody;
//					 alert("Your Records Saved Successfully")
//				 } else {
//					 console.log(response.data.error);
//					 alert(response.data.error);
//				 }
//			 })
//		 };
//		
//		 $scope.updateTransportAllocationAdmissionDetail = function(){
//			 console.log('updateTransportAllocationAdmissionDetail called in controller');
//			 transportService.updateTransportAllocationAdmissionDetail($scope.transportAllocationAdmissionDtl)
//			 .then(function(response) {
//				 console.log('updateTransportAllocationAdmissionDetail Data received from service : ');
//				 console.log(response);
//				 if (response != null && response.data != null && response.data.responseBody != null) {
//					 $scope.transportAllocationAdmissionDtl = response.data.responseBody;
//					 alert("Your Records Saved Successfully")
//				 } else {
//					 console.log(response.data.error);
//					 alert(response.data.error);
//				 }
//			 })
//		 };
//
//
//		 
//		 $scope.getTransportAllocationDtlForStudent = function(){
//
//				console.log('getTransportAllocationDtlForStudent called in controller');
//				var fileNo = prompt("enter file no" + " ") 
//				transportService.getTransportAllocationDtlForStudent(fileNo)
//				.then(function(response) {
//					console.log(' getTransportAllocationDtlForStudent Data received from service in controller : ');
//					console.log(response);
//					if (response != null && response.data != null && response.data.responseBody != null) {
//						$scope.TransportAllocationForStudent = response.data.responseBody;
//
//					} else {
//						console.log(response.data.error);
//						alert(response.data.error);
//					}
//				})
//			};


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
