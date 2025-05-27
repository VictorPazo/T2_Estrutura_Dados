public class TestePilha {
    public static void main(String[] args) throws InterruptedException {
        Pilha pilha = new Pilha(3);

        pilha.solicitarReimpressao("TrabalhoFinal.pdf", "Ana");
        pilha.solicitarReimpressao("Grafico.xlsx", "Bruno");
        pilha.solicitarReimpressao("CartazEvento.png", "Carla");
        pilha.solicitarReimpressao("Extra.txt", "Davi");

        System.out.println("\nEstado atual da pilha:");
        pilha.exibirPilha();

        System.out.println("\nConsultando documento na pilha:");
        pilha.consultar("Grafico.xlsx");

        System.out.println("\nExecutando reimpressoes...\n");

        while (!pilha.pilhaVazia()) {
            pilha.reimprimir();
            System.out.println("\nPilha atual:");
            pilha.exibirPilha();
        }

        System.out.println("\nTodas as reimpressoes foram realizadas.");
    }
}
