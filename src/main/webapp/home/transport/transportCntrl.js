var transportModule = angular.module('transportModule', []);

transportModule.controller('transportController', ['$scope','transportService','injectedData','masterdataService','$state',
                                                   '$rootScope',function($scope,transportService,injectedData,masterdataService,$state,$rootScope) {


	$scope.availableTransport = {};
	$scope.transportReservation = {};
	$scope.currentTransportReservation={};
	$scope.form={};
	$scope.form.content='dashboard';
	$scope.serverModelData={};
	$scope.searchCriteria = {};
	$scope.searchResultList=[];
	$scope.filteredSearch=[];
	$scope.showCriteria=true;
	$scope.transportAllocation = {};
	$scope.basicInfo={};
	if(injectedData.data){
		$scope.basicInfo = injectedData.data.responseBody;
	}

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

	$scope.itemsPerPage = 3;
	$scope.currentPage = 0;
	$scope.totalItems = 0;

	$scope.pageCount = function () {
		return Math.ceil($scope.searchResultList.length / $scope.itemsPerPage);
	};

	$scope.numPages = function () {
		return Math.ceil($scope.searchResultList.length / $scope.numPerPage);
	};

	$scope.$watch('currentPage + numPerPage', function() {
		var begin = (($scope.currentPage - 1) * $scope.itemsPerPage)
		, end = begin + $scope.itemsPerPage;

		$scope.filteredSearch = $scope.searchResultList.slice(begin, end);
	});

	$scope.gridOptions = {
			multiSelect:false,
			data: 'filteredSearch',
			rowTemplate: '<div ng-dblclick="redirectToTransportReservationScreen(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
			columnDefs: [{ field: "firstName", width: 100,displayName :"FirstName"},
			             { field: "lastName", width: 100,displayName :"LastName"},
			             { field: "fatherName", width: 180,displayName :"Father Name" },
			             { field: "course.course", width: 140,displayName :"Course" },
			             { field: "branch.branchName", width: 180,displayName :"Branch" },
			             {field:"applicationStatus",width:200,displayName :"Status"}]
	};


	$scope.AllocationGridOptions = {
			multiSelect:false,
			data: 'filteredSearch',
			rowTemplate: '<div ng-dblclick="redirectTotransportAllocationScreen(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
			columnDefs: [{ field: "firstName", width: 100,displayName :"FirstName"},
			             { field: "lastName", width: 100,displayName :"LastName"},
			             { field: "fatherName", width: 180,displayName :"Father Name" },
			             { field: "course.course", width: 140,displayName :"Course" },
			             { field: "branch.branchName", width: 180,displayName :"Branch" },
			             {field:"applicationStatus",width:200,displayName :"Status"}]
	};

	$scope.redirectToTransportReservationScreen=function(currentFileNo){
		$state.go('reserveTransport',{fileNo:currentFileNo});
	}

	$scope.redirectTotransportAllocationScreen=function(currentFileNo){
		$state.go('AllocateTransport',{fileNo:currentFileNo});
	}

	$scope.getStudentBasicInfo = function(){
		var fileNo=$scope.searchResultList[0].fileNo;
		if(fileNo){				

			hostelService.getStudentBasicInfo(fileNo)
			.then(function(response){
				console.log('Getting student basic in controller : ');
				console.log(response);
				if (response !=null && response.data != null && response.data.responseBody != null) {
					$scope.studentBasicInfo = response.data.responseBody;
				} 					 })
		}
	};


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

	$scope.getAvailableRooms=function(){

		hostelService.getAvailableRooms().then(function(response) {
			console.log('available rooms call back : ');
			console.log(response);
			if(response !=null && response.data != null && response.data.responseBody != null)
			{
				$scope.availableRooms=response.data.responseBody;
			}
		})
	};
	
	$scope.getAvailableVehicles=function(){

		transportService.getAvailableVehicles().then(function(response) {
			console.log('available vehicles call back : ');
			console.log(response);
			if(response !=null && response.data != null && response.data.responseBody != null)
			{
				$scope.availableVehicles=response.data.responseBody;
			}
		})
	};

	
	$scope.getReservedTransport = function(){
		var fileNo=$scope.basicInfo.fileNo;
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
					$scope.transportReservation = response.data.responseBody;
				}
			})
		}
	};


	$scope.reserveTransport = function(){

		var fileNo=$scope.basicInfo.fileNo;

		$scope.transportReservation.fileNo=fileNo;

		transportService.reserveTransport($scope.transportReservation, fileNo)
		.then(function(response){
			console.log('Transport Reservation callback');
			console.log(response.data.responseBody);
			if (response !=null && response.data != null && response.data.responseBody != null) {
				$scope.transportReservation = response.data.responseBody;
			}
		})
	};

	$scope.cancelReservation = function(){

		var fileNo=$scope.basicInfo.fileNo;

		if(fileNo){
			transportService.cancelReservation(fileNo)
			.then(function(response){

				$scope.transportReservation = {};
			})
		}};

		$scope.getAllocatedTransport = function(){
			var fileNo=$scope.basicInfo.fileNo;
			console.log('Getting current transport allocation for student : '+fileNo);
			if(fileNo){				

				transportService.getAllocatedTransport(fileNo)
				.then(function(response){
					console.log('Getting allocated transport in controller : ');
					console.log(response);
					if (response !=null && response.data != null && response.data.responseBody != null) {
						$scope.transportAllocation = response.data.responseBody;
						$scope.getReservedTransport();
					} 					 
					})
			}
		};

		$scope.AllocateTransport = function(){

			var fileNo=$scope.basicInfo.fileNo;
			$scope.transportAllocation.fileNo=fileNo;

			transportService.AllocateTransport($scope.transportAllocation, fileNo)
			.then(function(response){
				console.log('transport allocation callback');
				console.log(response.data.responseBody);
				if (response !=null && response.data != null && response.data.responseBody != null) {
					$scope.transportAllocation = response.data.responseBody;
					}
			})
		};

		$scope.cancelAllocation = function(){

			var fileNo=$scope.basicInfo.fileNo;

			if(fileNo){
				transportService.cancelAllocation(fileNo)
				.then(function(response){

					$scope.transportAllocation = {};
				})
			}};


			$scope.getStudentByCriteria = function() {
				console.log('get student by search criteria in controller');
				console.log($scope.searchCriteria);
				$scope.currentPage=0;
				transportService.getStudentByCriteria($scope.searchCriteria)
				.then(function(response) {
					console.log('get student Data received from service in controller : ');
					console.log(response);
					if (response != null && response.data != null && response.data.responseBody != null) {
						$scope.searchResultList=response.data.responseBody;
						$scope.showCriteria=false;
						$scope.currentPage=1;
						$scope.totalItems = $scope.searchResultList.length;
					} 
				})
			}

} ]);
