package stepdefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException
	{
		
		//Write a code that will give us place-id
		//Execute the code than when Place_id is null
		StepDefination m = new StepDefination();
		//Place_id is static that why called it with class name place_id
		if(StepDefination.place_id==null)
		{
		m.add_place_payload("Shetty","French","Asia");
		m.user_calls_with_http_request("AddPlaceAPI","POST");
		m.verify_place_id_created_maps_to_using("Shetty","getPlaceAPI");
	   }

	}
}
