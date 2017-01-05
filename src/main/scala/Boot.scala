package org.naasir.archetype

/** The bootstrapper for the app */
object Boot extends App {
  println(EmailAddress.create(Some("bob@gmail.com")));
  println(EmailAddress.create(Some("")));
  println(EmailAddress.create(Some("test")));
  println(EmailAddress("test@test.com"))
  println(EmailAddress("test@test.com").copy())
  // println(EmailAddress("test@test.com").copy(address = "garbage"))
  // println(EmailAddress("test"));
  // println(new EmailAddress("test"));
  println(s"Done.")
}
