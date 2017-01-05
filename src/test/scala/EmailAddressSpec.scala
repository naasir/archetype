package org.naasir.archetype.test

import org.naasir.archetype.EmailAddress

class EmailAddressSpec extends UnitSpec {

  "EmailAddress" should "not allow construction via 'new'" in {
    "new EmailAddress(\"test@test.com\")" shouldNot compile
  }

  it should "not allow construction via 'apply' with no args" in {
    "EmailAddress()" shouldNot compile
  }

  it should "allow construction via 'apply' with one arg" in {
    "EmailAddress(\"test@test.com\")" should compile
  }

  it should "throw an exception when constructed via 'apply' and email is invalid" in {
    an [IllegalArgumentException] should be thrownBy EmailAddress("garbage")
  }

  it should "throw an exception when constructed via 'apply' and email is empty" in {
    an [IllegalArgumentException] should be thrownBy EmailAddress("")
  }

  it should "return a valid instance when constructed via 'apply' and email is valid" in {
    EmailAddress("test@test.com") shouldBe a [EmailAddress]
  }

  it should "allow construction via 'copy' with no args" in {
    "EmailAddress(\"test@test.com\").copy()" should compile
  }

  it should "not allow construction via 'copy' with args" in {
    "EmailAddress(\"test@test.com\").copy(address = \"test\")" shouldNot compile
  }

  it should "return a valid instance when constructed via 'create' and email is valid" in {
    EmailAddress.create(Some("test@test.com")).isValid shouldBe true
  }

  it should "return a collection of validation errors when constructed via 'create' and email is invalid" in {
    EmailAddress.create(Some("garbage")).isValid shouldBe false
  }

  it should "return a collection of validation errors when constructed via 'create' and email is None" in {
    EmailAddress.create(None).isValid shouldBe false
  }
}
