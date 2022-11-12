package java_chess;

public enum Color {
    
    WHITE("w"),
    BLACK("b");
    
    private final String key;
    private Color(String key) {
        this.key = key;
    }
    @Override
    public String toString() {
        return key;
    }
}
