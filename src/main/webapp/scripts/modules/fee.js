var feeModule = angular.module('feeModule' , []);

feeModule.controller('feeController',['$scope','feeService',function($scope,feeService){
	
	$scope.studentBasicInfo = {};
	$scope.feeTransaction = {};
	
	$scope.getStudentBsInfo = function(){
		
			var fileNo=prompt("file No","");
			if(fileNo){
				feeService.getStudentBsInfo(fileNo)
								.then(function(response){
									 console.log('Getting Student Basic Information in controller : ');
							          console.log(response);
					if (response !=null && response.data != null && response.data.responseBody != null){
						$scope.studentBasicInfo = response.data.responseBody;
						$scope.getPendingFee();
					}
					else {
						 console.log(response.data.error);
						 alert(response.data.error);
					 }
									
								})
			}
			//console.log(response);
	}

	
	$scope.getPendingFee = function(){
		
		var fileNo=$scope.studentBasicInfo.fileNo;
		
		if(fileNo){
			feeService.getPendingFee(fileNo)
							.then(function(response){
								 console.log('Getting Pending Fees Information in controller : ');
						          console.log(response);
				if (response !=null && response.data != null && response.data.responseBody != null){
					$scope.feeTransaction = response.data.responseBody;	
				}
				else {
					 console.log(response.data.error);
					 alert(response.data.error);
				 }
								
							})
		}
	}

	
$scope.getDiscountDetail = function(){
		
		var fileNo=$scope.feeTransaction.fileNo;
		
		if(fileNo){
			feeService.getDiscountDetail(fileNo)
							.then(function(response){
								 console.log('Fee Discount Information in controller : ');
						          console.log(response);
				if (response !=null && response.data != null && response.data.responseBody != null){
					$scope.feeTransaction = response.data.responseBody;	
				}
				else {
					 console.log(response.data.error);
					 alert(response.data.error);
				 }
								
							})
		}
	}
	
	
	
	
}]);


feeModule.service('feeService', function($http, $q){
	// Return public API.
	return ({
		getStudentBsInfo : getStudentBsInfo,
	    getPendingFee : getPendingFee,
	    getDiscountDetail : getDiscountDetail
	});
	
	function getStudentBsInfo(fileNo) {

		console.log('getStudentBsInfo called in service');
		var request = $http({
			method : "get",
			url : "admission/StudentBsInfo/" + fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}
	
	function getPendingFee(fileNo) {

		console.log('Pending fee called in service');
		var request = $http({
			method : "get",
			url : "fee/debitedFeeTransaction/" + fileNo,
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}


	function getDiscountDetail(fileNo) {

		console.log('fee discount called in service');
		var request = $http({
			method : "get",
			url : "fee/creditedFeeTransaction/" + fileNo,
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
