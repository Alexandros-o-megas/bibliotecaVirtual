document.addEventListener('DOMContentLoaded', () => {
    // Gráfico 1: Livros por Idioma
    fetch('/api/analytics/livros-por-idioma')
        .then(response => response.json())
        .then(data => {
            Highcharts.chart('grafico-idiomas', {
                chart: {
                    type: 'pie' // Gráfico de Tarte
                },
                title: {
                    text: 'Distribuição de Livros por Idioma'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.y}'
                        }
                    }
                },
                series: [{
                    name: 'Quantidade',
                    colorByPoint: true,
                    data: data.map(item => ({ name: item.label, y: item.count }))
                }]
            });
        });

    // Gráfico 2: Livros por Classificação
    fetch('/api/analytics/livros-por-classificacao')
        .then(response => response.json())
        .then(data => {
            Highcharts.chart('grafico-classificacao', {
                chart: {
                    type: 'column' // Gráfico de Colunas
                },
                title: {
                    text: 'Quantidade de Livros por Classificação'
                },
                xAxis: {
                    categories: data.map(item => item.label),
                    title: {
                        text: 'Classificação'
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Número de Livros'
                    }
                },
                series: [{
                    name: 'Livros',
                    data: data.map(item => item.count),
                    color: '#5e95e1'
                }]
            });
        });
});