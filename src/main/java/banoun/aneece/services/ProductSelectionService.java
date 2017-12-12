package banoun.aneece.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import banoun.aneece.exceptions.LocationMatchException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class ProductSelectionService {
	
	@Autowired
	private CustomerLocationService customerLocationService;
	@Autowired
	private CatalogueService catalogueService;
	
	private static List<String> news = new ArrayList<>(Arrays.asList(new String[]{"Political News","Sports News"}));
	
	public Map<String, List<String>> getProducts(String customerId){
		
		Map<String, List<String>> products = new HashMap<>();
		List<String> sports = new ArrayList<>();
		try{
			String locationId = customerLocationService.getLocation(customerId);			
			sports = catalogueService.getProducts(locationId);
		}catch(LocationMatchException lme){
			//L0G
		}
		
		products.put("news", news);
		products.put("sports", sports);
		return products;
	}

}
