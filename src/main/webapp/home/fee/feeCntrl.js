var feeModule = angular.module('feeModule' , []);

feeModule.controller('feeController',['$scope','feeService','masterdataService','admissionService','$modal','$state',
                                      '$rootScope',
                                      'injectedData',function($scope,feeService,masterdataService,admissionService,$modal,$state,
                                     		 $rootScope,
                                    		 injectedData){

	$scope.searchResultList=[];
	$scope.filteredSearch=[];
	$scope.feeTransactionAdmissionBean = {};
	
	$scope.feeTransactionAdmissionBean.basicInfo={};
	
	if(injectedData.data){
		 $scope.feeTransactionAdmissionBean = injectedData.data.responseBody;
		 }
	$scope.currentFetchLimit=5;
	$scope.feeAdmissionBean=[];
	$scope.searchCriteria={};
	$scope.collapse=true;
	$scope.form={};
	$scope.showCriteria=true;
	$scope.form.content='dashboard';



	$scope.staging = {
			"discountHead":{

				"transactionType": "W"
			}
	};

	$scope.feeStaging = {
			"discountHead":{

				"transactionType": "A"
			}
	};

	$scope.newTransaction={};
	$scope.transactionTypes=[{"id":"9996","value":"CASH DEPOSITE"},
	                         {"id":"9995","value":"DEMAND DRAFT"},
	                         {"id":"9994","value":"CHEQUE DEPOSITE"}];

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
	
	
	$scope.updateAmenityCharges=function(temp){
		
		angular.forEach($scope.serverModelData.dropdownMasterData.AMENITIES, function(amenity) {
			if(amenity.feeDiscountHead.headId==temp.discountHead.headId){
				temp.amount=amenity.charges;
			}
		});
	}

	$scope.itemsPerPage = 3
	$scope.currentPage = 0;
	$scope.totalItems = 0;

	$scope.getMaxListLengthArray=function(){
		var size=0;
		if($scope.feeTransactionAdmissionBean && $scope.feeTransactionAdmissionBean.feeTransactionDebit && $scope.feeTransactionAdmissionBean.feeTransactionCredit){
			size=Math.max($scope.feeTransactionAdmissionBean.feeTransactionDebit.length,$scope.feeTransactionAdmissionBean.feeTransactionCredit.length);
		}
		if(size>0){
			return Array.apply(null, {length:size}).map(Number.call, Number);
		}
	};

	$scope.transactonType = function(type) {
		return function(staggingFee) {
			return staggingFee.discountHead.transactionType === type;
		}
	};

	$scope.totalBaseFee=function(){
		var sum=0;
		if($scope.admissionDetailManagement && $scope.admissionDetailManagement.applicableFeeDetails){
			angular.forEach($scope.admissionDetailManagement.applicableFeeDetails, function(transaction) {
				sum=sum+transaction.feeAmount;
			});
		}

		return sum;
	}

	$scope.addNewDiscount = function(){

		console.log("addNewDiscount called");
		var discount = angular.copy($scope.staging);
		$scope.admissionDetailManagement.stagingFee.push(discount);
	}

	$scope.totalCharges=function(){
		var sum=0;
		if($scope.admissionDetailManagement && $scope.admissionDetailManagement.stagingFee){
			angular.forEach($scope.admissionDetailManagement.stagingFee, function(object) {
				if(object.discountHead.transactionType!='W'){
					sum=sum+object.amount;
				}
			});
		}

		return sum;
	}

	$scope.totalDebit=function(){
		var sum=0;
		if($scope.feeTransactionAdmissionBean && $scope.feeTransactionAdmissionBean.feeTransactionDebit){
			angular.forEach($scope.feeTransactionAdmissionBean.feeTransactionDebit, function(transaction) {
				sum=sum+transaction.amount;
			});
		}
		if(sum>0){
			collapse=false;
		}
		return sum;
	}

	$scope.showBaseFee = function (size) {

		var modalInstance = $modal.open({
			templateUrl: 'baseFee.html',
			controller: 'feeController',
			scope:$scope,
			size: size,
		});
	};

	
	$scope.getSearchResult=function(){
		console.log('get student by search criteria in controller');
		console.log($scope.searchCriteria);
		$scope.currentPage = 0;
		admissionService.getStudentByCriteria($scope.searchCriteria)
		.then(function(response) {
			console.log('Data received from service in controller : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.searchResultList = response.data.responseBody;
				$scope.currentPage=1;
				$scope.showCriteria=false;
				$scope.totalItems = $scope.searchResultList.length;
			} else {
				console.log(response.data.error);
				alert(response.data.error);
			}
		})


	}

	$scope.totalCredit=function(){
		var sum=0;
		if($scope.feeTransactionAdmissionBean && $scope.feeTransactionAdmissionBean.feeTransactionCredit){
			angular.forEach($scope.feeTransactionAdmissionBean.feeTransactionCredit, function(transaction) {
				sum=sum+transaction.amount;
			});
		}
		return sum;
	}

	 $scope.directViewFeeDeposite = function(currentFileNo) {
		 $state.go('feeDeposite', {
			 fileNo : currentFileNo
		 });
	 }
	 
	$scope.getFeeTransactionAndBasicInfoDetail = function(fileNo) {
		console.log('FeeTransactionAndBasicInfoDetail called in controller');
		feeService.getFeeTransactionAndBasicInfoDetail(fileNo)
		.then(function(response) {
			console.log('Data received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.feeTransactionAdmissionBean = response.data.responseBody;
				$scope.directViewFeeDeposite(fileNo);
			}
		})
	}

	$scope.getPendingFee = function(){

		feeService.getPendingFee($scope.currentFetchLimit)
		.then(function(response) {
			console.log('pending fee data received in controller : ');
			console.log(response);
			if (response !=null && response.data != null && response.data.responseBody != null) {
				$scope.feeAdmissionBean = response.data.responseBody;

			} else {
				console.log(response.data.error);
				alert(response.data.error);
			}
		})

	}


	$scope.depositeFee = function() {

		$scope.newTransaction.fileNo=$scope.feeTransactionAdmissionBean.basicInfo.fileNo;
		console.log('fee deposite called in controller');

		feeService.depositeFee($scope.newTransaction)
		.then(function(response) {
			console.log('Data received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				alert("fee has been deposited successfully")
				$scope.newTransaction={};
				$scope.getFeeTransactionAndBasicInfoDetail($scope.feeTransactionAdmissionBean.basicInfo.fileNo);
			} else {
				console.log(response.data.error);
				alert(response.data.error);
			}
		})
	}

	
	$scope.getUnapprovedRecords=function(){
		feeService.getUnapprovedList($scope.currentFetchLimit)
		.then(function(response) {
			console.log('Data received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.unapprovedRecords = response.data.responseBody;
			} else {
				console.log(response.data.error);
			}
		})
	};

	$scope.getAdmissionDetailManagement=function(fileNo){
		feeService.getAdmissionDetailManagement(fileNo)
		.then(function(response) {
			console.log('Admission detail for management received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.admissionDetailManagement = response.data.responseBody;
				$scope.form.content='managementApproval';
			} else {
				console.log(response.data.error);
			}
		})
	};


	$scope.updateAdmissionDetailManagement=function(){
		feeService.updateAdmissionDetailManagement($scope.admissionDetailManagement)
		.then(function(response) {
			console.log('update Admission detail for management received from service in controller : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.updatedDetail = response.data.responseBody;
			} else {
				console.log(response.data.error);
			}
		})
	};

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
			rowTemplate: '<div ng-dblclick="directViewFeeDeposite(row.config.selectedItems[0].fileNo)" ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>',
			columnDefs: [{ field: "firstName", width: 90,displayName :"First Name"},
			             { field: "lastName", width: 90,displayName :"Last Name"},
			             { field: "fatherName", width: 150,displayName :"Father Name" },
			             { field: "course.course", width: 90,displayName :"Course" },
			             { field: "branch.branchName", width: 180,displayName :"Branch"}]
	};

	
}]);