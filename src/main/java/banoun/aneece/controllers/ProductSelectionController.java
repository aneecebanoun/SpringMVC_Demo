package banoun.aneece.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import banoun.aneece.model.Basket;
import banoun.aneece.services.ProductSelectionService;


@Controller
public class ProductSelectionController {
	
	@Autowired
	ProductSelectionService productSelectionService;
	
	@RequestMapping(value="/confirmation", method = RequestMethod.POST)
	public String confirmation(Model model, Basket basket){
		model.addAttribute("basket", basket);
		return "confirmation";
	}
	
	@RequestMapping({"/productSelection/{customerId}"})
	public String productSelection(Model model, @PathVariable("customerId") String customerId){
		
		model.addAttribute("newsChecks",productSelectionService.getProducts(customerId).get("news"));
		model.addAttribute("sportsChecks",productSelectionService.getProducts(customerId).get("sports"));
		Basket basket = new Basket();
		model.addAttribute("basket",basket);
		return "productSelectionView";
		
	} 

}
