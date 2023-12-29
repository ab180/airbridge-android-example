package co.ab180.exabr.presentation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import co.ab180.exabr.R;

public class MessageDialog extends DialogFragment {

    private Integer titleColor = null;
    private String title = null;

    private String message = null;

    MessageDialog() {
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View contentView = inflater.inflate(R.layout.message_dialog, null);

        TextView txtTitle = contentView.findViewById(R.id.txtTitle);
        TextView txtMessage = contentView.findViewById(R.id.txtMessage);

        if (title != null && !title.isEmpty()) {
            txtTitle.setText(title);
            if (titleColor != null) {
                txtTitle.setTextColor(titleColor);
            }
        } else {
            txtTitle.setVisibility(View.GONE);
        }
        if (message != null) {
            // Message
            txtMessage.setText(message);
        }

        return builder
                .setTitle(R.string.message_dialog_title)
                .setView(contentView)
                .setNegativeButton(
                        R.string.cancel,
                        (dialogInterface, i) -> {
                        })
                .create();
    }

    @Override
    public void onDestroyView() {
        Dialog dialog = getDialog();
        // handles https://code.google.com/p/android/issues/detail?id=17423
        if (dialog != null && getRetainInstance()) {
            dialog.setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public static void show(
            @NonNull FragmentManager fm,
            @ColorInt Integer titleColor,
            String title,
            String message) {
        MessageDialog dialog = new MessageDialog();
        dialog.titleColor = titleColor;
        dialog.title = title;
        dialog.message = message;
        dialog.show(fm, MessageDialog.class.getSimpleName());
    }
}
