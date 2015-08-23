var erp = angular
.module('erp', [
                'ngRoute',
                'ui.bootstrap',
                'ngGrid',
                'ui.bootstrap.pagination',
                'ngGrid',
                'ui.router',
                //'smart-table',
                'erp.services',
                'admissionModule',
                'masterdataModule',
                'transportModule',
                'admissionModule',
                'sidebarModule',
                'enquiryModule',
                'consultantModule',
                'feeModule',
                'userModule'
                ]);

erp.config(function ($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise("/home");
	// Now set up the states
	$stateProvider
	.state('home', {
		// Use a url of "/" to set a state as the "index".
		url: "/home",
	})
	.state('admission', {
		// Use a url of "/" to set a state as the "index".
		url: "/admission/{fileNo:[0-9]{1,8}}",
		templateUrl: 'home/admission/admissionMain.html',
		controller: "admissionController",
		resolve:{
			injectedData: ['$stateParams','admissionService', function($stateParams,admissionService){
				return admissionService.getStudent($stateParams.fileNo);
			}]
		}
	})
	.state('newadmission', {
		url: "/admission/new",
		templateUrl: 'home/admission/admissionMain.html',
		controller: "admissionController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})

	.state('admissionSearch', {
		url: "/admission/search",
		templateUrl: 'home/admission/admissionSearch.html',
		controller: "admissionController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})

	.state('enquiry', {
		url: "/enquiry/{enquiryId:[0-9]{1,8}}",
		templateUrl: 'home/enquiry/enquiryMain.html',
		controller: "enquiryController",
		resolve:{
			injectedData: ['$stateParams','enquiryService', function($stateParams,enquiryService){
				return enquiryService.getEnquiry($stateParams.enquiryId);
			}]
		}
	}) 
	.state('newEnquiry', {
		url: "/enquiry/new",
		templateUrl: 'home/enquiry/enquiryMain.html',
		controller: "enquiryController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})

	.state('enquirySearch', {
		url: "/enquiry/search",
		templateUrl: 'home/enquiry/enquirySearch.html',
		controller: "enquiryController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})

	.state('consultant', {
		url: "/consultant/{fileNo:[0-9]{1,8}}",
		templateUrl: 'home/consultant/consultantAdmissionInfo.html',
		controller: "consultantController",
		resolve:{
			injectedData: ['$stateParams','consultantService', function($stateParams,consultantService){
				return consultantService.getConsultantAdmissionDetail($stateParams.fileNo);
			}]
		}
	}) 

	.state('consultantM', {
		url: "/consultantM/{consultantId:[0-9]{1,8}}",
		templateUrl: 'home/consultant/newConsultant.html',
		controller: "consultantController",
		resolve:{
			injectedData: ['$stateParams','consultantService', function($stateParams,consultantService){
				return consultantService.getConsultant($stateParams.consultantId);
			}]
		}
	}) 
	.state('newConsultant', {
		url: "/consultant/new",
		templateUrl: 'home/consultant/newConsultant.html',
		controller: "consultantController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})

	.state('consultantSearch', {
		url: "/consultant/search",
		templateUrl: 'home/consultant/searchByStudent.html',
		controller: "consultantController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})
	
	.state('consultantSearchByC', {
		url: "/consultant/searchC",
		templateUrl: 'home/consultant/searchByConsultant.html',
		controller: "consultantController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})
	
	.state('SearchStudentForFee', {
		url: "/fee/search",
		templateUrl: 'home/fee/searchStudentForFee.html',
		controller: "feeController",
		resolve:{
			injectedData: ['$stateParams', function($stateParams){
				return {};
			}]
		}
	})
	
	.state('feeDeposite', {
		url: "/fee/{fileNo:[0-9]{1,8}}",
		templateUrl: 'home/fee/feeDeposite.html',
		controller: "feeController",
		resolve:{
			injectedData: ['$stateParams','feeService', function($stateParams,feeService){
				return feeService.getFeeTransactionAndBasicInfoDetail($stateParams.fileNo);
			}]
		}
	});

});

erp.config(['$httpProvider', '$sceProvider',
            function ($httpProvider, $sceProvider) {
	$sceProvider.enabled(false);
	$httpProvider.interceptors.push(
			['$q', '$location', '$rootScope', 'deferredManager',
			 function ($q, $location, $rootScope, deferredManager) {
				return {
					request: function (config) {
						if(config.url.search('/service/') > -1) {
							config.timeout = deferredManager.deferService(config.url);
						}
						return config;
					},
					response: function (response) {
						if(response.data) {
							if (response.data.error) {
								$rootScope.$broadcast('showError', response.data.error || 'Error '+response.status, response.status);
								return $q.reject(response);
							}
						}
						if(response.status == 202) {
							$rootScope.$broadcast('authorized',null, response.status);
						}
						return response;
					},
					responseError: function (response) {
						if(response.status == 401) {
							$rootScope.$broadcast('unauthorized',null, response.status);
							return $q.reject(response);
						} else if (response && [400,403, 404, 405, 415, 500, 501, 502, 503, 504].indexOf(response.status) > -1) {
							$rootScope.$broadcast('showError', response.data.error || 'Error '+response.status, response.status);
						}
						return $q.reject(response);
					}
				};
			}]);
}]);

erp.controller('ApplicationController',
		['$scope', '$rootScope', '$timeout', '$modal','userService', '$http',
		function ($scope, $rootScope, $timeout, $modal,userService,$http) {
		    $rootScope.enableSidebar = true;
		    $rootScope.user={};
		    $rootScope.user.privilege=["ROLE_PER_U","ROLE_DOC_U","ROLE_ADD_U","ROLE_DIS_U","ROLE_SCH_U","ROLE_ACD_U","ROLE_COUN_U","ROLE_OFF_U","ROLE_DIS_U","ROLE_CON_U","ROLE_REF_U","ROLE_TRA_U","ROLE_HOS_U","ROLE_AFE_U","ROLE_PER_R","ROLE_DOC_R","ROLE_ADD_R","ROLE_DIS_R","ROLE_SCH_R","ROLE_ACD_R","ROLE_COUN_R","ROLE_OFF_R","ROLE_DIS_R","ROLE_CON_R","ROLE_REF_R","ROLE_TRA_R","ROLE_HOS_R","ROLE_AFE_R"];
	        $rootScope.user=null;
		    
			 $scope.getUser = function() {
				 console
				 .log('getting user in app.js');
				 userService
				 .getUser()
				 .then(
						 function(data) {
							 console.log(data);
							 if (data) {
								 $rootScope.user = data.responseBody;
								 $rootScope.curModal.close();
							 } else {
								 console.log('error');
							 }
						 })
			 };
			 
			 if($rootScope.user==null){
		    	 $scope.getUser();
		       }
			 
			 $rootScope.$on('authorized', function (o, e, type) {
				 $scope.getUser();
			 });

			 $rootScope.$on('unauthorized', function (o, e, type) {
			        if (!$.isEmptyObject($rootScope.curModal)) {
			            return;
			        }
			        $rootScope.curModal = $modal.open({
			            templateUrl: 'home/modals/login.html'
			        });
			        $rootScope.curModal.result.finally(
			            function() {
			                $rootScope.curModal = {}
			            }
			        );
			    });
			 
		    $rootScope.$on('showError', function (o, e, type) {
		        if (!$.isEmptyObject($rootScope.curModal)) {
		            return;
		        }
		        $rootScope.curModal = $modal.open({
		            templateUrl: 'home/modals/errorModalContent.html',
		            controller: function ($scope) {
		                var title = "Failed to Load Data";
		                $scope._errorCode = type;
		                $scope._errorTitle = title + " (HTTP: " + type + ")";
		                $scope._errorMessage = e.data ? e.data : (e.message ? e.message : e);
		                $scope.resetCurModal = function(time) {
		                    $timeout(function () {
		                        $rootScope.curModal = {};
		                    }, time || 500);
		                };
		                $scope.closeErrorModal = function (back) {
		                    if(back)
		                        history.go(-1);
		                    $rootScope.curModal.close();
		                    $scope.resetCurModal();
		                };
		            }
		            // keyboard: false,
		            // backdrop: 'static'
		        });
		        $rootScope.curModal.result.finally(
		            function() {
		                $rootScope.curModal = {}
		            }
		        );
		    });
		}]);