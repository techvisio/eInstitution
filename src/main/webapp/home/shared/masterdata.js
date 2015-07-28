var masterDataModule=angular.module('masterdataModule',[]);

masterDataModule.service('masterdataService', function($http, $q){

	 return ({
         getAdmissionMasterData : getAdmissionMasterData,
         getAdmissionMasterDataEnquiry : getAdmissionMasterDataEnquiry
       });

       function getAdmissionMasterData() {

         console.log('Getting masterdata for admission module');
         var request = $http({
           method : "get",
           url : "masterdata/admission",
           params : "",
           data : ""
         });

         return (request.then(handleSuccess, handleError));

       }

       function getAdmissionMasterDataEnquiry() {

           console.log('Getting masterdata for enquiry module');
           var request = $http({
             method : "get",
             url : "masterdata/enquiry",
             params : "",
             data : ""
           });

           return (request.then(handleSuccess, handleError));

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
         return (response.data);

       }
	
});