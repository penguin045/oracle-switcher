package model

case class TarotCard(
  name: String,
  arcana: String, // "major" or "minor"
  suit: Option[String],
  number: Option[Int],
  meaningUpright: String,
  meaningReversed: String
)

case class DrawnCard(
  card: TarotCard,
  orientation: String // "正位置" or "逆位置"
)

case class AppState(
  userInput: String,
  spread: Int,
  temperature: Double,
  drawMode: String, // "random" | "mixed" | "llm"
  selectedCards: List[DrawnCard],
  interpretation: String,
  tone: String // "logical" | "poetic" | "casual" | "fortune"
)
