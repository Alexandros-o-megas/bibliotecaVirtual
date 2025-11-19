document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('livros-container');

    // Faz a chamada à API para buscar os livros (com paginação, pegando a primeira página)
    fetch('/api/livros?page=0&size=10')
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao buscar os dados da API');
            }
            return response.json();
        })
        .then(data => {
            if (!data.content || data.content.length === 0) {
                container.innerHTML = '<p>Nenhum livro encontrado.</p>';
                return;
            }

            // Para cada livro, cria um "card" e o adiciona ao container
            data.content.forEach(livro => {
                const card = document.createElement('div');
                card.className = 'livro-card';
                card.innerHTML = `
                    <h3>${livro.nome}</h3>
                    <p><strong>Idioma:</strong> ${livro.idioma || 'N/A'}</p>
                    <p><strong>Classificação:</strong> ${livro.classificacao || 'N/A'}</p>
                    <p><strong>Publicado em:</strong> ${livro.data_publicacao || 'N/A'}</p>
                `;
                container.appendChild(card);
            });
        })
        .catch(error => {
            console.error('Erro:', error);
            container.innerHTML = '<p>Ocorreu um erro ao carregar os livros.</p>';
        });
});