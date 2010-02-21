import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.OneInstancePerTest

class CheckoutSpec extends Spec with ShouldMatchers with OneInstancePerTest {
  describe ("Checkout") {
    describe ("with simple pricing rules") {
      val PRICING_RULES = new SimplePricingRules
      val checkout = new Checkout(PRICING_RULES)

      it ("calculates the price of no items as 0") {
        checkout.total should equal(0)
      }

      it ("calculates the price of a single item") {
        checkout.scan("A")
        checkout.total should equal(50)
      }

      it ("calculates the price of two identical items") {
        checkout.scan("A")
        checkout.scan("A")
        checkout.total should equal(100)
      }
    }
  }
}
