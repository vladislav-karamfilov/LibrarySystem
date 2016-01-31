$(function () {
    function ratings(data) {
        function ratePublicationWork(publicationWorkId, value) {
            return data.post('rate', { publicationWorkId: publicationWorkId, value: value });
        }

        return {
            ratePublicationWork: ratePublicationWork
        };
    }

    angular.module('librarySystem.services')
        .factory('ratings', ['data', ratings]);
}());