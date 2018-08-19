package chatfun.ducnd.com.chatfun.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import chatfun.ducnd.com.chatfun.GlideApp
import chatfun.ducnd.com.chatfun.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        inits()
    }

    private fun inits() {
        val linkImage = intent.getStringExtra("ULR_IMAGE");
        GlideApp.with(this)
                .load(linkImage)
                .placeholder(R.drawable.ao_dai)
                .error(R.drawable.ao_dai)
                .into(iv_image)

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}