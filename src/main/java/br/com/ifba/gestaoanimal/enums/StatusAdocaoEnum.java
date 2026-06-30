
package br.com.ifba.gestaoanimal.enums;



public enum StatusAdocaoEnum {
    ABERTA, EM_ANALISE, APROVADA, CONCLUIDA, RECUSADA, CANCELADA, PENDENTE;
    
     @Override    
    public String toString() {
        switch (this) {
            case ABERTA:
                return "Aberta";
            case EM_ANALISE:
                return "Em análise";
            case APROVADA:
                return "Aprovada";
            case CONCLUIDA:
                return "Concluída";
            case RECUSADA:
                return "Recusada";
            case CANCELADA:
                return "Cancelada";
            case PENDENTE:
                    return "Pendente";
            default:
                return name();
        }
    }
}
