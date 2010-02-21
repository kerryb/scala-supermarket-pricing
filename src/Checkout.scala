class Checkout(pricingRules: PricingRules) {
  var total = 0

  def scan(itemCode: String) {
    total += pricingRules.priceOf(itemCode)
  }
}
