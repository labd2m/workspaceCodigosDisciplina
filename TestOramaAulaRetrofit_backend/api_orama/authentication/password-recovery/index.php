<?php
	include_once "../../classes/passwordRecovery.class.php";

	//HACK PARA COMPENSAR PROBLEMA COM POST NO POSTMAN
	//$_POST["username"] = "";
	//$_POST["birth_date"] = "1986-11-12";
	
	// HACK CODE
    /*$_SG = empty($_POST) ? $_GET : $_POST;
    $_SG = empty($HTTP_RAW_POST_DATA) ? $_SG : $HTTP_RAW_POST_DATA;
    $_SG['username'] = trim($_SG['username'], '"');
	$_SG['birth_date'] = trim($_SG['birth_date'], '"');
    if( !is_array( $_SG ) ){
         $_SG = json_decode($_SG, true);
    }*/
	
	//echo json_encode(array($_POST['username'],$_POST['birth_date'], "vegi",$_SERVER['REQUEST_METHOD']));
	//echo json_encode(array($_SG['username'],$_SG['birth_date'], "lucas"));
	
	if ($_POST['username'] == "lucas.vegi@ufv.br" && $_POST['birth_date'] == "1986-11-12"){
		$pr = new passwordRecovery();
		
		//teste de resposta incompleta---200
		/*header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode(array("recovery_notification_authorization" => "d1b2fd8c5ef5acedbd63779cbdebae17e12593f4"));
		*/
		
		//OK----200
		$pr->recovery_notification_authorization = "d1b2fd8c5ef5acedbd63779cbdebae17e12593f4";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode($pr);
		
		//USERNAME não informado----400
		/*$pr->username = array("Este campo não pode ser em branco.");
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		//CAMPO USERNAME INVÁLIDO----400
		/*$pr->validation_field = "username";
		$pr->validation_type = "invalid_username_format";
		$pr->validation_message = "E-mail ou CPF inválido.";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		//CAMPO birth_date INVÁLIDO----400
		/*$pr->validation_field = "birth_date";
		$pr->validation_type = "invalid_birth_date";
		$pr->validation_message = "Data de nascimento inválida.";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		
	}
	else{
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(500);
		echo json_encode(array("erro" => "usuario inexistente"));
	}
?>