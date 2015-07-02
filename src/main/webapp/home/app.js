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
    'masterdataModule'
  ]);

erp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/home");
    // Now set up the states
    $stateProvider
        .state('home', {
            // Use a url of "/" to set a state as the "index".
            url: "/home",
            templateUrl: 'home/main.html'
        })
        .state('admission', {
            // Use a url of "/" to set a state as the "index".
            url: "/admission",
            templateUrl: 'home/admission/admissionDashboard.html',
            controller: "admissionController"
        })
        .state('newadmission', {
    	url: "/newadmission",
        templateUrl: 'home/admission/fixedAdmissionInfo.html',
        controller: "admissionController"
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
                             return response;
                         },
                         responseError: function (response) {
                             if(response.status == 401) {
                                 $rootScope.user = null;
                                 window.location = "/kyv-web/";
                             } else if (response && [403, 404, 405, 415, 500, 501, 502, 503, 504].indexOf(response.status) > -1) {
                                 // temporary - while 404 error in html mode
                                 if (response.data && response.data.match && response.data.match(/^<html/i)) {
                                     var rdata = response.data.match(/(\<body\>)(.*)(\<\/body\>)/);
                                     if (rdata.length == 4) {
                                         response.data = rdata[2];
                                     }
                                 }
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