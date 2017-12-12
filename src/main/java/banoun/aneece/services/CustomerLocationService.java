package banoun.aneece.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import banoun.aneece.exceptions.LocationMatchException;

@Service
public class CustomerLocationService {
	
	//in production we might use @Repository tier to get match customer location from Database
	private static Map<String, String> customerLocationRepo;
	
	static{
		customerLocationRepo = new HashMap<>();
		customerLocationRepo.put("1", "LONDON");
		customerLocationRepo.put("2", "LIVERPOOL");
	}
	
	public String getLocation(String customerId) throws LocationMatchException{
		String locationId = customerLocationRepo.get(customerId);
		if(locationId == null){
			throw new LocationMatchException();
		}
		return locationId;
		
	}

}
