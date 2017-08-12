var app = angular.module("domanda", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider){

	$routeProvider

	.when("/", {
        templateUrl: "views/loginadmin.html",
        controller: "LoginCtrl"
    })

    .when("/inicialRestaurantes", {
        templateUrl: "views/inicialRestaurantes.html"
    })

    .when("/inicialCabeleireiros", {
        templateUrl: "views/inicialCabeleireiros.html"
    })

    .when("/unidadeRestaurantes", {
        templateUrl: "views/unidadeRestaurantes.html"
    })

    .when("/unidadeCabeleireiros", {
        templateUrl: "views/unidadeCabeleireiros.html"
    })

    .when("/profissionais", {
        templateUrl: "views/profissionais.html"
    })

    .when("/agendaProfissionais/:idprofissional", {
        templateUrl: "views/agendaProfissionais.html"
    })

    .when("/agenda", {
        templateUrl: "views/agenda.html"
    })

    .when("/agendaProfissional/:idprofissional", {
        templateUrl: "views/agendaProfissional.html"
    })


});

app.run(function($rootScope, $filter) {

   $rootScope.idestabelecimento = null;
   $rootScope.nome = null;

})