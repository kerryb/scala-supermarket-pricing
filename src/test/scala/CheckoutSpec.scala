import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class CheckoutSpec extends Spec with ShouldMatchers {
  describe ("Checkout") {
    val PRICING_RULES = new SimplePricingRules
    it ("calculates the price of no items as 0") {
      (new Checkout(PRICING_RULES)).total should equal(0)
    }
  }
}
