<?php
header("Access-Control-Allow-Origin: *");
ini_set('display_errors', true);
error_reporting(E_ALL);

include_once("con.php");

$pdo = conectar();

$data = file_get_contents("php://input");
$data = json_decode($data);

if($data){
	$opcao = $data->opcao;
}else{
	$opcao = $_GET['opcao'];
}



switch ($opcao) {

	case 1: //Buscar profissional

		$idpro = $_GET['idpro'];

		$getPro=$pdo->prepare("SELECT nome FROM profissional WHERE idprofissional=:idpro");
		$getPro->bindValue(":idpro", $idpro);
		$getPro->execute();

		$return = array();

		while ($linha=$getPro->fetch(PDO::FETCH_ASSOC)) {

			$nome = $linha['nome'];

			$return = array(
				'nome'	=> $nome
			);

		}

	 	echo json_encode($return);

	 break;
	
	default:
		# code...
		break;
}




?>