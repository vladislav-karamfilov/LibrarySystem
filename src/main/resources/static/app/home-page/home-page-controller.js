$(function () {
    'use strict';

    function HomePageController(books, magazines) {
        var PAGE_SIZE = 10;

        var vm = this;

        books
            .getLastBooks(PAGE_SIZE)
            .then(function(booksData) {
                vm.books = booksData;
            });

        magazines
            .getLastMagazines(PAGE_SIZE)
            .then(function(magazinesData) {
                vm.magazines = magazinesData;
            });
    }

    angular.module('librarySystem.controllers')
        .controller('HomePageController', ['books', 'magazines', HomePageController]);
}());
