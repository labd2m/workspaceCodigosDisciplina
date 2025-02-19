<?php
class GCM{
	private $GOOGLE_API_KEY;
	
	public function __construct()
	{
		//server google key
		$this->GOOGLE_API_KEY = AIzaSyCzUpzgg7fe1oqTeIwidOGbe83ftZIyYh4;
	}
	
	private function send_notification($registatoin_ids, $message) {
        // Set POST variables
        $url = 'https://android.googleapis.com/gcm/send';
 
        $fields = array(
            'registration_ids' => $registatoin_ids,
            'data' => $message,
        );
 
        $headers = array(
            'Authorization: key=' . $this->GOOGLE_API_KEY,
            'Content-Type: application/json'
        );
        // Open connection
        $ch = curl_init();
 
        // Set the url, number of POST vars, POST data
        curl_setopt($ch, CURLOPT_URL, $url);
 
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 
        // Disabling SSL Certificate support temporarly
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
 
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
 
        // Execute post
        $result = curl_exec($ch);
        if ($result === FALSE) {
            die('Curl failed: ' . curl_error($ch));
        }
 
        // Close connection
        curl_close($ch);
        //echo $result;
    }
	
	private function codificaMsg( $str ) {
		return utf8_encode($str);  //codifica caracteres especiais da msg
	}
	
	public function enviaMsgDevice($msg, $aparelho){		
			$msg = $this->codificaMsg($msg);
			
			$aparelhos = array($aparelho);
			$msg = array("msg" => $msg);
			
			$this->send_notification($aparelhos,$msg);
	}
	
	public function enviaMsgDeviceComChave($msg, $aparelho, $chaveMsg){		
			$msg = $this->codificaMsg($msg);
			
			$aparelhos = array($aparelho);
			$msg = array($chaveMsg => $msg);
			
			$this->send_notification($aparelhos,$msg);
	}
	
	public function enviaMsgDeviceComArray($msgsArrayAssociativo, $aparelhosArray){
			//codifica caracteres especiais do array associativo de msg
			foreach($msgsArrayAssociativo as $chave => $valor){
				$msgsArrayAssociativo[$chave] = $this->codificaMsg($valor);
			}
			
			$aparelhos = $aparelhosArray;
			$msg = $msgsArrayAssociativo;
			
			$this->send_notification($aparelhos,$msg);
	}
	
}
?>