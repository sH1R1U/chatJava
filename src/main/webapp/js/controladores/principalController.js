 angular.module('chat').controller('PrincipalController', function($rootScope, $scope, $interval, cfpLoadingBar, $location, $window, $http, $uibModal) {
      
      $rootScope.mostrarPagina = false;
      
      $interval(function() {
        $rootScope.mostrarPagina = cfpLoadingBar.status()==1  ||cfpLoadingBar.status()== 0 ;
      }, 500);

          //Ventana Modal
    $scope.animationsEnabled = true;
    
    $scope.cerrar = function () {
	    $uibModalInstance.dismiss('cerrar');
	  };

    $scope.open = function (size) {

        var modalInstance = $uibModal.open({
          animation: $scope.animationsEnabled,
          templateUrl: 'myModalContentAcerca.html',
          controller: 'ModalInstanceCtrl',
          size: size,
          resolve: {
            items: function () {
              return "new";
            }
          }
        });

        modalInstance.result.then(function (selectedItem) {
          $scope.selected = selectedItem;
          $scope.save();          
        });
    };

	  $scope.toggleAnimation = function () {
	    $scope.animationsEnabled = !$scope.animationsEnabled;
	  };

  });