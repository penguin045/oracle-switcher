package app

import com.raquo.laminar.api.L.Var

case class TarotCard(
  name: String,
  arcana: String,
  suit: Option[String],
  number: Option[Int],
  meaningUpright: String,
  meaningReversed: String
)

case class DrawnCard(
  card: TarotCard,
  orientation: String
)

enum AppPage:
  case Input, Result, History

case class AppState(
  page: AppPage = AppPage.Input,
  userInput: String = "",
  spread: Int = 3,
  temperature: Double = 0.0,
  drawMode: String = "random",
  selectedCards: List[DrawnCard] = Nil,
  interpretation: String = "",
  tone: String = "logical"
)

object StateModel:
  val state: Var[AppState] = Var(AppState())
