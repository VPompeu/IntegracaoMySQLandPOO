public enum Urgencia {
    UM(1),
    DOIS(2),
    TRES(3),
    QUATRO(4),
    CINCO(5);

    private int valor;

    Urgencia(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static Urgencia fromValor(int valor) {
        for (Urgencia urgencia : values()) {
            if (urgencia.valor == valor) {
                return urgencia;
            }
        }
        throw new IllegalArgumentException("Valor de urgência inválido: " + valor);
    }
}
