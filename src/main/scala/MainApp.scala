package app

import org.scalajs.dom
import org.scalajs.dom.document

object MainApp:
  def main(args: Array[String]): Unit =
    val placeholder = document.createElement("div")
    placeholder.textContent = "Oracle Switcher Placeholder"
    document.body.appendChild(placeholder)

