Webタロット占いアプリ仕様書（Scala + Scala.js 構成）

アプリ名（仮）

Oracle Switcher

⸻

1. 概要

本アプリは Scala + Scala.js を用いて構築されるWebベースの占いアプリケーションです。ユーザーの相談内容をもとに、“温度感”（真剣度・情動的強度）をLLMで解析し、タロットカードの抽選方式を自動切替。カードはランダム・演出補正・完全演出（LLM選定）の3モードから選出されます。

LLMとの連携は OpenAI API 互換形式に準拠し、開発中は LM Studio を使用可能とします。

⸻

2. 技術スタック

区分	技術構成
言語	Scala 3
フロントエンド	Scala.js + Laminar / Outwatch 等
スタイリング	TailwindCSS or Custom CSS
バックエンド	Optional（JS側で完結可）
LLM連携	OpenAI API / LM Studio Proxy
デプロイ	GitHub Pages / Netlify / Vercel


⸻

3. 処理フロー

[相談入力] → [LLMで温度感分析] → [抽選モード決定] → [カード選定] → [LLMで解釈生成] → [表示/共有]


⸻

4. コンポーネント構成（Laminar構想）
	•	MainApp.scala : エントリーポイント（router含）
	•	InputForm.scala : ユーザー入力（相談文／スプレッド／文体）
	•	ResultView.scala : カード・リーディング結果表示
	•	HistoryView.scala : 過去結果（任意）
	•	StateModel.scala : アプリ状態管理（Var/AppState）
	•	ApiClient.scala : LLM呼び出し処理（OpenAI互換）

⸻

5. データ定義（Scala）

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


⸻

6. LLM連携仕様

6.1 温度感解析（/api/analyzeTone）
	•	入力：相談文（text）
	•	出力：スコア（0.0〜1.0）
	•	プロンプト例：

以下の文章の感情温度（Emotion Intensity）を0.0〜1.0で評価してください：
"{相談内容}"



6.2 カード抽選（/api/drawCards）
	•	モード：llm の場合、LLMがカードと正逆を選出
	•	出力：DrawnCard の配列
	•	プロンプト例：

以下の相談に対して、{n}枚のタロットカードを選び、それぞれの正位置・逆位置を指定してください：
"{相談内容}"



6.3 解釈生成（/api/interpret）
	•	入力：カードリスト＋相談文＋文体
	•	出力：解釈文（自然言語）
	•	プロンプト例：

以下のカードと相談内容に基づき、{tone}の文体で解釈を行ってください：
カード: [...]
相談: "{text}"



⸻

7. カード抽選ロジック（Scala側）

スコア	モード	処理
0.0〜0.4	random	scala.util.Random.shuffle(deck).take(n)
0.4〜0.7	mixed	上記＋補正：重複カード除外、出現率手動制御
0.7〜1.0	llm	LLMが選出

補正ロジックは重み付き抽選で実装（Map[Card, Weight] → WeightedShuffle）

⸻

8. 実装制約・補足
	•	すべてのLLM呼び出しは OpenAI API互換形式（chat/completions）で実装すること
	•	ローカル検証時は LM Studio（http://localhost:1234/v1/chat/completions）を利用
	•	APIキーは外部設定ファイルまたはブラウザ保存方式で切替
	•	カードデータは JSON で静的読み込み可（resources/tarot.json）
	•	状態は Var[AppState] で全体管理、各Viewは SubSignal で反映

⸻

この構成をもとに、Scala + Scala.js 環境で全体を実装可能とする。
