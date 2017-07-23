package by.bsu.tutor.models.enums;


public enum Gender {

    MALE(1, "Мужской"), FEMALE(0, "Женский");

    private int value;
    private String text;

    Gender(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static Gender byValue(int value) {
        for (Gender g : values()) {
            if (g.getValue() == value) {
                return g;
            }
        }
        return null;
    }

}

