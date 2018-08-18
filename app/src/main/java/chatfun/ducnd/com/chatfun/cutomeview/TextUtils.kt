package chatfun.ducnd.com.chatfun.cutomeview

import android.content.Context
import android.graphics.Typeface
import java.util.*

class TextUtils {
    companion object {
        const val FONT_NAME_NORMAL = "normal.ttf"
        const val FONT_NAME_BOLD = "bold.ttf"
        val fontSet = Collections.synchronizedMap(HashMap<String, Typeface>())
        fun getFontType(name: String, context: Context): Typeface {
            var typeF = fontSet.get(name)
            if (typeF == null) {
                typeF = Typeface.createFromAsset(context.assets, name)
                fontSet.put(name, typeF)
            }
            return typeF!!
        }
    }
}
