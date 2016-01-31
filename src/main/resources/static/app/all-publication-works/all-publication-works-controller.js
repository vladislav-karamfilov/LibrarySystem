$(function () {
    'use strict';

    function AllPublicationWorksController(publicationWorks, books, magazines) {
        var PAGE_SIZE = 10;

        var vm = this;

        books
            .getLastBooks(PAGE_SIZE)
            .then(function (lastBooks) {
                vm.books = lastBooks;
            });

        magazines
            .getLastMagazines(PAGE_SIZE)
            .then(function (lastMagazines) {
                vm.magazines = lastMagazines;
            });

        function filterPublicationWorks(filter) {
            publicationWorks
                .filterPublicationWorks(filter)
                .then(function (filteredPublicationWorks) {
                    vm.books = filteredPublicationWorks.books;
                    vm.magazines = filteredPublicationWorks.magazines;
                });
        }

        vm.filterPublicationWorks = filterPublicationWorks;
    }

    angular.module('librarySystem.controllers')
        .controller('AllPublicationWorksController',['publicationWorks', 'books', 'magazines', AllPublicationWorksController]);
}());