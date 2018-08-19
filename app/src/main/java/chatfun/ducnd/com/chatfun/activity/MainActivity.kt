package chatfun.ducnd.com.chatfun.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import chatfun.ducnd.com.chatfun.R
import chatfun.ducnd.com.chatfun.adapter.FriendAdapter
import chatfun.ducnd.com.chatfun.model.Friend
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), FriendAdapter.IFriendAdapter {
    private lateinit var friends: MutableList<Friend>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inits()
    }

    private fun inits() {
        friends = mutableListOf()
        val date = Date()
        friends.add(Friend("test1", "AMELIA", "https://ss-images.catscdn.vn/2016/07/09/609399/mantien-1.jpg", "Hello OLIVIA!!!", date, true))
        friends.add(Friend("test1", "OLIVIA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwnUvV3LgR64AzpPaz6-8t8BfH-PWYDTTI_NZluFTDV-gHqEdG", "Hello AMELIA!!!",
                Date(date.time - 1 * 60L * 60 * 1000), true))
        friends.add(Friend("test1", "EMILY", "http://2sao.vietnamnetjsc.vn/images/2017/09/03/06/48/hot-girl-3.jpg", "Hello AMELIA!!!",
                Date(date.time - 2 * 60L * 60 * 1000), false))
        friends.add(Friend("test1", "AVA", "https://znews-photo-td.zadn.vn/w660/Uploaded/kcwvouvs/2017_09_09/15673055_1851226515148889_4807245177765751719_n.jpg", "How old are yout?",
                Date(date.time - 5 * 60L * 60 * 1000), false))
        friends.add(Friend("test1", "ISLA", "http://media.game8.vn/Media/files/hotgirl-tra-sua-cuc-hoa-mi.jpg", "Hello AMELIA!!!",
                Date(date.time - 8 * 60L * 60 * 1000), true))
        friends.add(Friend("test1", "FRANCESCA", "http://st.phunuonline.com.vn/staticFile/Subject/2018/01/03/hotgirl-co-luong-fan-khung-tren-mang-xa-hoi_2_3110304.jpg", "Hello Maria, can i meet you?",
                Date(date.time - 9 * 60L * 60 * 1000), true))
        friends.add(Friend("test1", "JULIA", "https://media.foody.vn/images/hot-girl-tra-sua-dep-nhu-nang-mai-tai-da-lat-hinh-5.jpg", "Hello AMELIA!!!",
                Date(date.time - 10 * 60L * 60 * 1000), false))
        friends.add(Friend("test1", "MARIA", "https://ss-images.catscdn.vn/w620/2017/10/14/1672399/ban-trai-moi-cua-hot-girl-salim-dep-nhu-soai-ca-khien-fan-xon-xang-1645095.jpg", "Im fine, and you?",
                Date(date.time - 13 * 60L * 60 * 1000), true))
        friends.add(Friend("test1", "MADISON", "http://havanaspa.vn/images/nguyen-thanh-tu-hotgirl-4.jpg", "Can you talk with me one munite?",
                Date(date.time - 17 * 60L * 60 * 1000), true))
        friends.add(Friend("test1", "MADDISON", "https://image.thanhnien.vn/1600/uploaded/congthang/2017_04_12/hinh4_nrvm.jpg", "Hello AMELIA!!!",
                Date(date.time - 18 * 60L * 60 * 1000), false))
        friends.add(Friend("test1", "VICTORIA", "https://baomoi-photo-1-td.zadn.vn/w700_r1/18/02/21/105/25003846/1_119996.jpg", "Yes!!!",
                Date(date.time - 32 * 60L * 60 * 1000), true))
        friends.add(Friend("test1", "MUHAMMAD", "https://zing4u.vn/image/files/5-2014/Zing4u.Vn-Hinh-Nen-Hot-Girl-1.jpg", "Yes!!!",
                Date(date.time - 37 * 60L * 60 * 1000), true))
        friends.add(Friend("test1", "SAMUEL", "https://genknews.genkcdn.vn/k:2015/11-1441712323241/hot-girl-thich-choi-dota-2-gay-sot-cong-dong-game-thu-viet.jpg", "No!",
                Date(date.time - 50 * 60L * 60 * 1000), true))

        rc_friend.layoutManager = LinearLayoutManager(this)
        rc_friend.adapter = FriendAdapter(this)

        refresh.setOnRefreshListener {
            refresh.isRefreshing=false
        }
        btn_back.setOnClickListener{
            onBackPressed()
        }
    }

    override fun getCount() = friends.size

    override fun getData(position: Int) = friends.get(position)

    override fun onClick(position: Int) {
        val friend = friends.get(position)
        val intent = Intent()
        intent.putExtra("FRIEND_ID", friend.id)
        intent.putExtra("FRIEND_NAME", friend.name)
        intent.putExtra("FRIEND_AVATAR", friend.avatar)
        intent.putExtra("IS_ONLINE", friend.isOnline)
        intent.setClass(this, ChattingActivity::class.java)
        startActivity(intent)
    }
}
