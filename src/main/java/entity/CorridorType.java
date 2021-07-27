package entity;

public enum CorridorType {
    MAIN_CORRIDOR("Maon Corridor"),
    SUB_CORRIDOR("Sub Corridor");

    public String corridorType;
    CorridorType(String corridorType) {
        this.corridorType = corridorType;
    }
}
