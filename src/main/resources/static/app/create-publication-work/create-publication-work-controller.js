$(function () {
    'use strict';

    function CreatePublicationWorkController(publicationWorks, notifier, $location) {
        var vm = this;

        function createPublicationWork(newPublicationWork) {
             publicationWorks
                .createPublicationWork(newPublicationWork)
                .then(function(newPublicationWork) {
                    $location.path('/');
                    notifier.success('Publication work successfully created!');
                });
        }

        vm.createPublicationWork = createPublicationWork;
    }

    angular.module('librarySystem.controllers')
        .controller('CreatePublicationWorkController',['publicationWorks', 'notifier', '$location', CreatePublicationWorkController]);
}());