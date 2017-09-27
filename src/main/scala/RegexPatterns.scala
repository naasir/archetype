package org.naasir.archetype

object RegexPatterns {

  val email = """(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?""".r

  val base64UrlSafeString = """[0-9a-zA-Z_-]+""".r
}
