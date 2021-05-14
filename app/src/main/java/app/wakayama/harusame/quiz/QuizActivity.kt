package app.wakayama.harusame.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    val quizLists:List<List<String>> = listOf(
        listOf("Androidコースのキャラクターの名前は？","ランディ","フィル","ドロイド","ランディ"),
        listOf("Androidアプリを開発する言語はどれ？","JavaScript","Kotlin","Swift","Kotlin"),
        listOf("ImageViewは、何を扱える要素？","文字","音声","画像","画像")
    )

    val shuffledLists:List<List<String>> = quizLists.shuffled()
    var quizCount:Int = 0
    var correctAnswer:String = ""
    var correctCount:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
//        Log.d("debug",shuffledLists.toString())
        showQuestion()

        answerButton1.setOnClickListener {
            Log.d("debug","answerButton1 is pressed")
            //回答チェック
            checkAnswer(answerButton1.text.toString())
        }
        answerButton2.setOnClickListener {
            Log.d("debug","answerButton2 is pressed")
            //回答チェック
            checkAnswer(answerButton2.text.toString())
        }
        answerButton3.setOnClickListener {
            Log.d("debug","answerButton3 is pressed")
            //回答チェック
            checkAnswer(answerButton3.text.toString())
        }
        nextButton.setOnClickListener {
            Log.d("debug","nextButton is pressed")
            //最後なら
            if (quizCount == quizLists.size){
                Log.d("debug","the last")
                val resultIntent: Intent = Intent(this,ResultActivity::class.java)
                //クイズ数をセット
                resultIntent.putExtra("QuizCount",quizLists.size)
                //正解数をセット
                resultIntent.putExtra("CorrectCount",correctCount)
                //結果画面に移動
                Log.d("debug-count","the count on quiz is $quizCount")
                startActivity(resultIntent)
            }else{
                //最後でなければ進める
                Log.d("debug","next to quiz")
                judgeImage.isVisible = false
                nextButton.isVisible = false
                answerButton1.isVisible = true
                answerButton2.isVisible = true
                answerButton3.isVisible = true
                correntAnswerText.text = ""
                //クイズを表示する
                showQuestion()
            }
        }
        //最後なら
//        if (quizCount == quizLists.size){
//            val resultIntent: Intent = Intent(this,ResultActivity::class.java)
//            //クイズ数をセット
//            resultIntent.putExtra("QuizContent",quizLists.size)
//            //正解数をセット
//            resultIntent.putExtra("CorrectCount",correctCount)
//            //結果画面に移動
//            startActivity(resultIntent)
//        }else{
//            judgeImage.isVisible = false
//            nextButton.isVisible = false
//        }
    }

    fun showQuestion(){
        Log.d("debug","showQuestion is worked")
        Log.d("debug","count is ${quizCount.toString()}")
        val question:List<String> = shuffledLists[quizCount]
//        Log.d("debug",question.toString())
        //質問を表示
        quizText.text = question[0]
        //選択肢表示
        answerButton1.text = question[1]
        answerButton2.text = question[2]
        answerButton3.text = question[3]
        //選択肢ボタンを押せるようにする
        answerButton1.isEnabled = true
        answerButton2.isEnabled = true
        answerButton3.isEnabled = true
        //正解をセット
        correctAnswer = question[4]
    }
    fun checkAnswer(answerText:String){
        Log.d("debug","checkAnswer is worked")
        //正解なら○を表示
        if (answerText == correctAnswer){
            judgeImage.setImageResource(R.drawable.maru_image)
            correctCount++
        }else{//不正解なら☓を表示
            judgeImage.setImageResource(R.drawable.batu_image)
        }
        //答えを見せる関数実行
        showAnswer()
//        quizCount++
    }
    fun showAnswer(){
        quizCount += 1
        Log.d("debug","showAnswer is worked")
        correntAnswerText.text = "正解:$correctAnswer"//正解を表示
        judgeImage.isVisible = true//判定画像を表示
        nextButton.isVisible = true//次へボタンを表示
        //答えられないようにする
        answerButton1.isEnabled = false
        answerButton2.isEnabled = false
        answerButton3.isEnabled = false
    }
}