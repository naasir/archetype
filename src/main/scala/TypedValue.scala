package org.naasir.archetype

/** Adds the default toString implemention to a value type
  */
trait TypedValue[T] extends Any {
  def value: T
  override def toString = value.toString
}
