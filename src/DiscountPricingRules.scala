class DiscountPricingRules(pricing: Map[String, Price]) extends PricingRules {
  def priceOf(itemCode: String) = {
    pricing(itemCode).price
  }
}
