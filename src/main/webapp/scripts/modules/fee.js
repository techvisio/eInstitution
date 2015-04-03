var feeModule = angular.module('feeModule' , []);

feeModule.controller('feeController',['$scope','feeService',function($scope,feeService){

	$scope.feeTransactionAdmissionBean = {};
	$scope.feeTransactionAdmissionBean.basicInfo={};
	$scope.fileNo=null;
	$scope.currentFetchLimit=5;
	$scope.feeAdmissionBean=[];
	$scope.collapse=true;

	$scope.newTransaction={};
	$scope.transactionTypes=[{"id":"9996","value":"CASH DEPOSITE"},
	                         {"id":"9995","value":"DEMAND DRAFT"},
	                         {"id":"9994","value":"CHEQUE DEPOSITE"}];

	$scope.getMaxListLengthArray=function(){
		var size=0;
		if($scope.feeTransactionAdmissionBean && $scope.feeTransactionAdmissionBean.feeTransactionDebit && $scope.feeTransactionAdmissionBean.feeTransactionCredit){
			size=Math.max($scope.feeTransactionAdmissionBean.feeTransactionDebit.length,$scope.feeTransactionAdmissionBean.feeTransactionCredit.length);
		}
		if(size>0){
			return Array.apply(null, {length:size}).map(Number.call, Number);
		}
	};

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

	$scope.totalCredit=function(){
		var sum=0;
		if($scope.feeTransactionAdmissionBean && $scope.feeTransactionAdmissionBean.feeTransactionCredit){
			angular.forEach($scope.feeTransactionAdmissionBean.feeTransactionCredit, function(transaction) {
				sum=sum+transaction.amount;
			});
		}
		return sum;
	}

	$scope.getFeeTransactionAndBasicInfoDetail = function(fileNo) {
		console.log('FeeTransactionAndBasicInfoDetail called in controller');
		if(!fileNo){
			fileNo=alert("Enter file number to search.");
			if(!fileNo){
				return;
			}
		}
		feeService.getFeeTransactionAndBasicInfoDetail(fileNo)
		.then(function(response) {
			console.log('Data received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.feeTransactionAdmissionBean = response.data.responseBody;
			}

			else {
				console.log(response.data.error);
				alert(response.data.error);
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
		$scope.newTransaction.semester=$scope.feeTransactionAdmissionBean.basicInfo.semester;
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
		managementService.getUnapprovedList($scope.currentFetchLimit)
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

	$scope.getFileNoandFetchDetails=function(fileNo){
		if(!fileNo){
			alter("Please enter file number.");
			return;
		}else{
			$scope.getAdmissionDetailManagement(fileNo);			
		}		
	}

	$scope.getAdmissionDetailManagement=function(fileNo){
		managementService.getAdmissionDetailManagement(fileNo)
		.then(function(response) {
			console.log('Admission detail for management received from service : ');
			console.log(response);
			if (response != null && response.data != null && response.data.responseBody != null) {
				$scope.admissionDetailManagement = response.data.responseBody;
			} else {
				console.log(response.data.error);
			}
		})
	};


	$scope.updateAdmissionDetailManagement=function(){
		managementService.updateAdmissionDetailManagement($scope.admissionDetailManagement)
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


}]);

feeModule.service('feeService', function($http, $q){

	// Return public API.
	return ({
		getUnapprovedList : getUnapprovedList,
		getAdmissionDetailManagement : getAdmissionDetailManagement,
		updateAdmissionDetailManagement : updateAdmissionDetailManagement,
		getFeeTransactionAndBasicInfoDetail : getFeeTransactionAndBasicInfoDetail,
		depositeFee : depositeFee,
		getPendingFee : getPendingFee
	});

	function getUnapprovedList(size) {

		console.log('Get unapproved list service');
		var request = $http({
			method : "get",
			url : "management/uapprovedList/"+size,
			params : ""

		});

		return (request.then(handleSuccess, handleError));

	}

	function getAdmissionDetailManagement(fileNo) {

		console.log('getting Admission detail for management in service');
		var request = $http({
			method : "get",
			url : "management/admission/approval/"+fileNo,
			params : ""

		});

		return (request.then(handleSuccess, handleError));

	}


	function updateAdmissionDetailManagement(student) {

		console.log('update Admission Detail for Management called in service');
		var request = $http({
			method : "put",
			url : "management/updateManagementChanges/",
			params : "",
			data : student

		});

		return (request.then(handleSuccess, handleError));

	}

	function getFeeTransactionAndBasicInfoDetail(fileNo) {

		console.log('fee Transaction called in service');
		var request = $http({
			method : "get",
			url : "fee/feeTransaction/" + fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function depositeFee(newTransaction) {

		console.log('fee deposite called in service');
		var request = $http({
			method : "post",
			url : "fee/feeTransactionCredit/",
			params : "",
			data : newTransaction

		});

		return (request.then(handleSuccess, handleError));

	}

	function getPendingFee(limit) {

		console.log('pending fee called in service');
		var request = $http({
			method : "get",
			url : "fee/pandingFee/" + limit,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}

	function handleError(response) {
		console.log('Error occured while calling service');
		console.log(response);
		if (!angular.isObject(response.data) || !response.data.message) {

			return ($q.reject("An unknown error occurred."));

		}

		return ($q.reject(response.data.message));

	}

	function handleSuccess(response) {
		console.log('handle success');
		console.log(response);
		return (response);

	}



});