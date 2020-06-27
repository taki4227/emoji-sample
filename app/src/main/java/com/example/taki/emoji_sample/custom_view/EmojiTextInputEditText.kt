package com.example.taki.emoji_sample.custom_view

import android.content.Context
import android.text.method.KeyListener
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.annotation.IntRange
import androidx.emoji.widget.EmojiEditTextHelper
import com.google.android.material.textfield.TextInputEditText
import com.example.taki.emoji_sample.R

class EmojiTextInputEditText : TextInputEditText {

    private var _emojiEditTextHelper: EmojiEditTextHelper? = null

    private val emojiEditTextHelper: EmojiEditTextHelper
        get() {
            if (_emojiEditTextHelper == null) {
                _emojiEditTextHelper = EmojiEditTextHelper(this)
            }
            return _emojiEditTextHelper as EmojiEditTextHelper
        }

    constructor(context: Context) : super(context) {
        init(null /*attrs*/, 0 /*defStyleAttr*/)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, R.attr.editTextStyle)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {
        val attrHelper = EditTextAttributeHelper(this, attrs, defStyleAttr, 0)
        setMaxEmojiCount(attrHelper.maxEmojiCount)
        keyListener = super.getKeyListener()
    }

    override fun setKeyListener(input: KeyListener?) {
        var keyListener: KeyListener? = null
        if (input != null) {
            keyListener = emojiEditTextHelper.getKeyListener(input)
        }
        super.setKeyListener(keyListener)
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        val inputConnection = super.onCreateInputConnection(outAttrs)
        return emojiEditTextHelper.onCreateInputConnection(
            inputConnection,
            outAttrs
        ) as InputConnection
    }

    fun setMaxEmojiCount(@IntRange(from = 0) maxEmojiCount: Int) {
        emojiEditTextHelper.maxEmojiCount = maxEmojiCount
    }

    fun getMaxEmojiCount(): Int = emojiEditTextHelper.maxEmojiCount
}

private class EditTextAttributeHelper(
    view: View, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
) {
    var maxEmojiCount = 0
        private set

    init {
        if (attrs != null) {
            val context = view.context
            val styledAttribute = context.obtainStyledAttributes(
                attrs, R.styleable.EmojiTextInputEditText,
                defStyleAttr, defStyleRes
            )
            maxEmojiCount = styledAttribute.getInteger(
                R.styleable.EmojiTextInputEditText_maxEmojiCount,
                MAX_EMOJI_COUNT
            )
            styledAttribute.recycle()
        }
    }

    companion object {
        const val MAX_EMOJI_COUNT = Int.MAX_VALUE
    }
}
