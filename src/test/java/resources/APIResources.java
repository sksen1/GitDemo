package resources;
//Enum is Special class in java Which has Collection of constants or Methods

public enum APIResources {

  AddPlaceAPI("/maps/api/place/add/json"),
 getPlaceAPI("/maps/api/place/get/json"),
 deletePlaceAPI("maps/api/place/delete/json");
	private String resources;

APIResources(String resource) {
	
	this.resources=resource;
}

public String getResources()
{
	return resources;
}

}
