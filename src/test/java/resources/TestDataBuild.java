package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayLoad(String name,String language,String address)
	{
		AddPlace p = new AddPlace();
        p.setAccuracy(50);
     // p.setAddress("29, side layout, cohen 09");
        p.setAddress(address);
     // p.setLanguage("");  
        p.setLanguage(language); 
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
    //  p.setName("Frontline house");
        p.setName(name);
        System.out.println("ABC");
        System.out.println("ABC");
        System.out.println("ABC");
        System.out.println("ABC");
        System.out.println("ABC");
        System.out.println("ABC");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        
        System.out.println("Code is working properly--");
        
        System.out.println("Code is working properly--");
        
        System.out.println("Code is working properly--");
        
        System.out.println("Code is working properly--");
        
        System.out.println("Code is working properly from American development team--");
        System.out.println("Code is working properly from American development team--");
        System.out.println("Code is working properly from American development team--");

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
        
        
        
        
	}
	
	
	public String deletePlacePayload(String placeId) 
	{
	    return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}";
	}


}
