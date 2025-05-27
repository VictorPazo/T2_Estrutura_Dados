import java.time.LocalDateTime;
import java.time.Duration;

public class Fila {
    private NoFila primeiro;
    private NoFila ultimo;
    private int tamanho;
    private final int capacidadeMaxima;

    public Fila(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public boolean filaVazia() {
        return primeiro == null;
    }

    public boolean filaCheia() {
        return tamanho == capacidadeMaxima;
    }

    public void enfileirar(String nomeArquivo, String nomeUsuario) {
        if (filaCheia()) {
            System.out.println("Fila cheia. Nao e possivel adicionar: " + nomeArquivo);
            return;
        }

        DocumentoFila doc = new DocumentoFila(nomeArquivo, nomeUsuario);
        NoFila novo = new NoFila(doc);

        if (filaVazia()) {
            primeiro = novo;
        } else {
            ultimo.setProximo(novo);
        }

        ultimo = novo;
        tamanho++;
        System.out.println("Documento adicionado: " + doc);
    }

    public void desenfileirar() {
        if (filaVazia()) {
            System.out.println("Fila vazia. Nenhum documento para impressao.");
            return;
        }

        DocumentoFila doc = primeiro.getDocumento();
        LocalDateTime agora = LocalDateTime.now();
        Duration duracao = Duration.between(doc.getHorarioSolicitacao(), agora);
        System.out.println("Imprimindo: " + doc);
        System.out.println("Tempo de espera: " + duracao.getSeconds() + " segundos");

        primeiro = primeiro.getProximo();
        tamanho--;

        if (primeiro == null) {
            ultimo = null;
        }
    }

    public void consultar(String nomeArquivo) {
        NoFila atual = primeiro;
        int posicao = 0;

        while (atual != null) {
            if (atual.getDocumento().getNomeArquivo().equals(nomeArquivo)) {
                System.out.println("Documento encontrado:");
                System.out.println("Nome: " + atual.getDocumento().getNomeArquivo());
                System.out.println("Usuario: " + atual.getDocumento().getNomeUsuario());
                System.out.println("Horario de solicitacao: " + atual.getDocumento().getHorarioSolicitacao());
                System.out.println("Posicao na fila: " + posicao);
                return;
            }
            atual = atual.getProximo();
            posicao++;
        }

        System.out.println("Documento nao encontrado: " + nomeArquivo);
    }

    public void exibirFila() {
        if (filaVazia()) {
            System.out.println("Fila vazia.");
            return;
        }

        NoFila atual = primeiro;
        System.out.println("Documentos na fila:");
        while (atual != null) {
            System.out.println("-> " + atual.getDocumento());
            atual = atual.getProximo();
        }
    }

    private class DocumentoFila {
        private String nomeArquivo;
        private String nomeUsuario;
        private LocalDateTime horarioSolicitacao;

        public DocumentoFila(String nomeArquivo, String nomeUsuario) {
            this.nomeArquivo = nomeArquivo;
            this.nomeUsuario = nomeUsuario;
            this.horarioSolicitacao = LocalDateTime.now();
        }

        public String getNomeArquivo() {
            return nomeArquivo;
        }

        public String getNomeUsuario() {
            return nomeUsuario;
        }

        public LocalDateTime getHorarioSolicitacao() {
            return horarioSolicitacao;
        }

        @Override
        public String toString() {
            return nomeArquivo + " | Usuario: " + nomeUsuario + " | Solicitado em: " + horarioSolicitacao;
        }
    }

    private class NoFila {
        private DocumentoFila documento;
        private NoFila proximo;

        public NoFila(DocumentoFila documento) {
            this.documento = documento;
        }

        public DocumentoFila getDocumento() {
            return documento;
        }

        public NoFila getProximo() {
            return proximo;
        }

        public void setProximo(NoFila proximo) {
            this.proximo = proximo;
        }
    }
}
