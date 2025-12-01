/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.enums;
/**
 *
 * @author acer
 */
public enum Size {
    SMALL, MEDIUM, LARGE;

    public static double priceDelta(Size s) {
        return switch (s) {
            case LARGE -> 3000.0;
            case MEDIUM -> 1500.0;
            default -> 0.0;
        };
    }

    public static String label(Size s) {
        return switch (s) {
            case SMALL -> "S";
            case MEDIUM -> "M";
            case LARGE -> "L";
        };
    }
}