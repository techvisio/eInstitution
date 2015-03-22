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

		 if(angular.isUndefined($scope.feeTransactionAdmissionBean.basicInfo.fileNo) || $scope.feeTransactionAdmissionBean.basicInfo.fileNo==null )
		 {
			 alert("Please select student before depositing fee")
			 return;
		 }
		      
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

	 
	 
}]);


feeModule.service('feeService', function($http, $q){
	// Return public API.
	return ({
	    getFeeTransactionAndBasicInfoDetail : getFeeTransactionAndBasicInfoDetail,
	    depositeFee : depositeFee,
	    getPendingFee : getPendingFee
	});
	

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
