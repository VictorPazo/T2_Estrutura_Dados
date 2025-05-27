public class TesteFila {
    public static void main(String[] args) throws InterruptedException {
        Fila fila = new Fila(3);

        fila.enfileirar("Trabalho1.pdf", "Alice");
        fila.enfileirar("Relatorio.docx", "Bob");
        fila.enfileirar("TeseFinal.ppt", "Carol");

        System.out.println("\nEstado inicial da fila:");
        fila.exibirFila();

        while (!fila.filaVazia()) {
            fila.desenfileirar();
            System.out.println("\nFila apos impressao:");
            fila.exibirFila();
        }
        System.out.println("\nConsultando documento ja impresso:");
        fila.consultar("Relatorio.docx");

        System.out.println("\nFim da simulacao: todos os documentos foram impressos.");
    }
}
