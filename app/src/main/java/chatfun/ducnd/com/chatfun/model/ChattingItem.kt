package chatfun.ducnd.com.chatfun.model

class ChattingItem(val senderId: String, val receiverId: String, var content: String?, var linkImage: String?, var receiverAvatar: String?){
    var type = 0
    var hasAvatar=false
}
