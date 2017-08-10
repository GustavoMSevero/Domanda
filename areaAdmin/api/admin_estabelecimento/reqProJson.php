<?php
header("Access-Control-Allow-Origin: *");
ini_set('display_errors', true);
error_reporting(E_ALL);

include_once("con.php");

$pdo = conectar();

$opcao = 2;

switch ($opcao) {

	case 2: // Buscar Uidade para o app

		$idprofissional = $_GET['idpro']; //Ex: Restaurante


		$getProForApp=$pdo->prepare("SELECT * FROM profissional WHERE idprofissional=:idprofissional");
		$getProForApp->bindValue(":idprofissional", $idprofissional);

		$getProForApp->execute();

		$return = array();

		while ($linha=$getProForApp->fetch(PDO::FETCH_ASSOC)) {

			$idestabelecimento = $linha['idestabelecimento'];
			$idunidade = $linha['idunidade'];
			$unidade = $linha['unidade'];
			$endereco = $linha['endereco'];
			$numero = $linha['numero'];

			$return = array(
				'idestabelecimento'	=> $idestabelecimento,
				'idunidade'	=> $idunidade,
				'unidade'	=> $unidade,
				'endereco'	=> $endereco,
				'numero'	=> $numero
			);

		}

		echo json_encode($return);

		break;
	
	default:
		# code...
		break;
}




?>