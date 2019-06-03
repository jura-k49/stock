package com.dpcsa.stock.data;

import com.dpcsa.compon.base.BaseActivity;
import com.dpcsa.compon.base.BaseComponent;
import com.dpcsa.compon.interfaces_classes.DataFieldGet;
import com.dpcsa.compon.json_simple.Field;
import com.dpcsa.compon.json_simple.ListRecords;
import com.dpcsa.compon.json_simple.Record;
import com.dpcsa.compon.single.ComponPrefTool;
import com.dpcsa.compon.single.ComponTools;
import com.dpcsa.stock.R;
import com.dpcsa.stock.param.SkladDeclareScreens;

public class GetData extends DataFieldGet {
    private BaseActivity activity;
    private ComponPrefTool preferences;
    private ComponTools componTools;
    private String loc;

    @Override
    public Field getField(BaseComponent mComponent) {
        activity = mComponent.activity;
        preferences = mComponent.preferences;
        componTools = mComponent.getComponTools();
        if (mComponent.multiComponent.nameComponent != null) {
            switch (mComponent.multiComponent.nameComponent) {
                case SkladDeclareScreens.MAIN: return setLanguage();
            }
        }
        return null;
    }

    private Field setLanguage() {
        loc = componTools.getLocale();
        ListRecords records = new ListRecords();
        Field field = new Field("", Field.TYPE_LIST_FIELD, records);
        records.add(addLanguage("ru", activity.getString(R.string.ru)));
        records.add(addLanguage("uk", activity.getString(R.string.uk)));
        records.add(addLanguage("en", activity.getString(R.string.en)));
        return field;
    }

    private Record addLanguage(String id, String name) {
        Record rec = new Record();
        rec.add(new Field("name_language", Field.TYPE_STRING, name));
        rec.add(new Field("id_language", Field.TYPE_STRING, id));
        if (loc.equals(id)) {
            rec.add(new Field("select", Field.TYPE_INTEGER, 1));
        } else {
            rec.add(new Field("select", Field.TYPE_INTEGER, 0));
        }
        return rec;
    }
}
