package chatfun.ducnd.com.chatfun.utils

class StringUtils {
    companion object {
        fun isBlank(content:String?) = content == null || content.equals("")
    }
}