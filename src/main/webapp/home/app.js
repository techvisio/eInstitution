var erp = angular
  .module('erp', [
    'ngRoute',
    'ui.bootstrap',
    'ngGrid',
    'ui.bootstrap.pagination',
    'ngGrid',
    'ui.router',
    'erp.services',
    'admissionModule',
    'masterdataModule',
    'transportModule',
    'admissionModule',
    'sidebarModule'
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
        	
    });
    
//    .state('newadmission',{
//    views: {
//      'fixedAdmissionInfo': {
//        templateUrl: 'home/admission/fixedAdmissionInfo.html',
//        controller: function($scope){}
//      },
//      'personalInfo': {
//        templateUrl: 'home/admission/personalInfo.html',
//        controller: "admissionController"
//      },
//      'academicDetail': {
//        templateUrl: 'home/admission/academicDetail.html',
//        controller: "admissionController"
//      }
//    }
//  })
//  
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
                             return response;
                         },
                         responseError: function (response) {
                             if(response.status == 401) {
                                
                             } else if (response && [400,403, 404, 405, 415, 500, 501, 502, 503, 504].indexOf(response.status) > -1) {
                                
                                 $rootScope.$broadcast('showError', response.data || 'Error '+response.status, response.status);
                             }
                             return $q.reject(response);
                         }
                     };
                 }]);
             }]);

erp.controller('ApplicationController',
		['$scope', '$rootScope', '$timeout', '$modal', '$http',
		function ($scope, $rootScope, $timeout, $modal) {
		    $rootScope.enableSidebar = true;
		    $rootScope.user={};
		    $rootScope.user.privilege={"view":"VIEW_PERSONAL"};
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