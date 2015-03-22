var masterDataModule = angular.module('masterdataModule', []);

masterDataModule.service('masterdataService', function($http, $q) {

	var masterData = null;
	return ({
		getAdmissionMasterData : getAdmissionMasterData

	});

	function getAdmissionMasterData() {

		if (masterData) {
			console.log('Getting masterdata for admission module');
			var request = $http({
				method : "get",
				url : "masterdata/admission",
				params : "",
				data : ""
			});
			return (request.then(handleSuccess, handleError));
		}else{
			return masterData;
		}


	}

	function handleError(response) {
		console.log('handle error');
		console.log(response);

		if (!angular.isObject(response.data) || !response.data.message) {

			return ($q.reject("An unknown error occurred."));

		}

		return ($q.reject(response.data.message));

	}

	function handleSuccess(response) {
		console.log('handle success');
		console.log(response);
		masterData = data;
		return (response.data);

	}

});