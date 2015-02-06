var hostelModule = angular.module('hostelModule', []);

hostelModule.controller('hostelController', ['$scope','hostelService',function($scope,hostelService) {

			$scope.hostelAvailability = [ {
				"threshold":0,
				"typeCode":null,
				"price":0.0,
				"reservedRoom":0,
				"available":0
			} ];

			$scope.getHostelAvailability = function() {

				hostelService.getHostelAvailability().then(function(data) {
					console.log('Data received from service : ');
					console.log(data);
					if(data != null)
						{
						$scope.hostelAvailability=data;
						}
					else
						{
						console.log('Error getting transport inventory:'+data.error);
						alert('Error getting transport inventory:'+data.error);
						}
				})

			}

		} ]);

hostelModule.service('hostelService', function($http, $q) {

	// Return public API.
	return ({
		getHostelAvailability : getHostelAvailability
	});

	function getHostelAvailability() {

		var request = $http({
			method : "get",
			url : "Hostel/HostelAvailability",
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
		return (response.data);

	}

});