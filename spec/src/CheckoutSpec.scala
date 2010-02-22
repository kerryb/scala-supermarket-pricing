import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.OneInstancePerTest

class CheckoutSpec extends Spec with ShouldMatchers with OneInstancePerTest {
  describe ("Checkout") {
    def totalPrice(checkout: Checkout, items: String) = {
      items.toArray.foreach(c => checkout.scan(c.toString))
      checkout.total
    }

    def basicPricing(checkout: Checkout) = {
      it ("calculates the price of no items as 0") {
        totalPrice(checkout, "") should equal(0)
      }

      it ("calculates the price of a single item") {
        totalPrice(checkout, "A") should equal(50)
      }

      it ("calculates the price of two identical items") {
        totalPrice(checkout, "AA") should equal(100)
      }

      it ("calculates the price of two different items") {
        totalPrice(checkout, "AB") should equal(80)
      }
    }

    describe ("with simple pricing rules") {
      val rules = new SimplePricingRules(Map("A" -> 50, "B" -> 30))
      val checkout = new Checkout(rules)

      it should behave like basicPricing(checkout)
    }

    describe ("with discount pricing rules") {
      val rules = new DiscountPricingRules(Map(
        "A" -> new PriceWithDiscount(50, 3, 130),
        "B" -> new PriceWithDiscount(30, 2, 45),
        "C" -> new SimplePrice(20)
      ))
      val checkout = new Checkout(rules)

      it should behave like basicPricing(checkout)

      it ("applies discount when buying a specified number of an item") {
        totalPrice(checkout, "AAA") should equal(130)
      }

      it ("reverts to the normal price after the discount volume") {
        totalPrice(checkout, "AAAA") should equal(180)
      }

      it ("reapplies the discount for multiples of the discount volume") {
        totalPrice(checkout, "AAAAAA") should equal(260)
      }

      it ("calculates the price for a variety of items, with and without discounts") {
        totalPrice(checkout, "ACABABA") should equal(245)
      }
    }
  }
}
