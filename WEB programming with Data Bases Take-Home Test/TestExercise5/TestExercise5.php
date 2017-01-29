<html>
<head></head>
<title>TestExercise5</title>
	<center>
	<body>
	    <!--Creation of form with button-->
		
		<form action="TestExercise5.php" method="post">
		
			Get web response from page: </br>
			
			<input type="submit" value="Click here" name="submit">
			
			<!--Creates button called submit.-->
			
		</form>
	<?php
	
		//Creation of function that outputs web response.
		
		function web_response()
		{			
			$url = 'http://ai-robotix.com/qx7/qx7_version.php';			
			$ch = curl_init();
			 //initiating curl resource.
						
			curl_setopt($ch, CURLOPT_URL, $url);
			//Set URL.

			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			//Return the transfer as a string.

			$response = curl_exec($ch);
			//Save the transfer in response.
			
			curl_close($ch);
			//close curl resource.

			echo $response;
			//Output response.
		}		
		if(isset($_POST['submit']))
		{
			web_response();
			//If the button called submit is pressed, it will run the function.
		} 
	?>
	</body>
	</center>
</html>