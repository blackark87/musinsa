package exam.goyjin.musinsa.model;

public class RequestObject {


	public record Item(
			String brand,
			String category,
			double price
	) {

	}

}
