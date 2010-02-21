class Checkout(pricingRules: PricingRules) {
  var total = 0

  def scan(sku: String) {
    total += pricingRules.priceOf(sku)
  }
}
