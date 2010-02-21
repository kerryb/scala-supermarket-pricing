import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.OneInstancePerTest

class CheckoutSpec extends Spec with ShouldMatchers with OneInstancePerTest {
  describe ("Checkout") {
    def basicPricing(checkout: Checkout) = {
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

      it ("calculates the price of two different items") {
        checkout.scan("A")
        checkout.scan("B")
        checkout.total should equal(80)
      }
    }

    describe ("with simple pricing rules") {
      val rules = new SimplePricingRules(Map("A" -> 50, "B" -> 30))
      val checkout = new Checkout(rules)

      it should behave like basicPricing(checkout)
    }

    describe ("with discount pricing rules") {
      val rules = new DiscountPricingRules(Map("A" -> 50, "B" -> 30))
      val checkout = new Checkout(rules)

      it should behave like basicPricing(checkout)
    }
  }
}
