var masterDataModule=angular.module('masterdataModule',[]);

masterDataModule.service('masterdataService', function($http, $q){

	 return ({
         getAdmissionMasterData : getAdmissionMasterData,
         getEnquiryMasterData : getEnquiryMasterData,
         getConsultantMasterData : getConsultantMasterData,
         getFeeMasterData : getFeeMasterData
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

       function getEnquiryMasterData() {

           console.log('Getting masterdata for enquiry module');
           var request = $http({
             method : "get",
             url : "masterdata/enquiry",
             params : "",
             data : ""
           });

           return (request.then(handleSuccess, handleError));

         }

       function getConsultantMasterData() {

           console.log('Getting masterdata for consultant module');
           var request = $http({
             method : "get",
             url : "masterdata/consultant",
             params : "",
             data : ""
           });
           return (request.then(handleSuccess, handleError));
         }

       function  getFeeMasterData() {

           console.log('Getting masterdata for fee module');
           var request = $http({
             method : "get",
             url : "masterdata/fee",
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