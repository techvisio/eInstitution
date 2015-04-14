var reportsModule = angular.module('reportsModule', []);

reportsModule.controller('reportsController', ['$scope','reportsService',function($scope,reportsService) {

	$scope.dashboard = false;
	$scope.constRpt=false;
	$scope.admssnRpt=false;
	$scope.admissionReport= [];

	$scope.consultantReport=[];
	$scope.studentData =[];
	$scope.gridOptions = { data: 'studentData',showGroupPanel: true };

	$scope.getConsultantReport = function() {

		$scope.consultantReport = [
		                    {
		                    	"consultantName":"Pradeep",
		                    	"students":[{
		                    	            "name":"Test1",
		                    	            "dob":"21-05-1988",
		                    	            "fatherName":"PapaTest1",
		                    	            "feesSubmitted":"1200",
		                    	            "course":"MBA",
		                    	            "branch":"HR",
		                    	            "quota":"GEN"
		                    	},{
                    	            "name":"Test2",
                    	            "dob":"21-05-1988",
                    	            "fatherName":"PapaTest2",
                    	            "feesSubmitted":"1600",
                    	            "course":"MBA",
                    	            "branch":"Finance",
                    	            "quota":"GEN"
                    	}]
		                    },
		                    {
		                    	"consultantName":"Sandeep",
		                    	"students":[{
		                    	            "name":"TestS1",
		                    	            "dob":"21-05-1988",
		                    	            "fatherName":"PapaTestS1",
		                    	            "feesSubmitted":"300",
		                    	            "course":"Btech",
		                    	            "branch":"CS",
		                    	            "quota":"GEN"
		                    	},{
                    	            "name":"TestS2",
                    	            "dob":"21-05-1988",
                    	            "fatherName":"PapaTestS2",
                    	            "feesSubmitted":"600",
                    	            "course":"Btech",
                    	            "branch":"ECE",
                    	            "quota":"GEN"
		                    	}]
		                    }
		                    ];
		$scope.fillStudentData();
		//$scope.gridOptions = { data: 'studentData',showGroupPanel: true };
		constRpt=true;
		admssnRpt=false;
		dashboard=false;
//		hostelService.getConsultantReport().then(function(response) {
//			console.log('Received Consultant Report: ');
//			console.log(response);
//			if(response.data != null)
//			{
//				$scope.consultantReport=response.data;
//				constRpt=true;
//				admssnRpt=false;
//				dashboard=false;
//			}
//			else
//			{
//				console.log('Error getting consultant Report:'+data.error);
//				alert('Error retrieving consultant Report:'+data.error);
//			}
//		})

	}
	
	$scope.fillStudentData = function(){
		 for (var i=0; i<$scope.consultantReport.length; i++){
		 for(var j=0; j<$scope.consultantReport[i].students.length; j++)
			 $scope.studentData.push($scope.consultantReport[i].students[j]);
		 }
	}
	
	$scope.getAdmissionReport = function() {

		hostelService.getAdmissionReport().then(function(response) {
			console.log('Received Admission Report: ');
			console.log(response);
			if(response.data != null)
			{
				$scope.admissionReport=response.data;
				constRpt=false;
				admssnRpt=true;
				dashboard=false;
			}
			else
			{
				console.log('Error getting Admission Report:'+data.error);
				alert('Error retrieving admission Report:'+data.error);
			}
		})

	}



} ]);

reportsModule.service('reportsService', function($http, $q) {

	// Return public API.
	return ({
		getConsultantReport : getConsultantReport,
		getAdmissionReport : getAdmissionReport,
	});

	function getConsultantReport() {

		console.log('getConsultantReport called in service');
		var request = $http({
			method : "get",
			url : "report/consutantReport",
			params : {
				action : "get"
			}
		});

		return (request.then(handleSuccess, handleError));

	}
	
	function getAdmissionReport() {

		console.log('getAdmissionReport called in service');
		var request = $http({
			method : "get",
			url : "report/admissionReport",
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