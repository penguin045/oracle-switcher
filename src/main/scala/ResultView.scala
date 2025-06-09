package app

import com.raquo.laminar.api.L.*

object ResultView:
  def view(state: Signal[AppState]): HtmlElement =
    div(
      h2("Result"),
      ul(
        children <-- state.map(_.selectedCards.map { drawn =>
          li(s"${drawn.card.name} (${drawn.orientation})")
        })
      ),
      p(child.text <-- state.map(_.interpretation))
    )
