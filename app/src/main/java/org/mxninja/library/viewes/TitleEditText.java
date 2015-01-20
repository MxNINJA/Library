package org.mxninja.library.viewes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.mxninja.library.R;

/**
 * @author Mohammad Al-Najjar
 * @since 1/20/2015
 */
public class TitleEditText extends RelativeLayout {

    private TextView title;
    private EditText text;
    private View separator;
    private View separatorFocused;
    private TextView help;

    private int helpColor;
    private int focusColor;
    private int color;

    private boolean showHelp;
    private boolean hasFocus;

    private Typeface typeface;

    public TitleEditText(Context context) {
        super(context);
        init(context, null);
    }

    public TitleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet){
        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_title_edit_text, this, true);
        typeface = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        title = (TextView) findViewById(R.id.tv_title);
        title.setTypeface(typeface);
        text = (EditText) findViewById(R.id.edt_text);
        text.setTypeface(typeface);
        separator = findViewById(R.id.separator);
        separatorFocused = findViewById(R.id.separator_focusable);
        help = (TextView) findViewById(R.id.tv_help);
        help.setTypeface(typeface);
        focusColor = context.getResources().getColor(R.color.blue500);
        helpColor = context.getResources().getColor(R.color.red500);
        color = context.getResources().getColor(R.color.grey500);
        if(attributeSet == null){
            return;
        }
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.TitleEditText);

        String titleStr = array.getString(R.styleable.TitleEditText_label);
        title.setText(titleStr);
        text.setText(array.getString(R.styleable.TitleEditText_text));
        help.setText(array.getString(R.styleable.TitleEditText_help));
        if(array.hasValue(R.styleable.TitleEditText_focusColor)){
            focusColor = array.getColor(R.styleable.TitleEditText_focusColor, R.color.blue500);
        }
        if(array.hasValue(R.styleable.TitleEditText_helpColor)){
            helpColor = array.getColor(R.styleable.TitleEditText_helpColor, R.color.red500);
        }
        if(array.hasValue(R.styleable.TitleEditText_nonFocusColor)){
            color = array.getColor(R.styleable.TitleEditText_nonFocusColor, R.color.grey500);
        }
        array.recycle();
        help.setTextColor(helpColor);
        separator.setBackgroundColor(color);
        separatorFocused.setBackgroundColor(focusColor);

        text.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TitleEditText.this.hasFocus = hasFocus;
                focus();
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                text.requestFocus();
                return true;
            }
        });
    }

    private void focus(){
        if(hasFocus){
            title.setTextColor(focusColor);
            separatorFocused.setVisibility(VISIBLE);
            separator.setVisibility(INVISIBLE);
            if(showHelp){
                separatorFocused.setBackgroundColor(helpColor);
                title.setTextColor(helpColor);
            } else {
                separatorFocused.setBackgroundColor(focusColor);
                title.setTextColor(focusColor);
            }
        } else {
            title.setTextColor(color);
            separatorFocused.setVisibility(INVISIBLE);
            separator.setVisibility(VISIBLE);
        }
        if(showHelp){
            help.setVisibility(VISIBLE);
        } else {
            help.setVisibility(GONE);
        }
    }

    public void showHelp(boolean value) {
        showHelp = value;
        focus();
    }

    public void addTextChangedListener(TextWatcher watcher) {
        text.addTextChangedListener(watcher);
    }

    public void setHelp(int resid, TextView.BufferType type) {
        help.setText(resid, type);
    }

    public void setHelp(int resid) {
        help.setText(resid);
    }

    public void setHelp(char[] text, int start, int len) {
        help.setText(text, start, len);
    }

    public void setHelp(CharSequence text, TextView.BufferType type) {
        help.setText(text, type);
    }

    public void setHelp(CharSequence text) {
        help.setText(text);
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getHelp() {
        return help.getText();
    }

    public void setTitle(int resid, TextView.BufferType type) {
        title.setText(resid, type);
    }

    public void setTitle(int resid) {
        title.setText(resid);
    }

    public void setTitle(char[] text, int start, int len) {
        title.setText(text, start, len);
    }

    public void setTitle(CharSequence text, TextView.BufferType type) {
        title.setText(text, type);
    }

    public void setTitle(CharSequence text) {
        title.setText(text);
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return title.getText();
    }

    public void setText(int resid, TextView.BufferType type) {
        text.setText(resid, type);
    }

    public void setText(int resid) {
        text.setText(resid);
    }

    public void setText(char[] text, int start, int len) {
        this.text.setText(text, start, len);
    }

    public void setText(CharSequence text) {
        this.text.setText(text);
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getText() {
        return help.getText();
    }
}
