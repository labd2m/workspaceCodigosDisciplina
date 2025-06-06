<?php
	include_once '../classes/person.class.php';
	include_once '../classes/address.class.php';

	$person = new person();
	$address_vicosa = new address();
	$address_ufv = new address();
	

	if ($_GET['id_person'] == "123"){
		
		$address_vicosa->rua = "Rua dos Estudantes";
		$address_vicosa->numero = "200";
		$address_vicosa->bairro = "Centro";
		$address_vicosa->cidade = "Vicosa-MG";
		$address_vicosa->cep = "36.570-081";
		
		$address_ufv->rua = "Av. PH Rolphs";
		$address_ufv->numero = "sem numero";
		$address_ufv->bairro = "CCE";
		$address_ufv->cidade = "Vicosa-MG";
		$address_ufv->cep = "36.570-000";
		
		$person->name = "Lucas";
		$person->email = "lucasvegi@gmail.com";
		$person->addresses = array($address_vicosa, $address_ufv);
		$person->telefone = "31 99328-6344";

		//sucesso---200
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode($person);

	}
	else{
		//erro---400
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		$person->name = "Não existe...";
		echo json_encode($person);
	}
?>
