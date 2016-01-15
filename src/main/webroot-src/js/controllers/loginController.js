onlineRegApp.controller('LoginController', function ($scope, $location, $http, $rootScope, $translate, $routeParams) {
    //setStatus will be setted from RegisterUserController, if
    //registering was successful
    if(setStatus == 1) {
        $scope.status = 1;
        setStatus = null;
    }

    $scope.login = function () {
        $scope.button = true;

        $http({
            method: 'POST',
            url: 'api/idm/login/' + encodeURIComponent($scope.username),
            headers: {"Content-Type" : undefined},
            data: $.param({
                password: $scope.password,
                delegation: $routeParams.delegation
            })
        }).success(function (result) {
            $scope.error = null;
            $scope.button = false;
           console.log(result);
           if (result != "") {//result=object
               if(result.status.localeCompare("disabled")||result.status=="disabled"){
                   $scope.status=42;
                   $location.path($scope.nextPage);
               }else{
                   $rootScope.user_logged_in = $scope.username;
                   $rootScope.userinfo = result.status;
                   $rootScope.status = null;
                   $rootScope.messageContent = null;
                   $location.path($scope.nextPage);
               }
            } else {
                $rootScope.status = "danger";
                $translate('GENERIC_MESSAGE_USER_OR_PASSWORD_WRONG').then(function (text) {
                    $rootScope.messageContent = text;
                });
            }
        }).error(function (data, status, headers, config) {
            $scope.button = false;
            $rootScope.status = "danger";
            $translate('GENERIC_ERROR').then(function (text) {
                $rootScope.messageContent = text;
            });
        });
    }
});
