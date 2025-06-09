package app

import com.raquo.laminar.api.L.*

object InputForm:
  def view: HtmlElement =
    val questionVar = Var("")
    val spreadVar = Var(3)
    val toneVar = Var("logical")

    div(
      h2("Question"),
      textarea(
        placeholder := "Enter your question",
        onInput.mapToValue --> questionVar
      ),
      label("Spread"),
      input(
        typ := "number",
        value := "3",
        onInput.mapToValue.map(_.toIntOption.getOrElse(3)) --> spreadVar
      ),
      label("Tone"),
      select(
        onChange.mapToValue --> toneVar,
        option(value := "logical", "Logical"),
        option(value := "poetic", "Poetic"),
        option(value := "casual", "Casual"),
        option(value := "fortune", "Fortune")
      ),
      button(
        "Submit",
        onClick.mapTo(()) --> { _ =>
          StateModel.state.update(_.copy(
            page = AppPage.Result,
            userInput = questionVar.now(),
            spread = spreadVar.now(),
            tone = toneVar.now()
          ))
        }
      )
    )
