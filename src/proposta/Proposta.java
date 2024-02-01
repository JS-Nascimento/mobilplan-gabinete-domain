package proposta;

import helpers.NumberHelper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import proposta.ambiente.Ambiente;

public class Proposta {

    private UUID id;
    private LocalDate dataProposta;
    private UUID idCliente;
    private String descricao;
    private String detalhamento;
    private int prazoEntrega;
    private LocalDate dataEntrega;
    private List<Ambiente> ambientes;
    private double total;
    private String observacao;

    public Proposta(LocalDate dataProposta,
                    UUID idCliente,
                    String descricao,
                    String detalhamento,
                    int prazoEntrega,
                    List<Ambiente> ambientes,
                    String observacao) {
        this.id = UUID.randomUUID();
        this.dataProposta = dataProposta;
        this.idCliente = idCliente;
        this.descricao = descricao;
        this.detalhamento = detalhamento;
        this.prazoEntrega = prazoEntrega;
        this.dataEntrega = null;
        this.ambientes = ambientes;
        this.observacao = observacao;
        this.calcularTotal();
    }

    private void calcularTotal() {
        this.total = this.ambientes.stream().mapToDouble(Ambiente::total).sum();
    }

    @Override
    public String toString() {

        var sb = new StringBuilder();

        dataEntrega = prazoEntrega > 0 ? dataProposta.plusDays(prazoEntrega) : dataEntrega;

        sb.append("Ambiente : \n");
        sb.append("# ").append(id).append("\n");
        sb.append("Descrição : ").append(descricao).append("\n");
        sb.append("Cliente : ").append(idCliente).append("\n");
        sb.append("Data da Proposta : ").append(dataProposta.format(DateTimeFormatter.ISO_DATE)).append("\n");
        sb.append("Prazo de Entrega : ").append(prazoEntrega).append(" dias\n");
        sb.append("Data de Entrega : ").append(dataEntrega.format(DateTimeFormatter.ISO_DATE)).append("\n");
        sb.append("--------------------------------------------------------------\n");
        sb.append("Ambientes : \n");
        ambientes.forEach(ambiente -> {
            sb.append("# ").append(ambiente.id()).append(" - ").append(ambiente.descricao()).append(" : ")
                    .append(NumberHelper.formatCurrency(ambiente.total())).append("\n");
        });
        sb.append("--------------------------------------------------------------\n");
        sb.append("Total : ").append(NumberHelper.formatCurrency(total)).append("\n");
        sb.append("Observação : ").append(observacao).append("\n");

        return sb.toString();
    }
}
