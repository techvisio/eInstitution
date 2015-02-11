var transportModule = angular.module('transportModule', []);

transportModule.controller('transportController', ['$scope','transportService',function($scope,transportService) {

			$scope.availableTransport = [ {
				"reserved" : 0,
				"available" : 0,
				"threshold" : 0,
				"price" : 0.0,
				"routeCode" : null,
				"description" : null
			} ];

			$scope.getAvailableTransport = function() {

				transportService.getAvailableTransport().then(function(data) {
					console.log('Data received from service : ');
					console.log(data);
					if(data != null)
						{
						$scope.availableTransport=data;
						}
					else
						{
						console.log('Error getting transport inventory:'+data.error);
						alert('Error getting transport inventory:'+data.error);
						}
				})

			}

		} ]);

transportModule.service('transportService', function($http, $q) {

	// Return public API.
	return ({
		getAvailableTransport : getAvailableTransport
	});

	function getAvailableTransport() {

		var request = $http({
			method : "get",
			url : "transport/AvailableTransport",
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