package com.jin;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private final String name;
    private final String occupation;

    public FormEvent(Object source, String name, String occupation) {
        super(source);
        this.name = name;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

}
