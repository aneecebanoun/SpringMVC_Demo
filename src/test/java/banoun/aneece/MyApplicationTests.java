package banoun.aneece;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import banoun.aneece.exceptions.LocationMatchException;
import banoun.aneece.services.ProductSelectionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyApplicationTests {

	@Autowired
	private ProductSelectionService productSelectionService;
	
	@Test(expected= LocationMatchException.class)
	public void noProductsAvailableTestCase() {
		Map<String, List<String>> products = productSelectionService.getProducts("3");
		assertTrue(products.get("news").size() > 0);
		if(products.get("sports").isEmpty()){
			throw new LocationMatchException();
		}
	}
	
	@Test
	public void productsAvailableTestCase() {
		Map<String, List<String>> products = productSelectionService.getProducts("1");
		assertTrue(products.get("sports").size() > 0);
		assertTrue(products.get("news").size() > 0);
	}

}
