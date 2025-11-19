class AdminDashboard {
    constructor() {
        this.init();
    }

    async init() {
        await this.loadAllCharts();
        this.setupRealTimeUpdates();
    }

    async loadAllCharts() {
        await Promise.all([
            this.loadLivrosPorIdioma(),
            this.loadLivrosPorClassificacao(),
            this.loadLivrosPorCategoria(),
            this.loadExemplaresPorEstado(),
            this.loadExemplaresPorTipo(),
            this.loadStats()
        ]);
    }

    async loadStats() {
        try {
            const [livrosRes, exemplaresRes] = await Promise.all([
                fetch('/api/livros'),
                fetch('/api/analytics/exemplares-por-estado')
            ]);

            const livrosData = await livrosRes.json();
            const exemplaresData = await exemplaresRes.json();

            const totalLivros = livrosData.totalElements || 0;
            const exemplaresDisponiveis = exemplaresData.find(e => e.label === 'DISPONIVEL')?.count || 0;

            document.getElementById('total-livros').textContent = totalLivros.toLocaleString();
            document.getElementById('exemplares-disponiveis').textContent = exemplaresDisponiveis.toLocaleString();
        } catch (error) {
            console.error('Erro ao carregar estatísticas:', error);
        }
    }

    async loadLivrosPorIdioma() {
        try {
            const response = await fetch('/api/analytics/livros-por-idioma');
            const data = await response.json();

            Highcharts.chart('grafico-idiomas', {
                chart: { type: 'pie' },
                title: { text: 'Distribuição por Idioma' },
                tooltip: { pointFormat: '<b>{point.percentage:.1f}%</b>' },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: { enabled: true, format: '<b>{point.name}</b>: {point.y}' }
                    }
                },
                series: [{
                    name: 'Quantidade',
                    colorByPoint: true,
                    data: data.map(item => ({ name: item.label, y: item.count }))
                }]
            });
        } catch (error) {
            console.error('Erro no gráfico de idiomas:', error);
        }
    }

    async loadLivrosPorClassificacao() {
        try {
            const response = await fetch('/api/analytics/livros-por-classificacao');
            const data = await response.json();

            Highcharts.chart('grafico-classificacao', {
                chart: { type: 'column' },
                title: { text: 'Livros por Classificação' },
                xAxis: { categories: data.map(item => item.label) },
                yAxis: { title: { text: 'Número de Livros' } },
                series: [{
                    name: 'Livros',
                    data: data.map(item => item.count),
                    color: '#2563eb'
                }]
            });
        } catch (error) {
            console.error('Erro no gráfico de classificação:', error);
        }
    }

    async loadLivrosPorCategoria() {
        try {
            const response = await fetch('/api/analytics/livros-por-categoria');
            const data = await response.json();

            Highcharts.chart('grafico-categorias', {
                chart: { type: 'bar' },
                title: { text: 'Livros por Categoria' },
                xAxis: { categories: data.map(item => item.label) },
                yAxis: { title: { text: 'Número de Livros' } },
                series: [{
                    name: 'Livros',
                    data: data.map(item => item.count),
                    color: '#10b981'
                }]
            });
        } catch (error) {
            console.error('Erro no gráfico de categorias:', error);
        }
    }

    async loadExemplaresPorEstado() {
        try {
            const response = await fetch('/api/analytics/exemplares-por-estado');
            const data = await response.json();

            Highcharts.chart('grafico-estados', {
                chart: { type: 'pie' },
                title: { text: 'Exemplares por Estado' },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: { enabled: true, format: '<b>{point.name}</b>: {point.y}' }
                    }
                },
                series: [{
                    name: 'Quantidade',
                    colorByPoint: true,
                    data: data.map(item => ({ 
                        name: item.label, 
                        y: item.count,
                        color: this.getEstadoColor(item.label)
                    }))
                }]
            });
        } catch (error) {
            console.error('Erro no gráfico de estados:', error);
        }
    }

    async loadExemplaresPorTipo() {
        try {
            const response = await fetch('/api/analytics/exemplares-por-tipo');
            const data = await response.json();

            Highcharts.chart('grafico-tipos', {
                chart: { type: 'column' },
                title: { text: 'Exemplares por Tipo' },
                xAxis: { categories: data.map(item => item.label) },
                yAxis: { title: { text: 'Número de Exemplares' } },
                series: [{
                    name: 'Exemplares',
                    data: data.map(item => item.count),
                    color: '#f59e0b'
                }]
            });
        } catch (error) {
            console.error('Erro no gráfico de tipos:', error);
        }
    }

    getEstadoColor(estado) {
        const colors = {
            'DISPONIVEL': '#10b981',
            'EMPRESTADO': '#f59e0b',
            'RESERVADO': '#6366f1',
            'PERDIDO': '#ef4444',
            'MANUTENCAO': '#6b7280'
        };
        return colors[estado] || '#94a3b8';
    }

    setupRealTimeUpdates() {
        // Atualizar dados a cada 5 minutos
        setInterval(() => {
            this.loadAllCharts();
        }, 300000);
    }
}

// Inicializar dashboard admin
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('grafico-idiomas')) {
        new AdminDashboard();
    }
});