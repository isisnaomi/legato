package mx.yellowme.java.legato.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

/**
 * Helper to display {@link android.support.design.widget.Snackbar} and
 * {@link Toast} messages.
 *
 * Created by luisburgos on 6/19/18.
 */

public class MessageHelper {

    /**
     * Provides a definition for the {@link Snackbar} action across the app.
     */
    public interface MessageAction {
        void executeAction();
    }

    /**
     * Displays simple {@link Toast} message.
     *
     * @param context where the message must be shown.
     * @param message to be displayed.
     */
    public static void showToast(
            @NonNull Context context,
            @NonNull String message
    ){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Build and displays full {@link Snackbar} instance with only the message to be
     * displayed but also allows to determine a duration.
     *
     * @param container where the message must be shown.
     * @param message to be displayed.
     * @param length duration of the message.
     *
     * @return the instance fully setup.
     */
    public static Snackbar buildSnackBar(View container, String message, int length) {
        return buildSnackBar(
                container, message, length, null, null
        );
    }

    /**
     * Build and displays full {@link Snackbar} instance with only the message to be displayed.
     *
     * @param container where the message must be shown.
     * @param message to be displayed.
     *
     * @return the instance fully setup.
     */
    public static Snackbar buildSnackBar(View container, String message) {
        return buildSnackBar(
                container, message, Snackbar.LENGTH_LONG, null, null
        );
    }

    /**
     * Build and displays full {@link Snackbar} instance that contains an action for the
     * user to interact with.
     *
     * @param container where the message must be shown.
     * @param message to be displayed.
     * @param length duration of the message.
     * @param actionMessage to be displayed as label of the action button.
     * @param action to be trigger when user interacts with the action button.
     *
     * @return the instance fully setup.
     */
    public static Snackbar buildSnackBar(
            View container,
            String message,
            int length,
            @Nullable String actionMessage,
            @Nullable final MessageAction action
    ) {

        SpannableStringBuilder messageBuilder = new SpannableStringBuilder().append(message);
        messageBuilder.setSpan(
                new ForegroundColorSpan(Color.WHITE),
                0,
                message.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        final Snackbar snackbar = Snackbar.make(container, messageBuilder, length);
        if(actionMessage != null){
            snackbar.setAction(actionMessage, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(action != null) {
                        action.executeAction();
                    }
                    snackbar.dismiss();
                }
            });
        }
        snackbar.show();
        return snackbar;
    }
}