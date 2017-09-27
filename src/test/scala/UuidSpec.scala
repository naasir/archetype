package org.naasir.archetype.test

import org.naasir.archetype.Uuid

class UuidSpec extends UnitSpec {
  val VALID_VALUE = "v3cgVh3ETGK7QInjgXo_z-"
  val INVALID_VALUE = "garbage"

  "Uuid" should "not allow construction via 'new'" in {
    "new Uuid()" shouldNot compile
  }

  it should "not allow construction via 'apply' with no arguments" in {
    "Uuid()" shouldNot compile
  }

  it should "allow construction via 'apply' with one arggument" in {
    "Uuid(VALID_VALUE)" should compile
  }

  it should "throw an exception when constructed via 'apply' and argument is invalid" in {
    an [IllegalArgumentException] should be thrownBy Uuid(INVALID_VALUE)
  }

  it should "throw an exception when constructed via 'apply' and argument is empty" in {
    an [IllegalArgumentException] should be thrownBy Uuid("")
  }

  it should "return a valid instance when constructed via 'apply' and argument is valid" in {
    Uuid(VALID_VALUE) shouldBe a [Uuid]
  }

  it should "not allow construction via 'copy' with no args" in {
    "Uuid(VALID_VALUE).copy()" shouldNot compile
  }

  it should "not allow construction via 'copy' with args" in {
    "Uuid(VALID_VALUE).copy(value = INVALID_VALUE)" shouldNot compile
  }

  it should "return a valid instance when constructed via 'create' and argument is valid" in {
    Uuid.create(Some(VALID_VALUE)).isValid shouldBe true
  }

  it should "return a collection of validation errors when constructed via 'create' and argument is invalid" in {
    Uuid.create(Some(INVALID_VALUE)).isValid shouldBe false
  }

  it should "return a collection of validation errors when constructed via 'create' and argument is None" in {
    Uuid.create(None).isValid shouldBe false
  }

  it should "be equal to another instance with the same value" in {
    Uuid(VALID_VALUE) shouldEqual Uuid(VALID_VALUE)
  }

  it should "print as a string" in {
    Uuid(VALID_VALUE).toString shouldEqual VALID_VALUE
  }
}
