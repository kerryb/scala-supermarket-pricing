class PriceWithDiscount(basicPrice: Int, discountVolume: Int, discountPrice: Int) extends Price {
  var numberBought = 0
  def price = {
    numberBought = (numberBought + 1) % discountVolume
    if (numberBought == 0) {
      discountPrice - (discountVolume - 1) * basicPrice
    } else {
      basicPrice
    }
  }
}
