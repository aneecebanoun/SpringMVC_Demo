package banoun.aneece.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CatalogueService {

	//in production we might use @Repository tier to get the catalogue from Database  
	private static Map<String, List<String>> catalogueRepo;

	static {

		List<String> londonList = new ArrayList<>(Arrays.asList(new String[] { "Arsenal News", "Chelsea News" }));
		List<String> liverpoolList = new ArrayList<>(Arrays.asList(new String[] { "Liverpool News" }));

		catalogueRepo = new HashMap<>();
		catalogueRepo.put("LONDON", londonList);
		catalogueRepo.put("LIVERPOOL", liverpoolList);
	}

	public List<String> getProducts(String locationId) {
		return catalogueRepo.get(locationId);
	}

}
