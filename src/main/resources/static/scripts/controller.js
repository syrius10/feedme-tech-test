var feedMeApp = angular.module('feedMeApp', []);

feedMeApp.controller('FeedMeCtrl', function ($scope, $http) {

    $scope.getFixtures = function() {
        $http.get('/fixtures')
        .success(function (data) {
            $scope.feeds = data;
        })
        .error(function(data, status) {
            console.error('No data found yet.', status, data)
            $scope.error = 'No data found yet.'
        });
    }

    $scope.runProcess = function(flag) {
        if (flag === true) {
            $scope.processing = true;
            $http.get('/processFeeds').success(function(data) {});
        } else {
           $http.get('/abortFeedsProcess').success(function(data) {
               $scope.feeds = data
               $scope.processing = false;
           });
        }
    }
});