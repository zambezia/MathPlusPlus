package com.zambezia.mathplusplus.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


class TextBound
{
    public Rect rectButtonText;
    public float buttonTextSize; 
    public String buttonText;
    public Button button;
}; 

public class TextResizeHelper {

	public void setButtonTextSizeAllButtons(View v, float size) {
		try {
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					// recursively call this method
                    setButtonTextSizeAllButtons(child, size);
				}
			} else if (v instanceof Button) {
				((Button) v).setTextSize(size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getLargestButtonText(View v, TextBound textBoundLargestWidth, TextBound textBoundLargestHeight) {
		try {
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					// recursively call this method
                    getLargestButtonText(child, textBoundLargestWidth, textBoundLargestHeight);
				}
			} else if (v instanceof Button) {

			    Button button = (Button) v;
                float buttonTextSizeCurrent = button.getTextSize();
                String buttonTextCurrent = button.getText().toString();
                Paint paint2 = new Paint();
                Rect rectButtonTextCurrent = new Rect();
                paint2.setTextSize(buttonTextSizeCurrent);
                paint2.getTextBounds(buttonTextCurrent, 0, buttonTextCurrent.length(), rectButtonTextCurrent);
                if(textBoundLargestWidth.rectButtonText.width() < rectButtonTextCurrent.width())
                {
					textBoundLargestWidth.rectButtonText = rectButtonTextCurrent;
					textBoundLargestWidth.buttonTextSize = buttonTextSizeCurrent;
					textBoundLargestWidth.button = button;
					textBoundLargestWidth.buttonText = button.getText().toString();
                }
                if(textBoundLargestHeight.rectButtonText.height() < rectButtonTextCurrent.height())
                {
                    textBoundLargestHeight.rectButtonText = rectButtonTextCurrent;
                    textBoundLargestHeight.buttonTextSize = buttonTextSizeCurrent;
                    textBoundLargestHeight.button = button;
                    textBoundLargestHeight.buttonText = button.getText().toString();
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public float getCorrectedButtonTextSizeSmallest(LinearLayout layout, float percentageOfButtonWidth)
    {
        float buttonTextSizeWidest = 0, buttonTextSizeHighest = 0, buttonTextSizeMin = 0;
        String buttonText = "";
        Paint p = new Paint();
        Rect rectButtonText = new Rect();
        p.setTextSize(buttonTextSizeWidest);
        p.getTextBounds(buttonText, 0, buttonText.length(), rectButtonText);

        TextBound textBoundLargestWidth = new TextBound();
        textBoundLargestWidth.buttonTextSize = 0;
        textBoundLargestWidth.rectButtonText = rectButtonText;
        textBoundLargestWidth.buttonTextSize = buttonTextSizeWidest;
        TextBound textBoundLargestHeight = textBoundLargestWidth;
        getLargestButtonText(layout, textBoundLargestWidth, textBoundLargestHeight);

        float buttonWidth = textBoundLargestWidth.button.getWidth();
        float textWidth = textBoundLargestWidth.rectButtonText.width();
        buttonTextSizeWidest = textBoundLargestWidth.buttonTextSize;
        buttonText = textBoundLargestWidth.buttonText;

        //change textSize till text width is around % of the button width
        if( textWidth <= buttonWidth*percentageOfButtonWidth)
        {
            while(textWidth <= buttonWidth*percentageOfButtonWidth) {
                buttonTextSizeWidest += 1;
                p.setTextSize(buttonTextSizeWidest);
                p.getTextBounds(buttonText, 0, buttonText.length(), rectButtonText);
                textWidth = rectButtonText.width();
            }
        }
        else if( textWidth > buttonWidth*percentageOfButtonWidth)
        {
            while(textWidth >= buttonWidth*percentageOfButtonWidth) {
                buttonTextSizeWidest -= 1;
                p.setTextSize(buttonTextSizeWidest);
                p.getTextBounds(buttonText, 0, buttonText.length(), rectButtonText);
                textWidth = rectButtonText.width();
            }
        }


        float buttonHeight = textBoundLargestHeight.button.getHeight();
        float textHeight = textBoundLargestHeight.rectButtonText.width();
        buttonTextSizeHighest = textBoundLargestWidth.buttonTextSize;
        buttonText = textBoundLargestHeight.buttonText;

        //change textSize till text width is around % of the button width
        if( textHeight <= buttonHeight*percentageOfButtonWidth)
        {
            while(textHeight <= buttonHeight*percentageOfButtonWidth) {
                buttonTextSizeHighest+= 1;
                p.setTextSize(buttonTextSizeHighest);
                p.getTextBounds(buttonText, 0, buttonText.length(), rectButtonText);
                textHeight = rectButtonText.height();
            }
        }
        else if( textHeight > buttonHeight*percentageOfButtonWidth)
        {
            while(textHeight >= buttonHeight*percentageOfButtonWidth) {
                buttonTextSizeHighest -= 1;
                p.setTextSize(buttonTextSizeHighest);
                p.getTextBounds(buttonText, 0, buttonText.length(), rectButtonText);
                textHeight = rectButtonText.height();
            }
        }

       if(buttonTextSizeHighest < buttonTextSizeWidest)
           buttonTextSizeMin  = buttonTextSizeHighest;
       else
           buttonTextSizeMin  = buttonTextSizeWidest;

        return buttonTextSizeMin;
    }
	public float getCorrectedButtonTextSize(LinearLayout layout, float scaledDensity, float percentageOfButtonWidth)
	{

        float buttonTextSizeScaleAdjusted = getCorrectedButtonTextSizeSmallest(layout, percentageOfButtonWidth);
        //pixels to sp
        buttonTextSizeScaleAdjusted = buttonTextSizeScaleAdjusted / scaledDensity;
		return buttonTextSizeScaleAdjusted;
	}

	public void setCorrectedButtonTextSize( float buttonTextSize, LinearLayout layout)
    {
        setButtonTextSizeAllButtons(layout,buttonTextSize);
    }

}