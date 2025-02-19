<?php
	include_once "../../classes/passwordRecoveryAuthorization.class.php";

	//HACK PARA COMPENSAR PROBLEMA COM POST NO POSTMAN
	//$_POST["username"] = "";
	//$_POST["recovery_code"] = "0482";
	
	//echo json_encode(array($_POST['username'],$_POST['birth_date']));
	
	//username da documentação da API é vazio
	if ($_POST["username"] == "lucas.vegi@ufv.br" && $_POST["recovery_code"] == "0482"){
		$pr = new passwordRecoveryAuthorization();
		
		//OK----200
		$pr->recovery_authorization = "d0e5e4b347110d2ab4eae0b858adda3517ef40b2";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode($pr);
		
		//Codigo de recuperação expirado----400
		/*$pr->validation_type = "invalid_authentication_method_recovery_code_first_attempt";
		$pr->validation_message = "Código para recuperação de acesso inválido - primeira tentativa.";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		//Codigo de recuperação expirado----400 --TALVEZ NÃO EXISTA
		/*$pr->validation_type = "invalid_authentication_method_recovery_code_second_attempt";
		$pr->validation_message = "Código para recuperação de acesso inválido - segunda tentativa.";
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode($pr);*/
		
		//Codigo de recuperação expirado----400 --TALVEZ NÃO EXISTA
		/*$pr->validation_type = "invalid_authentication_method_recovery_code_third_attempt";
		$pr->validation_message = "Código para recuperação de acesso inválido - terceira tentativa.";
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