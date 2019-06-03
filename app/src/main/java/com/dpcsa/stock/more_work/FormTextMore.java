package com.dpcsa.stock.more_work;

import android.util.Log;

import com.dpcsa.compon.base.BaseComponent;
import com.dpcsa.compon.interfaces_classes.MoreWork;
import com.dpcsa.compon.json_simple.Field;
import com.dpcsa.compon.json_simple.Record;

public class FormTextMore extends MoreWork {

    int maxT = 400;

    @Override
    public void beforeProcessingResponse(Field response, BaseComponent baseComponent) {
        Record rec = (Record) response.value;
        Field ff = rec.getField("description");
        String text = null;
        if (ff != null) {
            text = ((Record) ff.value).getString("text");
        } else {
            text = rec.getString("text");
        }
        String text1, text2 = "";
        if (text != null && text.length() > 0) {
            if (text.length() > maxT) {
                int i = text.indexOf(".", maxT) + 1;
                text1 = insertSeparator(text.substring(0, i));
                text2 = insertSeparator(text.substring(i));
            } else {
                text1 = text;
            }
            rec.add(new Field("text1", Field.TYPE_STRING, text1));
            rec.add(new Field("text2", Field.TYPE_STRING, text2));
        }
    }

    private String insertSeparator(String text2) {
        String stText2 = "";
        int jn = 0;
        int j = text2.indexOf(".");
        while (j > -1) {
            stText2 += text2.substring(jn, j + 1).trim() + System.getProperty("line.separator") + System.getProperty("line.separator");
            jn = j + 1;
            j = text2.indexOf(".", jn);
        }
        if (jn < text2.length()) {
            stText2 += text2.substring(jn, text2.length() - 1);
        }
        return stText2;
    }
}
