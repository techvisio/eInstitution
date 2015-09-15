var hostelModule = angular.module('hostelModule', []);

hostelModule.controller('hostelController', ['$scope','hostelService','masterdataService','injectedData','$state',
                                             '$rootScope',function($scope,hostelService,masterdataService,injectedData,$state,$rootScope) {

	$scope.form={};
	$scope.form.content='dashboard';
	$scope.searchCriteria = {};
	$scope.searchResultList=[];
	$scope.filteredSearch=[];
	$scope.showCriteria=true;
	$scope.hostelAvailability = {};
	$scope.hostelReservation = {};
	$scope.roomAllocation = {};
	$scope.basicInfo={};
	if(injectedData.data){
		 $scope.basicInfo = injectedData.data.responseBody;
	 }

	$scope.currentHostelReservation={};

	
	$scope.isNew=false;

	$scope.init=function(){

		console.log('getting masterdata for hostel module in init block');

		masterdataService.getHostelMasterData()
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
			rowTemplate: '<div ng-dblclick="redirectToHostelReservationScreen(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
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
			rowTemplate: '<div ng-dblclick="redirectToHostelAllocationScreen(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
			columnDefs: [{ field: "firstName", width: 100,displayName :"FirstName"},
			             { field: "lastName", width: 100,displayName :"LastName"},
			             { field: "fatherName", width: 180,displayName :"Father Name" },
			             { field: "course.course", width: 140,displayName :"Course" },
			             { field: "branch.branchName", width: 180,displayName :"Branch" },
			             {field:"applicationStatus",width:200,displayName :"Status"}]
	};

	$scope.redirectToHostelReservationScreen=function(currentFileNo){
		$state.go('reserveHostel',{fileNo:currentFileNo});
	}
	
	$scope.redirectToHostelAllocationScreen=function(currentFileNo){
		$state.go('AllocateHostel',{fileNo:currentFileNo});
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

	$scope.getHostelAvailability=function(){

		hostelService.getHostelAvailability().then(function(response) {
			console.log('getHostelAvailability call back : ');
			console.log(response);
			if(response.data != null)
			{
				$scope.hostelAvailability=response.data;
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
	
	$scope.getReservedHostel = function(){
		var fileNo=$scope.basicInfo.fileNo;
		console.log('Getting current hostel reservation for student : '+fileNo);
		if(fileNo){				

			hostelService.getReservedHostel(fileNo)
			.then(function(response){
				console.log('Getting reserved hosetl in controller : ');
				console.log(response);
				if (response !=null && response.data != null && response.data.responseBody != null) {
					$scope.hostelReservation = response.data.responseBody;
				} 					 })
		}
	};

	$scope.reserveRoom = function(){

		var fileNo=$scope.basicInfo.fileNo;
		$scope.hostelReservation.fileNo=fileNo;

		hostelService.reserveRoom($scope.hostelReservation, fileNo)
		.then(function(response){
			console.log('Hostel Reservation callback');
			console.log(response.data.responseBody);
			$scope.hostelReservation=response.data.responseBody;
		})
	};

	$scope.cancelReservation = function(){

		var fileNo=$scope.basicInfo.fileNo;

		if(fileNo){
			hostelService.cancelReservation(fileNo)
			.then(function(response){

				$scope.hostelReservation = {};
			})
		}};

		$scope.getAllocatedRoom = function(){
			var fileNo=$scope.basicInfo.fileNo;
			console.log('Getting current allocated room for student : '+fileNo);
			if(fileNo){				

				hostelService.getAllocatedRoom(fileNo)
				.then(function(response){
					console.log('Getting allocated room in controller : ');
					console.log(response);
					if (response !=null && response.data != null && response.data.responseBody != null) {
						$scope.roomAllocation = response.data.responseBody;
						$scope.getReservedHostel();
					} 					 })
			}
		};

		$scope.AllocateRoom = function(){

			var fileNo=$scope.basicInfo.fileNo;
			$scope.roomAllocation.fileNo=fileNo;

			hostelService.AllocateRoom($scope.roomAllocation, fileNo)
			.then(function(response){
				console.log('Hostel allocation callback');
				console.log(response.data.responseBody);
				$scope.roomAllocation=response.data.responseBody;
			})
		};

		$scope.cancelAllocation = function(){

			var fileNo=$scope.basicInfo.fileNo;

			if(fileNo){
				hostelService.cancelAllocation(fileNo)
				.then(function(response){

					$scope.roomAllocation = {};
				})
			}};

		
		$scope.getStudentByCriteria = function() {
			console.log('get student by search criteria in controller');
			console.log($scope.searchCriteria);
			$scope.currentPage=0;
			hostelService.getStudentByCriteria($scope.searchCriteria)
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
