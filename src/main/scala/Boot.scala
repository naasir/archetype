package org.naasir.archetype

/** The bootstrapper for the app */
object Boot extends App {
  println(EmailAddress.create(Some("bob@gmail.com")));
  println(EmailAddress.create(Some("test")));
  println(Uuid.create(Some("v3cgVh3ETGK7QInjgXo_z-")))
  println(Uuid.create(Some("test")))
  println("Done.")
}
