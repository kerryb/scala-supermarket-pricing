import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class CheckoutSpec extends Spec with ShouldMatchers {
  describe ("Checkout") {
    it ("calculates the price of no items as 0") {
      (new Checkout).total should equal(0)
    }
  }
}
