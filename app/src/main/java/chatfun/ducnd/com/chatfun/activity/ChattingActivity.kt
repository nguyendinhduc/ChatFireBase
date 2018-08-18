package chatfun.ducnd.com.chatfun.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import chatfun.ducnd.com.chatfun.GlideApp
import chatfun.ducnd.com.chatfun.R
import chatfun.ducnd.com.chatfun.adapter.ChattingAdapter
import chatfun.ducnd.com.chatfun.model.ChattingItem
import chatfun.ducnd.com.chatfun.utils.StringUtils
import kotlinx.android.synthetic.main.activity_chatting.*

class ChattingActivity : AppCompatActivity(), ChattingAdapter.IChattingAdapter {

    private lateinit var chattingItems: MutableList<ChattingItem>
    private  var friendAvatar:String? = null
    private  var myAvatar:String? = null
    private  var isOnline=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
        inits()

    }

    private fun inits() {
        val frientId = intent.getStringExtra("FRIEND_ID")
        val friendName = intent.getStringExtra("FRIEND_NAME")
        tv_name.text=friendName
        val senderId = "Test"
        friendAvatar = intent.getStringExtra("FRIEND_AVATAR")
        isOnline = intent.getBooleanExtra("IS_ONLINE", false)
        myAvatar = "https://znews-photo-td.zadn.vn/w860/Uploaded/neg_rtlzofn/2017_01_23/14494601_177404746951l3484_2482115257403382069_n.jpg"
        iv_avatar.isActive=isOnline
        iv_avatar.invalidate()
        if (StringUtils.isBlank(friendAvatar)){
            GlideApp.with(this)
                    .load(R.drawable.ic_person_grey_400_48dp)
                    .placeholder(R.drawable.ic_person_grey_400_48dp)
                    .error(R.drawable.ic_person_grey_400_48dp)
                    .into(iv_avatar)
        }else {
            GlideApp.with(this)
                    .load(friendAvatar)
                    .placeholder(R.drawable.ic_person_grey_400_48dp)
                    .error(R.drawable.ic_person_grey_400_48dp)
                    .into(iv_avatar)
        }


        chattingItems = mutableListOf()
        chattingItems.add(ChattingItem(senderId, frientId, "Hello!!!", null, friendAvatar))
        chattingItems.add(ChattingItem(senderId, frientId, "How was the party", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId, senderId, "Hi!!!", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, null, "http://www.realdetroitweekly.com/wp-content/uploads/2016/12/3D-Party.jpg", friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, null, "http://www.realdetroitweekly.com/wp-content/uploads/2016/12/3D-Party.jpg", friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, "Really good!! " + friendName, null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, "Jedd waw there!", null, friendAvatar))
        chattingItems.add(ChattingItem(senderId , frientId, "Who???", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, "Jedd sumary>3>3>3", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, "In siz from", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, "and he's got a scooter", null, friendAvatar))
        chattingItems.add(ChattingItem(senderId , frientId, "Ok.......! Spike!", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , senderId, "Spike???", null, friendAvatar))
        chattingItems.add(ChattingItem(senderId , frientId, "That 's nickname", null, friendAvatar))

        chattingItems.add(ChattingItem(frientId , senderId, "Is it? You know him", null, friendAvatar))
        chattingItems.add(ChattingItem(senderId , frientId, "No really. He got to Judo with my sister", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , frientId, "No way he's sooo!!!!", null, friendAvatar))
        chattingItems.add(ChattingItem(frientId , frientId, "He waw DJing!!", null, friendAvatar))
        detectChattingItems(senderId, frientId)

        rc_send.layoutManager = LinearLayoutManager(this)
        rc_send.adapter = ChattingAdapter(this)


    }
    
    private fun detectChattingItems(myId:String, frientId:String){
        for (i in chattingItems.size-1 downTo 1){
            val currentItem = chattingItems.get(i)
            val previousItem = chattingItems.get(i-1)
            if(currentItem.senderId.equals(myId)){
                if ( currentItem.content != null ){
                    currentItem.type = ChattingAdapter.TO_TEXT
                }else {
                    currentItem.type = ChattingAdapter.TO_IMG
                }
            }else {
                if ( currentItem.content != null ){
                    currentItem.type = ChattingAdapter.FROM_TEXT
                }else {
                    currentItem.type = ChattingAdapter.FROM_IMG
                }
            }
            currentItem.hasAvatar = !currentItem.senderId.equals(previousItem.senderId)
        }
        chattingItems.get(0).hasAvatar = chattingItems.size >0
    }

    override fun getCount() = chattingItems.size

    override fun getData(position: Int)= chattingItems.get(position)

    override fun onClickItem(position: Int) {

    }

    override fun getAvatarFriend() = friendAvatar

    override fun getMyAvatar()= myAvatar

    override fun isFriendOnline() = isOnline

}
