package app.wakayama.harusame.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //クイズ画面からクイズ数を受け取る
        val quizCount:Int = intent.getIntExtra("QuizCount",0)
        Log.d("debug-count","the count on result is $quizCount")
        //クイズ数をTextViewに反映する
        quizCountText.text = "$quizCount 問中..."
        //正解数を受け取る
        val correctCount:Int = intent.getIntExtra("CorrectCount",0)
        //正解数をTextViewに表示する
        correctCountText.text = correctCount.toString()

        againButton.setOnClickListener{
            val quizIntent:Intent = Intent(this,QuizActivity::class.java)
            startActivity(quizIntent)
        }
    }
}