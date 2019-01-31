package com.chesterlsy.mhwassistant.utility;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myandroidtricks.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;

public class MyTricks {

    // popup window close on back key and not close the activity at the same time: popupWindow.setFocusable(true);

    public static void textViewAddDrawableAtEnd(Context context, TextView textView, String text, int drawableId) {
        text = text + "  ";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        Drawable drawable = context.getResources().getDrawable(drawableId);
        int originalWidth = drawable.getIntrinsicWidth();
        int originalHeight = drawable.getIntrinsicHeight();
        double ratio = originalWidth * 1.0 / originalHeight;
        float fontSize = textView.getTextSize();
        float fontHeight = fontSize * 0.8f;
        drawable.setBounds(0, 0, (int) (fontHeight * ratio), (int) fontHeight);
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannableStringBuilder.setSpan(imageSpan, text.length() - 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableStringBuilder);
    }

    public static String getFormatTime2(long startTime, long endTime) {
        Date startDate = new Date(startTime * 1000);
        Date endDate = new Date(endTime * 1000);
        if (isSameDay(startDate, endDate)) { // same day
            return getFormatString(startDate, endDate, "MM月dd日 E HH:mm", "HH:mm");
        } else if (isSameYear(startDate, endDate)) { // same year different day
            return getFormatString(startDate, endDate, "MM月dd日 HH:mm", "MM月dd日 HH:mm");
        } else { // different year
            return getFormatString(startDate, endDate, "YYYY年MM月dd日 HH:mm", "YYYY年MM月dd日 HH:mm");
        }
    }

    @NonNull
    private static String getFormatString(Date startDate, Date endDate, String pattern, String pattern2) {
        SimpleDateFormat sf1 = new SimpleDateFormat(pattern, Locale.CHINA);
        SimpleDateFormat sf2 = new SimpleDateFormat(pattern2, Locale.CHINA);
        String start = sf1.format(startDate);
        String end = sf2.format(endDate);
        return start + "-" + end;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        return (year1 == year2) && (day1 == day2);
    }

    public static boolean isSameYear(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);

        return (year1 == year2);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float sp2px(Context context, float spValue) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public static void videoNoBlackBorder(Context context, ViewGroup container, int originalWidth, int originalHeight, String videoUrl, String coverUrl) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int margin = 15;
        int width = screenWidth - margin * 2;
//        DebugUtil.error("Video screenWidth: " + screenWidth);
//        DebugUtil.error("Video margin: " + marginLayoutParams.leftMargin);
//        DebugUtil.error("Video margin px: " + margin);
        double ratio = (double) originalWidth / originalHeight;
        double height = (double) width / ratio;
//
//        DebugUtil.error("Video ratio: " + ratio);
//        DebugUtil.error("Video itemView width: " + tutorialCardVideoHolder.itemView.getWidth());
//        DebugUtil.error("Video tutorial_card_video_player width: " + width);
//        DebugUtil.error("Video tutorial_card_video_container width: " + tutorialCardVideoHolder.tutorial_card_video_container.getWidth());
//        DebugUtil.error("Video height: " + height);

        if (width != 0 && height != 0) {
            ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = (int) height;
            container.setLayoutParams(layoutParams);
//            container.findViewById(R.id.thumb).setBackground(context.getResources().getDrawable(R.color.white));
//            container.findViewById(R.id.moremom_root_video).setBackground(context.getResources().getDrawable(R.color.white));
        }
//        Glide.with(context)
//                .load(coverUrl)
////                            .apply(videoRequestOptions)
//                .into(container.findViewById(R.id.cover));
//        container.setUp(new JZDataSource(videoUrl), JzvdStd.SCREEN_WINDOW_LIST);
//        container.tinyBackImageView.setVisibility(View.GONE);


        // old
//        tutorialCardVideoHolder.tutorial_card_video_player.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        tutorialCardVideoHolder.tutorial_card_video_player.setVideoImageDisplayType(AbstractJzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
//
//
//        tutorialCardVideoHolder.tutorial_card_video_player.findViewById(R.id.thumb).setBackground(context.getResources().getDrawable(R.color.transparent));
//        tutorialCardVideoHolder.tutorial_card_video_player.findViewById(R.id.moremom_root_video).setBackground(context.getResources().getDrawable(R.color.transparent));
//
//        tutorialCardVideoHolder.tutorial_card_video_player.thumbImageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
//        tutorialCardVideoHolder.tutorial_card_video_player.thumbImageView.setAdjustViewBounds(true);
//        tutorialCardVideoHolder.tutorial_card_video_player.findViewById(R.id.surface_container).setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//        tutorialCardVideoHolder.tutorial_card_video_player.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//
//        tutorialCardVideoHolder.tutorial_card_video_player.setVideoImageDisplayType(AbstractJzvd.VIDEO_IMAGE_DISPLAY_TYPE_ORIGINAL);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            tutorialCardVideoHolder.tutorial_card_video_player.setOutlineProvider(new ViewOutlineProvider() {
//                @Override
//                public void getOutline(View view, Outline outline) {
//                    Rect rect = new Rect();
//                    view.getGlobalVisibleRect(rect);
//                    Rect selfRect = new Rect(0, 0, view.getWidth(), view.getHeight());
//                    outline.setRoundRect(selfRect, DensityUtil.dip2px(context, context.getResources().getDimension(R.dimen.card_view_corner)));
//
//                }
//            });
//            tutorialCardVideoHolder.tutorial_card_video_player.setClipToOutline(true);
//        }

    }

    public void textViewColorSpanText(Context context, TextView textView, String text, int start, int end) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }
}
