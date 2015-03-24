var erpModule = angular
  .module('mainModule', [
    'ngRoute',
    'admissionModule',
    'masterdataModule',
    'transportModule',
    'hostelModule',
    'managementModule',
    'feeModule',
    'ui.bootstrap',
    'enquiryModule',
    'consultantModule'
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
      .when('/transportFrom', {
        templateUrl: 'views/transport.html',
        controller: 'transportController'
        
      })
      .when('/hostelFrom', {
        templateUrl: 'views/hostel.html',
        controller: 'hostelController'
        
      })
       .when('/managementform', {
        templateUrl: 'views/management.html',
        controller: 'managementController'
        
      })
       .when('/feeFrom', {
        templateUrl: 'views/fee.html',
        controller: 'feeController'
        
      })
      .when('/enquiryForm', {
        templateUrl: 'views/enquiry.html',
        controller: 'enquiryController'
        
      }).when('/consultantForm', {
          templateUrl: 'views/consultant.html',
          controller: 'consultantController'})
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