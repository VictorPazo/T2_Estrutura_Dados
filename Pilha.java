import java.time.LocalDateTime;
import java.time.Duration;

public class Pilha {
    private NoPilha topo;
    private int tamanho;
    private final int capacidadeMaxima;

    public Pilha(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public boolean pilhaVazia() {
        return topo == null;
    }

    public boolean pilhaCheia() {
        return tamanho == capacidadeMaxima;
    }

    public void solicitarReimpressao(String nomeArquivo, String nomeUsuario) {
        if (pilhaCheia()) {
            System.out.println("Pilha cheia. Nao e possivel adicionar: " + nomeArquivo);
            return;
        }

        DocumentoPilha doc = new DocumentoPilha(nomeArquivo, nomeUsuario);
        NoPilha novo = new NoPilha(doc);
        novo.setProximo(topo);
        topo = novo;
        tamanho++;

        System.out.println("Solicitacao adicionada: " + doc);
    }

    public void reimprimir() {
        if (pilhaVazia()) {
            System.out.println("Pilha vazia. Nenhum documento para reimpressao.");
            return;
        }

        DocumentoPilha doc = topo.getDocumento();
        LocalDateTime agora = LocalDateTime.now();
        Duration duracao = Duration.between(doc.getHorarioSolicitacao(), agora);
        System.out.println("Reimprimindo: " + doc);
        System.out.println("Tempo de espera: " + duracao.getSeconds() + " segundos");

        topo = topo.getProximo();
        tamanho--;
    }

    public void consultar(String nomeArquivo) {
        NoPilha atual = topo;
        int posicao = 0;

        while (atual != null) {
            if (atual.getDocumento().getNomeArquivo().equals(nomeArquivo)) {
                System.out.println("Documento encontrado:");
                System.out.println("Nome: " + atual.getDocumento().getNomeArquivo());
                System.out.println("Usuario: " + atual.getDocumento().getNomeUsuario());
                System.out.println("Horario de solicitacao: " + atual.getDocumento().getHorarioSolicitacao());
                System.out.println("Posicao (do topo): " + posicao);
                return;
            }
            atual = atual.getProximo();
            posicao++;
        }

        System.out.println("Documento nao encontrado: " + nomeArquivo);
    }

    public void exibirPilha() {
        if (pilhaVazia()) {
            System.out.println("Pilha vazia.");
            return;
        }

        NoPilha atual = topo;
        System.out.println("Documentos na pilha:");
        while (atual != null) {
            System.out.println("-> " + atual.getDocumento());
            atual = atual.getProximo();
        }
    }

    // Classes internas renomeadas
    private class DocumentoPilha {
        private String nomeArquivo;
        private String nomeUsuario;
        private LocalDateTime horarioSolicitacao;

        public DocumentoPilha(String nomeArquivo, String nomeUsuario) {
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

    private class NoPilha {
        private DocumentoPilha documento;
        private NoPilha proximo;

        public NoPilha(DocumentoPilha documento) {
            this.documento = documento;
        }

        public DocumentoPilha getDocumento() {
            return documento;
        }

        public NoPilha getProximo() {
            return proximo;
        }

        public void setProximo(NoPilha proximo) {
            this.proximo = proximo;
        }
    }
}