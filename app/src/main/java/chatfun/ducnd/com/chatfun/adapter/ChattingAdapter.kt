package chatfun.ducnd.com.chatfun.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chatfun.ducnd.com.chatfun.GlideApp
import chatfun.ducnd.com.chatfun.R
import chatfun.ducnd.com.chatfun.databinding.ItemChatImageFromBinding
import chatfun.ducnd.com.chatfun.databinding.ItemChatImageToBinding
import chatfun.ducnd.com.chatfun.databinding.ItemChatTextFromBinding
import chatfun.ducnd.com.chatfun.databinding.ItemChatTextToBinding
import chatfun.ducnd.com.chatfun.model.ChattingItem
import chatfun.ducnd.com.chatfun.utils.StringUtils
import com.ducnd.statuscircel.CircleStatusImageView

class ChattingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>, View.OnClickListener {
    private val inter: IChattingAdapter

    constructor(inter: IChattingAdapter) {
        this.inter = inter
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        val inflat = LayoutInflater.from(parent.context)
        val viewHolder: ViewDataBinding
        when (type) {
            ChattingAdapter.TO_TEXT -> {
                viewHolder = ItemChatTextToBinding.inflate(inflat, parent, false)
            }
            ChattingAdapter.FROM_TEXT -> {
                viewHolder = ItemChatTextFromBinding.inflate(inflat, parent, false)
            }
            ChattingAdapter.TO_IMG -> {
                viewHolder = ItemChatImageToBinding.inflate(inflat, parent, false)
                viewHolder.ivImage.setOnClickListener(this)
            }
            else -> {
                viewHolder = ItemChatImageFromBinding.inflate(inflat, parent, false)
                viewHolder.ivImage.setOnClickListener(this)
            }
        }
        return ChattingHolder(viewHolder, type, inter)
    }

    override fun getItemCount() = inter.getCount()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chattingItem = inter.getData(position)
        var linkAvar: String? = null
        var imageAvatar: CircleStatusImageView? = null
        when (getItemViewType(position)) {
            ChattingAdapter.TO_TEXT -> {
                val viewHolder = holder as ChattingHolder<ItemChatTextToBinding>
                viewHolder.binding.tvContent.text = chattingItem.content
                linkAvar = inter.getMyAvatar()
                imageAvatar = viewHolder.binding.ivAvatar
            }
            ChattingAdapter.FROM_TEXT -> {
                val viewHolder = holder as ChattingHolder<ItemChatTextFromBinding>
                viewHolder.binding.tvContent.text = chattingItem.content
                linkAvar = inter.getAvatarFriend()
                imageAvatar = viewHolder.binding.ivAvatar

            }
            ChattingAdapter.TO_IMG -> {
                val viewHolder = holder as ChattingHolder<ItemChatImageToBinding>
                linkAvar = inter.getMyAvatar()
                imageAvatar = viewHolder.binding.ivAvatar
                if (StringUtils.isBlank(chattingItem.linkImage)) {
                    GlideApp.with(holder.itemView.context)
                            .load(R.drawable.ao_dai)
                            .placeholder(R.drawable.ic_person_grey_400_48dp)
                            .error(R.drawable.ic_person_grey_400_48dp)
                            .into(viewHolder.binding.ivImage)
                } else {
                    GlideApp.with(holder.itemView.context)
                            .load(chattingItem.linkImage)
                            .placeholder(R.drawable.ic_person_grey_400_48dp)
                            .error(R.drawable.ic_person_grey_400_48dp)
                            .into(viewHolder.binding.ivImage)
                }
            }
            ChattingAdapter.FROM_IMG -> {
                val viewHolder = holder as ChattingHolder<ItemChatImageFromBinding>
                imageAvatar = viewHolder.binding.ivAvatar
                linkAvar = inter.getAvatarFriend()
                if (StringUtils.isBlank(chattingItem.linkImage)) {
                    GlideApp.with(holder.itemView.context)
                            .load(R.drawable.ao_dai)
                            .placeholder(R.drawable.ic_person_grey_400_48dp)
                            .error(R.drawable.ic_person_grey_400_48dp)
                            .into(viewHolder.binding.ivImage)
                } else {
                    GlideApp.with(holder.itemView.context)
                            .load(chattingItem.linkImage)
                            .placeholder(R.drawable.ic_person_grey_400_48dp)
                            .error(R.drawable.ic_person_grey_400_48dp)
                            .into(viewHolder.binding.ivImage)
                }
            }
            else -> {

            }
        }

        if (imageAvatar != null) {
            if (chattingItem.hasAvatar) {
                imageAvatar.visibility = View.VISIBLE
                if (StringUtils.isBlank(linkAvar)) {
                    GlideApp.with(holder.itemView.context)
                            .load(R.drawable.ic_person_grey_400_48dp)
                            .placeholder(R.drawable.ic_person_grey_400_48dp)
                            .error(R.drawable.ic_person_grey_400_48dp)
                            .into(imageAvatar)
                } else {
                    GlideApp.with(holder.itemView.context)
                            .load(linkAvar)
                            .placeholder(R.drawable.ic_person_grey_400_48dp)
                            .error(R.drawable.ic_person_grey_400_48dp)
                            .into(imageAvatar)
                }
                if (imageAvatar.isActive != inter.isFriendOnline()) {
                    imageAvatar.isActive = inter.isFriendOnline()
                    imageAvatar.invalidate()
                }

            } else {
                imageAvatar.visibility = View.INVISIBLE
            }
        }

    }

    override fun getItemViewType(position: Int) = inter.getData(position).type

    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_image -> {
                val position = (view.tag as () -> Int)()
                inter.onClickImage(position, view)
            }
            else -> {

            }
        }
    }

    interface IChattingAdapter {
        fun getCount(): Int
        fun getData(position: Int): ChattingItem
        fun onClickItem(position: Int)
        fun getAvatarFriend(): String?
        fun getMyAvatar(): String?
        fun isFriendOnline(): Boolean
        fun onClickImage(position: Int, viewClick: View)
    }

    companion object {
        const val TO_TEXT = 0
        const val FROM_TEXT = 1
        const val TO_IMG = 2
        const val FROM_IMG = 3

        class ChattingHolder<Bi : ViewDataBinding>(val binding: Bi, val type: Int, inter: IChattingAdapter) : RecyclerView.ViewHolder(binding.root) {
            init {
                if (binding is ItemChatImageFromBinding) {
                    (binding as ItemChatImageFromBinding).ivImage.setOnClickListener {
                        inter.onClickImage(adapterPosition, it)
                    }
                } else {
                    if (binding is ItemChatImageToBinding) {
                        (binding as ItemChatImageToBinding).ivImage.setOnClickListener {
                            inter.onClickImage(adapterPosition, it)
                        }
                    }
                }
            }
        }
    }
}