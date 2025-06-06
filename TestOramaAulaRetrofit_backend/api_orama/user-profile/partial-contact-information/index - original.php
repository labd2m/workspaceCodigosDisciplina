<?php
	include_once '../../classes/user.class.php';
	
	if ($_GET['username'] == "12227095709"){
		$usuario = new user();
		
		$usuario->first_name = "Lucas";
		$usuario->is_approved = true;
		$usuario->partial_email = "lucas.vegi@ufv.br"; //padrão do exemplo é null
		$usuario->partial_mobile_phone = "(21) 3797-8000"; //padrão do exemplo é null
		$usuario->support_phone_list = array("0800 728 0880","(21) 3797-8000","(21) 2042-1942");
		$usuario->support_type = 3;
		
		//sucesso---200
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(200);
		echo json_encode($usuario);
		
		//erro---400
		/*header("Content-Type: application/json; charset=utf-8");
		http_response_code(400);
		echo json_encode(array("is_approved" => false));*/
	}
	else{
		header("Content-Type: application/json; charset=utf-8");
		http_response_code(500);
		echo json_encode(array("erro" => "usuario inexistente"));
	}
?>