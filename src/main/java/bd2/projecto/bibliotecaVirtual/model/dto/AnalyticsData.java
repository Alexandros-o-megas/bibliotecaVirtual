package bd2.projecto.bibliotecaVirtual.model.dto;

public class AnalyticsData {
    private String label;
    private Long count;

    public AnalyticsData(String label, Long count) {
        this.label = label;
        this.count = count;
    }

    // Getters e Setters
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Long getCount() { return count; }
    public void setCount(Long count) { this.count = count; }
}
