$(function () {
    'use strict';

    function books(data) {
        function getLastBooks(count) {
            return data.get('books/last', { count: count });
        }

        return {
            getLastBooks: getLastBooks
        };
    }

    angular.module('librarySystem.services')
        .factory('books', ['data', books]);
}());