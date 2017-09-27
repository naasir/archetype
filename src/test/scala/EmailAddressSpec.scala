package org.naasir.archetype.test

import org.naasir.archetype.EmailAddress

class EmailAddressSpec extends UnitSpec {
  val VALID_VALUE = "test@test.com"
  val INVALID_VALUE = "garbage"

  "EmailAddress" should "not allow construction via 'new'" in {
    "new EmailAddress(VALID_VALUE)" shouldNot compile
  }

  it should "not allow construction via 'apply' with no arguments" in {
    "EmailAddress()" shouldNot compile
  }

  it should "allow construction via 'apply' with one arggument" in {
    "EmailAddress(VALID_VALUE)" should compile
  }

  it should "throw an exception when constructed via 'apply' and argument is invalid" in {
    an [IllegalArgumentException] should be thrownBy EmailAddress(INVALID_VALUE)
  }

  it should "throw an exception when constructed via 'apply' and argument is empty" in {
    an [IllegalArgumentException] should be thrownBy EmailAddress("")
  }

  it should "return a valid instance when constructed via 'apply' and argument is valid" in {
    EmailAddress(VALID_VALUE) shouldBe a [EmailAddress]
  }

  it should "not allow construction via 'copy' with no args" in {
    "EmailAddress(VALID_VALUE).copy()" shouldNot compile
  }

  it should "not allow construction via 'copy' with args" in {
    "EmailAddress(VALID_VALUE).copy(value = INVALID_VALUE)" shouldNot compile
  }

  it should "return a valid instance when constructed via 'create' and argument is valid" in {
    EmailAddress.create(Some(VALID_VALUE)).isValid shouldBe true
  }

  it should "return a collection of validation errors when constructed via 'create' and argument is invalid" in {
    EmailAddress.create(Some(INVALID_VALUE)).isValid shouldBe false
  }

  it should "return a collection of validation errors when constructed via 'create' and argument is None" in {
    EmailAddress.create(None).isValid shouldBe false
  }

  it should "be equal to another instance with the same address" in {
    EmailAddress(VALID_VALUE) shouldEqual EmailAddress(VALID_VALUE)
  }

  it should "print as a string" in {
    EmailAddress(VALID_VALUE).toString shouldEqual VALID_VALUE
  }
}
