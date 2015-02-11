var erpModule = angular
  .module('mainModule', [
    'ngRoute',
    'admissionModule',
    'masterdataModule',
    'transportModule',
    'hostelModule',
    'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'admissionController'
      })
      .when('/admissionFrom', {
        templateUrl: 'views/admission.html',
        controller: 'admissionController'
        
      })
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'admissionController'
      })
      .when('/transportFrom', {
        templateUrl: 'views/transport.html',
        controller: 'transportController'
        
      })
      .when('/hostelFrom', {
        templateUrl: 'views/hostel.html',
        controller: 'hostelController'
        
      })
      .otherwise({
        redirectTo: '/'
      });
  })
 



    // erpModule.run(['$rootScope',
    // function($rootScope) {
    //   $rootScope.showSidebar = true;
    //     $(".container").toggleClass("fullview");
    //     $rootScope.toggleSidebar = function() {
    //         $(".container").toggleClass("toggled fullview");
    //         if ($(".container.toggled").length) {
    //             $(".toggler").text("<");
    //         } else {
    //             $(".toggler").text(">");
    //         }
    //     }
    // }
// ]);