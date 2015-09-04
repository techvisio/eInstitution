var transportModule = angular.module('transportModule', []);

transportModule.controller('transportController', ['$scope','transportService','injectedData','masterdataService',function($scope,transportService,injectedData,masterdataService) {

	$scope.transportAllocationAdmissionDtl={};
	$scope.availableTransport = {};
	$scope.transportReservation = {};
	$scope.currentTransportReservation={};
	$scope.form={};
	$scope.form.content='dashboard';
	$scope.serverModelData={};

	$scope.init=function(){

		console.log('getting masterdata for transport module in init block');

		masterdataService.getTransportMasterData()
		.then(
				function(data) {
					console.log(data);
					if (data) {
						$scope.serverModelData = data.responseBody;
					} else {
						console.log('error');
					}
				})
	}

	$scope.getAvailableTransport = function() {

		transportService.getAvailableTransport().then(function(response) {
			console.log('Available Transport Data received from service : ');
			console.log(response);
			if(response.data != null)
			{
				$scope.availableTransport=response.data;
			}
		})

	};

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
					$scope.currentTransportReservation = response.data.responseBody;
				}
			})
		}
	};


	$scope.reserveTransport = function(){

		var fileNo=$scope.student.fileNo;

		$scope.transportReservation.fileNo=fileNo;

		transportService.reserveTransport($scope.transportReservation, fileNo)
		.then(function(response){
			console.log('Transport Reservation callback');
			console.log(response.data.responseBody);
			$scope.currentTransportReservation=response.data;
		})
	};

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

//		console.log('getTransportlAllocationAdmissionDetail called in controller');
//		var fileNo = prompt("enter file no" + " ")
//		transportService.getTransportlAllocationAdmissionDetail(fileNo)
//		.then(function(response) {
//		console.log('getTransportlAllocationAdmissionDetail Data received from service : ');
//		console.log(response);
//		if (response != null && response.data != null && response.data.responseBody != null) {
//		$scope.transportAllocationAdmissionDtl = response.data.responseBody;

//		} else {
//		console.log(response.data.error);
//		alert(response.data.error);
//		}
//		})
//		};

//		$scope.addTransportAllocationAdmissionDetail = function(){
//		console.log('addTransportAllocationAdmissionDetail called in controller');
//		transportService.addTransportAllocationAdmissionDetail($scope.transportAllocationAdmissionDtl)
//		.then(function(response) {
//		console.log('addTransportAllocationAdmissionDetail Data received from service : ');
//		console.log(response);
//		if (response != null && response.data != null && response.data.responseBody != null) {
//		$scope.transportAllocationAdmissionDtl = response.data.responseBody;
//		alert("Your Records Saved Successfully")
//		} else {
//		console.log(response.data.error);
//		alert(response.data.error);
//		}
//		})
//		};

//		$scope.updateTransportAllocationAdmissionDetail = function(){
//		console.log('updateTransportAllocationAdmissionDetail called in controller');
//		transportService.updateTransportAllocationAdmissionDetail($scope.transportAllocationAdmissionDtl)
//		.then(function(response) {
//		console.log('updateTransportAllocationAdmissionDetail Data received from service : ');
//		console.log(response);
//		if (response != null && response.data != null && response.data.responseBody != null) {
//		$scope.transportAllocationAdmissionDtl = response.data.responseBody;
//		alert("Your Records Saved Successfully")
//		} else {
//		console.log(response.data.error);
//		alert(response.data.error);
//		}
//		})
//		};



//		$scope.getTransportAllocationDtlForStudent = function(){

//		console.log('getTransportAllocationDtlForStudent called in controller');
//		var fileNo = prompt("enter file no" + " ") 
//		transportService.getTransportAllocationDtlForStudent(fileNo)
//		.then(function(response) {
//		console.log(' getTransportAllocationDtlForStudent Data received from service in controller : ');
//		console.log(response);
//		if (response != null && response.data != null && response.data.responseBody != null) {
//		$scope.TransportAllocationForStudent = response.data.responseBody;

//		} else {
//		console.log(response.data.error);
//		alert(response.data.error);
//		}
//		})
//		};


//		$scope.getTransportAllocationDtlForVehicle = function(){

//		console.log('getTransportAllocationDtlForVehicle called in controller');
//		var vehicleId = prompt("enter vehicle id" + " ") 
//		transportService.getTransportAllocationDtlForVehicle(vehicleId)
//		.then(function(response) {
//		console.log(' getTransportAllocationDtlForVehicle Data received from service in controller : ');
//		console.log(response);
//		if (response != null && response.data != null && response.data.responseBody != null) {
//		$scope.TransportAllocationDtlForVehicle = response.data.responseBody;

//		} else {
//		console.log(response.data.error);
//		alert(response.data.error);
//		}
//		})
//		};


} ]);
