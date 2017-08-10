<?php
header("Access-Control-Allow-Origin: *");
ini_set('display_errors', true);
error_reporting(E_ALL);

include_once("con.php");

$pdo = conectar();

$opcao = $_GET['opcao'];

switch ($opcao) {

	case "nome": //Cadastro de unidade

		$bairro = $data->bairro;
		$contato = $data->contato;
		$idestabelecimento = $data->idestabelecimento;
		$cidade = $data->localidade;
		$logradouro = $data->logradouro;
		$numero = $data->numero;
		$uf = $data->uf;
		$contato = $data->contato;
		$nome = $data->nome;

		//echo $bairro.'-'.$contato.'-'.$idestabelecimento.'-'.$cidade.'-'.$logradouro.'-'.$numero.'-'.$uf.'-'.$nome;
		$registerUnit=$pdo->prepare("INSERT INTO unidade (idunidade, idestabelecimento, unidade, endereco, numero, cidade, uf, contato) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		$registerUnit->bindValue(1, NULL);
		$registerUnit->bindValue(2, $idestabelecimento);
		$registerUnit->bindValue(3, $nome);
		$registerUnit->bindValue(4, $logradouro);
		$registerUnit->bindValue(5, $numero);
		$registerUnit->bindValue(6, $cidade);
		$registerUnit->bindValue(7, $uf);
		$registerUnit->bindValue(8, $contato);
		$registerUnit->execute();

		$registerOK = array();

		$registerOK = array(
				'msgRegisterSuccess' => "Unidade adicionada com sucesso!"
			);

		echo json_encode($registerOK);

		break;

	case "funcao": // Buscar Unidades

		$idestabel = $_GET['idestabel'];

		//echo $idestabel;
		$getUnit=$pdo->prepare("SELECT * FROM unidade WHERE idestabelecimento=:idestabelecimento");
		$getUnit->bindValue(":idestabelecimento", $idestabel);

		$getUnit->execute();

		$return = array();

		while ($linha=$getUnit->fetch(PDO::FETCH_ASSOC)) {

			$idunidade = $linha['idunidade'];
			$unidade = $linha['unidade'];
			$endereco = $linha['endereco'];
			$numero = $linha['numero'];

			$return[] = array(
				'idunidade'	=> $idunidade,
				'unidade'	=> $unidade,
				'endereco'	=> $endereco,
				'numero'	=> $numero
			);

		}
		
		if(!$return){
			$msgVazio = "Não existe unidades cadastradas";
			$return = array(
				'msgVazio'	=> $msgVazio
			);
		}else{
			echo json_encode($return);
		}


		break;

	case 1: // Buscar Todos

		$idestabel = $_GET['idestabel'];

		$getUnits=$pdo->prepare("SELECT idunidade FROM unidade WHERE idestabelecimento=:idestabel");
		$getUnits->bindValue(":idestabel", $idestabel);

		$getUnits->execute();

		$return = array();

		while ($linha=$getUnits->fetch(PDO::FETCH_ASSOC)) {

			$idunidade = $linha['idunidade'];

			$getPro=$pdo->prepare("SELECT * FROM profissional INNER JOIN unidade 
									ON profissional.idunidade = unidade.idunidade 
									WHERE profissional.idunidade=:idunidade");
			$getPro->bindValue(":idunidade", $idunidade);
			$getPro->execute();

			while ($linhaPro=$getPro->fetch(PDO::FETCH_ASSOC)) {

				$unidade = $linhaPro['unidade'];
				$idprofissional = $linhaPro['idprofissional'];
				$nome = $linhaPro['nome'];
				$funcao = $linhaPro['funcao'];

				$return[] = array(
					'idunidade'	=> $idunidade,
					'unidade'	=> $unidade,
					'idprofissional'	=> $idprofissional,
					'nome'	=> $nome,
					'funcao'	=> $funcao
				);

			}


		}

		echo json_encode($return);
		
		break;
	
	default:
		# code...
		break;
}




?>