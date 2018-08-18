package chatfun.ducnd.com.chatfun.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chatfun.ducnd.com.chatfun.GlideApp
import chatfun.ducnd.com.chatfun.R
import chatfun.ducnd.com.chatfun.databinding.ItemFriendBinding
import chatfun.ducnd.com.chatfun.model.Friend
import java.text.SimpleDateFormat

@Suppress("UNCHECKED_CAST")
class FriendAdapter : RecyclerView.Adapter<FriendAdapter.Companion.FriendViewHolder>, View.OnClickListener {


    private val inter: IFriendAdapter
    private val formatDate: SimpleDateFormat

    constructor(inter: IFriendAdapter) {
        this.inter = inter
        formatDate = SimpleDateFormat("HH:mm aa")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val viewHolder = FriendViewHolder(ItemFriendBinding.inflate(layoutInflate, parent, false))
        viewHolder.binding.root.setOnClickListener(this)
        return viewHolder
    }

    override fun getItemCount() = inter.getCount()

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = inter.getData(position)
        holder.binding.tvName.text = friend.name
        holder.binding.tvLastMessage.text = friend.lastMessage
        if (friend.dateLastMessage == null) {
            holder.binding.tvDateLastMessage.text = ""
        } else {
            holder.binding.tvDateLastMessage.text = formatDate.format(friend.dateLastMessage)
        }
        if (holder.binding.ivAvatar.isActive != friend.isOnline) {
            holder.binding.ivAvatar.isActive = friend.isOnline
            holder.binding.ivAvatar.invalidate()
        }

        if (friend.avatar != null) {
            GlideApp.with(holder.itemView.context)
                    .load(friend.avatar)
                    .placeholder(R.drawable.ao_dai)
                    .error(R.drawable.ao_dai)
                    .into(holder.binding.ivAvatar)
        } else {
            GlideApp.with(holder.itemView.context)
                    .load(R.drawable.ao_dai)
                    .placeholder(R.drawable.ao_dai)
                    .error(R.drawable.ao_dai)
                    .into(holder.binding.ivAvatar)
        }

    }

    override fun onClick(view: View) {
        val position = (view.tag as () -> Int)()
        inter.onClick(position)
    }

    interface IFriendAdapter {
        fun getCount(): Int
        fun getData(position: Int): Friend
        fun onClick(position: Int)
    }

    companion object {
        class FriendViewHolder : RecyclerView.ViewHolder {
            val binding: ItemFriendBinding

            constructor(binding: ItemFriendBinding) : super(binding.root) {
                this.binding = binding
                binding.root.tag = fun() = adapterPosition

            }
        }
    }
}