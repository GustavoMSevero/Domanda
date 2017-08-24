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

	case 1: //Buscar unidades

		$idestabel = $_GET['idestabel'];

		//echo $idestabel;
		$getUnit=$pdo->prepare("SELECT idunidade, unidade FROM unidade WHERE idestabelecimento=:idestabel");
		$getUnit->bindValue(":idestabel", $idestabel);
		$getUnit->execute();

		$return = array();

		while ($linha=$getUnit->fetch(PDO::FETCH_ASSOC)) {

			$idunidade = $linha['idunidade'];
			$unidade = $linha['unidade'];

			$return[] = array(
				'idunidade'	=> $idunidade,
				'unidade'	=> $unidade
			);

		}

		echo json_encode($return);

		break;

	case 2: //Cadastrar profissional

		$nome = $data->nome;
		$funcao = $data->funcao;
		$idunidade = $data->unidade;

		//echo $idunidade.'-'.$funcao.'-'.$nome;
		$setPro=$pdo->prepare("INSERT INTO profissional (idprofissional, idunidade, nome, funcao) VALUES (?, ?, ?, ?)");
		$setPro->bindValue(1, NULL);
		$setPro->bindValue(2, $idunidade);
		$setPro->bindValue(3, $nome);
		$setPro->bindValue(4, $funcao);
		$setPro->execute();

		$msgSuccess = "Profissional adicionado com sucesso";

		$return = array(
				'msgSuccess'	=> $msgSuccess
			);

		echo json_encode($return);

		break;

	case 3: //Buscar profissionais

		$idestabel = $_GET['idestabel'];

		$getPro=$pdo->prepare("SELECT * FROM unidade INNER JOIN profissional 
							  ON profissional.idunidade = unidade.idunidade WHERE idestabelecimento=:idestabel");
		$getPro->bindValue(":idestabel", $idestabel);
		$getPro->execute();

		while ($linhaPro=$getPro->fetch(PDO::FETCH_ASSOC)) {

			$unidade = $linhaPro['unidade'];
			$idprofissional = $linhaPro['idprofissional'];
			$idunidade = $linhaPro['idunidade'];
			$nome = $linhaPro['nome'];
			$funcao = $linhaPro['funcao'];

			$return[] = array(
				'idprofissional'	=> $idprofissional,
				'nome'	=> $nome,
				'funcao'	=> $funcao,
				'unidade'	=> $unidade
			);


		}

		echo json_encode($return);

		break;

	case 4: //Buscar profissional

		$idpro = $_GET['idpro'];

		$getPro=$pdo->prepare("SELECT * FROM profissional WHERE idprofissional=:idpro");
		$getPro->bindValue(":idpro", $idpro);
		$getPro->execute();

		while ($linhaPro=$getPro->fetch(PDO::FETCH_ASSOC)) {

			$idprofissional = $linhaPro['idprofissional'];
			$nome = $linhaPro['nome'];
			$funcao = $linhaPro['funcao'];

			$return = array(
				'idprofissional'	=> $idprofissional,
				'nome'	=> $nome,
				'funcao'	=> $funcao
			);

		}

		echo json_encode($return);

		break;
	
	default:
		# code...
		break;
}




?>