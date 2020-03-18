package com.example.taki.emoji_sample.custom_view

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.emoji.widget.EmojiTextViewHelper

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var _emojiTextViewHelper: EmojiTextViewHelper? = null

    private val emojiTextViewHelper: EmojiTextViewHelper
        get() {
            if (_emojiTextViewHelper == null) {
                _emojiTextViewHelper = EmojiTextViewHelper(this)
            }
            return _emojiTextViewHelper as EmojiTextViewHelper
        }

    init {
        emojiTextViewHelper.updateTransformationMethod()
    }

    override fun setFilters(filters: Array<InputFilter>) {
        super.setFilters(emojiTextViewHelper.getFilters(filters))
    }

    override fun setAllCaps(allCaps: Boolean) {
        super.setAllCaps(allCaps)
        emojiTextViewHelper.setAllCaps(allCaps)
    }
}