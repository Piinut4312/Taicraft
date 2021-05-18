package com.piinut.taicraft.recipe;

public enum FluidTankMode {
    REPLACE_ALL,
    REPLACE_EMPTY,
    REPLACE_ITEM,
    REMOVE_ALL;

    public static FluidTankMode getMode(String value){
        if(value == null || value.length() < 1){
            return null;
        }
        for(FluidTankMode mode : values()){
            if(mode.name().equalsIgnoreCase(value)){
                return mode;
            }
        }
        return null;
    }

}
