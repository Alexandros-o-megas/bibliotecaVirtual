class BibliotecaApp {
    constructor() {
        this.currentPage = 0;
        this.pageSize = 12;
        this.currentFilters = {};
        this.init();
    }

    init() {
        this.loadLivros();
        this.setupEventListeners();
        this.setupSearch();
    }

    setupEventListeners() {
        // Navegação entre páginas
        document.querySelectorAll('.nav-link').forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                const target = e.target.getAttribute('href');
                this.navigateTo(target);
            });
        });

        // Filtros
        document.querySelectorAll('.filter-select').forEach(select => {
            select.addEventListener('change', (e) => {
                this.currentFilters[e.target.name] = e.target.value;
                this.loadLivros();
            });
        });
    }

    setupSearch() {
        const searchForm = document.getElementById('search-form');
        const searchInput = document.getElementById('search-input');

        searchForm.addEventListener('submit', (e) => {
            e.preventDefault();
            this.pesquisarLivros(searchInput.value);
        });

        // Pesquisa em tempo real
        searchInput.addEventListener('input', (e) => {
            if (e.target.value.length > 2) {
                this.pesquisarLivros(e.target.value);
            } else if (e.target.value.length === 0) {
                this.loadLivros();
            }
        });
    }

    async loadLivros() {
        try {
            const response = await fetch(`/api/livros?page=${this.currentPage}&size=${this.pageSize}`);
            if (!response.ok) throw new Error('Falha ao carregar livros');
            
            const data = await response.json();
            this.renderLivros(data.content);
            this.setupPagination(data);
        } catch (error) {
            console.error('Erro:', error);
            this.showError('Erro ao carregar livros');
        }
    }

    async pesquisarLivros(termo) {
        try {
            const response = await fetch(`/api/livros/pesquisar?termo=${encodeURIComponent(termo)}`);
            if (!response.ok) throw new Error('Falha na pesquisa');
            
            const livros = await response.json();
            this.renderLivros(livros);
        } catch (error) {
            console.error('Erro:', error);
            this.showError('Erro na pesquisa');
        }
    }

    renderLivros(livros) {
        const container = document.getElementById('livros-container');
        
        if (!livros || livros.length === 0) {
            container.innerHTML = `
                <div class="no-results">
                    <span class="material-icons">search_off</span>
                    <h3>Nenhum livro encontrado</h3>
                    <p>Tente ajustar seus filtros ou termos de pesquisa</p>
                </div>
            `;
            return;
        }

        container.innerHTML = livros.map(livro => this.createLivroCard(livro)).join('');
    }

    createLivroCard(livro) {
        const exemplaresDisponiveis = livro.exemplares ? 
            livro.exemplares.filter(e => e.estado === 'DISPONIVEL').length : 0;
        
        const status = exemplaresDisponiveis > 0 ? 'available' : 'borrowed';
        const statusText = exemplaresDisponiveis > 0 ? 'Disponível' : 'Emprestado';

        return `
            <div class="livro-card" data-livro-id="${livro.id_livro}">
                <div class="livro-header">
                    <h3 class="livro-title">${livro.nome}</h3>
                    <span class="status-badge status-${status}">${statusText}</span>
                </div>
                
                <div class="livro-meta">
                    <div class="meta-item">
                        <span class="material-icons">language</span>
                        ${livro.idioma || 'N/A'}
                    </div>
                    <div class="meta-item">
                        <span class="material-icons">category</span>
                        ${livro.classificacao || 'N/A'}
                    </div>
                    <div class="meta-item">
                        <span class="material-icons">calendar_today</span>
                        ${livro.data_publicacao || 'N/A'}
                    </div>
                    <div class="meta-item">
                        <span class="material-icons">library_books</span>
                        ${exemplaresDisponiveis} exemplares disponíveis
                    </div>
                </div>

                <div class="button-group">
                    <button class="btn btn-primary" onclick="app.detalhesLivro(${livro.id_livro})">
                        <span class="material-icons">visibility</span>
                        Ver Detalhes
                    </button>
                    ${exemplaresDisponiveis > 0 ? `
                    <button class="btn btn-secondary" onclick="app.requisitarLivro(${livro.id_livro})">
                        <span class="material-icons">bookmark_add</span>
                        Reservar
                    </button>
                    ` : ''}
                </div>
            </div>
        `;
    }

    async detalhesLivro(id) {
        try {
            const response = await fetch(`/api/livros/${id}`);
            if (!response.ok) throw new Error('Livro não encontrado');
            
            const livro = await response.json();
            this.showLivroModal(livro);
        } catch (error) {
            console.error('Erro:', error);
            this.showError('Erro ao carregar detalhes do livro');
        }
    }

    showLivroModal(livro) {
        const modal = document.createElement('div');
        modal.className = 'modal';
        modal.innerHTML = `
            <div class="modal-content">
                <div class="modal-header">
                    <h2>${livro.nome}</h2>
                    <button class="close-btn" onclick="this.closest('.modal').remove()">
                        <span class="material-icons">close</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="livro-info">
                        <div class="info-section">
                            <h3>Informações Básicas</h3>
                            <p><strong>ISBN:</strong> ${livro.isbn || 'N/A'}</p>
                            <p><strong>Idioma:</strong> ${livro.idioma || 'N/A'}</p>
                            <p><strong>Classificação:</strong> ${livro.classificacao || 'N/A'}</p>
                            <p><strong>Edição:</strong> ${livro.edicao || 'N/A'}</p>
                            <p><strong>Páginas:</strong> ${livro.numero_paginas || 'N/A'}</p>
                        </div>
                        
                        <div class="info-section">
                            <h3>Autores</h3>
                            ${livro.autores ? livro.autores.map(autor => 
                                `<p>${autor.nome}</p>`
                            ).join('') : '<p>N/A</p>'}
                        </div>
                        
                        <div class="info-section">
                            <h3>Categorias</h3>
                            ${livro.categorias ? livro.categorias.map(cat => 
                                `<span class="category-tag">${cat.nome}</span>`
                            ).join('') : '<p>N/A</p>'}
                        </div>
                        
                        <div class="info-section">
                            <h3>Exemplares</h3>
                            ${this.renderExemplares(livro.exemplares)}
                        </div>
                    </div>
                </div>
            </div>
        `;
        
        document.body.appendChild(modal);
    }

    renderExemplares(exemplares) {
        if (!exemplares || exemplares.length === 0) {
            return '<p>Nenhum exemplar disponível</p>';
        }

        return `
            <div class="exemplares-list">
                ${exemplares.map(exemplar => `
                    <div class="exemplar-item">
                        <strong>Código:</strong> ${exemplar.codigo} | 
                        <strong>Tipo:</strong> ${exemplar.tipo} | 
                        <strong>Estado:</strong> ${exemplar.estado} |
                        <strong>Formato:</strong> ${exemplar.formato}
                    </div>
                `).join('')}
            </div>
        `;
    }

    requisitarLivro(id) {
        // Implementar lógica de requisição
        this.showSuccess('Livro reservado com sucesso!');
    }

    setupPagination(data) {
        // Implementar paginação
    }

    showError(message) {
        this.showNotification(message, 'error');
    }

    showSuccess(message) {
        this.showNotification(message, 'success');
    }

    showNotification(message, type) {
        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        notification.innerHTML = `
            <span class="material-icons">${type === 'error' ? 'error' : 'check_circle'}</span>
            ${message}
        `;
        
        document.body.appendChild(notification);
        
        setTimeout(() => {
            notification.remove();
        }, 5000);
    }

    navigateTo(path) {
        window.location.href = path;
    }
}

// Inicializar a aplicação
const app = new BibliotecaApp();