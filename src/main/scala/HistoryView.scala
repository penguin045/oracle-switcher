package app

import com.raquo.laminar.api.L.*

object HistoryView:
  def view: HtmlElement =
    div(
      h2("History"),
      p("No history yet.")
    )
