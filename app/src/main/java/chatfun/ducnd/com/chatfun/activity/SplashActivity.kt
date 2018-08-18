package chatfun.ducnd.com.chatfun.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import chatfun.ducnd.com.chatfun.R

class SplashActivity : AppCompatActivity() {
    private var isDestroy = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        isDestroy = false
        Handler().postDelayed({
            if (isDestroy) {
                return@postDelayed
            }
            val intent = Intent()
            intent.setClass(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    override fun onDestroy() {
        isDestroy = true
        super.onDestroy()
    }
}
