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
	case 1: //Cadastro de login

		$name = $data->name;
		$email = $data->email;
		$password = $data->password;
		$tipo = $data->tipo;

		$registerCia=$pdo->prepare("INSERT INTO estabelecimento (idestabelecimento, tipo, nome, email, password)
									 VALUES (?, ?, ?, ?, ?)");
		$registerCia->bindValue(1, NULL);
		$registerCia->bindValue(2, $tipo);
		$registerCia->bindValue(3, $name);
		$registerCia->bindValue(4, $email);
		$registerCia->bindValue(5, $password);

		$registerCia->execute();

		break;

	case 2: //Login

		$email = $data->email;
		$password = $data->password;

		$getCia=$pdo->prepare("SELECT * FROM estabelecimento WHERE email=:email AND password=:password");
		$getCia->bindValue(":email", $email);
		$getCia->bindValue(":password", $password);

		$getCia->execute();

		$return = array();

		while ($linha=$getCia->fetch(PDO::FETCH_ASSOC)) {

			$idestabelecimento = $linha['idestabelecimento'];
			$nome = $linha['nome'];
			$tipo = $linha['tipo'];

			$return = array(
				'idestabelecimento'	=> $idestabelecimento,
				'nome'	=> $nome,
				'tipo'	=> $tipo
			);

		}

		echo json_encode($return);

		break;

	case 3: //Cadastro de endereço

		$bairro = $data->bairro;
		$cep = $data->cep;
		$idestabelecimento = $data->idestabelecimento;
		$cidade = $data->localidade;
		$logradouro = $data->logradouro;
		$number = $data->number;
		$contato = $data->phone;
		$uf = $data->uf;

		$registerAddress=$pdo->prepare("INSERT INTO endereco (idendereco, idcliente, endereco, numero, cidade, uf, contato) VALUES (?, ?, ?, ?, ?, ?, ?)");

		$registerAddress->bindValue(1, NULL);
		$registerAddress->bindValue(2, $idestabelecimento);
		$registerAddress->bindValue(3, $logradouro);
		$registerAddress->bindValue(4, $number);
		$registerAddress->bindValue(5, $cidade);
		$registerAddress->bindValue(6, $uf);
		$registerAddress->bindValue(7, $contato);
		$registerAddress->execute();

		$return = array();

		$return = array(
				'msgRegisterSuccess' => "Endereço cadastrado com sucesso!"
			);

		echo json_encode($return);

		break;

	case 4: //Buscar nome e id do estebelecimento

		$idestabelecimento = $_GET['idestabelecimento'];

		$getNameIdCia=$pdo->prepare("SELECT * FROM estabelecimento WHERE idestabelecimento=:idestabelecimento
			");
		$getNameIdCia->bindValue(":idestabelecimento", $idestabelecimento);
		$getNameIdCia->execute();

		$dados = array();

		while ($linha=$getNameIdCia->fetch(PDO::FETCH_ASSOC)) {

			$idestabelecimento = $linha['idestabelecimento'];
			$nome = $linha['nome'];

			$dados[] = array(
				'idestabelecimento'	=> $idestabelecimento,
				'nome'	=> $nome
			);

		}

		echo json_encode($dados);

		break;

	case 5: //Buscar Endereço de cadastro

		$idestabel = $_GET['idestabel'];

		$getAddressCia=$pdo->prepare("SELECT * FROM endereco WHERE idcliente=:idestabel");
		$getAddressCia->bindValue(":idestabel", $idestabel);
	
		$getAddressCia->execute();

		$return = array();

		while ($linha=$getAddressCia->fetch(PDO::FETCH_ASSOC)) {

			$endereco = $linha['endereco'];
			$numero = $linha['numero'];
			$cidade = $linha['cidade'];
			$uf = $linha['uf'];
			$contato = $linha['contato'];

			$return[] = array(
				'endereco'	=> $endereco,
				'numero'	=> $numero,
				'cidade'	=> $cidade,
				'uf'	=> $uf,
				'contato'	=> $contato
			);

		}

		echo json_encode($return);

		break;
	
	default:
		# code...
		break;
}




?>