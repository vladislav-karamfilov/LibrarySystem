$(function () {
    'use strict';

    function publicationWorks() {
        return {
            restrict: 'A',
            templateUrl: 'app/common/publication-works-directive.html',
            scope: {
                publicationWorks: '='
            }
        };
    }

    angular.module('librarySystem.directives')
        .directive('publicationWorks', [publicationWorks])
}());