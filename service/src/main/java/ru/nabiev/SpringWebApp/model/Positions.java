package ru.nabiev.SpringWebApp.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV("developer",2.2, false),
    HR("human resources",1.2, false),
    ACC("accountants ",1.2, false),
    TL("team lead",2.6, true),
    PM("project manager", 2.4, true),
    SM("security manager",2.4,true);

    private final String name;
    private final double positionCoefficient;
    private final boolean isManager;

    Positions(String name, double positionCoefficient, boolean isManager) {
        this.name = name;
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}
