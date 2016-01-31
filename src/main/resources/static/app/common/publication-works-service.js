$(function () {
    function publicationWorks(data) {
        var PUBLICATION_WORKS_BASE_URL = 'publicationworks';

        function createPublicationWork(newPublicationWork) {
            return data.post(PUBLICATION_WORKS_BASE_URL, newPublicationWork);
        }

        function updatePublicationWork(publicationWork) {
            return data.put(PUBLICATION_WORKS_BASE_URL, publicationWork);
        }

        function getPublicationWork(id) {
            return data.get(PUBLICATION_WORKS_BASE_URL, { id: id });
        }

        function filterPublicationWorks(filter) {
            return data.get(PUBLICATION_WORKS_BASE_URL + '/filter', filter);
        }

        return {
            createPublicationWork: createPublicationWork,
            updatePublicationWork: updatePublicationWork,
            getPublicationWork: getPublicationWork,
            filterPublicationWorks: filterPublicationWorks
        };
    }

    angular.module('librarySystem.services')
        .factory('publicationWorks', ['data', publicationWorks]);
}());