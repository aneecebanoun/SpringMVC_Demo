package banoun.aneece.controllers;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import banoun.aneece.model.Basket;
import banoun.aneece.services.ProductSelectionService;
import banoun.aneece.util.TradeReportingUtility;


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
	
	@RequestMapping(value="/stockReporting", method = { RequestMethod.GET, RequestMethod.POST })
	public String stockReporting(Model model, final HttpServletResponse response, @RequestParam(value="sortingOption", required = false) String sortingOption){
		response.setHeader("Cache-Control", "no-cache");
		model.addAttribute("stockReporting", TradeReportingUtility.runTradeReporting(sortingOption));
		return "stockReporting";
	}
	
	@RequestMapping("/stockFilteredReporting/{filter}")
	public String stockFilteredReporting(Model model, @PathVariable("filter") String filter, final HttpServletResponse response){
		response.setHeader("Cache-Control", "no-cache");
		model.addAttribute("stockFilteredReporting", TradeReportingUtility.runTradeFilteredReporting(filter));
		return "stockFilteredReporting";
	}

}
