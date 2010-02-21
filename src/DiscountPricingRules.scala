class DiscountPricingRules(prices: Map[String, Int]) extends PricingRules {
  def priceOf(itemCode: String) = {
    prices(itemCode)
  }
}
