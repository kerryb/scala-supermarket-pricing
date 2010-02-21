class SimplePricingRules(prices: Map[String, Int]) extends PricingRules {
  def priceOf(sku: String) = {
    prices(sku)
  }
}
