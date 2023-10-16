package com.yagita.esh.json;

import java.util.List;

public class ListJsonVocab {
    List<JsonVocab> list;

    public ListJsonVocab(List<JsonVocab> list) {
        this.list = list;
    }

    public List<JsonVocab> getList() {
        return list;
    }

    public void setList(List<JsonVocab> list) {
        this.list = list;
    }
}
