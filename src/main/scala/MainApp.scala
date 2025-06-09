package app

import org.scalajs.dom
import org.scalajs.dom.document
import com.raquo.laminar.api.L.*

object MainApp:
  private def rootElement: HtmlElement =
    div(
      child <-- StateModel.state.signal.map { state =>
        state.page match
          case AppPage.Input   => InputForm.view
          case AppPage.Result  => ResultView.view(StateModel.state.signal)
          case AppPage.History => HistoryView.view
      }
    )

  def main(args: Array[String]): Unit =
    val mount = Option(document.getElementById("app")).getOrElse {
      val el = document.createElement("div")
      document.body.appendChild(el)
      el
    }
    render(mount, rootElement)

