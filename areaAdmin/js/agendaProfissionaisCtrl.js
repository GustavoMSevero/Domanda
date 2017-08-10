app.controller("AgendaProfissionaisCtrl", ['$scope', '$http', '$window', '$location', '$rootScope', '$routeParams', function ($scope, $http, $window, $location, $rootScope, $routeParams) {

	$rootScope.idestabelecimento = localStorage.getItem('idestabelecimento');
	$scope.idprofissional = $routeParams.idprofissional;

	//var urlGetPro = "http://localhost:8888/sistemas/Android/areaAdmin/api/admin_estabelecimento/agendaProfissionais.php?opcao=";
	var urlGetPro = "http://reservacomdomanda.com/areaAdmin/api/admin_estabelecimento/agendaProfissionais.php?opcao=";

	// $scope.nome;
	var getPro = function(){
		var idpro = $scope.idprofissional;
		var opcao = 1; //Pegar profissional
		
		$http.get(urlGetPro+opcao+"&idpro="+idpro).success(function(response){
			$scope.nome = response.nome;
		})
	}

	getPro();

	$scope.setAgenda = function(agenda){
		agenda.idpro = $scope.idprofissional;
		var mes  = new Date(agenda.dia).getMonth();
		var dia  = new Date(agenda.dia).getDate();
		var ano  = new Date(agenda.dia).getFullYear();

		var hora  = new Date(agenda.hora).getHours();
		var min  = new Date(agenda.hora).getMinutes();

		agenda.hora = new Date(0,0,0,hora,min);

		agenda.dia = new Date(ano, mes, dia);

		agenda.opcao = 2; // Agendar horario

		console.log(agenda)
		
	}

	
}]);



