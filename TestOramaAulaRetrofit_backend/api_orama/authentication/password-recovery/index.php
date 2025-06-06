<?php
	include_once "../../classes/passwordRecovery.class.php";

	$pr = new passwordRecovery();

	if($_POST['username'] == ""){
		//USERNAME não informado----400
		$pr->username = array("Este campo não pode ser em branco.");
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);
	}elseif ($_POST['birth_date'] == "") {
		//CAMPO birth_date INVÁLIDO----400
		$pr->validation_field = "birth_date";
		$pr->validation_type = "invalid_birth_date";
		$pr->validation_message = "Data de nascimento inválida.";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);
	}else{
		//SUCESSO----200
		$pr->recovery_notification_authorization = "d1b2fd8c5ef5acedbd63779cbdebae17e12593f4";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode($pr);
	}
?>
