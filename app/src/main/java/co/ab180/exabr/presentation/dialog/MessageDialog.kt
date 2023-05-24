package co.ab180.exabr.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import co.ab180.exabr.R

class MessageDialog : DialogFragment() {

    private var titleColor: Int? = null
    private var title: String? = null

    private lateinit var message: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(context)
        val inflater = activity!!.layoutInflater
        val contentView = inflater.inflate(R.layout.message_dialog, null)

        val txtTitle: TextView = contentView.findViewById(R.id.txtTitle)
        val txtMessage: TextView = contentView.findViewById(R.id.txtMessage)

        // Title
        if (!title.isNullOrEmpty()) {
            txtTitle.text = title
            txtTitle.setTextColor(titleColor!!)
        } else { txtTitle.visibility = View.GONE }
        // Message
        txtMessage.text = message

        return builder
            .setTitle(R.string.message_dialog_title)
            .setView(contentView)
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
    }

    companion object {

        fun show(fm: FragmentManager, message: String) {
            val dialog = MessageDialog()
            dialog.message = message
            dialog.show(fm, MessageDialog::class.java.simpleName)
        }

        fun show(
            fm: FragmentManager,
            @ColorInt titleColor: Int,
            title: String,
            message: String
        ) {
            val dialog = MessageDialog()
            dialog.titleColor = titleColor
            dialog.title = title
            dialog.message = message
            dialog.show(fm, MessageDialog::class.java.simpleName)
        }
    }
}