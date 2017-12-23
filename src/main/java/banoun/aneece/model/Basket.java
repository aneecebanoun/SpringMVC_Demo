package banoun.aneece.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<String> sportsChecks = new ArrayList<>();
	private List<String> newsChecks = new ArrayList<>();

	public List<String> getSportsChecks() {
		return sportsChecks;
	}

	public void setSportsChecks(List<String> sportsChecks) {
		this.sportsChecks = sportsChecks;
	}

	public List<String> getNewsChecks() {
		return newsChecks;
	}

	public 	void setNewsChecks(List<String> newsChecks) {
		this.newsChecks = newsChecks;
	}

}
