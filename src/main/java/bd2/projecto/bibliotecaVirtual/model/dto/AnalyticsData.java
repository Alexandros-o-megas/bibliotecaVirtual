package bd2.projecto.bibliotecaVirtual.model.dto;

public class AnalyticsData {
    private String label;
    private Long count;
    private Integer month; // Para dados mensais

    public AnalyticsData(String label, Long count) {
        this.label = label;
        this.count = count;
    }

    public AnalyticsData(Integer month, Long count) {
        this.month = month;
        this.count = count;
        this.label = getMonthName(month);
    }

    private String getMonthName(Integer month) {
        if (month == null) return "Desconhecido";
        String[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
                          "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return month >= 1 && month <= 12 ? months[month - 1] : "Mês " + month;
    }

    // Getters e Setters
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Long getCount() { return count; }
    public void setCount(Long count) { this.count = count; }
    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }
}