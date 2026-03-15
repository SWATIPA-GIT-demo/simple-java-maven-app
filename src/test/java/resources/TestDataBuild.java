package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.Maps;

public class TestDataBuild {
	

	public Maps addPlacePayloadBuild(String name, String address, String language) {
		Location l = new Location();
		Maps maps = new Maps();
		List<String> input = new ArrayList<String>();
		input.add("shoe park");
		input.add("shop");
		l.setLat(-38.383494);
		l.setLng(33.427362);
		maps.setLocation(l);
		maps.setAccuracy(50);
		maps.setAddress(address);
		maps.setPhone_number("(+91) 9148754404");
		maps.setLanguage(language);
		maps.setName(name);
		maps.setWebsite("http://google.com");
		maps.setTypes(input);
		return maps;
	}
}
