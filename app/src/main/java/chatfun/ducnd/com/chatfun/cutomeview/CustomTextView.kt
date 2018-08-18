package chatfun.ducnd.com.chatfun.cutomeview


import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import chatfun.ducnd.com.chatfun.R

class CustomTextView : AppCompatTextView {


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initFont(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initFont(attrs, 0)
    }

    @SuppressLint("CustomViewStyleable")
    private fun initFont(attr: AttributeSet?, defStyleAttr: Int) {
        if (attr == null) {
            typeface = TextUtils.getFontType(TextUtils.FONT_NAME_NORMAL, context)
        } else {
            val obtain = context.obtainStyledAttributes(attr, R.styleable.CustomText, defStyleAttr, 0)
            val nameTypeFace = obtain.getInt(R.styleable.CustomText_typeFaceText, 0)
            typeface = TextUtils.getFontType(
                    if (nameTypeFace == 0) TextUtils.FONT_NAME_NORMAL else TextUtils.FONT_NAME_BOLD, context)
            obtain.recycle()
        }

    }
}
